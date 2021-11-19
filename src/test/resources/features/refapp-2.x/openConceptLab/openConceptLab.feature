Feature: OCL Subscription module
 
Background:
    Given User clicks on configure Metadata link from home page
    When User selects Manage OCL
    Then System loads Open Concept Lab page
 
  @selenium
  @subscription
  Scenario: OCL MANAGEMENT
     And User clicks on Setup subscription button
     Then System on Subscription page
      And  the user enters the URL of a new released dictionary
     Then the "Token" title is visible
      And the user enters the "User url
      And User clicks on the Save Changes button
     Then the user should be on the "Open Concept Lab" page
      And the API should be displayed on the previous imports
     