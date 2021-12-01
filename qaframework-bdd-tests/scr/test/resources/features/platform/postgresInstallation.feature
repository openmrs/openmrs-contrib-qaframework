Feature: New Postgres Installation

  @selenium
  @initialSetup
  @postgresInstall
  Scenario: Postgres Platform Installation
    Given User selects Language on step one of postgres Installation
    And User selects Advanced Installation Type on postgres
    When User enters all postgres Installation steps
    Then System should run postgres installation