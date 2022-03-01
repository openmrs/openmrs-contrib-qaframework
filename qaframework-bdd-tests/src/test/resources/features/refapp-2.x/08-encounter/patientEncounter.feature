Feature: Patient Encounter Management

  @selenium
  @patientEncounter
  Scenario: Add and Delete Encounter
    #User story: Add Encounter
    Given a user clicks on System Administration app from the home page
    When a user clicks on Advanced Administration app from the System Administration page
    And a user clicks on Manage Encounters link from the Administration page
    And a user clicks on Add Encounter link from the encounters page
    And a user fills the ecounter form
    And a user clicks Save Encounter button
    Then the system saves encounter in the encounters table
    #User story: Delete Encounter
    Given a user checks delete checkbox
    When a user provides a reason for deletion
    And a user clicks Save Encounter button
    Then the system saves encounter in the encounters table
