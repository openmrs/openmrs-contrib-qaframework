Feature: Clinical Visit Management

  Background:
    Given a user clicks on active visits link from home page
    When a user selects a patient from active patient list
    Then the system loads Patient dashboard page

  @selenium
  @clinicalVisit
  Scenario: Clinical Visit  
    # User story: Complete visit note
    When a user clicks visit note link from the patient dashboard
    Then the system loads visit note page
    When a user fills the visit note
    And a user clicks on save visit note button
    Then the system adds the note into visit note table
    
    # User story: Add known allergies
    When a user clicks on Allergies link from Patient dashboard page
    Then the system loads Allergies board page
    When a user clicks Add Known Allergy button
    And a user clicks on save allergy button
    Then the system adds known allergies into the allergies table
          
    # User story: Add known conditions
    When a user clicks on Conditions link from Patient dashboard
    Then the system loads Manage Conditions Page
    When a user clicks on Add new condition
    And a user enters patient condition
    And a user clicks on save condition button
    Then the system adds New Condition in Conditions table
     
    # User story: Book an appointment
    When a user clicks on Request appointment link from Patient dashboard
    Then the system loads Request appointment page
    When a user fills the Request appointment form
    And a user clicks on save appointment button
    Then the system adds Appointment request into the appointment table 
    
    # User story: End patient visit
    When a user clicks on recent visit link
    Then the system loads recent visit page
    When a user clicks on end visit button
    Then the system ends the patient visit
  
  Scenario: Clinical Visit for uploading supporting file
    # User story: Attach supporting document
    When a user clicks on Attachments link from patient visits dashboard
    Then the system loads Attachments page
    When a user attaches patient supporting file
    And a user clicks the upload file button
    Then the system uploads the file
    