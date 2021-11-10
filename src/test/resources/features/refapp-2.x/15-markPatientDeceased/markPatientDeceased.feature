Feature: Mark Patient Deceased Test

  @selenium @markpatientdeceased
  Scenario: Creating causes of death concept
    Given user adds a new concept called cause of death
    And user adds the concept id to the global properties
    Then the global property for is saved

  @selenium @markpatientdeceased
  Scenario: Marking a patient deceased
    Given a patient is selected
    And User eneters the details of the deceased and saves
    Then Patiet deceased confirmation message is displayed
