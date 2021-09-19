package org.openmrs.contrib.qaframework.automation;

import org.openmrs.reference.ReferenceApplicationTestBase;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.PatientVisitsDashboardPage;
import org.openmrs.uitestframework.page.InitialSetupPage;
import org.openmrs.uitestframework.page.LoginPage;
import org.openmrs.uitestframework.page.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
	protected HomePage homePage;

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
