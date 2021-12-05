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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.VisitTypeListPage;
import org.openmrs.contrib.qaframework.page.VisitTypePage;

public class DeleteVisitTypeTest extends ReferenceApplicationTestBase {

    private String visitTypeName;
    private String visitTypeDesc;
    private String visitTypeUuid;

    @Before
    public void setup() throws Exception {
        visitTypeName = RandomStringUtils.randomAlphanumeric(8);
        visitTypeDesc = RandomStringUtils.randomAlphanumeric(16);
        visitTypeUuid = new TestData.TestVisitType(visitTypeName, visitTypeDesc).create();
    }

    @Test
    @Category(BuildTests.class)
    public void deleteVisitTypeTest() {
        AdministrationPage administrationPage = homePage.goToAdministration();
        VisitTypeListPage visitTypeListPage = administrationPage.goToVisitTypePage();
        VisitTypePage visitTypePage = visitTypeListPage.goToVisitType(visitTypeName);
        visitTypeListPage = visitTypePage.delete();
        assertThat(visitTypeListPage.getVisitTypeList(), not(hasItem(visitTypeName)));
    }

    @After
    public void teardown() throws Exception {
        RestClient.delete("visittype/" + visitTypeUuid, true);
    }
}
