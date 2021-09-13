Feature: Patient Search

  Background:
    Given User clicks on Find Patient App

  @selenium
  @findPatient
  Scenario: Searching missing a patient
    And User enters missing patient
    Then Search Page returns no patients

  @selenium
  @findPatient
  Scenario: Searching an existing patient
    And User enters "<patientName>" patientName
    Then Search Page returns patients
    And User clicks on first patient
    Then System loads patient dashboard
     Examples:
      | patientName  | 
      | John Smith   | 

