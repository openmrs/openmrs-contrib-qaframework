 Feature: Attachments Management

   Background:
     Given a user clicks on Attachments link from patient dashboard
     Then the system loads Attachments page 
    
   @selenium
   Scenario: Uploading Attachment
     And a user attaches a file
     And a user enters a caption
     Then the system enables upload button
     When a user clicks the upload button 
     Then the system uploads the attached file
   
   @selenium
   Scenario: Removing an unintended Attachments 
     And a user attaches a file or enters a caption
     And a user clicks clear forms button
     Then the system clears the form 
    
   @selenium 
   Scenario: Capturing user image
     And a user clicks on camera icon
     Then the system enables capturing user image
     And a user clicks on save button
     And a user enters a caption
     And a user clicks upload button
     Then the system uploads the captured image