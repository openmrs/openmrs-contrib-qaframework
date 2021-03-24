Feature: Allergies Management

  Background:
    Given a user clicks on Allergies link from Patient dashboard
    Then the system loads Allergies page

  @selenium
  @dashboard
  Scenario: Add No known allergies
    And a user clicks No Known Allergy button
    Then the system add no known allergies into the allergies table

  @selenium
  @dashboard
  Scenario: Remove No known allergies
    And a user clicks Remove No Known Allergy icon
    Then the system displays unknown in the allergies table

  @selenium
  Scenario: Add New Allergy
    And user clicks add new allergy button
    Then system loads add new allergy page
    Given User selects one of the three allergen Types options
    Then corresponding allergens should appear
    And User selects an allergen
    And user selects the reactions
    And user  enters severity
    And user enters comment
    And user clicks Save button
    Then System should show the allergies of the patient in a table

  @selenium
  Scenario: Revert No known allergies
    Given user has registered No known allergies
    And user clicks on the X near the registered No known allergies in the table
    Then No known allergies is removed from the table

  @selenium
  Scenario: List allergies
    Then A list of added allergies should apear in a table

  @selenium
  Scenario: Edit allergies
    And user clicks on edit icon in the Actions colum of an entry
    Then system loads Edit allergy page
    And user selects diffrent reactions like Cough and Rash
    And user selects Severity as Mild
    And user clicks save
    Then List of allergies should appear with the changes made

  @selenium
  Scenario: delete allergies
    And user clicks delete icon in Actions column
    Then prompt to remove allergy appears
    And user clicks yes
    Then allergy entry is deleted