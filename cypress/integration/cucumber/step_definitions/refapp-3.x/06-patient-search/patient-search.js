import {Given} from 'cypress-cucumber-preprocessor/steps';

Given('the user login to the Registration Desk', () => {    
    cy.on('uncaught:exception', (err, runnable) => {
    	console.log(err);
    	return false;
    });
    cy.login();
    cy.visit('home');
})

When('the user search for {string}', patientName => {
    cy.get('button[name=SearchPatientIcon]').click();
    cy.getByPlaceholder('Search for a patient by name or identifier number').type(patientName);
    // Adding an artificial wait because it takes time to get typed text updates
    cy.wait(500);
    cy.contains('Search').click({force:true});
})

Then('the result should be {string}', result => {
    cy.contains(result);
});
