Feature: Admit Patient

Background:
    	Given a user clicks on find patient record from home page
    	When a user selects a patient from active patient list
    	Then the system loads Patient visit dashboard page
    
    @selenium
    @admitPatient
    Scenario: Admit patient
    	When a user clicks on start visit
    	And a user clicks on Admit to Inpatient
    	Then a user saves the patient to Inpatient ward