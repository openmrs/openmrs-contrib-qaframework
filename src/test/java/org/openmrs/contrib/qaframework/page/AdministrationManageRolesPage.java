package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class AdministrationManageRolesPage extends Page{
	protected static final String PAGE_URL = "/openmrs/admin/users/role.list";
	private static final By ADD_NEW_ROLE = By.cssSelector("#content > a");
	private static final By DELETE_SELECTED_ROLES = By.cssSelector("#content > form > input[type=submit]");
	private static final By CHECKBOX = By.cssSelector("#content > form > table > tbody input[type=checkbox]");
	private static final By EDIT_ROLE = By.cssSelector("#content > form > table > tbody > tr:nth-child(3) > td:nth-child(2) > a");

	public AdministrationManageRolesPage(Page parent) {
		super(parent);
	}
	
	public AddNewRolePage goToaddNewRole(){
		clickOn(ADD_NEW_ROLE);
		return new AddNewRolePage(this);
	}
	
	public void goToEditRole(){
		clickOn(EDIT_ROLE);
	}
	
	public void deleteSelectedRoles(){
		clickOn(DELETE_SELECTED_ROLES);
	}
	
	public void selectRole(){
		clickOn(CHECKBOX);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
	
}
