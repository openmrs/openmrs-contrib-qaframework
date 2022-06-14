Feature: Location Management

  Background:
    Given a user clicks on the configure metadata link from the home page
    Then the system loads configure metadata link dashboard

  @selenium
  @location
  Scenario: Add Location Attribute type
    When a user clicks on Manage Location Attribute Types link
    Then the system loads the manage Location Attribute Types page
    And a user clicks on add new location attribute type button
    And a user fills the form
    Then the user saves the form

  @selenium
  @location
  Scenario: Add new location tag
    When the user clicks on manage location tag link
    Then the system loads the manage location tags page
    And the user clicks on add new location tag
    And the user fills add new location tag form
    Then the user saves add New location tag form
    Then the New location tag form is saved
    And the the user clicks on the retire location button
    And the user cancels retire location
    Then a user deletes location tag

  @selenium
  @location
  Scenario: Manager locations
    When a user clicks on manager locations
    Then the user click on add new location
    And a user fills add new location form
    Then the add new location form is saved
