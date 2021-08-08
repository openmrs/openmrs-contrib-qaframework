package org.openmrs.contrib.qaframework.automation;

import java.util.List;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page.ManageUserAccountPage;
import org.openmrs.reference.page.SystemAdministrationPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ManageUserAccountSteps extends Steps {

	private static final String SYSTEM_ALERT = "Atleast 8 character(s) are required";
	private SystemAdministrationPage systemAdministrationPage;
	private ManageUserAccountPage userAccountPage;

	@Before(RunTest.HOOK.SELENIUM_USER_ACCOUNT)
	public void visitHomePage() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_USER_ACCOUNT)
	public void destroy() {
		quit();
	}

	@Given("a user clicks on System Administartion app from home page")
	public void loadSystemAdministrationPage() {
		systemAdministrationPage = homePage.goToSystemAdministrationPage();
	}

	@Then("the system loads the system administration page")
	public void systemloadsSystemAdministrationPage() {
		assertTrue(textExists("System Administration"));
	}

	@When("a user clicks on Manage accounts app")
	public void loadManageAccountsPage() {
		userAccountPage = systemAdministrationPage.goToManageAccounts();
	}

	@Then("the system loads the manage acccount page")
	public void systemloadsManageAccountsPage() {
		assertTrue(textExists("Manage Accounts"));
	}

	@When("a user clicks Add New Account button")
	public void goToAddNewAccountPage() {
		userAccountPage.clickOnAddUser();
	}

	@Then("the system loads the add new account form")
	public void systemLoadsAddNewAccountPage() {
		assertTrue(textExists("Add New Account"));
	}

	@When("a user fills details of the Data clerk in the user account form")
	public void registerDataClerkDetails() {
		userAccountPage.enterPersonalDetails("Data", "Clerk");
		userAccountPage.selectGender();
		userAccountPage.clickOnAddUserAccount();
		userAccountPage.enterUsername("dclerk");
		userAccountPage.setUserPrivilegeLevel("High");
		userAccountPage.setUserPassword("Clerk123", "Clerk123");
		assertFalse(userAccountPage.containsText(SYSTEM_ALERT));
		List<String> validationErrors = userAccountPage.getValidationErrors();
		assertTrue(userAccountPage.isConfirmPasswordMatching(validationErrors));
		userAccountPage.selectConfiguresForms();
		userAccountPage.selectRecordsAllergies();
		userAccountPage.selectRegistersPatients();
		userAccountPage.selectUsesPatientSummary();
		userAccountPage.addProviderDetails();
		userAccountPage.setUserIdentifier("C010");
		userAccountPage.setUserProviderRole("Clerk");
	}

	@When("a user fills details of the Nurse in the user account form")
	public void registerNurseDetails() {
		userAccountPage.enterPersonalDetails("Super", "Nurse");
		userAccountPage.selectGender();
		userAccountPage.clickOnAddUserAccount();
		userAccountPage.enterUsername("snurse");
		userAccountPage.setUserPrivilegeLevel("High");
		userAccountPage.setUserPassword("Nurse123", "Nurse123");
		assertFalse(userAccountPage.containsText(SYSTEM_ALERT));
		List<String> validationErrors = userAccountPage.getValidationErrors();
		assertTrue(userAccountPage.isConfirmPasswordMatching(validationErrors));
		userAccountPage.selectAdministersSystem();
		userAccountPage.selectEntersVitals();
		userAccountPage.selectRegistersPatients();
		userAccountPage.selectUsesPatientSummary();
		userAccountPage.addProviderDetails();
		userAccountPage.setUserIdentifier("N101");
		userAccountPage.setUserProviderRole("Nurse");
	}

	@When("a user fills details of the Doctor in the user account form")
	public void registerDoctorDetails() {
		userAccountPage.enterPersonalDetails("Super", "Doctor");
		userAccountPage.selectGender();
		userAccountPage.clickOnAddUserAccount();
		userAccountPage.enterUsername("sdoctor");
		userAccountPage.setUserPrivilegeLevel("Full");
		userAccountPage.setUserPassword("Doctor123", "Doctor123");
		assertFalse(userAccountPage.containsText(SYSTEM_ALERT));
		List<String> validationErrors = userAccountPage.getValidationErrors();
		assertTrue(userAccountPage.isConfirmPasswordMatching(validationErrors));
		userAccountPage.selectAdministersSystem();
		userAccountPage.selectConfiguresForms();
		userAccountPage.selectConfiguresMetadata();
		userAccountPage.selectSchedulesAndOverbooksAppointments();
		userAccountPage.selectHasSuperUserPrivileges();
		userAccountPage.addProviderDetails();
		userAccountPage.setUserIdentifier("D050");
		userAccountPage.setUserProviderRole("Clinical Doctor");
	}

	@And("a user clicks on save account button")
	public void clickOnSaveUserAccount() {
		userAccountPage.saveUserAccount();
		userAccountPage.waitForPage();
	}

	@Then("the system adds user account into the users table")
	public void systemAddsUserAccount() {
		assertTrue(textExists("Account Saved Successfully"));
	}
}
