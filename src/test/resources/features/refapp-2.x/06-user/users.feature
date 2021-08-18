Feature: User Account Management

  Background:
    Given a user clicks on System Administartion app from home page
    Then the system loads system administration page
    When a user clicks on Manage accounts app
    Then the system loads manage acccount page

  @selenium
  @userAccount
  Scenario: Create user accounts
    # User story: Creating user account for Data clerk
    When a user clicks Add New Account button
    Then the system loads add new account form
    When a user enters Data clerk details in the user account form
    And a user clicks on save account button
    Then the system adds user account into the users table

    # User story: Creating user account for Nurse
    When a user clicks Add New Account button
    Then the system loads add new account form
    When a user enters Nurse details in the user account form
    And a user clicks on save account button
    Then the system adds user account into the users table
    
    # User story: Creating user account for Doctor
    When a user clicks Add New Account button
    Then the system loads add new account form
    When a user enters Doctor details in the user account form
    And a user clicks on save account button
    Then the system adds user account into the users table
