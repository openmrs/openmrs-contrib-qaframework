Feature: Edit Demographics and contact info from Patient Dashboard

  @selenium
  @patientDemographics
  Scenario: Edit Patient Demographics and Contact Info
    Given a user clicks on the find patient app on the home page
    Then the system loads the find patient page
    And a user searches for the patient to edit
    And a user clicks on the first patient
    And a user clicks on Edit Registration Information link from Patient dashboard
    Then the system loads Registration Summary Page
    And a user edits demographics
    And a user confirms the changes
    Then the system saves the updated patient demographics

  @selenium
  @patientDemographics
  Scenario: Edit Patient Contact Information
    Given a user clicks on the active visits app on the home page
    Then the system loads the active visits page
    And a user searches for the active visit patient to edit
    And a user clicks on the active visit patient to edit
    And a user clicks on the show contact downdrop
    And a user clicks on Edit link under contact info section from Registration Summary Page
    Then the system loads the edit contact information section
    And a user edits contact information
    Then a user clicks on the confirm button
