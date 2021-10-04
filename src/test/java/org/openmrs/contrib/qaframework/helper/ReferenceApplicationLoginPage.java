package org.openmrs.contrib.qaframework.helper;

import org.openmrs.contrib.qaframework.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class ReferenceApplicationLoginPage extends LoginPage {

	public ReferenceApplicationLoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void go() {
		goToPage("/appui/header/logout.action?successUrl=openmrs");
	}
}
