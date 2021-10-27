Feature: Patient's Conditions Managment
 
  Background:
    Given a user clicks on the active visits link from home page
    When a user selects a patient from the active patient list
    Then the system loads the Patient dashboard page
 
  @selenium
  @condition
  Scenario Outline: Adding an active or inactive condition
    When a user clicks on Conditions from Patient dashboard
    Then System loads Manage Conditions Page
    And User clicks on Add new condition
    Then System on Add New Condition Page
    And User enters "<activity>" condition
    And User clicks save
    Then System loads Manage Conditions Page
    Examples:
      | activity |
      | active   |
      | inactive |

  @selenium
  @condition
  Scenario: Set first condition to inactive
    And User clicks on set inactive button
    Then System should move condition to inactive section

  @selenium
  @condition
  Scenario: Set first condition to active
    And User clicks on set active button
    Then System should move condition to active section

  @selenium
  @condition
  Scenario: Edit first active condition
    And User edits active
    Then System should edit all active adjustable fields

  @selenium
  @condition
  Scenario: Edit first inactive condition
    And User edits inactive
    Then System should edit all inactive adjustable fields

  @selenium
  @condition
  Scenario: Delete first condition
    And User clicks delete condition
    Then System should trash first condition
