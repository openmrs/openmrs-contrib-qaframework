Feature: User Login check
  I want to be able to see if a valid user login correctly

  Scenario: Open the home page fill in credentials and login
    Given User opens the website at "https://openmrs-spa.org/openmrs/spa"
    When User enters username "admin"
      And User clicks "continue"
      And User enters Password "Admin123"
      And User "Log in"
      And User clicks "confirm"
    Then User should arrive at "https://openmrs-spa.org/openmrs/spa/home"