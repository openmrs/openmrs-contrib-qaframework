Feature: User Settings

  Background:
    Given the user is logged in
    And the user arrives on a patient’s chart

  @patient-involved
  @locale
  Scenario: The user should be able to change the locale
    When the user navigate back to home by clicking Home breadcrumb
    And the user can change the locale to Spanish
    Then the text should change into spanish

  @patient-involved
  Scenario: The user should be able to change the location
    When the user clicks on the users icon
    Then the current location should be there
    When the user change the location to "Pharmacy"
    Then the user should be on the patient’s chart
    And the current location should be "Pharmacy"

  @logout
  Scenario: The user should be able to log out and find themselves back on the login
    When the user clicks on the users icon
    When the user clicks on logout
    Then the user should be on the login page
