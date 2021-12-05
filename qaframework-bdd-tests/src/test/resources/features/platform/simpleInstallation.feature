Feature: New Simple Installation

  @selenium
  @initialSetup
  @simpleInstall
  Scenario: Simple Platform Installation
    Given User selects Language on step one of Simple Installation
    And User selects Simple Installation Type
    When User enters all Simple Installation steps
    Then System should run Simple installation