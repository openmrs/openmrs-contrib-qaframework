package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ManagePatientPage extends Page{

    private static final By SEARCH_ELEMENT = By.id("inputNode");
    private static final By SEARCH_STATUS = By.id("pageInfo");
    private static final By ADD_NEW_IDENTIFIER = By.id("identifier");
    private static final By PREFERRED_BUTTON = By.name("preferred");
    private static final By IDENTIFIER_FIELD = By.name("identifier");
    private static final By IDENTIFIER_TYPE_FIELD = By.id("identifierTypeBox2");
    private static final String IDENTIFIER = "1007A1";
    private static final String IDENTIFIER_TYPE = "OpenMRS ID";
    private static final By LOCATION_FIELD = By.id("locationBox2");
    private static final String LOCATION = "Amani Hospital";
    private static final By SAVE_PATIENT_BUTTON = By.id("saveButton");
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

    public void clickOnReturnedPatient(String text) {
        setText(SEARCH_ELEMENT, text);
        clickOn(By.cssSelector("#openmrsSearchTable > tbody > tr:nth-child(1)"));
    }
    public void clickOnAddNewIdentifier() {
        waitForElementToBeEnabled(ADD_NEW_IDENTIFIER);
        clickOn(ADD_NEW_IDENTIFIER);
    }

    public void selectPreferred() {
        clickOn(PREFERRED_BUTTON);
    }

    public void setIdentifier(String identifier) {
        findElement(IDENTIFIER_FIELD).clear();
        findElement(IDENTIFIER_FIELD).sendKeys(IDENTIFIER);
    }

    public void setIdentifierType() {
        selectFrom(IDENTIFIER_TYPE_FIELD, IDENTIFIER_TYPE);
    }

    public void setLocation() {
        selectFrom(LOCATION_FIELD, LOCATION);
    }

    public void savePatient() {
        clickOn(SAVE_PATIENT_BUTTON);
    }
}
