import { Given, When, Then, And } from 'cypress-cucumber-preprocessor/steps';

/// <reference types="Cypress" />

beforeEach(() => {
  cy.initiateExceptionsLogger();
});

afterEach(() => {
  cy.end();
});

Given('User opens the website at {string}', (url) => {
  cy.visit(url);
});

When('User enters username {string}', (username) => {
  cy.get('#username').type(username);
});

And('User clicks {string}', (text) => cy.get('#continueButton').click());

And('User enters Password {string}', (password) => {
  cy.get('#password').type(password);
});

And('User {string}', (text) => cy.get('#submitButton').click());

And('User clicks {string}', (text) => cy.get('RadioButton').first().click());

And('User clicks {string}', (text) => cy.get('#confirmLocation').click());

Then('User should arrive at {string}', (url) => {
  cy.url().should('contain', url);
});
