Feature: Patient's Conditions

  Background:
    Given User logs in, searches John and visits first patient dashboard
    And User clicks on Conditions
    Then System on Manage Conditions Page

  @run
  Scenario Outline: Adding existing condition
    And User clicks on Add new condition
    Then System on Add New Condition Page
    And User enters "<activity>" existing condition
    And User clicks save
    Then System on Add New Condition Page
    And Quit browserhttps://youtu.be/NYB2-3PQiX0
    Examples:
      | activity |
      | active   |
      | inactive |

  @ignore
  Scenario: Set first condition to inactive
    And User clicks on set inactive button
    Then System should move condition to inactive section
    And Quit browser

  @ignore
  Scenario: Set first condition to active
    And User clicks on set active button
    Then System should move condition to active section
    And Quit browser

  @ignore
  Scenario: Edit first active condition
    And User edits active
    Then System should edit all active adjustable fields
    And Quit browser

  @ignore
  Scenario: Edit first inactive condition
    And User edits inactive
    Then System should edit all inactive adjustable fields
    And Quit browser

  @ignore
  Scenario: Delete first condition
    And User clicks delete condition
    Then System should trash first condition
    And Quit browser
