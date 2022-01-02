Feature: Allergies Management

  Background:
    Given a user clicks on Allergies link from the Patient dashboard
    Then the system loads the Allergies board page

  @selenium
  @allergies
  Scenario: Allergies management
    # User story: Add No known allergies
    When a user clicks No Known Allergy button
    Then the system adds no known allergies into the allergies table
    # User story: Remove No known allergies
    When a user clicks Remove No Known Allergy icon
    Then the system displays unknown in the allergies table
    # User story: Add known allergy
    When a user clicks Add New Allergy button
    Then the system loads add new allergy page
    And a user selects an allergy
    And a user clicks on the save allergy button
    Then the system adds known allergies into allergies table
    # User story: Edit known allergy
    When a user clicks on the edit Allergy icon
    Then the system loads edit allergy page
    And a user edits an allergy
    And a user clicks on the save allergy button
    Then the system adds edited allergies into the allergies table
    # User story: Delete known allergy
    When a user clicks on the delete Allergy icon
    Then a user confirms the delete action
    And the system deletes an allergy from the allergies table
