package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddEditNewPrivilegePage extends Page{
	protected static final String PAGE_URL = "adminui/metadata/configureMetadata/privileges/managePrivileges.page/privilege.page?action=add&";
	public static final By ENTER_PRIVILEGE_NAME = By.cssSelector("#privilege-field");
	public static final By ENTER_PRIVILEGE_DESCRIPTION = By.cssSelector("#description-field");
	public static final By SAVE_BUTTON = By.cssSelector("#save-button");
	public static final By CANCEL_BUTTON = By.cssSelector("#privilegeForm input.cancel");
	
	public AddEditNewPrivilegePage(Page parent) {
		super(parent);
	}
	
	public AddEditNewPrivilegePage(Page parent, WebElement waitForStaleness) {
		super(parent, waitForStaleness);
	}
	
	public ManagePrivilegesPage enterPrivilegeName(String privilegeName){
		findElement(ENTER_PRIVILEGE_NAME).clear();
		findElement(ENTER_PRIVILEGE_NAME).sendKeys(privilegeName);
		return new ManagePrivilegesPage(this);
	}
	
	public ManagePrivilegesPage enterPrivilegeDescription(String privilegeDescription){
		findElement(ENTER_PRIVILEGE_DESCRIPTION).clear();
		findElement(ENTER_PRIVILEGE_DESCRIPTION).sendKeys(privilegeDescription);
		return new ManagePrivilegesPage(this);
	}
	
	public ManagePrivilegesPage clickSaveButton() {
		clickOn(SAVE_BUTTON);
		return new ManagePrivilegesPage(this);
	}
	
	public void clickCancelButton() {
		clickOn(CANCEL_BUTTON);
	}

	@Override
	public String getPageUrl() {
		return PAGE_URL;
	}
}
