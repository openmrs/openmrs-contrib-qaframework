Feature: Patient Encounter Management

  Background:
    Given a user clicks on System Administration app from the home page
    When a user clicks on Advanced Administration app from the System Administration page
    Then a user clicks on Manage Encounters link from the Administration page

  @selenium
  @patientEncounter
  Scenario: Add and Delete Encounter
    #User story: Add Encounter
    When a user clicks on Add Encounter link from the encounters page
    And a user fills the ecounter form
    And a user clicks Save Encounter button
    Then the system saves encounter in the encounters table
    #User story: Delete Encounter
    When a user checks delete checkbox
    And a user provides a reason for deletion
    And a user clicks Save Encounter button
    Then the system saves encounter in the encounters table
