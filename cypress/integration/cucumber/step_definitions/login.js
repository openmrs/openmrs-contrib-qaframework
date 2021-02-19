import {Given, When, Then} from "cypress-cucumber-preprocessor/steps";

beforeEach(() => {
  cy.initiateExceptionsLogger();
});

afterEach(() => {
  cy.end();
});

Given('User visits login page', () => {
  cy.visitUrl('/login.htm');
});

When('User enters {string} username', (username) => {
    if('setupUser' == username) {
        cy.typeInPropertyIntoElement('login.username', '#username');
    } else {
        cy.get('#username').type(username);
    }
})

And('User enters {string} password', (password) => {
    if('setupPass' == password) {
        cy.typeInPropertyIntoElement('login.password', '#password');
    } else {
        cy.get('#password').type(password);
    }
})

And('User Selects {string} Login Location', (location) => {
    if('firstLocation' == location) {
        cy.get('#sessionLocation li:first').click();
    } else if('noLocation' == location) {
        // cy.contains('#loginButton').click();
    } else if('setupLocation' == location) {
        cy.clickOnPropertyAsId('login.location');
    } else {
        cy.get('#' + location).click();
    }
})

And('User Logs in', () => {
    cy.get('#loginButton').click();
})

Then('System Evaluates Login {string}', (status) => {
    if ('true' == status) {
    	cy.get('.logout').should('exist');
    } else if ('false' == status) {
        cy.get('#loginButton').should('exist');
    }
})
