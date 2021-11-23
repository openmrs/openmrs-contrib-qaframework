Feature: Patient Search

  Background:
    Given the user login to the Registration Desk

  @patient-search
  Scenario: Search for a patient
    When the user searches for a patient by name
    Then the result should be "Found 1 patient"
