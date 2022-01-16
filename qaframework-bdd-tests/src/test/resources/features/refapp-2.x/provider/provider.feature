Feature: Provider Management

  Background: 
    Given User clicks on the system administration link on the home page
    When User clicks on the manage Provider link on the administration page

  @selenium
  @provider
  Scenario: Adding Provider
    And User clicks on the add provider link
    And User fills the provider form
    And User searches for the created provider
    Then User deletes provider forever

  @selenium
  @provider
  Scenario: Editing Provider
    And User searches for the created provider
    Then User edits provider details

  @selenium
  @provider
  Scenario: Retiring Provider
    And User searches for the created provider
    Then User retires a provider

  @selenium
  @provider
  Scenario: Deleting Provider
    And User searches for the created provider
    Then User deletes a provider
