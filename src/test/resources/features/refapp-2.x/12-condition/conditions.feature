Feature: Patient's Conditions Managment

  Background:
    Given User clicks on Conditions from Patient dashboard
    Then System loads Manage Conditions Page

  @selenium
  @dashboard
  Scenario Outline: Adding an active or inactive condition
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
  @dashboard
  Scenario: Set first condition to inactive
    And User clicks on set inactive button
    Then System should move condition to inactive section

  @selenium
  @dashboard
  Scenario: Set first condition to active
    And User clicks on set active button
    Then System should move condition to active section

  @selenium
  @dashboard
  Scenario: Edit first active condition
    And User edits active
    Then System should edit all active adjustable fields

  @selenium
  @dashboard
  Scenario: Edit first inactive condition
    And User edits inactive
    Then System should edit all inactive adjustable fields

  @selenium
  @dashboard
  Scenario: Delete first condition
    And User clicks delete condition
    Then System should trash first condition
