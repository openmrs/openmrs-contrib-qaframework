import { Given, Then, When } from 'cypress-cucumber-preprocessor/steps';

const userApiToken = 'e6415c890158e06c36f7f52eca335bdf7204d50f';
const sUrl = 'https://api.staging.openconceptlab.org/users/openmrs/collections/hdz/1/';

Given('the user goes to the Dictionary Manager App', () => {
  cy.on('uncaught:exception', (err, runnable) => {
    console.log(err);
    return false;
  });
  cy.visit('https://openmrs.staging.openconceptlab.org/login');
});

Given('the user goes to the openmrs application', () => {
  cy.on('uncaught:exception', (err, runnable) => {
    console.log(err);
    return false;
  });
  cy.visit('https://qa-refapp.openmrs.org/openmrs/login.htm');
});

When('the user logs into the Dictionary Manager', () => {
  cy.get("#username").type('openmrs');
  cy.get("#password").type('Openmrs123');
  cy.get("#login-form form").submit();
  cy.url().should('contain', 'https://openmrs.staging.openconceptlab.org/user/collections/');
});

When('the user logs into the openmrs Ref App', () => {
  cy.get("#username").type('admin');
  cy.get("#password").type('Admin123');
  cy.get("#Pharmacy").click();
  cy.get("#loginButton").click();

});

When('the user goes to the dictionary details page', () => {
  cy.visit('https://openmrs.staging.openconceptlab.org/users/openmrs/collections/hdz/')
});

When('the user clicks the more actions button', () =>
  cy.findByTitle("More actions").click()
);

When(/the user selects the "(.+)" menu list item/, menuItem => {
  cy.findByText(menuItem).click();
});

Then('the user should copy the subscription url', () => {
  const getClipBoardText = () => cy.window().then(win => {
    win.navigator.clipboard.readText().then(text => text);
  });
  // TODO: Need to find a way to save the copied text somewhere
});

When('the user visit the OCL module page', () => {
  cy.visit('https://qa-refapp.openmrs.org/openmrs/owa/openconceptlab/index.html#/subscription')
});

When('the user pastes the subscription url and user API token', () => {
  cy.window().its('navigator.clipboard')
    .invoke('readText').then(text => console.log(text, 'text'));
  // TODO: I need to access the text here instead of hardcoding it
  cy.get("#subscription-url").type(sUrl);
  cy.get("#subscription-token").type(userApiToken);
});

When('the user saves changes', () => {
  cy.findByText('Save changes').click();
});

Then('the user should see the import button', () => {
  cy.findByText('Import from subscription server').should("be.visible")
});

When('the user clicks the import button', () => {
  cy.findByText('Import from subscription server').click();
});

Then('the dictionary should be imported', () => {
  cy.findByText('Items selected').should('be.visible'); // Currently failing because the app has some error
});
