Feature: User Account Management

  Background:
    Given a user clicks on System Administartion app from home page
    Then the system loads the system administration page
    When a user clicks on Manage accounts app
    Then the system loads the manage acccount page

  @selenium
  @userAccount
  Scenario: Create user accounts
    When a user clicks Add New Account button
    Then the system loads the add new account form
    When a user fills the account form
    And a user clicks on save account button
    Then the system adds user account into the users table

  Scenario: Assign User privileges
    And a user clicks Edit user button
    Then the system displays displays registred user
