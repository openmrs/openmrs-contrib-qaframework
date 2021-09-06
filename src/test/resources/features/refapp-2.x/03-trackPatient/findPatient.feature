Feature: Patient Search

  Background:
    Given User clicks on Find Patient App

  @selenium
  @findPatient
  Scenario: Searching missing a patient
    And User enters missing patient

  @selenium
  @findPatient
  Scenario: Searching an existing patient
    And User enters John Smith
   #Then Search Page returns patients
    And User clicks on first patient
    Then System loads patient dashboard
