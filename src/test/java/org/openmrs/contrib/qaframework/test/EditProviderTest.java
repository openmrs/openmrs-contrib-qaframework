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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ManageProviderPage;
import org.openmrs.contrib.qaframework.page.ProviderPage;

public class EditProviderTest extends ReferenceApplicationTestBase {
	
	private TestData.PersonInfo person;
	private TestData.TestProvider provider;
	private String providerUuid;
	
	@Before
	public void setUp() {
		person = TestData.generateRandomPerson();
		TestData.createPerson(this.person);
		provider = new TestData.TestProvider(person.uuid, person.uuid);
		providerUuid = provider.create();
	}
	
	@Test
	@Category(BuildTests.class)
	public void editProviderTest() {
		AdministrationPage administrationPage = homePage.goToAdministration();
		ManageProviderPage manageProviderPage = administrationPage.clickOnManageProviders();
		manageProviderPage.setProviderNameOrId(person.getName());
		ProviderPage providerPage = manageProviderPage.clickOnProvider(person.getName());
		providerPage.setIdentifier("uitest-" + person.uuid);
		providerPage.clickOnSave();
	}
	
	@After
	public void tearDown() throws InterruptedException {
		RestClient.delete("provider/" + providerUuid, true);
		RestClient.delete("person/" + person.uuid, true);
	}
}