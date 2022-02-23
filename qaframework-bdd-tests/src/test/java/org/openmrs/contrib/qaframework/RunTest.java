/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
// defining ordered by name features folder
features = {"src/test/resources/features"},
// defining the definition steps package
glue = "org.openmrs.contrib.qaframework.automation", plugin = {
		"html:target/index.html", "message:target/cucumber.ndjson"}, monochrome = true)
public class RunTest {

	public class HOOK {
		public static final String LOCATION_MANAGEMENT = "@location";
		public static final String LOGIN = "@login";
		public static final String CONDITION = "@condition";
		public static final String SELENIUM = "@selenium";
		public static final String FORM = "@form";
		public static final String PERSON = "@person";
		public static final String ALLERGIES = "@allergies";
		public static final String INITIAL_SETUP = "@initialSetup";
		public static final String SIMPLE_INSTALL = "@simpleInstall";
		public static final String ADVANCED_INSTALL = "@advancedInstall";
		public static final String POSTGRES_INSTALL = "@postgresInstall";
		public static final String TESTING_INSTALL = "@testingInstall";
		public static final String CLINICAL_VISIT = "@clinicalVisit";
		public static final String REGISTRATION = "@registration";
		public static final String USER_ACCOUNT = "@userAccount";
		public static final String INPATIENT = "@inpatient";
		public static final String PATIENT = "@patient";
		public static final String UPGRADE = "@upgrade";
		public static final String REPORT = "@report";
		public static final String PROVIDER = "@provider";
		public static final String VITALS = "@vitals";
		public static final String ROLES_AND_PRIVILEGES = "@rolesAndPrivileges";
		public static final String FIND_PATIENT = "@findPatient";
		public static final String PATIENT_DEMOGRAPHICS = "@patientDemographics";
		public static final String VISIT_TYPE = "@visitType";
		public static final String PATIENT_VISIT = "@patientVisit";
		public static final String SELENIUM_PERSON = SELENIUM + " and " + PERSON;
		public static final String SELENIUM_LOGIN = SELENIUM + " and " + LOGIN;
		public static final String SELENIUM_PROVIDER = SELENIUM + " and " + PROVIDER;
		public static final String SELENIUM_CONDITION = SELENIUM + " and " + CONDITION;
		public static final String SELENIUM_INITIAL_SETUP = SELENIUM + " and " + INITIAL_SETUP;
		public static final String SELENIUM_VITALS = SELENIUM + " and " + VITALS;
		public static final String SELENIUM_CLINICAL_VISIT = SELENIUM + " and " + CLINICAL_VISIT;
		public static final String SELENIUM_ENCOUNTER = SELENIUM + " and " + INPATIENT;
		public static final String SELENIUM_PATIENT = SELENIUM + " and " + PATIENT;
		public static final String SELENIUM_REGISTRATION = SELENIUM + " and " + REGISTRATION;
		public static final String SELENIUM_FIND_PATIENT = SELENIUM + " and " + FIND_PATIENT;
		public static final String SELENIUM_USER_ACCOUNT = SELENIUM + " and " + USER_ACCOUNT;
		public static final String SELENIUM_VISIT_TYPE = SELENIUM + " and " + VISIT_TYPE;
		public static final String SELENIUM_REPORT = SELENIUM + " and " + REPORT;
		public static final String SELENIUM_PATIENT_DEMOGRAPHICS = SELENIUM + " and " + PATIENT_DEMOGRAPHICS;
		public static final String SELENIUM_ROLES_AND_PRIVILEGES = SELENIUM + " and " + ROLES_AND_PRIVILEGES;
		public static final String SELENIUM_FORM = SELENIUM + " and " + FORM;
		public static final String SELENIUM_ALLERGIES = SELENIUM + " and " + ALLERGIES;
<<<<<<< HEAD
		public static final String SELENIUM_LOCATION_MANAGEMENT = SELENIUM + " and " + LOCATION_MANAGEMENT;
=======
		public static final String SELENIUM_PATIENT_VISIT = SELENIUM + " and " + PATIENT_VISIT;
>>>>>>> master
	}
}
