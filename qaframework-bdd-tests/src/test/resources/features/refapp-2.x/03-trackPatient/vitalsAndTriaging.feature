Feature: Vitals Management

  Background:
    Given a user clicks on Capture Vitals link from Patient dashboard
    Then the system loads Vitals page

  @selenium
  @vitals
  Scenario: Normal Vitals
    When a user enters normal patient vitals
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table 
   
  @selenium
  @vitals 
  Scenario: Vitals under minimum value
    When a user enters a vital below minimum value and the system alerts until valid
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table

  @selenium
  @vitals 
  Scenario: Vitals over maximum value
    When a user enters a vital above maximum value and the system alerts until valid
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table

  @selenium
  @vitals 
  Scenario: Editing Vitals
    # User Story: Capture normal vitals
    When a user enters normal patient vitals
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table 
    #User Story: Edit vitals
    When a user clicks on edit vitals icon from patient visits dashboard
    And the system loads the edit vitals page
    And a user edits existing vitals
    And a user clicks on the save changes button
    Then the system adds patient vitals into the vitals table
