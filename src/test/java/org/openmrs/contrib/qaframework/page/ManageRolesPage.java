package org.openmrs.contrib.qaframework.page;

import java.util.List;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageRolesPage extends Page{
	protected static final String PAGE_URL = "adminui/metadata/configureMetadata/roles/manageRoles.page#/list";
	public static final By ADD_NEW_ROLE = By.cssSelector("#manage-roles > ui-view > button");
	public static final By EDIT_ROLE = By.cssSelector("#list-roles td:nth-child(3) a:nth-child(1)");
	public static final By DELETE_ROLE = By.cssSelector("#list-roles td:nth-child(3) a.right");
	
	public ManageRolesPage(Page configureMetadataPage) {
		super(configureMetadataPage);
	}
	
	public AddEditNewRolePage goToAddNewROle(){
		clickOn(ADD_NEW_ROLE);
		return new AddEditNewRolePage(this);
	}
	
	public void goToEditRole(){
		clickOn(EDIT_ROLE);
	}
	
	public void deleteRole(){
		clickOn(DELETE_ROLE);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
	
}

