package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.HtmlFormsPage;
import org.openmrs.contrib.qaframework.page.ManageFormsPage;
import org.openmrs.contrib.qaframework.page.ManageHtmlFormsPage;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class FormSteps extends Steps {

	private static String name = "newFormTest1";

	private static String description = "description of new form";

	private static String version = "1.2";

	private HomePage homePage;

	private AdministrationPage administrationPage;

	private ManageFormsPage manageForm;

	private ManageHtmlFormsPage manageHtmlFormsPage;

	private HtmlFormsPage htmlFormsPage;

	@Before(RunTest.HOOK.SELENIUM_FORMS)
	public void visitDashboard() {
		initiatePatientDashboard();
	}

	@After(RunTest.HOOK.SELENIUM_FORMS)
	public void destroy() {
		quit();
	}

	@Given("a user click on manage html forms")
	public void launchManageHtmlFormsPage() {
		manageHtmlFormsPage = homePage.goToAdministration()
				.clickOnManageHtmlForms();
	}

	@When("check the availability of forms")
	public void checkAvailabilityOfForms() throws InterruptedException {
		if (manageHtmlFormsPage.getElementsIfExisting(
				By.xpath("//*[contains(text(), '" + name + "')]")).isEmpty()) {
			manageHtmlFormsPage.clickOnNewHtmlForm();
			htmlFormsPage.createNewFormTest(name, description, version);
		}
	}
}
