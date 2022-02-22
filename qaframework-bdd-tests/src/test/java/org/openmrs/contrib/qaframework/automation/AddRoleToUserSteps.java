package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AddEditUserPage;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.ManageUserPage;

public class AddRoleToUserSteps extends Steps {
	
	private ManageUserPage manageUserPage;
	private AddEditUserPage addEditUserPage;
	private AdministrationPage administrationPage;
    private static final String NURSE_PASSWORD = "Nurse321";
    private static final String NURSE_USERNAME = "newNurse" + new Random().nextInt(1024);
    
    private void reLoginAsUser() throws InterruptedException {
        goToLoginPage().login(NURSE_USERNAME, NURSE_PASSWORD);
    }

    private void reLoginAsAdmin() throws InterruptedException {
        goToLoginPage().loginAsAdmin();
    }
    
    private void fillInRoleModules(Map<String, Integer> roleModules) {
        roleModules.put("roleStrings.Anonymous", 0);
        roleModules.put("roleStrings.Authenticated", 0);
        roleModules.put("roleStrings.Organizational:SystemAdministrator", 2);
        roleModules.put("roleStrings.SystemDeveloper", 9);
        roleModules.put("roleStrings.Application:AdministersSystem", 1);
        roleModules.put("roleStrings.Organizational:Doctor", 3);
        roleModules.put("roleStrings.Organizational:Nurse", 4);
        roleModules.put("roleStrings.Organizational:RegistrationClerk", 3);
    }
	
	@Before(RunTest.HOOK.SELENIUM_ADD_ROLE_TO_USER)
	public void visitHomePage() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_ADD_ROLE_TO_USER)
	public void destroy() throws InterruptedException {
		quit();
	}
	
	@Given("user clicks on system administration app")
	public void clickOnSystemAdministrationApp() {
		manageUserPage = homePage.goToAdministration().clickOnManageUsers();
	}
	
	@Then("system loads manage users page")
	public void systemLoadsManageUsersPage() {
		assertTrue(textExists("User Management"));
	}
	
	@When("user fills in person details")
	public void fillInPersonDetails() {
        manageUserPage.clickOnAddUser().createNewPerson().fillInPersonName("Super", "Nurse", NURSE_USERNAME, NURSE_PASSWORD);
	}
	
	@When("user clicks the add user link")
	public void clickOnAddUserLink() {
        addEditUserPage = manageUserPage.clickOnAddUser();
        addEditUserPage.createNewPerson();
	}
	
	@And("user assigns roles to the created user")
	public void assignRolesToUser() throws InterruptedException {
        Map<String, Integer> roleModules = new HashMap<>();
        fillInRoleModules(roleModules);
        String oldRole = null;
        for (Entry<String, Integer> role : roleModules.entrySet()) {
            manageUserPage.assignRolesToUser(oldRole, role.getKey(), NURSE_USERNAME);

            reLoginAsUser();
            if (homePage.numberOfAppsPresent() != role.getValue()) {
                throw new AssertionError("role " + role + " doesn't have matching number of accessible applications: "
                		+ "should be: " + role.getValue() + "is: " + homePage.numberOfAppsPresent());
            }

            reLoginAsAdmin();
            oldRole = role.getKey();
            homePage.goToAdministration().clickOnManageUsers();
        }
	 }
	
	 @Then("the system loads the create new user page")
	 public void systemLoadsCreateNewUserPage() {
		 assertTrue(textExists("Add/Edit User"));
	 }
	
	 @And("user enters the details of the user")
	 public void enterUserDetails() {
		 addEditUserPage.saveUser();
         List<String> validationErrors = addEditUserPage.getValidationErrors();
         assertTrue(validationErrors.contains("You must define at least one name"));
         assertTrue(validationErrors.contains("Cannot be empty or null"));
         assertFalse(addEditUserPage.isDataCorrect(validationErrors));
         addEditUserPage.enterGivenFamily("Super", "Nurse");
         addEditUserPage.saveUser();
         validationErrors = addEditUserPage.getValidationErrors();
         assertFalse(addEditUserPage.isDataCorrect(validationErrors));
         addEditUserPage.clickOnFemale();
         addEditUserPage.enterUsernamePassword("super_nurse", "supernurse", "supernurse123");
         addEditUserPage.saveUser();
         assertFalse(addEditUserPage.isDataCorrect(validationErrors));
         addEditUserPage.enterUsernamePassword("super_nurse", "Nurse123", "Nurse123");
         addEditUserPage.saveUser();
         assertFalse(addEditUserPage.isDataCorrect(validationErrors));
         manageUserPage.waitForPage();
         assertTrue(manageUserPage.getUserSavedNotification().contains("User Saved"));

	 }
	 
	 @And("user logins into the system as the created user")
	 public void loginIntoSystemAsUser() {
         homePage = new HomePage(goToLoginPage().login("super_nurse", "Nurse123"));
         homePage.waitForPage();
         assertTrue(homePage.containsText("super_nurse"));
	 }
	 
	 @And("user logins into the system as an admin")
	 public void loginIntoSystemAsAnAdmin() {
		 administrationPage.clickOnLogOut();
         goToLoginPage().loginAsAdmin();
         homePage.goToAdministration();
         administrationPage.clickOnManageUsers();
	 }
	 
	 @And("user deletes the user")
	 public void deleteUser() {
         manageUserPage.removeUser("super_nurse");
         manageUserPage.waitForPage();
	 }
	 
	 @Then("the system confirms the deletion of the user")
	 public void systemConfirmsDelete() {
		 assertTrue(manageUserPage.getUserSavedNotification().contains("Successfully deleted user."));
	 }
	
	
	
	
	
	
	
	
}
