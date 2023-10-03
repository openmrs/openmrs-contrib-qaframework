Feature:Phone Number validation

  Background:
    Given user goes to find patient record app

  @selenium
  @phoneNumberValidation
  Scenario: Phone Number validation
    When user searches for patient using patient identifier
    And user clicks on returned patient in the patient table
    Then system loads patient dashboard page
    And user clicks on show contact link
    And user clicks on edit contact info link
    And user clicks on phone number tab
    And user clears phone number
    And user enters an invalid phone number
    And user clicks on confirm edit button
    Then system returns a validation error message
    And user clears the phone number input area
    And user enters the correct phone number
    And user clicks again on confirm edit button
    And user clicks on confirm patient button
    And user clicks again on show contact link
    Then system validates the correct phone number to be entered into the input area
