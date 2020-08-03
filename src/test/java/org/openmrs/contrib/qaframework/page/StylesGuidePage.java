package org.openmrs.contrib.qaframework.page;

import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.SystemAdministrationPage;
import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;

public class StylesGuidePage extends Page {
    public static By stylesGuideLink = By.className("icon-magic");

    public StylesGuidePage(SystemAdministrationPage systemAdministrationPage) {
        super(systemAdministrationPage);
    }

    @Override
    public String getPageUrl() {
        return "/referenceapplication/home.page";
    }

}
