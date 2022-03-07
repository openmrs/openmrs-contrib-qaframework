Feature: User Account Management

  Background:
    Given a user clicks on system administration app from home page
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
  Scenario: Password Requires Upper and Lower case
    And a user enters person details in the user account form
    And a user enters user account details in the user account form
    And a user sets the passwords with only upper case letters
    # Then the system throws a validation error

  @selenium
  @userAccount
  Scenario: Password Requires Non-digits
    And a user enters person details in the user account form
    And a user enters user account details in the user account form
    And a user sets the passwords with only digits
    # Then the system throws a validation error

  @selenium
  @userAccount
  Scenario: Password Requires digits
    And a user enters person details in the user account form
    And a user enters user account details in the user account form
    And a user sets the passwords with only letters
    # Then the system throws a validation error

  @selenium
  @userAccount
  Scenario: Password Requires minimum length
    And a user enters person details in the user account form
    And a user enters user account details in the user account form
    And a user sets the passwords which are lower than 8 characters
    # Then the system throws a validation error
