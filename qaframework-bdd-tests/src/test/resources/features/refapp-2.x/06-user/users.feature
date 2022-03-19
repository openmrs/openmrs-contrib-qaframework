Feature: User Account Management

  Background: 
    Given a user clicks on system administartion app from home page
    Then the system loads system administration page
    When a user clicks on manage accounts app
    Then the system loads manage acccount page
    And a user clicks add new account button
    Then the system loads add new account form

  @selenium
  @userAccount
  Scenario: Creating user account for Clerk
    And a user enters data clerk details in the user account form
    And a user clicks on save account button
    Then the system adds user account into the users table

  @selenium
  @userAccount
  Scenario: Creating user account for Nurse
    And a user enters nurse details in the user account form
    And a user clicks on save account button
    Then the system adds user account into the users table

  @selenium
  @userAccount
  Scenario: Creating user account for Doctor
    And a user enters doctor details in the user account form
    And a user clicks on save account button
    Then the system adds user account into the users table

  @selenium
  @userAccount
  Scenario: Password that doesn't meet the password criteria
    And a user enters person details in the user account form
    And a user enters user account details in the user account form
    And a user enters password that doesn't meet the password criteria
    And a user clicks on save account button
    Then the system throws a validation error message

  @selenium
  @userAccount
  Scenario: Password that meets the password criteria
    And a user enters person details in the user account form
    And a user enters user account details in the user account form
    And a user enters password that meets the password criteria
    And a user clicks on save account button
    Then the system confirms the password meets the password criteria
