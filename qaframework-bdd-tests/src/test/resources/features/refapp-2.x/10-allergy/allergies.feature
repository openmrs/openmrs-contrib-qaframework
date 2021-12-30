Feature: Allergies Management

  Background:
    Given a user clicks on Allergies link from Patient dashboard
    Then the system loads Allergies page

  @selenium
  @allergies
  Scenario: Allergies management
  	# User story: Add No known allergies
    And a user clicks No Known Allergy button
    Then the system add no known allergies into the allergies table
    # User story: Remove No known allergies
    And a user clicks Remove No Known Allergy icon
    Then the system displays unknown in the allergies table
    # User story: Add, Edit and Delete known allergies
    When a user adds a known allergy into the system
    Then a user edits a known allergy
    And a user deletes a known allergy
