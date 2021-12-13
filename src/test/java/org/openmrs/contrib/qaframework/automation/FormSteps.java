package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertNotNull;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.HtmlFormsPage;
import org.openmrs.contrib.qaframework.page.ManageFormsPage;
import org.openmrs.contrib.qaframework.page.ManageHtmlFormsPage;
import org.openmrs.contrib.qaframework.page.SystemAdministrationPage;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FormSteps extends Steps {
	
	private static String name = "newFormTest1";
	
	private static String description = "description of new form";
	
	private static String version = "1.2";
	
	private HomePage homePage;
	
	private ManageFormsPage manageForm;
	
	private ManageHtmlFormsPage manageHtmlFormsPage;
	
	private SystemAdministrationPage systemAdministrationPage;
	
	private AdministrationPage administrationPage;
	
	private HtmlFormsPage htmlFormsPage;
	
	private ClinicianFacingPatientDashboardPage patientDashboardPage;
	
	@Before(RunTest.HOOK.SELENIUM_FORMS)
	public void visitDashboard() {
		initiateWithLogin();
		homePage = new HomePage(loginPage);
		administrationPage = new AdministrationPage(page);
		manageForm = new ManageFormsPage(driver);
		htmlFormsPage = new HtmlFormsPage(page);
		manageHtmlFormsPage = new ManageHtmlFormsPage(page);
		patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
	}
	
	@After(RunTest.HOOK.SELENIUM_FORMS)
	public void tearDown() {
		quit();
	}
	
	@Given("a user click on manage html forms")
	public void clickOnManageHtmlFormsPage() {
		manageHtmlFormsPage = homePage.goToAdministration().clickOnManageHtmlForms();
	}
	
	@When("check the availability of forms")
	public void instantiateFormPresence() throws InterruptedException {
		ManageFormsPage manageForm = new ManageFormsPage(driver);
		if (manageHtmlFormsPage.getElementsIfExisting(By.xpath("//*[contains(text(), '" + name + "')]")).isEmpty()) {
			manageHtmlFormsPage.clickOnNewHtmlForm();
			htmlFormsPage.createNewFormTest(name, description, version);
		}
		
		homePage.go();
		homePage.goToManageForm();
		if (!manageForm.addPresent()) {
			manageForm.delete();
		}
	}
	
	@Then("a user adds a form in the system")
	public void addFormTest() throws Exception {
		instantiateFormPresence();
		ManageFormsPage manageForm = new ManageFormsPage(driver);
		manageForm.add();
		manageForm.addLabel("Eye Report");
		manageForm.addIcon("icon-align-justify");
		manageForm.formIdFromUrl();
		manageForm.save();
		homePage.go();
		assertNotNull("Eye Report", patientDashboardPage.FORM_EXIST);
	}
	
	@And("a user edits a form")
	public void editFormTest() throws Exception {
		addFormTest();
		homePage.goToManageForm();
		ManageFormsPage manageForm = new ManageFormsPage(driver);
		manageForm.waitForPage();
		manageForm.editPath();
		manageForm.addLabel("Eye Test");
		manageForm.save();
		homePage.go();
		assertNotNull("Eye Test", patientDashboardPage.FORM_EXIST);
	}
	
	@Then("a user deletes a form")
	public void deleteFormTest() throws Exception {
		addFormTest();
		homePage.goToManageForm();
		manageForm.waitForPage();
		manageForm.deletePath();
		assertNotNull("Add", manageForm.ADD);
		homePage.go();
	}
}
