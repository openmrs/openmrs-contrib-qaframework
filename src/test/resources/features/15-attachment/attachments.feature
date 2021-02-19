 Feature: Attachments Management

   Background:
     Given a user logs in, searches a patient and visits Attachment page 
     And a user clicks on Attachment link
     Then the system loads Attachment page 
     
   Scenario: Uploading Attachment
     And a user attaches a file
     And a user enters a caption
     Then the system enables upload button
     When a user clicks the upload button 
     Then the system uploads the attached file
     
   Scenario: Removing un intended Attachement 
     And a user attaches a file or enters a caption
     And a user clicks clear forms button
     Then the system clears the form 
     
   Scenario: Capturing an image
     And a user clicks in camera icon
     Then the system enables capturing the photo
     And a user focuses in the camera
     And a user clicks save button
     Then the system saves the captured photo