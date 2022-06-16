  Feature: Location Management

  Background:
    Given a user clicks on the configure metadata link from the home page

  @selenium
  @location
  Scenario: LOCATION_MANAGEMENT
    # User story: Add Location Attribute type
    When the system loads configure metadata link dashboard
    And a user clicks on Manage Location Attribute Types
    And a user clicks on add new location attribute type
    And a user fills the form
    Then the user saves the form

    # User story: Add new location tag
    When a user clicks on manage location tag
    Then the user clicks on add new location
    And a user fills add new location tag form
    And a user saves add New location tag form
    Then the New location tag form is saved
    And the the user clicks on the retire location button
    And the user cancels retire location
    Then a user deletes location tag
    
    # User story: Manager locations
    When a user clicks on manager locations
    Then the user click on add new location
    And a user fills add new location form
    Then the add new location form is saved
