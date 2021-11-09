Feature: Patient Search

  Background:
    Given the user login to the Registration Desk

  Scenario Outline: Search for a patient
    When the user search for "<patientName>"
    Then the result should be "<result>"
    Examples:
      | patientName | result           |
      | Kevin Jones | Found 1 patient  |
      | Eward       | Found 10 patients  |
      | James Smith Eldoret | Found 3 patients |
      | D2 | Found 1 patient |
      | 107L6E | Found 1 patient |
