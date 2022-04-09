
/**This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.ManageUserAccountPage;
import org.openmrs.contrib.qaframework.page.SystemAdministrationPage;

public class UserAccountSteps extends Steps {

	private static final String SYSTEM_ALERT = "Atleast 8 character(s) are required";
	private static final String CLERK = RandomStringUtils.randomAlphabetic(8);
	private static final String NURSE = RandomStringUtils.randomAlphabetic(8);
	private static final String DOCTOR = RandomStringUtils.randomAlphabetic(8);
	private SystemAdministrationPage systemAdministrationPage;
	private ManageUserAccountPage userAccountPage;

	@Before(RunTest.HOOK.SELENIUM_USER_ACCOUNT)
	public void visitHomePage() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_USER_ACCOUNT)
	public void destroy() {
		quit();
	}

	@Given("a user clicks on system administartion app from home page")
	public void goToSystemAdministrationPage() {
		systemAdministrationPage = homePage.goToSystemAdministrationPage();
	}

	@Then("the system loads system administration page")
	public void systemLoadsSystemAdministrationPage() {
		assertTrue(textExists("System Administration"));
	}

	@When("a user clicks on manage accounts app")
	public void goToManageAccountsPage() {
		userAccountPage = systemAdministrationPage.goToManageAccounts();
	}

	@Then("the system loads manage acccount page")
	public void systemLoadsManageAccountsPage() {
		assertTrue(textExists("Manage Accounts"));
	}

	@And("a user clicks add new account button")
	public void goToAddNewAccountPage() {
		userAccountPage.clickOnAddUser();
	}

	@Then("the system loads add new account form")
	public void systemLoadsAddNewAccountPage() {
		assertTrue(textExists("Add New Account"));
	}

	@And("a user enters data clerk details in the user account form")
	public void enterDataClerkDetails() {
		userAccountPage.enterPersonalDetails("Clerk", "Data");
		userAccountPage.selectGender();
		userAccountPage.clickOnAddUserAccount();
		userAccountPage.setUsername(CLERK);
		userAccountPage.setUserPrivilegeLevel("High");
		userAccountPage.setUserPassword("Dataclerk123", "Dataclerk123");
		assertFalse(userAccountPage.containsText(SYSTEM_ALERT));
		List<String> validationErrors = userAccountPage.getValidationErrors();
		assertTrue(userAccountPage.isConfirmPasswordMatching(validationErrors));
		userAccountPage.selectConfiguresForms();
		userAccountPage.selectRecordsAllergies();
		userAccountPage.selectRegistersPatients();
		userAccountPage.selectUsesPatientSummary();
		userAccountPage.addProviderDetails();
		userAccountPage.setUserIdentifier("C501");
		userAccountPage.setUserProviderRole("Clerk");
	}

	@And("a user enters nurse details in the user account form")
	public void enterNurseDetails() {
		userAccountPage.enterPersonalDetails("Nurse", "Senior");
		userAccountPage.selectGender();
		userAccountPage.clickOnAddUserAccount();
		userAccountPage.setUsername(NURSE);
		userAccountPage.setUserPrivilegeLevel("High");
		userAccountPage.setUserPassword("Seniornurse123", "Seniornurse123");
		assertFalse(userAccountPage.containsText(SYSTEM_ALERT));
		List<String> validationErrors = userAccountPage.getValidationErrors();
		assertTrue(userAccountPage.isConfirmPasswordMatching(validationErrors));
		userAccountPage.selectAdministersSystem();
		userAccountPage.selectEntersVitals();
		userAccountPage.selectRegistersPatients();
		userAccountPage.selectUsesPatientSummary();
		userAccountPage.addProviderDetails();
		userAccountPage.setUserIdentifier("N801");
		userAccountPage.setUserProviderRole("Nurse");
	}

	@And("a user enters doctor details in the user account form")
	public void enterDoctorDetails() {
		userAccountPage.enterPersonalDetails("Doctor", "Senior");
		userAccountPage.selectGender();
		userAccountPage.clickOnAddUserAccount();
		userAccountPage.setUsername(DOCTOR);
		userAccountPage.setUserPrivilegeLevel("Full");
		userAccountPage.setUserPassword("Seniordoctor123", "Seniordoctor123");
		assertFalse(userAccountPage.containsText(SYSTEM_ALERT));
		List<String> validationErrors = userAccountPage.getValidationErrors();
		assertTrue(userAccountPage.isConfirmPasswordMatching(validationErrors));
		userAccountPage.selectAdministersSystem();
		userAccountPage.selectConfiguresForms();
		userAccountPage.selectConfiguresMetadata();
		userAccountPage.selectSchedulesAndOverbooksAppointments();
		userAccountPage.selectHasSuperUserPrivileges();
		userAccountPage.addProviderDetails();
		userAccountPage.setUserIdentifier("D050");
		userAccountPage.setUserProviderRole("Clinical Doctor");
	}

	@And("a user clicks on save account button")
	public void clickOnSaveUserAccountButton() {
		userAccountPage.saveUserAccount();
	}

	@Then("the system adds user account into the users table")
	public void systemAddsUserAccount() {
		assertTrue(textExists("Account Saved Successfully"));
	}

	@Then("the system confirms password meets the password criteria")
	public void systemConfirmsPasswordMeetsPasswordCriteria() {
		assertFalse(textExists("Validation errors found Failed to save account details"));
	}

	@And("a user enters person details in the user account form") 
	public void enterPersonalDetails() {
		userAccountPage.enterPersonalDetails("Clerk", "Data");
		userAccountPage.selectGender();
	}

	@And("a user enters user account details in the user account form")
	public void enterUserAccountDetails() {
		userAccountPage.clickOnAddUserAccount();
		userAccountPage.setUsername(CLERK);
		userAccountPage.setUserPrivilegeLevel("Full");
	}

	@And("a user sets the password with only upper case letters")
	public void setPasswordWithOnlyUpperCaseLetters() {
		userAccountPage.setUserPassword("DATACLERK123", "DATACLERK123");
	}

	@And("a user sets the password with only digits")
	public void setPasswordsWithOnlyDigits() {
		userAccountPage.setUserPassword("123459876", "123459876");
	}

	@And("a user sets the password with only letters")
	public void setPasswordsWithOnlyLetters() {
		userAccountPage.setUserPassword("dataclerk", "dataclerk");
	}

	@And("a user sets the password which is lower than 8 characters")
	public void setPasswordBelowTheMinimumLength() {
		userAccountPage.setUserPassword("123Abc", "123Abc");
	}

	@And("a user enters password that meets the password criteria")
        public void userEnterPasswordThatMeetsThePasswordCriteria() {
                userAccountPage.setUserPassword("Dataclerk123", "Dataclerk123");
        }

	@Then("the system throws validation error on the password input field")
	public void systemThrowsValidationErrorOnPasswordInputField() {
		assertFalse(userAccountPage.containsText(SYSTEM_ALERT));
	}

	@Then("the system throws a validation error message")
        public void systemThrowsAvalidationError() {
		assertTrue(userAccountPage.getErrorMessage().contains("Failed to save account details"));
        }
}
