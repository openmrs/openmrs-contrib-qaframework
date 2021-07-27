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

	private SystemAdministrationPage systemAdministrationPage;
	private ManageUserAccountPage manageUserAccountPage;

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
		manageUserAccountPage = systemAdministrationPage.goToManageAccounts();
	}

	@Then("the system loads the manage acccount page")
	public void systemloadsManageAccountsPage() {
		assertTrue(textExists("Manage Accounts"));
	}

	@When("a user clicks Add New Account button")
	public void goToAddNewAccountPage() {
		manageUserAccountPage.clickOnAddUser();
	}

	@Then("the system loads the add new account form")
	public void systemLoadsAddNewAccountPage() {
		assertTrue(textExists("Add New Account"));
	}

	@When("a user fills the account form")
	public void fillUserAccountForm() {
		manageUserAccountPage.enterPersonalDetails("Datas", "Clerkerl");
		manageUserAccountPage.selectGender();
		manageUserAccountPage.clickOnAddUserAccount();
		manageUserAccountPage.enterUsername("dclerkerss");
		manageUserAccountPage.setUserPrivilegeLevel("Full");
		manageUserAccountPage.setUserPassword("Dclerk123", "Dclerk123");
		assertFalse(manageUserAccountPage
				.containsText("Atleast 8 character(s) are required"));
		List<String> validationErrors = manageUserAccountPage
				.getValidationErrors();
		assertTrue(manageUserAccountPage
				.isConfirmPasswordMatching(validationErrors));
		manageUserAccountPage.selectRegistersPatients();
		manageUserAccountPage.selectUsesPatientSummary();
		manageUserAccountPage.selectConfiguresForms();
		manageUserAccountPage.selectRecordsAllergies();
		manageUserAccountPage.addProviderDetails();
		manageUserAccountPage.setUserIdentifier("DC0050x");
		manageUserAccountPage.setUserProviderRole("Clerk");
	}

	@And("a user clicks on save account button")
	public void clickOnSaveUserAccount() {
		manageUserAccountPage.saveUserAccount();
	}

	@Then("the system adds user account into the users table")
	public void systemAddsUserAccount() {
		assertTrue(textExists("Account Saved Successfully"));
	}
}
