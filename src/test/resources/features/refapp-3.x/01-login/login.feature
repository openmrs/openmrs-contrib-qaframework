Feature: User Login

  @login
  Scenario Outline: User login to the dashboard
    Given the user logs in with "<username>" and "<password>" to the "<location>"
    Then the user should be "<ability>" to login

    Examples:
      | username   | password   | location          | ability |
      | admin      | Admin123   | Registration Desk | able    |
      | wrong user | Admin123   | Registration Desk | unable  |
      | admin      | wrong pass | Registration Desk | unable  |
