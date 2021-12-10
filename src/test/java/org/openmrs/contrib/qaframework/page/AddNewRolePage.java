package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class AddNewRolePage extends Page{
	private static final By ENTER_ROLE_NAME = By.cssSelector("#role");
	private static final By SELECT_OR_UNSELECT_ALL_PRIVILEGES = By.id("toggleSelectionCheckbox");
	private static final By ENTER_DESCRIPTION = By.cssSelector("#content > form > table > tbody > tr:nth-child(2) > td > textarea");
	private static final By SAVE = By.cssSelector("#content > form > input[type=submit]");

	public AddNewRolePage(Page parent) {
		super(parent);
	}
	
	public AdministrationManageRolesPage addRoleName(String roleName){
		findElement(ENTER_ROLE_NAME).clear();
		findElement(ENTER_ROLE_NAME).sendKeys(roleName);
		return new AdministrationManageRolesPage(this);
	}
	
	public AdministrationManageRolesPage addDescription(String description){
		findElement(ENTER_DESCRIPTION).clear();
		findElement(ENTER_DESCRIPTION).sendKeys(description);
		return new AdministrationManageRolesPage(this);
	}
	
	public AdministrationManageRolesPage selectPrivileges(){
		clickOn(SELECT_OR_UNSELECT_ALL_PRIVILEGES);
		return new AdministrationManageRolesPage(this);
	}
	
	public AdministrationManageRolesPage saveRole(){
		clickOn(SAVE);
		return new AdministrationManageRolesPage(this);
	}

	@Override
	public String getPageUrl() {
		return null;
	}
	
}
