Feature: Person Management

  Background: 
    Given User clicks on the system administration link on the home page
    When User clicks on the manage Persons link on the administration page

  @selenium
  @person
  Scenario: Creating a Person
    And User clicks on the create person link on the manage persons page
    Then User fills the create person form

  @selenium
  @person
  Scenario: Editing a Person
    And User searches for the person
    And User clicks on the first person found from the search results
    Then User edits the person details

  @selenium
  @person
  Scenario: Deleting a Person
    And User searches for the person
    And User clicks on the first person found from the search results
    Then User clicks on the delete person forever button

  @selenium
  @person
  Scenario: Retiring a Person
    And User searches for the person
    And User clicks on the first person found from the search results
    Then User clicks on the retire person button
