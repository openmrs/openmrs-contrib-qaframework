package org.openmrs.contrib.qaframework.page;

import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.SystemAdministrationPage;
import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;

public class StylesGuidePage extends Page {
	public static By STYLES_GUIDE_LINK = By.className("icon-magic");

	public static By STYLES_GUIDE_HEADER = By.id("style-guide-header");

	public StylesGuidePage(SystemAdministrationPage systemAdministrationPage) {
		super(systemAdministrationPage);
	}

	public void pressBack() {
		driver.navigate().back();
	}

	@Override
	public String getPageUrl() {
		return "/referenceapplication/home.page";
	}

}
