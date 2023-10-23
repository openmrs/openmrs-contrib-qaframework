Feature: Add New Identifier

  Background:
    Given a user clicks on system administration link on the home page
    Then the administration page is loaded
    And a user clicks on the manage Patients link on the administration page
    Then the system loads patient page

  @selenium
  @addNewIdentifier
  Scenario: Adding New Identifier
    When a user searches for the patient
    And a user clicks on add new identifier
    And a user mentions preferred identifier, identifier type and location
    Then the system adds the new identifier
