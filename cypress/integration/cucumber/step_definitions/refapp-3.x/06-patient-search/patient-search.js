import {Given, When, Before, After} from 'cypress-cucumber-preprocessor/steps';

let identifier = null;
let patient = null;
let patientName = `Patient${Math.random().toString(36).substring(2,7)}`;

Before({tags: '@patient-search'}, () => {
    cy.generateIdentifier().then((generatedIdentifier) => {
        identifier = generatedIdentifier;
        cy.createPatient(identifier, patientName).then((generatedPatient) => {
            patient = generatedPatient;
            cy.startFacilityVisit(patient.uuid);
            cy.generateLabResults(patient.uuid);
        });
    });
});

Given('the user login to the Registration Desk', () => {
    cy.on('uncaught:exception', (err, runnable) => {
    	console.log(err);
    	return false;
    });
    cy.login();
    cy.visit('home');
})

When('the user searches for a patient by name', () => {
    cy.log(`Searching for ${patientName}`);
    cy.get('button[name=SearchPatientIcon]').click();
    cy.getByPlaceholder('Search for a patient by name or identifier number').type(patientName);
    // Adding an artificial wait because typed texts takes some time to get updated
    cy.wait(1000);
    cy.getByPlaceholder('Search for a patient by name or identifier number').type('{enter}');
})

Then('the result should be {string}', result => {
    cy.contains(result);
});

After({tags: '@patient-search'}, () => {
    cy.deletePatient(patient.uuid);
});
