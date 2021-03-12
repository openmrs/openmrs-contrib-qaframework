Feature: Allergies Management

  Background:
    Given a user clicks on Allergies link from Patient dashboard
    Then the system loads Allergies page

  @selenium
  @dashboard
  Scenario: Add No known allergies
    And a user clicks No Known Allergy button
    Then the system add no known allergies into the allergies table

  @selenium
  @dashboard
  Scenario: Remove No known allergies
    And a user clicks Remove No Known Allergy icon
    Then the system displays unknown in the allergies table