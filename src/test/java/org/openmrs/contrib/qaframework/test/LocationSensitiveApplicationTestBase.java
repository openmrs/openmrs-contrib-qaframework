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

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;

@Ignore
public class LocationSensitiveApplicationTestBase extends ReferenceApplicationTestBase {
	
	private String locationUuid;
	
	private String locationName;
	
	@Before
	public void createTaggedLocation() {
		String visit = TestData.getLocationTag("Visit Location");
		String login = TestData.getLocationTag("Login Location");
		String transfer = TestData.getLocationTag("Transfer Location");
		String admission = TestData.getLocationTag("Admission Location");
		
		locationName = "Location" + TestData.randomSuffix();
		locationUuid = new TestData.TestLocation(locationName, Arrays.asList(visit, login, transfer, admission)).create();
		
		goToLoginPage().loginAsAdmin(locationName);
	}
	
	@After
	public void deleteTestLocation() {
		if (StringUtils.isNotBlank(locationUuid)) {
			RestClient.delete("location/" + locationUuid);
		}
	}
	
	public String getLocationName() {
		return locationName;
	}
}
