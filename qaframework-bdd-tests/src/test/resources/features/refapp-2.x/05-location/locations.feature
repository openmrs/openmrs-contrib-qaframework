Feature: Location Management

    @selenium
    @location
    Scenario: Add a new Location Attribute Type
        Given a user clicks on the configure metadata link from the home page
        Then the system loads configure metadata link dashboard
        When a user clicks on the Manage Location Attribute Types
        Then the system loads the manage location attribute type page
        #User story: Add Location Attribute type
        And a user clicks on the add new location attribute type
        And a user fills the add new location attribute type form
        Then the user saves the form
    @selenium
    @location
    Scenario: Edit the newly created Location Attribute Type
        Given a user clicks on the configure metadata link from the home page
        Then the system loads configure metadata link dashboard
        When a user clicks on the Manage Location Attribute Types
        Then the system loads the manage location attribute type page
        #User story: Edit Location Attribute type
        And the user clicks on the edit icon
        And the user fills in the prefered details in the edit location attribute type form
        Then the user saves the edit location attribute type form
    @selenium
    @location
    Scenario: Retire and Delete the Location Attribute Type
        Given a user clicks on the configure metadata link from the home page
        Then the system loads configure metadata link dashboard
        When a user clicks on the Manage Location Attribute Types
        Then the system loads the manage location attribute type page
        #User story: Retire Location Attribute type
        And the user clicks on the retire icon
        Then the system retires the location attribute type
        #User story: Delete Location Attribute type Forever
        And the user clicks on the delete forever icon
        Then the system deletes the location attribute type forever

    @selenium
    @location
    Scenario: Manage Location Tag
        Given a user clicks on the configure metadata link from the home page
        Then the system loads configure metadata link dashboard
        When a user clicks on the manage location tag
        Then the system loads the manage location tag
        # User story: Add new location tag
        And the user clicks on the add new location tag
        And a user fills add new location tag form
        Then a user saves add New location tag form
        # User story: Edit Location Tag
        And the user clicks on the edit location tag icon
        And the user fills in the prefered details in the edit location tag form
        Then a user saves add New location tag form
        # User story: Retire Location Tag
        And the user clicks on the retire location tag button
        Then the system retires the location tag
        #User story: Delete Location Attribute type Forever
        And the user clicks on the restore location tag icon
        And the user clicks on the delete location tag forever icon

    @selenium
    @location
    Scenario: Manage Location
        Given a user clicks on the System Administration Link from home page
        When User clicks on the Advanced Administration link from the System Administration Page
        When a user clicks on the manage location link
        Then the system loads the manage location page
        # User story: Add new location
        And the user clicks on the add new location button
        And the user fills the form
        Then the user saves the location form
        # User story: Edit Location
        And the user clicks on the edit location icon
        And the user fills in the prefered details in the edit location form
        # User story: Retire Location
        And the user clicks on the retire location button
        Then the system retires the location
        #User story: Delete Location Attribute type Forever
        And the user restores the retired location
        Then the system deletes the location forever
