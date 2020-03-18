Feature: Login Testing

  Background:
    Given User visits login page

  Scenario Outline:
    When User enters "<username>" username
    And User enters "<password>" password
    And User Selects "<location>" Login Location
    And User Logs in
    Then System Evaluates Login "<status>"
    And System Closes browser
    Examples:
      | username| password | location    | status |
      | admin   | wrongPas | anyLocation | false  |
      |wrongUser| Admin123 | anyLocation | false  |
      |wrongUser| wrongPas | anyLocation | false  |
      | admin   | Admin123 | noLocation  | false  |
      |setupUser| setupPass|setupLocation| true   |

  Scenario:
    When setup user rightly logs in
    Then System logs in user
    And System Closes browser
