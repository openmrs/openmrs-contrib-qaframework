import {Given} from 'cypress-cucumber-preprocessor/steps';

Given('user arrives at the login page', () => {
    cy.visit('/login');
})

When('the user logs in with {string} and {string} to the {string}', (username, password, location) => {
    cy.getByLabel('Username').type(username)
    cy.contains('Continue').click({force: true});
    cy.getByLabel('Password').type(password)
    cy.contains('Login').click({force: true});
    // The user will be redirected to the location selection page if the credentials are correct.
    // The following artificial wait is to wait until it changes the url.
    cy.wait(2000);
    // If the url includes "/location" that means the user is on the location selection page. If so, pick the location.
    cy.url().then(($url) => {
        if($url.includes("/location")) {
            cy.contains(location).click({force: true});
            cy.contains('Confirm').click({force: true});
        }
    })
})

Then('the user should be {string} to login', (ability) => {
    switch (ability) {
        case "able":
            cy.url().should('include', '/home');
            break;
        case "unable":
            cy.contains("Incorrect username or password");
            break;
        default:
            throw new Error(`Ability '${ability}' is not supported`);
    }
});
