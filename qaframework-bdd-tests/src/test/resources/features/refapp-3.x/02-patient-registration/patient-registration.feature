Feature: Patient Registration

  Background:
    Given the user login to the Outpatient Clinic

  Scenario Outline: Register a patient
    When the user clicks on the add patient icon
    And the user enters "<validity>" details for Andria Faiza
    And the user clicks on the create patient button
    Then the patient registration should be "<status>"
    Examples:
      | validity   | status       |
      | correct      | successful   |
      | wrong      | unsuccessful |
