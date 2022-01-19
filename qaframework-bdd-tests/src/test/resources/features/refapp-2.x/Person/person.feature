Feature: Person Management

  Background: 
    Given a user clicks on the system administration link on the home page
    Then the system loads the administration page
    And a user clicks on the manage Persons link on the administration page
    Then the system loads manage person page

  @selenium
  @person
  Scenario: Creating a Person
    When a user clicks on the create person link on the manage persons page
    Then the system loads the create person page
    And a user fills the create person form
    Then a user saves the person details

  @selenium
  @person
  Scenario: Editing a Person
    When a user searches for the person
    And a user clicks on the first person found from the search results
    And a user edits the person details
    Then a user saves the edited person details

  @selenium
  @person
  Scenario: Deleting a Person
    When a user searches for the person
    And a user clicks on the first person found from the search results
    And a user clicks on the delete person forever button

  @selenium
  @person
  Scenario: Retiring a Person
    When a user searches for the person
    And a user clicks on the first person found from the search results
    And a user fills in the reason for retiring person
    Then a user clicks on the retire person button
