Feature: Inpatient Management

  Background:
    Given a user clicks on Find Patient app from Home page
    Then the system loads find patient record page
    When a user searches an existing patient from find patient record page
    And a user starts visit from the patient dashboard page
    Then the system loads patient visit dashboard page

  @selenium
  @inpatient
  Scenario: Inpatient Encounter
    # User story: Admission encounter
    When a user clicks on Admit to Inpatient button and selects location
    Then the system confirms admission encounter is made
 
    # User story: Trasfer encounter
    When a user clicks on Trasfer to Ward service button and selects location
    Then the system confirms trasfer within hospital encounter is made

    # User story: Discharge encounter
    When a user clicks on Exit from Inpatient button and selects location
    Then the system confirms discharge encounter is made
