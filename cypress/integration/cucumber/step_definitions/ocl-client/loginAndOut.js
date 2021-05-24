import {Given, When, Then} from "cypress-cucumber-preprocessor/steps";

beforeEach(() => {
  cy.initiateExceptionsLogger();
});

afterEach(() => {
  cy.end();
});

Given("User visits user collection page", async () => {
  cy.visitUrl("/user/collections/");
});

Given("User should access login", async () => {
  cy.findByText("Log In to Open Concept Lab").should("exist");
});

When("User logins in", async () => {
  cy.login();
});

When("User re-visits user collection page", async () => {
  cy.visitUrl("/user/collections/");
});

Then("User should access dictionaries", async () => {
   cy.findByText("Your Dictionaries").should("exist");
   cy.url().should('eq', 'http://localhost:8080/user/collections/');
});

When("User logs out", async () => {
   cy.logout();
});

Then("User should go back to login page", async () => {
  cy.findByText("Log In to Open Concept Lab").should("exist");
});