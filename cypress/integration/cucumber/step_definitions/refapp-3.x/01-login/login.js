import {Given} from 'cypress-cucumber-preprocessor/steps';

Given('user arrives at the login page', () => {
    cy.task('getProperty', 'webapp.url').then(baseUrl => {
        cy.visit(`${baseUrl}/login`);
    });
})

When('the user logs in with {string} and {string}', (username, password) => {
    cy.get('#username').type(username);
    cy.contains('Continue').click();
    cy.get('#password').type(password);
    cy.contains('Log in').click();
    cy.wait(3000);
})

When('the user selects location {string}', (location) => {
    cy.contains(location).click();
    cy.get('button[type="submit"]').click({force: true});
})

Then('the user should arrive at the home page', () => {
    cy.url().should('include', '/home');
});
