Feature: Visit Note Management

  Background:
    Given a user clicks on an active visits link from home page
    When a user selects a patient from an active patient list
    Then the system loads the Patient dashboard page
    And a user clicks visit note link from a patient dashboard
    Then the system loads the visit note page

  @selenium
  @visitNote
  Scenario: Adding a Visit Note
    When a user fills the visit note form
    And a user clicks on save a visit note button
    Then the system saves the note into visit note table

  @selenium
  @visitNote
  Scenario: Editing a Visit Note
    When a user fills the visit note form
    And a user clicks on save a visit note button
    Then the system saves the note into visit note table
    And a user clicks on the edit icon of a saved visit note
    Then the system loads the visit note page
    And a user edits the visit note
    And  a user clicks on save a visit note button
    Then the system saves the updated note into visit note table

  @selenium
  @visitNote
  Scenario: Adding a Diagnosis to Visit Note
    When a user enters the diagnosis into the visit note form
    And a user clicks on save a visit note button
    Then the system saves the diagnosis into the visit note table

  @selenium
  @visitNote
  Scenario: Deleting a Visit Note
    When a user fills the visit note form
    And a user clicks on save a visit note button
    Then the system saves the note into visit note table
    And a user clicks on the delete icon of a saved visit note
    Then the system deletes the visit note
