Feature: Upgrading

  @selenium
  @initialSetup
  @upgrade
  Scenario: Upgrading an Installation
    Given User is on login page
    And User enters credentials
    When User proceeds with Upgrade
    Then System should run upgrade