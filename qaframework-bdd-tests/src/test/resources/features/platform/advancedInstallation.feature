Feature: New Advanced Installation

  @selenium
  @initialSetup
  @advancedInstall
  Scenario: Advanced Platform Installation
    Given User selects Language on step one of Advanced Installation
    And User selects Advanced Installation Type
    When User enters all Advanced Installation steps
    Then System should run Advanced installation