Feature: Provider Management

  Background: 
    Given a user clicks on the administration app
    Then system loads administration page
    And a user clicks on the manage provider link
    Then system loads manage provider page

  @selenium
  @provider
  Scenario: Adding Provider
    When a user clicks on the add provider link
    Then system loads add provider page
    And a user fills the provider form
    Then a user clicks the save button

  @selenium
  @provider
  Scenario: Editing Provider
    When a user searches for the created provider
    And a user clicks on the provider in the search results
    And a user edits provider details
    Then a user clicks the save button

  @selenium
  @provider
  Scenario: Retiring Provider
    When a user searches for the created provider
    And a user clicks on the provider in the search results
    And a user sets the reason for retiring a provider
    Then a user retires the provider

  @selenium
  @provider
  Scenario: Deleting Provider
    When a user searches for the created provider
    And a user clicks on the provider in the search results
    Then a user deletes provider forever
