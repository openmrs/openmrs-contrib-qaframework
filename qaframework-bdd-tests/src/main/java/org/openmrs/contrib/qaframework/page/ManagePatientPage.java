package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ManagePatientPage extends Page{

    private static final By SEARCH_ELEMENT = By.id("inputNode");
    private static final By SEARCH_STATUS = By.id("pageInfo");

    public ManagePatientPage(Page parent) {
        super(parent);
    }

    @Override
    public String getPageUrl() {
        return "/admin/patients/index.htm";
    }

    public void searchPatientIdentifierOrPatientName(String text) {
        setText(SEARCH_ELEMENT, text);
        findElement(SEARCH_ELEMENT).sendKeys(Keys.BACK_SPACE);
        waitForTextToBePresentInElement(SEARCH_STATUS,text.substring(0, text.length() - 1));
    }

}
