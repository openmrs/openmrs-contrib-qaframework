Feature: Roles & Privileges Management
  
  i want the user to Log in as an admin.Go to configure meta data.
  Go to privileges, add a new privilege, edit it and delete the privilege
  Go to roles,add a new role, edit it and delete a role.
  lastly,Return to home page

  Background: 
    Given User clicks on configure metadata link from home page

  @selenium
  @rolesAndPrivileges
  Scenario: Adding a New Privilege
    When User clicks on manage privileges link on the configure metadata page
    And User clicks the Add New Privilege button
    And User fills the new privilege form
    And User clicks the save button

  @selenium
  @rolesAndPrivileges
  Scenario: Edit the privilege
    When User clicks on manage privileges link on the configure metadata page
    And User clicks the Add New Privilege button
    And User fills the new privilege form
    And User clicks the save button
    And User search for the created privilege
    And User edits privilege

  @selenium
  @rolesAndPrivileges
  Scenario: Delete the privilege
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
    When User clicks on manage roles link on the configure metadata page
    And User clicks the Add New Role button
    And User fills the new role form
    And User saves role

  @selenium
  @rolesAndPrivileges
  Scenario: Edit the role
    When User clicks on manage roles link on the configure metadata page
    And User clicks the Add New Role button
    And User fills the new role form
    And User saves role
    And User edits Role

  Scenario: Delete the role
    When User clicks on manage roles link on the configure metadata page
    And User clicks the Add New Role button
    And User fills the new role form
    And User saves role
    And User clicks delete Role
