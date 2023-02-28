Feature: Clinical Visit Management

  Background:
    Given a user clicks on active visits link from home page
    When a user selects a patient from active patient list
    Then the system loads Patient dashboard page

  @selenium
  @clinicalVisit
  Scenario: Clinical Visit  
    # User story: Add known allergies
    When a user clicks on Allergies link from Patient dashboard page
    Then the system loads Allergies board page
    And a user clicks Add Known Allergy for the first allergy
    And a user clicks Add Known Allergy for the second allergy
    And the user selects an allergy
    And a user clicks on save allergy button
    Then the system adds known allergies into the allergies table
    And a user clicks on the delete Allergy
    And a user confirms delete action
    Then the system deletes an allergy from the allergy table   
    # User story: Add known conditions
    When a user clicks on Conditions link from Patient dashboard
    Then the system loads Manage Conditions Page
    And a user clicks on Add new condition
    And a user enters first patient condition
    And a user enters second patient condition
    And a user clicks on save condition button
    Then the system adds New Condition in Conditions table
    And a user clicks on the delete button from dashboard
    Then the system deletes a condition from the conditions table
    # User story: Book an appointment
    When a user clicks on Request appointment link from Patient dashboard
    Then the system loads Request appointment page
    When a user fills the Request appointment form
    And a user clicks on save appointment button
    Then the system adds Appointment request into the appointment table 
    # User story: Complete visit note
    When a user clicks visit note link from the patient dashboard
    Then the system loads visit note page
    When a user fills the visit note
    Then the system displays diagnosis cards
    When the user clicks on save visit note button
    Then the system adds the note into visit note table
    # User story: Attach supporting document
    When a user clicks on Attachments link from patient visits dashboard
    Then the system loads Attachments page
    And a user attaches patient supporting file
    And a user clicks the upload file button
    Then the system uploads the file
    # User story: Use of clear forms button while attaching document
    When a user clicks on Attachments link from patient visits dashboard
    Then the system loads Attachments page
    And a user attaches patient supporting file
    And a user clicks on clear forms button
    Then the system drops the supporting file
    # User story: End patient visit
    When a user clicks on end visit button
    Then the system ends the patient visit
