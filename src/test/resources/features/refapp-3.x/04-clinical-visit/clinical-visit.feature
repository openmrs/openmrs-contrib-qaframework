Feature: User Settings

  Background:
    Given the user is logged in
    And the user arrives on a patient’s chart page

  @clinical-visit
  Scenario: The Patient Chart should load properly
    Then the Patient header should display correct information
    And the user should be able to expand header to see more information

  @clinical-visit
  Scenario: The Patient Summary should load properly
    Then the Patient Summary should load properly

  @clinical-visit
  Scenario: The programs page should function properly
    When the user clicks on "Programs" in the menu
    Then the program list should be empty
    When the user enrolls the patient into a program
    Then the patient should be enrolled into the program

  @clinical-visit
  Scenario: The Allergies page should function properly
    When the user clicks on "Allergies" in the menu
    Then the allergies list should be empty
    When the user adds an allergy
    Then the added allergy should be listed

  @clinical-visit
  Scenario: The Conditions page should function properly
    When the user clicks on "Conditions" in the menu
    Then the conditions list should be empty
    When the user adds a condition
    Then the added condition should be listed
