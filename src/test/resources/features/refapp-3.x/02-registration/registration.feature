Feature: Search & Registration

  Background:
    Given the user login to the Registration Desk

  Scenario Outline: Search for a patient
    When the user search for "<patientName>"
    Then the result should be "<result>"

    Examples:
      | patientName | result           |
      | Kevin Jones | Found 1 patient  |
      | 100MQ       | No results found |

  Scenario Outline: Register a patient
    When the user clicks on the add patient icon
    And the user enters "<validity>" details for Andria Faiza
    And the user clicks on the create patient button
    Then the patient registration should be "<status>"
    Examples:
      | validity   | status       |
      | right      | successful   |
      | wrong      | unsuccessful |
