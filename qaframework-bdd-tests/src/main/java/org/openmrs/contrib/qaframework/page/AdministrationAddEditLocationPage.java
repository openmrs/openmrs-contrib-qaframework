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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdministrationAddEditLocationPage extends Page {

    private static final By NAME_FIELD = By.cssSelector("#content form fieldset table tbody tr:nth-child(1) td input[type=text]");
    private static final By DESCRIPTION_FIELD = By.cssSelector("#content form fieldset table tbody  td textarea");
    private static final By ADDRESS_ONE_FIELD = By.cssSelector("#address1");
    private static final By ADDRESS_TWO_FIELD = By.cssSelector("#address2");
    private static final By STATE_PROVINCE_FIELD = By.cssSelector("#stateProvince");
    private static final By COUNTRY_FIELD = By.cssSelector("#country");
    private static final By POSTAL_CODE_LINK = By.cssSelector("#postalCode");
    private static final By SAVE_LOCATION_BUTTON = By.cssSelector("#content form fieldset input[type=submit]");
    private static final By RETIRE_BUTTON = By.cssSelector("#content fieldset input[type=submit]:nth-child(6)");
    private static final By DELETE_LOCATION_FOREVER_BUTTON = By.cssSelector("#content form td input[type=submit]");

    public AdministrationAddEditLocationPage(Page parent) {
        super(parent);
    }

    public void unRetireLocation() {
        clickOn(By.cssSelector("#content div input[type=submit]"));
    }

    public void enterName(String name) {
        findElement(NAME_FIELD).clear();
        findElement(NAME_FIELD).sendKeys(name);
    }

    public void enterDescription(String description) {
        findElement(DESCRIPTION_FIELD).clear();
        findElement(DESCRIPTION_FIELD).sendKeys(description);
    }

    public void enterAddressOne(String addressOne) {
        findElement(ADDRESS_ONE_FIELD).clear();
        findElement(ADDRESS_ONE_FIELD).sendKeys(addressOne);
    }

    public void enterAddressTwo(String addressTwo) {
        findElement(ADDRESS_TWO_FIELD).clear();
        findElement(ADDRESS_TWO_FIELD).sendKeys(addressTwo);
    }

    public void enterStateOrProvince(String province) {
        findElement(STATE_PROVINCE_FIELD).clear();
        findElement(STATE_PROVINCE_FIELD).sendKeys(province);
    }

    public void enterCountry(String country) {
        findElement(COUNTRY_FIELD).clear();
        findElement(COUNTRY_FIELD).sendKeys(country);
    }

    public void enterPostalCode(String code) {
        findElement(POSTAL_CODE_LINK).clear();
        findElement(POSTAL_CODE_LINK).sendKeys(code);
    }

    public void saveLocation() {
        clickOn(SAVE_LOCATION_BUTTON);
    }

    public void retireLocation(String reason) {
        findElement(By.cssSelector("#content fieldset input[type=text]:nth-child(3)")).clear();
        findElement(By.cssSelector("#content fieldset input[type=text]:nth-child(3)")).sendKeys(reason);
        clickOn(RETIRE_BUTTON);
    }

    public void deleteLocationForever() {
        driver.findElement(DELETE_LOCATION_FOREVER_BUTTON).click();
        waiter.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Override
    public String getPageUrl() {
        return "/openmrs/admin/locations/location.form";
    }
}
