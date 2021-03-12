Feature: Patient Search

  Background:
    Given User clicks on Find Patient App

  @selenium
  @login
  Scenario: Searching missing a patient
    And User enters missing patient
    Then Search Page returns no patients

  @selenium
  @login
  Scenario: Searching an existing patient
    And User enters John Taylor
    Then Search Page returns patients
    And User clicks on first patient
    Then System loads patient dashboard