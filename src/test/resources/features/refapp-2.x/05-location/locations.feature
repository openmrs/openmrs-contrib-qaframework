Feature: Location Management

  Background:
    Given a user clicks on configure metadata link from home page
    Then the system loads configure metadata page

  @selenium
  @location
  Scenario: Create Location
    When a user clicks on manage location link from configure metadata page
    Then the system loads manage locations page
    And a user clicks on add new location button
    Then the system loads add new location form
    And a user enters location details into the form
    And a user clicks on save location button
    Then the system adds location into the locations table         
    
  @selenium
  @location
  Scenario: Manage Location Tags
    When a user clicks on manage location tags link from configure metadata page
    Then the system loads manage location tags page
    And a user clicks on add new location tag button
    Then the system loads add new location tag form
    And a user enters location tag details into the form
    And a user clicks on save location tag button
    Then the system adds location tag into the location tags table
     
  @selenium
  @location
  Scenario: Manage Location Attribute Types
    When a user clicks on manage location attribute types link from configure metadata page
    Then the system loads manage location attribute types page
    And a user clicks on add new location attribute type button
    Then the system loads add new location attribute type form
    And a user enters location attribute type details into the form
    And a user clicks on save location attribute type button
    Then the system adds location attribute type into the location attribute types table 
