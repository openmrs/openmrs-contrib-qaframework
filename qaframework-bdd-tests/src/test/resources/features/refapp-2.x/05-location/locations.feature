  Feature: Location Management

  @selenium
  @location
  Scenario: Manage Location Attribute Type
    Given a user clicks on the configure metadata link from the home page
    Then the system loads configure metadata link dashboard
    When a user clicks on Manage Location Attribute Types
    #User story: Add Location Attribute type
    And a user clicks on add new location attribute type
    And a user fills the form
    Then the user saves the form
    #User story: Edit Location Attribute type
    And the user clicks on the edit icon
    And the user fills in the prefered details in the edit location attribute type form
    And the user clicks save
    #User story: Retire Location Attribute type
    And the user clicks on the retire icon
    And the user clicks the confirm button
    Then the system retires the location attribute type
    #User story: Delete Location Attribute type Forever
    And the user clicks the restore location attribute type icon
    And the user clicks on the delete forever icon
    And the user clicks the confirm button
    Then the system deletes the location attribute type forever

  @selenium
  @location
  Scenario: Manage Location Tag
    Given a user clicks on the configure metadata link from the home page
    Then the system loads configure metadata link dashboard
    When a user clicks on manage location tag
    Then the system loads the manage location tag
    # User story: Add new location tag
    And the user clicks on add new location
    And a user fills add new location tag form
    Then a user saves add New location tag form
    # User story: Edit Location Tag
    And the user clicks on the edit location tag icon
    And the user fills in the prefered details in the edit location tag form
    And the user clicks save
    # User story: Retire Location Tag
    And the the user clicks on the retire location tag button
    And the user clicks the confirm button
    Then the system retires the location tag
    #User story: Delete Location Attribute type Forever
    And the user clicks the restore location tag icon
    And the user clicks on the delete location tag forever icon
    And the user clicks the confirm button
    Then the system deletes the location attribute type forever

    @selenium
    @location
    Scenario: Manage Location
    Given a user clicks on the configure metadata link from the home page
    Then the system loads configure metadata link dashboard
    When a user clicks on manage location link
    Then the system loads the manage location page
    # User story: Add new location
    And the user clicks on add new location button
    And the user fills add new location form
    Then the user saves add New location form
    # User story: Edit Location 
    And the user clicks on the edit location icon
    And the user fills in the prefered details in the edit location  form
    And the user clicks save
    # User story: Retire Location 
    And the the user clicks on the retire location  button
    And the user clicks the confirm button
    Then the system retires the location 
    #User story: Delete Location Attribute type Forever
    And the user clicks the restore location icon
    And the user clicks on the delete location forever icon
    And the user clicks the confirm button
    Then the system deletes the location attribute type forever
