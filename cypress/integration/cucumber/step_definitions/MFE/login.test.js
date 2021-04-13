import { Given, When, Then, And } from 'cypress-cucumber-preprocessor/steps';

/// <reference types="Cypress" />

beforeEach(() => {
  cy.initiateExceptionsLogger();
});

afterEach(() => {
  cy.end();
});

Given('User opens the websites login page', () => {
  cy.visit('/');
});

When('User enters username {string}', (username) => {
  cy.get('#username').type(username);
});

And('User clicks continue', () => cy.get('.iw0iHcFjY--gDZi9jUQ1_').click());

And('User enters Password {string}', (password) => {
  cy.get('.NGQQUK5YhTAwqMeeVQW5R').type(password);
});

And('User Log in', () => cy.get('.iw0iHcFjY--gDZi9jUQ1_').click());

And('User selects unknown location', () =>
  cy.get('.bx--radio-button__appearance').first().click()
);

And('User clicks confirm', () => cy.get('.bx--btn').click());

Then('User should arrive at home page', () => {
  cy.visit('/home');
});
