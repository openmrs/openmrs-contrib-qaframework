package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AddEditUserPage;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.ManageUserPage;

public class AddUserAndAddRolesToUserSteps extends Steps {
	
    private ManageUserPage manageUserPage;
    private AddEditUserPage addEditUserPage;
    private static final String NURSE_PASSWORD = "Nurse321";
    private static final String NURSE_USERNAME = "newNurse" + new Random().nextInt(1024);
    
    private void reLoginAsUser() throws InterruptedException {
        goToLoginPage().login(NURSE_USERNAME, NURSE_PASSWORD);
    }

    private void reLoginAsAdmin() throws InterruptedException {
        goToLoginPage().loginAsAdmin();
    }
    
    private void fillInRoleModules(Map<String, Integer> roleModules) {
//        roleModules.put("roleStrings.Anonymous", 0);
//        roleModules.put("roleStrings.Authenticated", 0);
        roleModules.put("roleStrings.Organizational:SystemAdministrator", 2);
        roleModules.put("roleStrings.SystemDeveloper", 9);
        roleModules.put("roleStrings.Application:AdministersSystem", 1);
        roleModules.put("roleStrings.Organizational:Doctor", 3);
        roleModules.put("roleStrings.Organizational:Nurse", 4);
        roleModules.put("roleStrings.Organizational:RegistrationClerk", 3);
    }
	
    @Before(RunTest.HOOK.SELENIUM_ADD_USER_AND_ADD_ROLES_TO_USER)
    public void visitHomePage() {
        initiateWithLogin();
    }

    @After(RunTest.HOOK.SELENIUM_ADD_USER_AND_ADD_ROLES_TO_USER)
    public void destroy() throws InterruptedException {
        quit();
    }
	
    @Given("a user clicks on system administration app")
    public void userClicksOnSystemAdministrationApp() {
        manageUserPage = homePage.goToAdministration().clickOnManageUsers();
    }
	
    @Then("the system loads manage users page")
    public void systemLoadsManageUsersPage() {
        assertTrue(textExists("User Management"));
    }
	
    @When("a user fills in person details")
    public void userFillsInPersonDetails() {
        manageUserPage.clickOnAddUser().createNewPerson().fillInPersonName("Super", "Nurse", NURSE_USERNAME, NURSE_PASSWORD);
    }
	
    @When("a user clicks the add user link")
    public void userClicksOnTheAddUserLink() {
        addEditUserPage = manageUserPage.clickOnAddUser();
        addEditUserPage.createNewPerson();
    }
	
    @And("a user assigns roles to the created user")
    public void userAssignsRolesToUser() throws InterruptedException {
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
    
    @And("a user checks whether the form can save with empty fields")
    public void checkIfFormCanSaveWithEmptyFields() {
      addEditUserPage.saveUser();
    }
    
    @Then("the system throws validations indicating an empty form cant be saved")
    public void systemThrowsValidationErrors() {
        List<String> validationErrors = addEditUserPage.getValidationErrors();
        assertTrue(validationErrors.contains("You must define at least one name"));
        assertTrue(validationErrors.contains("Cannot be empty or null"));
        assertFalse(addEditUserPage.isDataCorrect(validationErrors));
    }
    
    @And("a user enters the demorgraphic information")
    public void UserEntersDemorgraphicInfo() {
    	addEditUserPage.enterGivenFamily("Super", "Nurse");
    	addEditUserPage.clickOnFemale();
    }
	
    @And("a user enters the login information")
    public void userEntersLoginDetails() {      
        addEditUserPage.enterUsernamePassword("super_nurse", "supernurse", "supernurse123");
        addEditUserPage.enterUsernamePassword("super_nurse", "Nurse123", "Nurse123");
    }
    
    @And("a user saves the addedit user form")
    public void userSavesTheForm() {
        addEditUserPage.saveUser();
        manageUserPage.waitForPage();
        assertTrue(textExists("User Saved"));
    }
	 
    @And("a user logins into the system as the created user")
    public void userLoginsIntoSystemAsUser() {
        homePage = new HomePage(goToLoginPage().login("super_nurse", "Nurse123"));
        homePage.waitForPage();
        assertTrue(textExists("super_nurse"));
    }
	 
    @And("a user logins into the system as an admin")
    public void userLoginsIntoSystemAsAnAdmin() {
        goToLoginPage().loginAsAdmin();
        homePage.goToSystemAdministrationPage().goToAdvancedAdministration().clickOnManageUsers();
    }
	 
    @And("a user deletes the user account")
    public void userDeletesUserAccount() {
        manageUserPage.removeUser("super_nurse");
        manageUserPage.waitForPage();
    }
	 
    @Then("the system confirms the deletion of the user account")
    public void systemConfirmsDelete() {
        assertTrue(manageUserPage.getUserSavedNotification().contains("Successfully deleted user."));
    }	
}
