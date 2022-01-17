Feature: User Login

  Scenario Outline: User login to the dashboard
    Given user arrives at the login page
    When the user logs in with "<username>" and "<password>" to the "<location>"
    Then the user should be "<ability>" to login

    Examples:
      | username   | password   | location          | ability |
      | admin2     | Admin123   | Registration Desk | able    |
      | wrong user | Admin123   | Registration Desk | unable  |
      | admin2     | wrong pass | Registration Desk | unable  |
