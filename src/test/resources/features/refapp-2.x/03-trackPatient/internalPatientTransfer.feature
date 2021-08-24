Feature: Internal Patient Transfer Management

  Background:
    Given a user clicks on Admit to Inpatient link from Patient dashboard
    Then the system loads Admission page
    
  @selenium
  @internalTransfer
  Scenario: Admission and Internal Transfer of patient
    When a user clicks on save button after selecting location
    Then the system adds the patient data to admission table 
    And a user clicks on Transfer To Ward Service link from Patient dashboard
    Then the system loads Transfer to Ward Service Page 
    When a user clicks on save button after choosing location
    Then the system saves the patient transfer data
