Feature: Visits management

  Background: 
    Given user clicks on the find patient record app
    When user searches for the patient record
    And user clicks on the first patient record
    Then the system loads the clinician facing patient dashboard

  @selenium
  @visit
  Scenario: Starting a visit
    When user clicks on the start visit link
    Then the system starts the patient visit

  @selenium
  @visit
  Scenario: Adding a past visit
    When user clicks on the add past visit link
    Then the system adds the patient past visit

  @selenium
  @visit
  Scenario: Merging visits
    When user clicks on the recent visits link in the recent visits section
    And user clicks on the actions button
    And user clicks on the merge visits link
    And user selects the available visits to be merged
    And user clicks on the merge selected visits button
    Then the system merges the visits successfully

  @selenium
  @visit
  Scenario: Ending a visit
    When user clicks on the recent visits link in the recent visits section
    And user clicks on the end visit button
    Then the system ends the visit
