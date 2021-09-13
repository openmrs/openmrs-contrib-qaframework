import {And, Before, Given, Then} from 'cypress-cucumber-preprocessor/steps';

let identifier = null;
let patient = null;

Before({tags: '@vitalsAndTriage'}, () => {
    cy.generateIdentifier().then((generatedIdentifier) => {
        identifier = generatedIdentifier;
        cy.createPatient(identifier).then((generatedPatient) => {
            patient = generatedPatient;
            cy.startFacilityVisit(patient.uuid);
        });
    });

});

Given('the user logs  to the Registration Desk', () => {
    cy.login();
    cy.visit('home');
})

Given('the user arrives on a patient’s chart', () => {
    cy.visit(`patient/${patient_uuid}/chart`);
});


