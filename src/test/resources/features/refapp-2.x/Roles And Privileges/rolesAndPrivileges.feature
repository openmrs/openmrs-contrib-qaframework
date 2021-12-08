Feature: Roles & Privileges Management
  
  i want the user to Log in as an admin.Go to configure meta data.
  Go to privileges, add a new privilege, edit it and delete the privilege
  Go to roles,add a new role, edit it and delete a role.
  lastly,Return to home page

  @selenium
  @rolesAndPrivileges
  Scenario: Adding the privilege
    Given User clicks on configure metadata link from home page
    When User clicks on manage privileges link on the configure metadata page
    And User clicks the Add New Privilege button
    And User fills the new privilege form
    And User clicks the save button

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

  @selenium
  @rolesAndPrivileges
  Scenario: Editing Role
    Given User clicks on System Administration Link from home page
    When User clicks on Advanced Administration link from the System Administration Page
    Then User clicks on manage roles link on the advanced administration page
    And User edits the role
    And User saves role

  @selenium
  @rolesAndPrivileges
  Scenario: Delete the role
    Given User clicks on System Administration Link from home page
    When User clicks on Advanced Administration link from the System Administration Page
    Then User clicks on manage roles link on the advanced administration page
    And User clicks the Add New Role button on the manage roles page
    And User fills the new role form
    And User saves role
    And User selects the role to be deleted
    And User deletes role
