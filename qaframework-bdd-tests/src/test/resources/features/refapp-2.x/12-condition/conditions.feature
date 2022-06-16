Feature: Patient's Conditions Managment

  Background: 
    Given User clicks on Conditions from Patient dashboard
    Then System loads Manage Conditions Page
    And User clicks on Add new condition
    Then System loads Add New Condition Page

  @selenium
  @condition
  Scenario: Active condition management
    # User story: Adding an active condition
    And User enters active condition
    And User clicks save
    # User story: Set first condition to inactive
    And User clicks on set inactive button
    Then System should move condition to inactive section

  @selenium
  @condition
  Scenario: Inactive condition management
    # User story: Adding an inactive condition
    And User enters inactive condition
    And User clicks save
    # User story: Set first condition to active
    And User clicks on set active button
    Then System should move condition to active section

  @selenium
  @condition
  Scenario: Editing First Active condition
    And User enters active condition
    And User clicks save
    # User story: Edit first active condition
    And User edits active
    Then System should edit all active adjustable fields

  @selenium
  @condition
  Scenario: Editing First Inactive condition
    And User enters inactive condition
    And User clicks save
    # User story: Edit first inactive condition
    And User edits inactive
    Then System should edit all inactive adjustable fields

  @selenium
  @condition
  Scenario: Delete first active condition
    And User enters active condition
    And User clicks save
    # User story: Delete first active condition
    And User clicks delete first active condition
    Then System should trash first active condition

  @selenium
  @condition
  Scenario: Delete first inactive condition
    And User enters inactive condition
    And User clicks save
    # User story: Delete first inactive condition
    And User clicks delete first inactive condition
    Then System should trash first inactive condition
