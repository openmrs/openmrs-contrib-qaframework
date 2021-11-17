Feature: User Login

  Background:
    Given User visits login page

  @cypress
  @selenium
  Scenario Outline: Failing or Succeeding to Login
    When User enters "<username>" username
    And User enters "<password>" password
    And User Selects "<location>" Login Location
    And User Logs in
    Then System Evaluates Login "<status>"

    Examples:
      | username  | password  | location      | status |
      | admin     | wrongPas  | firstLocation | false  |
      | wrongUser | Admin123  | firstLocation | false  |
      | wrongUser | wrongPas  | firstLocation | false  |
      | admin     | Admin123  | noLocation    | false  |
      | setupUser | setupPass | setupLocation | true   |
