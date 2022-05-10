import {Given, Then, And, When} from 'cypress-cucumber-preprocessor/steps';

const FHIR_API_BASE_URL = Cypress.env('FHIR_API_BASE_URL');

Given('the user login to the Registration Desk', () => {    
  cy.login();
  cy.visit('home');
});

When('GET - single patient domain by {string}', uuid => {
  const patientUrl = FHIR_API_BASE_URL + '/Patient/' + uuid;
  cy.request({
    method: 'GET',
    url: patientUrl,
    headers: {
      'Content-Type': 'application/json'  
    },
    failOnStatusCode:false
  }).as('patient');
});

Then('verify response status code is {int}', code => {
  cy.get('@patient').then(patient => {
    expect(patient.status).to.eq(code);
  });
});

And('patient has {string} and {string}', (identifier, name)  => {
  cy.get('@patient').then(patient => {
    assert.isObject(patient.body, 'Patient Response is an Object');
    assert.equal(patient.body.identifier[0].id, identifier);
    assert.equal(patient.body.name[0].id, name);
  });
});