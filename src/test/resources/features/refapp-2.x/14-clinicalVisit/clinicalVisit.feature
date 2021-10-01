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

  @selenium
  @clinicalVisit
  Scenario: Patient Dashboard adding Allergy
    # User story: Adding New Allergy
    When user clicks on Allergies link from Patient dashboard
    Then system loads Allergies table page
    When a user clicks Add New Allergy button
    And the user clicks on the Save button
    Then the system adds a new Allergy

  @selenium
  @clinicalVisit
  Scenario: Patient Dashboard adding condition
    # User story: Add New Condition
    When a user clicks on Conditions on Patient dashboard
    Then system loads Conditions Page
    When user clicks on Add new condition button
    And user enters patient condition
    And user clicks on save button
    Then system adds New Condition in Conditions table

  @selenium
  @clinicalVisit
  Scenario: Patient Dashboard adding Allergy2
    # User story: Adding and deleting New Allergy2
    When a user clicks on Allergy2 link from Patient dashboard page2
    Then the system loads Allergy2 board page
    When a user clicks Add New Allergy2 button
    And the user clicks on the Save button2
    Then the system adds a new Allergy2
    When the user clicks on the delete button from the patient dashboard
    And the system loads Remove Allergy dashboard
    Then system displays no allergy in the Allergies table

  @selenium
  @clinicalVisit
  Scenario: Patient Dashboard adding Condition2
    # User story: Adding and deleting New Condition2
    When a user clicks on Condition2 link from Patient dashboard page
    Then the system loads Condition2 board page
    When a user clicks Add New Condition2 button
    And the system loads Add New Condition2 dashboard
    And user clicks on the Save button2
    Then the system adds a new Condition2
    When the user clicks on the delete button from dashboard
    Then user clicks on the yes button to confirm

