Feature: Allergies Management

  Background:
    Given a user logs in, searches John and visits his dashboard
    And a user clicks on Allergies link
    Then the system loads Allergies page

  @run
  Scenario: Add No known allergies
    And a user clicks No Known Allergy button
    Then the system add no known allergies into the allergies table
    And close browser
  
  @run
  Scenario: Remove No known allergies
    And a user clicks Remove No Known Allergy icon
    Then the system displays unknown in the allergies table
    And close browser
  
  Scenario: Edit allergies
    And a user clicks Edit Allergy icon
    Then the system displays the edit Allergy page
    When a user clicks save button
    Then the system saves the user adjustments in the allergy table

  Scenario: Delete allergies
    And a user clicks Delete Allergy icon
    Then the system prompts the user to confirm the transaction
    And a user confirms the transaction
    Then the system deletes the allergy
    
  Scenario: List allergies
    And a list of recorded allergies is displayed