package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class ManagePrivilegesPage extends Page{
	protected static final String PAGE_URL = "adminui/metadata/configureMetadata/privileges/managePrivileges.page";
	public static final By ADD_NEW_PRIVILEGE = By.cssSelector("#content > input");
	public static final By EDIT_PRIVILEGE = By.cssSelector("i.icon-pencil.edit-action");
	public static final By DELETE_PRIVILEGE = By.cssSelector("i.icon-trash.delete-action.right");
	public static final By SEARCH_CREATED_PRIVILEGE = By.cssSelector("#list-privileges_filter > label > input[type=text]");
	

	public ManagePrivilegesPage(Page configureMetadataPage) {
		super(configureMetadataPage);
	}
	
	public AddEditNewPrivilegePage goToAddNewPrivilege(){
		clickOn(ADD_NEW_PRIVILEGE);
		return new AddEditNewPrivilegePage(this);
	}
	
	public void goToEditPrivilege(){
		clickOn(EDIT_PRIVILEGE);
	}
	
	public void searchForPrivilege(String searchInput){
		setText(SEARCH_CREATED_PRIVILEGE, searchInput);
	}
	
	public void deletePrivilege(){
		clickOn(DELETE_PRIVILEGE);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
	
}
