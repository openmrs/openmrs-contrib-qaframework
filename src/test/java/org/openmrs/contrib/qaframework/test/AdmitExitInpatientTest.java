package org.openmrs.contrib.qaframework.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.contrib.qaframework.helper.BuildTests;
import org.openmrs.contrib.qaframework.helper.ReferenceApplicationTestBase;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.FindPatientPage;
import org.openmrs.contrib.qaframework.page.PatientVisitsDashboardPage;

public class AdmitExitInpatientTest extends ReferenceApplicationTestBase {
	
	private static final String INPATIENT_WARD = "Inpatient Ward";
	
	private TestData.PatientInfo testPatient;
	
	@Before
	public void createTestData() throws Exception {
		testPatient = createTestPatient();
	}
	
	@Test
	@Category(BuildTests.class)
	public void admitExitInpatientTest() {
		FindPatientPage findPatientPage = homePage.goToFindPatientRecord();
		findPatientPage.enterPatient(testPatient.identifier);
		findPatientPage.waitForPageToLoad();
		PatientVisitsDashboardPage patientVisitsDashboardPage = findPatientPage.clickOnFirstPatient().startVisit();
		
		patientVisitsDashboardPage = (PatientVisitsDashboardPage) patientVisitsDashboardPage.goToAdmitToInpatient()
		        .confirm(INPATIENT_WARD);
		assertThat(patientVisitsDashboardPage.getEncountersCount(), is(1));
		
		patientVisitsDashboardPage.goToExitFromInpatient().confirm(INPATIENT_WARD);
		assertThat(patientVisitsDashboardPage.getEncountersCount(), is(2));
	}
	
	@After
	public void deleteTestPatient() {
		deletePatient(testPatient);
	}
}
