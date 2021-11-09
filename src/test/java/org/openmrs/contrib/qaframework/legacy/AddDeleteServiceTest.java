/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.legacy;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.page.AppointmentSchedulingPage;
import org.openmrs.contrib.qaframework.page.ManageServiceTypesPage;
import org.openmrs.contrib.qaframework.page.ServicePage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddDeleteServiceTest extends ReferenceApplicationTestBase {

    private String name;
    private String duration;
    private String description;

    @Before
    public void setup() throws Exception {
        name = RandomStringUtils.randomAlphabetic(5);
        duration = RandomStringUtils.randomNumeric(2);
        description = RandomStringUtils.randomAlphabetic(10);
    }

    @Test
    @Category(BuildTests.class)
    public void addDeleteServiceTest() {
        AppointmentSchedulingPage appointmentSchedulingPage = homePage.goToAppointmentScheduling();
        ManageServiceTypesPage manageServiceTypesPage = appointmentSchedulingPage.goToManageServices();
        ServicePage servicePage = manageServiceTypesPage.clickOnNewServiceType();
        servicePage.setName(name);
        servicePage.setDuration(duration);
        servicePage.setDescription(description);
        manageServiceTypesPage = servicePage.save();
        assertThat(manageServiceTypesPage.getServiceType(name), is(true));
        manageServiceTypesPage.deleteServiceType(name);
        manageServiceTypesPage.confirmDelete();
        assertThat(manageServiceTypesPage.getServiceType(name), is(false));
    }
}
