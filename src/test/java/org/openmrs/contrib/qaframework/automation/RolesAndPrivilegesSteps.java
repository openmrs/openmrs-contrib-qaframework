package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AddEditNewPrivilegePage;
import org.openmrs.contrib.qaframework.page.AddEditNewRolePage;
import org.openmrs.contrib.qaframework.page.ConfigureMetadataPage;
import org.openmrs.contrib.qaframework.page.ManagePrivilegesPage;
import org.openmrs.contrib.qaframework.page.ManageRolesPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RolesAndPrivilegesSteps extends Steps {
	private static final String PRIVILEGE_NAME_TO_BE_CREATED_AND_EDITED = "PrivilegeTest";
	private static final String ROLE_NAME_TO_BE_CREATED_AND_EDITED = "RoleTest";
	private static final String ROLE_DESCRIPTION_TO_BE_CREATED = "for e2e automation test";
	private static final String PRIVILEGE_DESCRIPTION_TO_BE_CREATED = "This privilege is just one developed for the roles&privileges e2e test";
	private ManagePrivilegesPage manageprivilegesPage;
	private ConfigureMetadataPage configuremetadatapage;
	private ManageRolesPage manageRolesPage;
	private AddEditNewPrivilegePage addNewPrivilegePage;
	private AddEditNewRolePage addNewRolePage;
	
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
    
    @When("User clicks on manage privileges link on the configure metadata page")
    public void clickManagePrivilegesLink(){
    	manageprivilegesPage = configuremetadatapage.goToManagePrivileges();
    }
    
    @When ("User clicks on manage roles link on the configure metadata page")
    public void clickManageRolesLink(){
    	manageRolesPage = configuremetadatapage.goToManageRoles();
    }
    
    @And ("User clicks the Add New Privilege button")
    public void clickAddNewPrivilegeButton(){
    	addNewPrivilegePage = manageprivilegesPage.goToAddNewPrivilege();
    }
    
    @And ("User clicks the Add New Role button")
    public void clickAddNewRoleButton(){
    	addNewRolePage = manageRolesPage.goToAddNewROle();
    }
    
    @And ("User fills the new privilege form")
    public void launchAddNewPrivilegePage(){
    	manageprivilegesPage = addNewPrivilegePage.enterPrivilegeName(PRIVILEGE_NAME_TO_BE_CREATED_AND_EDITED);
    	manageprivilegesPage = addNewPrivilegePage.enterPrivilegeDescription(PRIVILEGE_DESCRIPTION_TO_BE_CREATED);
    	manageprivilegesPage = addNewPrivilegePage.clickSaveButton();
    }
    
    @And ("User fills the new role form")
    public void launchAddNewRolePage(){
    	manageRolesPage = addNewRolePage.enterRoleName(ROLE_NAME_TO_BE_CREATED_AND_EDITED);
    	manageRolesPage = addNewRolePage.enterRoleDescription(ROLE_DESCRIPTION_TO_BE_CREATED);
    	manageRolesPage = addNewRolePage.clickOnFullPrivilegeLevelCheckbox();
    	manageRolesPage = addNewRolePage.clickSaveButton();
    }
    
    @And ("User search for the created privilege")
    public void searchPrivilege(){
    	manageprivilegesPage.searchForPrivilege(PRIVILEGE_NAME_TO_BE_CREATED_AND_EDITED);
    }
    
    @And ("User edits privilege")
    public void editPrivilege(){
    	manageprivilegesPage.goToEditPrivilege();
    	manageprivilegesPage = addNewPrivilegePage.enterPrivilegeDescription("just testing the editing of a privilege");
    	manageprivilegesPage = addNewPrivilegePage.clickSaveButton();
    }
    
    @And ("User edits Role")
    public void editRole(){
    	manageRolesPage.goToEditRole();
    	manageRolesPage = addNewRolePage.clickOnHighPrivilegeLevelCheckbox();
    	manageRolesPage = addNewRolePage.clickSaveButton();
    }
    
    @And ("User clicks delete privilege")
    public void deletePrivilege(){
    	manageprivilegesPage.deletePrivilege();
    }
    
    @And ("User clicks delete Role")
    public void deleteRole(){
    	manageRolesPage.deleteRole();
    }
}
