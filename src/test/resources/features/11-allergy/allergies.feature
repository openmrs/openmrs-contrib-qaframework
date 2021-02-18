Feature: Allergies Management

  Background:
    Given a user logs in, searches John and visits his dashboard
    And a user clicks on Allergies link
    Then the system loads Allergies page

  @selenium
  Scenario: Add No known allergies
    And a user clicks No Known Allergy button
    Then the system add no known allergies into the allergies table
    And close browser

  @selenium
  Scenario: Remove No known allergies
    And a user clicks Remove No Known Allergy icon
    Then the system displays unknown in the allergies table
    And close browser