/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.ActiveVisitsPage;
import org.openmrs.contrib.qaframework.page.VisitNotePage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class VisitNoteSteps extends Steps{

    private static final String DIAGNOSIS_PRIMARY = "Cancer";
    private static final String DIAGNOSIS_SECONDARY = "Malaria";
    private ActiveVisitsPage activeVisitsPage;
    private VisitNotePage visitNotePage;
    private static final String DIAGNOSIS_SECONDARY_UPDATED = "Pneumonia";
    private TestData.PatientInfo testPatient;

    @Before(RunTest.HOOK.SELENIUM_VISIT_NOTE)
    public void visitHomePage() {
        initiateWithLogin();
        testPatient = createTestPatient();
        new TestData.TestVisit(testPatient.uuid, TestData.getAVisitType(), getLocationUuid(homePage)).create();
    }

    @After(RunTest.HOOK.SELENIUM_VISIT_NOTE)
    public void destroy() {
        deletePatient(testPatient);
        quit();
    }

    @Given("a user clicks on an active visits link from home page")
    public void launchPatientDashboardActiveVisits() {
        activeVisitsPage = homePage.goToActiveVisitsSearch();
    }

    @When("a user selects a patient from an active patient list")
    public void searchActivePatient() {
        activeVisitsPage.search(testPatient.identifier);
        dashboardPage = activeVisitsPage.goToPatientDashboardOfLastActiveVisit();
    }

    @Then("the system loads the Patient dashboard page")
    public void launchPatientDashboardPage() {
        assertNotNull(dashboardPage.getActiveVisitList());
    }

    @When("a user clicks visit note link from a patient dashboard")
    public void loadVisitNotePage() {
        visitsDashboardPage = dashboardPage.goToRecentVisits();
        visitNotePage = (VisitNotePage) dashboardPage.goToVisitNote().waitForPage();
    }

    @Then("the system loads the visit note page")
    public void systemLoadsVisitNotePage() {
        assertTrue(textExists("Visit Note"));
    }

    @When("a user fills the visit note form")
    public void fillVisitNoteForm() {
        visitNotePage.selectProviderAndLocation();
        visitNotePage.addDiagnosis(DIAGNOSIS_PRIMARY);
        visitNotePage.addSecondaryDiagnosis(DIAGNOSIS_SECONDARY);
        visitNotePage.addNote("This is a new visit note.");
    }

    @And("a user clicks on save a visit note button")
    public void addVisitNote() {
        visitNotePage.save();
    }

    @Then("the system saves the note into visit note table")
    public void systemAddsVisitNote() {
        assertEquals(DIAGNOSIS_PRIMARY, visitNotePage.primaryDiagnosis());
        assertEquals(DIAGNOSIS_SECONDARY, visitNotePage.secondaryDiagnosis());
    }

    @When("a user clicks on the edit icon of a saved visit note")
    public void userclicksOnEditVisitNote() {
        visitNotePage.editVisitNote();

    }

    @When("a user edits the visit note")
    public void editVisitNote() {
        visitNotePage.removeDiagnosis();
        visitNotePage.clearNote();
        visitNotePage.addNote("This is edited note");
        visitNotePage.addSecondaryDiagnosis(DIAGNOSIS_SECONDARY_UPDATED);
    }

    @Then("the system saves the updated note into visit note table")
    public void systemAddsUpdatedVisitNote() {
        assertEquals(DIAGNOSIS_SECONDARY_UPDATED, visitNotePage.secondaryDiagnosis());
    }

    @When("a user clicks on the delete icon of a saved visit note")
    public void deleteVisitNote(){
        visitNotePage.viewVisitNote();
        visitNotePage.deleteDiagnosis();
        visitNotePage.confirmDeleteDiagnosis();
    }

    @Then("the system deletes the visit note")
    public void systemdeletesVisitNotePage() {
        assertTrue(textExists("deleted"));
    }
}
