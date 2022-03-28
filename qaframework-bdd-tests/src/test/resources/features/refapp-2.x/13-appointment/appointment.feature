Feature: Appointment Management

  Background:
    Given a user clicks on Request Appointment link from the Patient dashboard
    When the system loads Request Appointment page
    And a user fills in the appointment request details
    And a user clicks save button
    Then the system adds appointment request into the appointment requests table

  @selenium
  @appointment
  Scenario: Delete Appointment Request
    When a user clicks on Schedule Appointment link from the Patient dashboard
    Then the system loads Manage Appointments page
    And a user clicks on Cancel this Request icon
    Then the system deletes appointment request from the request table

  @selenium
  @appointment
  Scenario: Create, Edit and Delete Appointment Block
    #User Story: Create Appointment Block
    When a user clicks Appointment scheduling app from the home page
    Then the system loads the appointment scheduling page
    And a user clicks on Manage Provider Schedules app
    And the system loads Manage Provider Schedules page
    And a user fills in the details of the appointment block
    And a user clicks on the save button
    Then the system saves the appointment block
    #User Story: Edit Appointment Block
    When a user clicks Appointment scheduling app from the home page
    Then the system loads the appointment scheduling page
    And a user clicks on the Manage Provider Schedules app
    And the system loads Appointment Blocks page
    And a user clicks on an appointment block
    And a user clicks on Edit appointment block
    And the system loads Edit Appointment Block section
    And a user edits the Appointment block
    And a user clicks on Save button
    Then the system saves the edited appointment block
    #User Story: Delete Appointment Block
    When a user clicks Appointment scheduling app from the home page
    Then the system loads the appointment scheduling page
    And a user clicks on the Manage Provider Schedules app
    And the system loads Appointment Blocks page
    And a user clicks on an appointment block
    And a user clicks on Delete appointment block
    Then the system deletes appointment block from the appointment Blocks table

  @selenium
  @appointment
  Scenario: Booking An Appointment
    # User Story: Create appointment block
    When a user clicks Appointment scheduling app from the home page
    Then the system loads the appointment scheduling page
    And a user clicks on Manage Provider Schedules app
    And the system loads Manage Provider Schedules page
    And a user fills in the details of the appointment block
    And a user clicks on the save button
    Then the system saves the appointment block
    #User Story: Book Appointment
    When a user clicks on the Schedule Appointment link from the Patient dashboard
    Then the system loads Manage Appointments page
    And a user searches appointment request
    And a user clicks on book appointment icon
    And a user clicks the Save button
    Then the system saves the scheduled appointment
    #User Story: Delete Booked Appointment
    When a user clicks Appointment scheduling app from the home page
    Then the system loads the appointment scheduling page
    And a user clicks on the Manage Provider Schedules app
    And the system loads Appointment Blocks page
    And a user clicks on an appointment block
    And a user clicks on Delete appointment block
    Then the system deletes appointment block from the appointment Blocks table
