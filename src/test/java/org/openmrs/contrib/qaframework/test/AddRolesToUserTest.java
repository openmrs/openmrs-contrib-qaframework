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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.After;
import org.junit.Ignore;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ManageUserPage;

public class AddRolesToUserTest extends ReferenceApplicationTestBase {

	private static final String NURSE_PASSWORD = "Nurse321";

	private static final String NURSE_USERNAME = "newNurse"
			+ new Random().nextInt(1024);

	private void reLoginAsUser() throws InterruptedException {
		goToLoginPage().login(NURSE_USERNAME, NURSE_PASSWORD);
	}

	private void reLoginAsAdmin() throws InterruptedException {
		goToLoginPage().loginAsAdmin();
	}

	@Ignore
	// @Category(org.openmrs.reference.groups.BuildTests.class)
	public void addRolesToUserTest() throws InterruptedException {
		AdministrationPage administrationPage = homePage.goToAdministration();
		ManageUserPage manageUserPage = administrationPage.clickOnManageUsers();

		Map<String, Integer> roleModules = new HashMap();

		fillInRoleModules(roleModules);
		if (!manageUserPage.userExists(NURSE_USERNAME)) {
			manageUserPage
					.clickOnAddUser()
					.createNewPerson()
					.fillInPersonName("Super", "Nurse", NURSE_USERNAME,
							NURSE_PASSWORD);
		}

		String oldRole = null;
		for (Entry<String, Integer> role : roleModules.entrySet()) {
			manageUserPage.assignRolesToUser(oldRole, role.getKey(),
					NURSE_USERNAME);

			reLoginAsUser();
			if (homePage.numberOfAppsPresent() != role.getValue()) {
				throw new AssertionError(
						"role "
								+ role
								+ " doesn't have matching number of accessible applications: should be:"
								+ role.getValue() + "is:"
								+ homePage.numberOfAppsPresent());
			}

			reLoginAsAdmin();
			oldRole = role.getKey();
			homePage.goToAdministration().clickOnManageUsers();
		}
	}

	private void fillInRoleModules(Map<String, Integer> roleModules) {
		roleModules.put("roleStrings.Anonymous", 0);
		roleModules.put("roleStrings.Authenticated", 0);
		roleModules.put("roleStrings.Organizational:SystemAdministrator", 2);
		roleModules.put("roleStrings.SystemDeveloper", 10);
		roleModules.put("roleStrings.Application:AdministersSystem", 1);
		roleModules.put("roleStrings.Organizational:Doctor", 3);
		roleModules.put("roleStrings.Organizational:Nurse", 4);
		roleModules.put("roleStrings.Organizational:RegistrationClerk", 3);
	}

	@After
	public void tearDown() throws Exception {
		reLoginAsAdmin();
		AdministrationPage administrationPage = homePage.goToAdministration();
		ManageUserPage manageUserPage = administrationPage.clickOnManageUsers();
		manageUserPage.removeUser(NURSE_USERNAME);
	}
}
