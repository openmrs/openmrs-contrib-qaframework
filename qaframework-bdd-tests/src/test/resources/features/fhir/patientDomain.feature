Feature: Patient Domain

  Background:
    Given the user login to the Registration Desk

  Scenario Outline: Test FHIR Patient Domain
    When GET - single patient domain by "<uuid>"
    Then verify response status code is <code> 
    And patient has "<identifier>" and "<name>"

    Examples:
    | uuid                                  | code | identifier                           | name                                 |
    | 6007b8d8-b00a-4d68-8408-d9e9c303f305  | 200  | e8f561b4-d6af-4ef7-9fa8-f45c6fba4c80 | 4fc37731-4363-482c-aa02-e29c7e3e73d9 |