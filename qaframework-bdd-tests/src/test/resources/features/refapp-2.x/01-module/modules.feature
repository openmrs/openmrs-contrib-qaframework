Feature: Modules Management

  @selenium
  @modules
  Scenario: Check list of modules to be sure they are all started
    Given a user clicks on administration app
    Then the system loads administration page
    When a user clicks on the modules link
    Then the system loads modules page
    And the user confirms that all the modules are started
