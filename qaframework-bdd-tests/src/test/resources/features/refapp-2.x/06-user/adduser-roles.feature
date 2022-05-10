Feature: User and Add Roles To User

  Background: 
    Given a user clicks on system administration app
    Then the system loads manage users page

  @selenium
  @user
  Scenario: Adding role to user
    When a user fills in person details
    Then a user assigns roles to the created user

  @selenium
  @user
  Scenario: Adding user
    When a user clicks the add user link
    Then the system loads the create new user page
    And a user checks whether the form can save with empty fields
    Then the system throws validations indicating an empty form cant be saved
    And a user enters the demorgraphic information
    And a user enters the login information
    And a user saves the addedit user form
    And a user logins into the system as the created user
    And a user logins into the system as an admin
    And a user deletes the user account
    Then the system confirms the deletion of the user account
