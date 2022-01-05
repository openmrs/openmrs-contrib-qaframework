Feature: Import Dictionary via subscription module

  Scenario: The user should copy the subscription url from the Dictionary manager
    Given the user goes to the Dictionary Manager App
    When the user logs into the Dictionary Manager
    And the user goes to the dictionary details page
    When the user clicks the more actions button
    And the user selects the "Copy Subscription URL" menu list item
    Then the user should copy the subscription url

  Scenario: The User should be able to install the dictionary in openmrs
    Given the user goes to the openmrs application
    When the user logs into the openmrs Ref App
    And the user visit the OCL module page
    When the user pastes the subscription url and user API token
    And the user saves changes
    Then the user should see the import button
    When the user clicks the import button
    Then the dictionary should be imported
    And the data should be correct

    