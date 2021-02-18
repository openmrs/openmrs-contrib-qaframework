Feature: Patient Search

  Background:
    Given Search user rightly logs in
    And User clicks on Find Patient App

  @selenium
  Scenario: Searching missing a patient
    And User enters missing patient
    Then Search Page returns no patients

  @selenium
  Scenario: Searching an existing patient
    And User enters John
    Then Search Page returns patients
    And User clicks on first patient
    Then System loads patient dashboard