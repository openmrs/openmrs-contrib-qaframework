Feature: Add Role To User

  Background: 
    Given user clicks on system administration app
    Then system loads manage users page

  @selenium 
  @addRoleToUser
  Scenario: Adding role to user
    When user fills in person details
    Then user assigns roles to the created user

  @selenium 
  @addRoleToUser
  Scenario: Adding user
    When user clicks the add user link
    Then the system loads the create new user page
    And user enters the details of the user
    And user logins into the system as the created user
    And user logins into the system as an admin
    And user deletes the user
    Then the system confirms the deletion of the user
