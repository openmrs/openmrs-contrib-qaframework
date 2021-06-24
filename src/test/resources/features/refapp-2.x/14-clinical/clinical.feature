Feature: Clinical Management

  Background:
    Given a user clicks on search patient link from home page
    And a user searches and starts the patient visit

  @selenium
  @clinical
  Scenario: Complete visit note
    And a user clicks visit note link from the patient dashboard
    Then the system loads visit note page
    And a user fills the visit note
    And a user clicks on save button
    Then the system adds the note into visit note table