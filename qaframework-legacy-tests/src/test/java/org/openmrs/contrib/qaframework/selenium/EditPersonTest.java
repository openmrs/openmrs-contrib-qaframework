/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.selenium;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ManagePersonPage;
import org.openmrs.contrib.qaframework.page.PersonFormPage;

public class EditPersonTest extends ReferenceApplicationTestBase {

    private String personUuid;
    private TestData.PersonInfo personInfo;

    @Before
    public void setup() throws Exception {
        personInfo = TestData.generateRandomPerson();
        personUuid = TestData.createPerson(personInfo);
    }

    @Test
    @Category(BuildTests.class)
    public void editPersonTest() {
        AdministrationPage administrationPage = homePage.goToAdministration();
        ManagePersonPage managePersonPage = administrationPage.clickOnManagePersons();
        managePersonPage.setPersonName(personInfo.givenName);
        PersonFormPage personFormPage = managePersonPage.clickFirstFoundPerson();
        personFormPage.setFamilyNameField("newFamilyName");
        personFormPage.savePerson();
        assertThat(personFormPage.getFamilyName(), is("newFamilyName"));
    }

    @After
    public void teardown() throws Exception {
        RestClient.delete("person/" + personUuid, true);
    }
}
