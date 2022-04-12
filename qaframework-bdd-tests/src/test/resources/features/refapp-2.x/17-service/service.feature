Feature:Service Management

  Background:
    Given user goes to the appointment scheduling app
    When user goes to manage service page
    Then user clicks on new service page
    And user enters service details

  @selenium
  @service
  Scenario: Service management
    And user clicks on save button
    And system checks the saved service
    #edit service type section
    And user clicks on edit service type icon
    And user enters new name
    And user clicks again on save button
    And system checks the editted service type
    #delete service type section
    And user clicks on delete service type icon
    And user clicks on comfirm button
    Then user checks the availability of service type by name
