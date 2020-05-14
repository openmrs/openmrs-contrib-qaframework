Feature: Patient's Conditions
  Background:
    Given User logs in, searches John and visits first patient dashboard
    And User clicks on Conditions
    Then System loads Manage Conditions

  Scenario: User returns to patient dashboard
    And User clicks on Return
    Then System returns to patient dashboard

