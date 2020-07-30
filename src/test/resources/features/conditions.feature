Feature: Patient's Conditions

  Background:
    Given User logs in, searches John and visits first patient dashboard
    And User clicks on Conditions
    Then System on Manage Conditions Page

  Scenario: Adding missing condition
    And User clicks on Add new condition
    Then System on Add New Condition Page
    And User enters Missing condition
    And User clicks save
    Then System on Add New Condition Page
    And Quit browser

  Scenario Outline: Adding existing condition
    And User clicks on Add new condition
    Then System on Add New Condition Page
    And User enters "<activity>" existing condition
    And User clicks save
    Then System on "<page>" Page
    And Quit browser
    Examples:
      | activity | page    |
      | true     | parent  |
      | false    | current |
