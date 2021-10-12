package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.contrib.qaframework.helper.InitialSetupPage;
import org.openmrs.contrib.qaframework.helper.LoginPage;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.TestProperties;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.PatientVisitsDashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

// Use english locale, of not set, the test instance should be set to english language
public class Steps extends ReferenceApplicationTestBase {

	protected TestProperties testProperties = TestProperties.instance();
	protected LoginPage loginPage;
	protected FindPatientPage findPatientPage;
	protected String firstPatientIdentifier;
	protected ClinicianFacingPatientDashboardPage dashboardPage;
	protected By patientHeaderId = By.cssSelector("div.identifiers span");
	protected InitialSetupPage initialSetupPage;
	protected PatientVisitsDashboardPage visitsDashboardPage;

	public Steps() {
		try {
			startWebDriver();
			loginPage = getLoginPage();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	protected void quit() {
		if (driver != null) {
			driver.quit();
		}
	}

	protected WebElement getElement(By elementBy) {
		try {
			return driver.findElement(elementBy);
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	protected boolean textExists(String text) {
		return driver.findElements(
				By.xpath("//*[contains(text(),'" + text + "')]")).size() > 0;
	}

	protected void initiateWithLogin() {
		goToLoginPage();
		goToLoginPage().login(testProperties.getUsername(),
				testProperties.getPassword(), testProperties.getLocation());
		homePage = (HomePage) new HomePage(loginPage).waitForPage();
	}

	protected void initiatePatientDashboard() {
		initiateWithLogin();
		findPatientPage = (FindPatientPage) homePage.goToFindPatientRecord()
				.waitForPage();
		findPatientPage.enterPatient("John Smith");
		dashboardPage = (ClinicianFacingPatientDashboardPage) findPatientPage
				.clickOnFirstPatient().waitForPage();
	}

	protected String trimPatientId(String id) {
		id = id.replace("Recent", "");
		if (id.indexOf("[") > 0) {
			id = id.split("\\[")[0];
		}
		if (id.indexOf(" ") > 0) {
			id = id.split(" ")[0];
		}
		return id;
	}

	protected void matchPatientIds(String patientId) {
		List<String> ids = new ArrayList<>();
		driver.findElements(patientHeaderId).forEach(id-> {
			ids.add(trimPatientId(id.getText()));
		});
		assertTrue(ids.contains(trimPatientId(patientId)));
	}
}
