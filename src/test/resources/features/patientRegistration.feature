Feature: Patient Registration

  @run
  Scenario Outline: Failing or Succeeding to register a patient
    Given Registration user rightly logs in
    And User clicks on Registration App
    And User enters "<validity>" patient details
    Then User's patient registration is "<status>"
    Examples:
      | validity   | status       |
      | right      | successful   |
      | wrong      | unsuccessful |
      | incomplete | inactive     |