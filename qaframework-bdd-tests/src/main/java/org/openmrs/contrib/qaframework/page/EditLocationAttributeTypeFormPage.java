/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class EditLocationAttributeTypeFormPage extends Page {

    private static final By SAVE_BUTTON = By.linkText("Save");

    public EditLocationAttributeTypeFormPage(Page manageLocationAttributeTypesPage) {
        super(manageLocationAttributeTypesPage);
    }

    public void editLocationAttributeTypeName(String name) {
        findElement(By.cssSelector("#name-field")).clear();
        findElement(By.cssSelector("#name-field")).sendKeys(name);
    }

    public void editLocationAttributeTypeDescription(String description) {
        findElement(By.cssSelector("#description-field")).clear();
        findElement(By.cssSelector("#description-field")).sendKeys(description);
    }

    public void editedMinimumOccurs(String value) {
        setText(By.cssSelector("#minOccurs-field"), value);
    }

    public void editedMaximumOccurs(String value) {
        setText(By.cssSelector("#maxOccurs-field"), value);
    }

    public void saveEditForm() {
        clickOn(SAVE_BUTTON);
    }

    @Override
    public String getPageUrl() {
        return "adminui/metadata/locations/locationAttributeType.page";
    }
}
