Feature: XSS Check

  @selenium
  Scenario: Patching XSS
    Given user clicks on Add Conditions Icon from Patient dashboard
    When user clicks on Add new condition
    And user enters a malicious code
    Then System does not execute the XSS