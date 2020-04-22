Feature: PatientSearch

  Background:
    Given Search user rightly logs in
    And User clicks on Find Patient App

  Scenario:
    And User enters missing patient
    Then Search Page returns no patients

  Scenario:
    And User enters John
    Then Search Page returns patients
    And User clicks on first patient
    Then System loads patient dashboard



