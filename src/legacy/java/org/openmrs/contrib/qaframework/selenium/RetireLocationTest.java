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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ManageLocationsPage;

public class RetireLocationTest extends ReferenceApplicationTestBase {

    private String locationName = "TEST" + TestData.randomSuffix();
    private String locationUuid = null;

    @Before
    public void createLocation() throws Exception {
        locationUuid = new TestData.TestLocation(locationName).create();
    }

    @Test
    @Category(BuildTests.class)
    public void retireLocationTest() {
        ManageLocationsPage manageLocationsPage = homePage.goToConfigureMetadata().goToManageLocations();
        manageLocationsPage.retireLocation(locationName);
        manageLocationsPage.assertRetired(locationName);
    }

    @After
    public void deleteLocation() throws Exception {
        RestClient.delete("location/" + locationUuid, true);
    }
}
