Feature: Admit To Inpatient

Background:
    	Given a user clicks on find patient record from home page
    	
    @selenium
    @admitToInpatient
    Scenario: Select Existing Patient		
    	When a user selects a patient from the patient list
    	Then the system loads Patient visit dashboard page

    @selenium
    @admitToInpatient
    Scenario: Admit To Inpatient
    	When a user clicks on start visit
    	And a user clicks on start visit
    	Then a user saves the patient to Inpatient ward 
