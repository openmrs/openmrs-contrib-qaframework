Feature: allergies feature

  Background: 
    Given User logs in, searches John doe and visits first patient dashboard
    And User clicks on edit icon near allergies label

  @run
  Scenario: Launching allergies page
    Then system loads patients allergies page

  @run
  Scenario: Add new allergies
    And user clicks add new allergy button
    Then system loads allergies page
    Given User selects one of the three allergy options
    Then coressponding allergy sources should appear
    And User selects specific allergy source
    And user selects the reactions , eneters severity and a comment
    And clicks Save button
    Then System should show the alergies of the patient in a table

  @run
  Scenario: No known allergies
    And user clicks on No known allergy button
    Then No known  allergies should be registered in the table

  @run
  Scenario: List, Edit, delete allergies
    Then A list of added allergies should apear in a table
    And user clicks on edit icon in the Actions colum of an entry
    Then system loads allergies page
    And user makes any changes
    And user clicks save
    Then List of allergies should appear with the changes made
    And user clicks delete icon in Actions column
    Then promp to remove allergy appears
    And user clicks yes
    Then allergy entry is deleted
