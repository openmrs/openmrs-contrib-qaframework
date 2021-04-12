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

And('User clicks {string}', (text) => cy.get('.iw0iHcFjY--gDZi9jUQ1_').click());

And('User enters Password {string}', (password) => {
  cy.get('.NGQQUK5YhTAwqMeeVQW5R').type(password);
});

And('User {string}', (text) => cy.get('.iw0iHcFjY--gDZi9jUQ1_').click());

And('User clicks {string}', (text) =>
  cy.get('.bx--radio-button__appearance').click()
);

And('User clicks {string}', (text) => cy.get('.bx--btn').click());

Then('User should arrive at {string}', (url) => {
  cy.visit(url);
});
