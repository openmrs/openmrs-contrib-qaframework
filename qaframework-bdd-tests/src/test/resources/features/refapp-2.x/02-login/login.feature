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
    Then User logs in as clerk
    And  User logs in as doctor
    And  User logs in as Nurse
    Then User logs in system admin
    Examples:
      | username  | password  | location      | status |
      | admin     | wrongPas  | firstLocation | false  |
      | wrongUser | Admin123  | firstLocation | false  |
      | wrongUser | wrongPas  | firstLocation | false  |
      | setupUser | setupPass | setupLocation | true   |
