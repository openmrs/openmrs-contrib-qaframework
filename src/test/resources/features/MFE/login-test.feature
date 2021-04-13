Feature: User Login check
  I want to be able to see if a valid user login correctly

 Background: User Opens visits login page
    Given User opens the websites login page
  Scenario Outline: User fill in credentials and login
    
    When User enters username "<username>"
      And User clicks continue
      And User enters Password "<password>"
      And User Log in
      And User selects unknown location
      And User clicks confirm
    Then User should arrive at home page

    Examples:
        | username | password | 
        | admin  | Admin123  | 