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
    And User enters patient identifer
    Then Search Page returns patients
    And User clicks on returned patient
    Then System loads patient dashboard
