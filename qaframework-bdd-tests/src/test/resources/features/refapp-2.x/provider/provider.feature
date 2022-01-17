Feature: Provider Management

  Background: 
    Given User clicks on the system administration link on the home page
    Then System loads system administration page
    And User clicks on the manage Provider link on the administration page
    Then System loads manage provider page

  @selenium
  @provider
  Scenario: Adding Provider
    When User clicks on the add provider link
    Then System loads add provider page
    And User fills the provider form
    And User searches for the created provider
    Then User deletes provider forever

  @selenium
  @provider
  Scenario: Editing Provider
    When User searches for the created provider
    Then User edits provider details

  @selenium
  @provider
  Scenario: Retiring Provider
    When User searches for the created provider
    Then User retires a provider

  @selenium
  @provider
  Scenario: Deleting Provider
    When User searches for the created provider
    Then User deletes a provider
