Feature: New Testing Installation

  @selenium
  @initialSetup
  @testingInstall
  Scenario: Testing Platform Installation
    Given User selects Language on step one of Testing Installation
    And User selects Testing Installation Type
    When User enters all Testing Installation steps
    Then System should run Testing installation