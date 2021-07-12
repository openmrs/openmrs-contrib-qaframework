Feature: User Login

  Scenario Outline: User login to the dashboard
    Given user arrives at the login page
    When the user logs in with "<username>" and "<password>"
    And the user selects location "<location>"
    Then the user should arrive at the home page

    Examples:
      | username  | password  | location          |
      | admin     | Admin123  | Registration Desk |
