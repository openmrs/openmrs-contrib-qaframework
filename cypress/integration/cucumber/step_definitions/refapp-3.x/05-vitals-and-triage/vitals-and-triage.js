import { Given, Before, When } from "cypress-cucumber-preprocessor/steps";

let identifier = null;
let patient = null;

Before({ tags: "@vitals-and-triage" }, () => {
  cy.generateIdentifier().then((generatedIdentifier) => {
    identifier = generatedIdentifier;
    cy.createPatient(identifier).then((generatedPatient) => {
      patient = generatedPatient;
      cy.startFacilityVisit(patient.uuid);
    });
  });
});

Given("the user is logged in", () => {
  cy.on("uncaught:exception", (err, runnable) => {
    console.log(err);
    return false;
  });
  cy.login();
});

Given("the user arrives on a patient’s summary page", () => {
  cy.visit(`patient/${patient.uuid}/chart`);
});

When("the user clicks on Edit patient details", () => {
  cy.contains("Actions").click({ force: true });
  cy.contains("Edit patient details").click({ force: true });
});

Then("the edit page should load", () => {
  cy.contains("Edit Patient");
});

When("the user updates the address", () => {
  cy.get("#address1").clear({ force: true }).type("45 baker's street");
  cy.get("#country").clear({ force: true }).type("Germany");
  cy.get("#stateProvince").clear({ force: true }).type("Central");
  cy.get("#cityVillage").clear({ force: true }).type("Kandy");
  cy.get("#postalCode").clear({ force: true }).type("3000");
  cy.contains("Update Patient").click({ force: true });
});

Then("the address should be updated", () => {
  cy.contains("Update Patient").click({ force: true });
});

When("the user clicks on Record Vitals", () => {
  cy.contains("Record Vitals").click({ force: true });
});

Then("the Vitals form should load", () => {
  cy.contains("Record Vitals and Biometrics");
});

When("the user adds vitals", () => {
  // Todo: Use getByLabel instead of ids
  cy.get("#systolic").type("120", { force: true });
  cy.get("#diastolic").type("80", { force: true });
  cy.get("#Pulse").type("80", { force: true });
  cy.get("#Notes").type("This is a note", { force: true });
  cy.get("input[name='Oxygen Saturation']").type("90", { force: true });
  cy.get("input[name='Respiration Rate']").type("15", { force: true });
  cy.get("#Temperature").type("38", { force: true });
  cy.get("#Height").type("160", { force: true });
  cy.get("#Weight").type("60", { force: true });
  cy.get("#MUAC").type("100", { force: true });
});

When("the user adds abnormal vital signs", () => {
  cy.get("#systolic").clear({ force: true }).type("140", { force: true });
  cy.get("#diastolic").clear({ force: true }).type("80", { force: true });
});

Then("the abnormal vital signs should show up as red", () => {
  cy.get("#systolic").should("have.css", "color", "rgb(218, 30, 40)");
  cy.get("#diastolic").should("have.css", "color", "rgb(218, 30, 40)");
});

When("the user saves the form", () => {
  cy.contains("Sign & Save").click({ force: true });
  cy.reload();
});

Then("the vitals needs to be displayed on the Vitals table", () => {
  cy.contains("Last Recorded").click({ force: true });
  cy.contains("15");
  cy.contains("38");
  cy.contains("140 / 80");
  cy.contains("90");
  cy.contains("160");
  cy.contains("60");
  cy.contains("23.4");
});
