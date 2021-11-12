Feature: Edit Demographics and contact info from Patient Dashboard

  Background:
    Given a user clicks on Edit Registration Information link from Patient dashboard
    Then the system loads Registration Summary Page

  @selenium
  @editDemographics
  Scenario: Edit Patient Demographics and Contact Info
    # User story: Edit Patient Demographics
    When a user clicks on Edit link from Registration Summary Page
    And the system loads edit demographics section
    And a user edits existing demographics
    And a user clicks on the confirm button
    Then the system saves the updated patient demographics
    
    # User story: Edit Patient Contact Information
    When a user clicks on Edit link under contact info section from Registration Summary Page
    And the system loads the edit contact information section
    And a user edits existing contact information
    And a user clicks on the confirm button
    Then the system saves the updated patient contact information
