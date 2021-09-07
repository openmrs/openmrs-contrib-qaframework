Feature: Location Management

  Background:
    Given a user clicks on configure metadata link from home page
    Then the system loads configure metadata page

  @selenium
  @location
  Scenario: Create Location
    # User story: Add New Location
    When a user clicks on manage location link from configure metadata page
    Then the system loads manage locations page
    And a user clicks on add new location button
    Then the system loads add new location form
    And a user enters location details into the form
    And a user clicks on save location button
    Then the system adds location into the locations table         
    # User story: Delete Location
    And a user clicks on delete location icon and confirms the action
    Then the system deletes location from the system

  @selenium
  @location
  Scenario: Manage Location Tags
    # User story: Add New Location Tag
    When a user clicks on manage location tags link from configure metadata page
    Then the system loads manage location tags page
    And a user clicks on add new location tag button
    Then the system loads add new location tag form
    And a user enters location tag details into the form
    And a user clicks on save location tag button
    Then the system adds location tag into the location tags table  
    # User story: Delete Location Tag
    And a user clicks on delete location attribute tag icon and confirms the action
    Then the system deletes loaction attribute tag from the table
     
  @selenium
  @location
  Scenario: Manage Location Attribute Types
    # User story: Add New Location Attribute Types
    When a user clicks on manage location attribute types link from configure metadata page
    Then the system loads manage location attribute types page
    And a user clicks on add new location attribute type button
    Then the system loads add new location attribute type form
    And a user enters location attribute type details into the form
    And a user clicks on save location attribute type button
    Then the system adds location attribute type into the location attribute types table   
    # Use story: Delete Location Attribute Types
    And a user clicks on delete location attribute type icon and confirms the action
    Then the system deletes the location attribute type from the table
