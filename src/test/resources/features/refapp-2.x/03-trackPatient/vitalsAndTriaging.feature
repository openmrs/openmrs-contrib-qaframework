Feature: Vitals Management

  Background:
    Given a user clicks on Capture Vitals link from Patient dashboard
    Then the system loads Vitals page

  @selenium
  @vitals
  Scenario: Capture Patient vitals
    And a user enters patient vitals
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table 