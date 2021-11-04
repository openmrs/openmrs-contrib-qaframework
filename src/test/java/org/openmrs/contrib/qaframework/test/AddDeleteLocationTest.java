/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AddEditLocationPage;
import org.openmrs.contrib.qaframework.page.ManageLocationsPage;

@Ignore
public class AddDeleteLocationTest extends ReferenceApplicationTestBase {
	
	private String locationTagUuid;
	
	@Before
	public void createLocationTag() {
		//locationTagUuid = createTestLocationTag();
	}
	
	@Test
	@Category(BuildTests.class)
	public void addLocationTest() {
		System.out.println(".... Add delete location test.......");
		AddEditLocationPage addEditLocationPage = homePage.goToConfigureMetadata().goToManageLocations().goToAddLocation();
		
		addEditLocationPage.save();
		assertThat(addEditLocationPage.getValidationErrors(), hasItem("This field is required."));
		
		String locationName = "TEST" + TestData.randomSuffix();
		addEditLocationPage.enterName(locationName);
		addEditLocationPage.selectFirstTag();
		ManageLocationsPage manageLocationsPage = addEditLocationPage.save();
		
		assertThat(manageLocationsPage, is(notNullValue()));
		manageLocationsPage.purgeLocation(locationName);
		assertThat(pageContent(), not(containsString(locationName)));
	}
	
	@After
	public void delete() throws Exception {
		RestClient.delete("/locationtag/" + locationTagUuid, true);
	}
}
