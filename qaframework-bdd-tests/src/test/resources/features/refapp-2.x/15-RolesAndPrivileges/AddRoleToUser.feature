Feature: Add Role To User

  Background: 
    Given a user clicks on system administration app
    Then the system loads manage users page

  @selenium 
  @addRoleToUser
  Scenario: Adding role to user
    When a user fills in person details
    Then a user assigns roles to the created user

  @selenium 
  @addRoleToUser
  Scenario: Adding user
    When user clicks the add user link
    Then the system loads the create new user page
    And a user enters the details of the user
    And a user logins into the system as the created user
    And a user logins into the system as an admin
    And a user deletes the user
    Then the system confirms the deletion of the user
