package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class PatientFormPage extends Page {

    private static final By ADD_NEW_IDENTIFIER = By.id("identifier");
    private static final By PREFERRED_BUTTON = By.cssSelector("#preferred input[type=\"checkbox\"]");
    private static final By IDENTIFIER_FIELD = By.name("identifiers[0].identifier");
    private static final By IDENIFIER_TYPE_FIELD = By.id("identifierTypeBox2");
    private static final String IDENIFIER_TYPE = "OpenMRS ID";
    private static final By LOCATION_FIELD = By.id("locationBox2");
    private static final String LOCATION = "Amani Hospital";
    private static final By SAVE_PATIENT_BUTTON = By.id("saveButton");

    public PatientFormPage(Page parent) {
        super(parent);
    }

    @Override
    public String getPageUrl() {
        return "/admin/patients/patient.form";
    }
    public void addNewIdentifier() {
        clickOn(ADD_NEW_IDENTIFIER);
    }

    public void selectPreferred() {
        clickOn(PREFERRED_BUTTON);
    }

    public void setIdentifier(String identifier) {
        findElement(IDENTIFIER_FIELD).clear();
        findElement(IDENTIFIER_FIELD).sendKeys(identifier);
    }

    public void setIdentifierType() {
        selectFrom(IDENIFIER_TYPE_FIELD, IDENIFIER_TYPE);
    }

    public void setLocation() {
        selectFrom(LOCATION_FIELD, LOCATION);
    }

    public void savePatient() {
        clickOn(SAVE_PATIENT_BUTTON);
    }
}
