Feature: User Login

  Background:
    Given User visits login page

  @run
  Scenario Outline: Failing or Succeeding to Login
    When User enters "<username>" username
    And User enters "<password>" password
    And User Selects "<location>" Login Location
    And User Logs in
    Then System Evaluates Login "<status>"
    Examples:
      | username  | password  | location      | status |
      | admin     | wrongPas  | anyLocation   | false  |
      | wrongUser | Admin123  | anyLocation   | false  |
      | wrongUser | wrongPas  | anyLocation   | false  |
      | admin     | Admin123  | noLocation    | false  |
      | setupUser | setupPass | setupLocation | true   |

  @run
  Scenario: Right Login
    When Setup user rightly logs in
    Then System logs in usero