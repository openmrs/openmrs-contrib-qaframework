Feature: Upgrading

  @selenium
  @initialSetup
  @upgrade
  Scenario: Upgrading an Installation
    Given User enters credentials
    When User proceeds with Upgrade
    Then System should run upgrade