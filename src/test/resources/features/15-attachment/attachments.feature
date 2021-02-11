 Feature: Attachments Management

   Background:
     Given a user logs in, searches a patient and visits Attachment page 
     And a user clicks on Attachment link
     Then the system loads Attachment page
     And a user attaches a file
     
   Scenario: Uploading Attachment
     And a user enters a caption
     Then the system enables upload button
     When a user clicks the upload link button 
     Then the system uploads the attached file
     And close browser
     
   Scenario: Clearing forms
     And a user clicks clear forms button
     Then the system clears the form
     And close browser 