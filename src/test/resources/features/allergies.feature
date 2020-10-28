Feature: allergies feature

  Background: 
    Given User logs in, searches John and visits first patient dashboard
    And User clicks on edit icon near allergies label
    Then system loads patients allergies page

  Scenario: Add new allergies
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

  Scenario: No known allergies
    And user clicks on No known allergy button
    Then No known  allergies should be registered in the table

  Scenario: Revert No known allergies
    Given user has registered No known allergies
    And user clicks on the X near the registered No known allergies in the table
    Then No known allergies is removed from the table

  Scenario: List allergies
    Then A list of added allergies should apear in a table

  Scenario: Edit allergies
    And user clicks on edit icon in the Actions colum of an entry
    Then system loads Edit allergy page
    And user selects diffrent reactions like Cough and Rash 
    And user selects Severity as Mild
    And user clicks save
    Then List of allergies should appear with the changes made
        

  Scenario: delete allergies
    And user clicks delete icon in Actions column
    Then prompt to remove allergy appears
    And user clicks yes
    Then allergy entry is deleted
