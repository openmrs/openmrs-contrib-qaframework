/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AddEditNewPrivilegePage;
import org.openmrs.contrib.qaframework.page.AddNewRolePage;
import org.openmrs.contrib.qaframework.page.AdministrationManageRolesPage;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ConfigureMetadataPage;
import org.openmrs.contrib.qaframework.page.ManagePrivilegesPage;
import org.openmrs.contrib.qaframework.page.SystemAdministrationPage;

public class RolesAndPrivilegesSteps extends Steps {

	private static final String ROLE_DESCRIPTION = "for e2e automation test";
	private static final String PRIVILEGE_DESCRIPTION = "This privilege is just one developed for the roles&privileges e2e test";
	private ManagePrivilegesPage manageprivilegesPage;
	private ConfigureMetadataPage configuremetadatapage;
	private AddEditNewPrivilegePage addNewPrivilegePage;
	private AddNewRolePage addNewRolePage;
	private SystemAdministrationPage systemAdministrationPage;
	private AdministrationPage administrationPage;
	private AdministrationManageRolesPage administrationManageRolesPage;
	String privilegeName = TestData.createRandomName();
	String roleName = TestData.createRandomName();
	
	@Before(RunTest.HOOK.SELENIUM_ROLES_AND_PRIVILEGES)
	public void visitDashboard(){
		initiateWithLogin();
	}
	
    @After(RunTest.HOOK.SELENIUM_ROLES_AND_PRIVILEGES)
    public void destroy() {
        quit();
    }
    
    @Given("User clicks on configure metadata link from home page")
    public void launchHomeconfigureMetaData(){
    	configuremetadatapage = homePage.goToConfigureMetadata();
    }
    
    @Given ("User clicks on System Administration Link from home page")
    public void launchSystemAdministration(){
    	systemAdministrationPage = homePage.goToSystemAdministrationPage();
    }
    
    @When("User clicks on manage privileges link on the configure metadata page")
    public void clickManagePrivilegesLink(){
    	manageprivilegesPage = configuremetadatapage.goToManagePrivilegesPage();
    }
    
    @When ("User clicks on Advanced Administration link from the System Administration Page")
    public void clickAdvancedAdministrationLink(){
    	administrationPage = systemAdministrationPage.goToAdvancedAdministration();
    }
    
    @And ("User clicks the Add New Privilege button")
    public void clickAddNewPrivilegeButton(){
    	addNewPrivilegePage = manageprivilegesPage.goToAddNewPrivilegePage();
    }
    
    @And ("User fills the new privilege form")
    public void launchAddNewPrivilegePage(){
    	addNewPrivilegePage.enterPrivilegeName(privilegeName);
    	addNewPrivilegePage.enterPrivilegeDescription(PRIVILEGE_DESCRIPTION);
    }
    
    @And ("User clicks the save button")
    public void savePrivilege(){
    	addNewPrivilegePage.clickSaveButton();
    }
    
    @And ("User saves role")
    public void saveRole(){
    	addNewRolePage.saveRole();
    }
    
    @And ("User fills the new role form")
    public void launchAddNewRolePage(){
    	addNewRolePage.addRoleName(roleName);
    	addNewRolePage.addDescription(ROLE_DESCRIPTION);
    	addNewRolePage.selectPrivileges();
    }
    
    @And ("User search for the created privilege")
    public void searchPrivilege(){
    	manageprivilegesPage.searchForPrivilege(privilegeName);
    }
    
    @And ("User edits privilege")
    public void editPrivilege(){
    	manageprivilegesPage.goToEditPrivilegePage();
    	addNewPrivilegePage.enterPrivilegeDescription("just testing the editing of a privilege");
    	addNewPrivilegePage.clickSaveButton();
    }
    
    @And ("User edits the role")
    public void editRole(){
		administrationManageRolesPage.goToEditRolePage(roleName);
	    addNewRolePage.addDescription("Developers of the OpenMRS...edited for the e2e automation test");
	    addNewRolePage.selectPrivileges();
	    addNewRolePage.saveRole();
	}
    
    @And ("User clicks delete privilege")
    public void deletePrivilege(){
    	manageprivilegesPage.deletePrivilege();
    }
    
    @And ("User clicks the Add New Role button on the manage roles page")
    public void clickNewRoleLink(){
    	addNewRolePage = administrationManageRolesPage.goToAddNewRolePage();
    }
    
    @And ("User clicks on manage roles link on the advanced administration page")
    public void clickManageRolesLink(){
    	administrationManageRolesPage = administrationPage.goToManageRolesPage();
    }
    
    @Then ("System confirms delete")
    public void confirmPrivilegeDeletion(){
    	manageprivilegesPage.confirmPrivilegeDelete();
    }
    
    @Then ("User deletes role")
    public void deleteRole(){
    	administrationManageRolesPage.deleteSelectedRoles();
    }
    
    @Then ("Privilege is saved successfully")
    public void privilegeSuccessfullySaved(){
    	assertTrue(textExists("Saved privilege"));
    }
    
    @Then ("Role is saved successfully")
    public void roleSuccessfullySaved(){
    	assertTrue(textExists("Role saved"));
    }
    
    @And ("User selects the role to be deleted")
    public void selectRole(){
    	administrationManageRolesPage.selectRole();
    }
}
