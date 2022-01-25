Feature: Patient Search

  Background:
    Given the user login to the Registration Desk
  @patient-search
  Scenario: Search for a patient
    When the user search for "John Doe"
    Then the result should be "Found 1 patient"