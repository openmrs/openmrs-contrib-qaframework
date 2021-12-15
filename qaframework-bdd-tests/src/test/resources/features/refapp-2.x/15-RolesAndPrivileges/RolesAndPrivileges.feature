Feature: Roles & Privileges Management

  @selenium
  @rolesAndPrivileges
  Scenario: Adding the privilege
    Given User clicks on configure metadata link from home page
    When User clicks on manage privileges link on the configure metadata page
    And User clicks the Add New Privilege button
    And User fills the new privilege form
    And User clicks the save button
    Then Privilege is saved successfully

  @selenium
  @rolesAndPrivileges
  Scenario: Editing the privilege
    Given User clicks on configure metadata link from home page
    When User clicks on manage privileges link on the configure metadata page
    And User clicks the Add New Privilege button
    And User fills the new privilege form
    And User clicks the save button
    And User search for the created privilege
    And User edits privilege
    Then Privilege is saved successfully

  @selenium
  @rolesAndPrivileges
  Scenario: Deleting the privilege
    Given User clicks on configure metadata link from home page
    When User clicks on manage privileges link on the configure metadata page
    And User clicks the Add New Privilege button
    And User fills the new privilege form
    And User clicks the save button
    And User search for the created privilege
    And User clicks delete privilege
    Then System confirms delete

  @selenium
  @rolesAndPrivileges
  Scenario: Adding a new Role
    Given User clicks on System Administration Link from home page
    When User clicks on Advanced Administration link from the System Administration Page
    Then User clicks on manage roles link on the advanced administration page
    And User clicks the Add New Role button on the manage roles page
    And User fills the new role form
    And User saves role
    Then Role is saved successfully

  @selenium
  @rolesAndPrivileges
  Scenario: Editing Role
    Given User clicks on System Administration Link from home page
    When User clicks on Advanced Administration link from the System Administration Page
    And User clicks on manage roles link on the advanced administration page
    And User clicks the Add New Role button on the manage roles page
    And User fills the new role form
    And User saves role
    And User edits the role
    Then Role is saved successfully

  @selenium
  @rolesAndPrivileges
  Scenario: Delete the role
    Given User clicks on System Administration Link from home page
    When User clicks on Advanced Administration link from the System Administration Page
    And User clicks on manage roles link on the advanced administration page
    And User clicks the Add New Role button on the manage roles page
    And User fills the new role form
    And User saves role
    And User selects the role to be deleted
    And User deletes role
    Then Role is deleted successfully
