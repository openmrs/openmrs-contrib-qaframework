package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.AdministrationPage;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.HtmlFormsPage;
import org.openmrs.reference.page.ManageFormsPage;
import org.openmrs.reference.page.ManageHtmlFormsPage;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class FormSteps extends Steps {
	private static String name = "newFormTest1";
	private static String description = "description of new form";
	private static String version = "1.2";
	private HomePage homePage;
	private AdministrationPage administrationPage;
	private HeaderPage headerPage;
	private ManageFormsPage manageForm;
	private ManageHtmlFormsPage manageHtmlFormsPage;
	private HtmlFormsPage htmlFormsPage;
	private ClinicianFacingPatientDashboardPage patientDashboardPage;

//	@Before(RunTest.HOOK.SELENIUM)
//	public void setUp() throws Exception {
//		if(homePage != null) {
//		homePage = new HomePage(page);
//		assertPage(homePage.waitForPage());
//		headerPage = new HeaderPage(driver);
//		administrationPage = new AdministrationPage(page);
//		manageForm = new ManageFormsPage(driver);
//		htmlFormsPage = new HtmlFormsPage(page);
//		manageHtmlFormsPage = new ManageHtmlFormsPage(page);
//		patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
//		}
//	}

	@Given("User finds a form in the system")
	public void initiateFormPresence() throws InterruptedException {
		manageHtmlFormsPage = homePage.goToAdministration()
				.clickOnManageHtmlForms();
		if (manageHtmlFormsPage.getElementsIfExisting(
				By.xpath("//*[contains(text(), '" + name + "')]")).isEmpty()) {
			manageHtmlFormsPage.clickOnNewHtmlForm();
			htmlFormsPage.createNewFormTest(name, description, version);
		}
		homePage.go();
		homePage.goToManageForm();
		if (!manageForm.addPresent()) {
			manageForm.delete();
		}
	}

	@And("User adds a new form into the system")
	public void addFormTests() throws InterruptedException {
		initiateFormPresence();
		manageForm.add();
		manageForm.addLabel("Eye Report");
		manageForm.addIcon("icon-align-justify");
		manageForm.formIdFromUrl();
		manageForm.save();
		headerPage.clickOnHomeIcon();
		assertNotNull("Eye Report",ClinicianFacingPatientDashboardPage.FORM_EXIST);
	}

	@And("User edits a new Form from system")
	public void editFormTests() throws InterruptedException {
		addFormTests();
		homePage.goToManageForm();
		manageForm.waitForPage();
		manageForm.editPath();
		manageForm.addLabel("Eye Test");
		manageForm.save();
		headerPage.clickOnHomeIcon();
		assertNotNull("Eye Test",ClinicianFacingPatientDashboardPage.FORM_EXIST);
	}

	@Then("User deletes a form from the system")
	public void deleteFormTests() throws InterruptedException {
		addFormTests();
		homePage.goToManageForm();
		manageForm.waitForPage();
		manageForm.deletePath();
		assertNotNull("Add", manageForm.ADD);
	}

	@After(RunTest.HOOK.SELENIUM)
	public void destroy() {
		quit();
	}
}
