Feature: Patient Registration

  @selenium
  @registration
  Scenario Outline: Failing or Succeeding to register a patient
    Given Registered user rightly logs in
    And User clicks on Registration App
    And User enters "<validity>" details for John Smith
    Then User's patient registration is "<status>"

    Examples:
      | validity   | status       |
      | right      | successful   |
      | wrong      | unsuccessful |
      | incomplete | inactive     |
