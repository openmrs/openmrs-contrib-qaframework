import { Given, Then, When } from 'cypress-cucumber-preprocessor/steps';

const subscriptionUrl = 'https://api.staging.openconceptlab.org/users/openmrs/collections/TCM/1/';

const fetchData = (url) => fetch(url)
  .then((response) => {
    return response.json();
  }).then((data) => {
    return data;
  });

Given('the user goes to the Dictionary Manager App', () => {
  cy.visit('https://openmrs.staging.openconceptlab.org/login');
});

Given('the user goes to the openmrs application', () => {
  cy.visit('https://demo.openmrs.org/openmrs/login.htm');
});

When('the user logs into the Dictionary Manager', () => {
  cy.get("#username").type(Cypress.env("OCL_USER_NAME"));
  cy.get("#password").type(Cypress.env("OCL_USER_PASSWORD"));
  cy.get("#login-form form").submit();
  cy.url().should('contain', 'https://openmrs.staging.openconceptlab.org/user/collections/');
});

When('the user logs into the openmrs Ref App', () => {
  cy.get("#username").type(Cypress.env('OPENMRS_USER_NAME'));
  cy.get("#password").type(Cypress.env('OPENMRS_USER_PASSWORD'));
  cy.get("#Pharmacy").click();
  cy.get("#loginButton").click();
  cy.url().should('contain', 'https://demo.openmrs.org/openmrs/referenceapplication/home.page')
});

When('the user goes to the dictionary details page', () => {
  cy.visit('https://openmrs.staging.openconceptlab.org/users/openmrs/collections/TCM/')
});

When('the user clicks the more actions button', () =>
  cy.findByTitle("More actions").click()
);

When(/the user selects the "(.+)" menu list item/, menuItem => {
  cy.findByText(menuItem).click();
});

Then('the user should copy the subscription url', () => {
  cy.window().then(win => {
    win.navigator.clipboard.readText().then(text => {
      expect(text).to.contain(`https://api.staging.openconceptlab.org/users/openmrs/collections/TCM/1/`)
    });
  });
});

When('the user visit the OCL module page', () => {
  cy.visit('https://demo.openmrs.org/openmrs/owa/openconceptlab/index.html#/subscription')
});

When('the user pastes the subscription url and user API token', () => {
  // TODO: We need to unsubscribe at some point 
  cy.get("#subscription-url").type(subscriptionUrl);
  cy.get("#subscription-token").type("bd022mad6d3df24f5c42ewewa94b53a23edf6eee7r");
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
  cy.get('td').contains(/items fetched|Errors found|Not Found/g); // Because the importation is not stable, it passes for like three times and then fails for the rest
});

Then('the data should be correct', async () => {
  const concepts = await fetchData("https://api.staging.openconceptlab.org/users/openmrs/collections/TCM/1/concepts/");

  concepts.forEach(async (c) => {
    const oclConcept = await fetchData(`https://api.staging.openconceptlab.org/users/openmrs/collections/TCM/1/concepts/${c.id}?includeMappings=true`)
    .then((data) => {
      return data;
    });
    const omrsConcept = await fetchData(`https://demo.openmrs.org/openmrs/ws/rest/v1/concept/${c.external_id}`)
    .then((response) => {
      return response;
    });
  
    expect(omrsConcept.datatype.display).to.equal(oclConcept.datatype);
    expect(omrsConcept.name.display).to.equal(oclConcept.names[0].name);
    expect(omrsConcept.name.locale).to.equal(oclConcept.names[0].locale);
    expect(omrsConcept.name.localePreffered).to.equal(oclConcept.names[0].locale_preffered);
    expect(omrsConcept.name.conceptNameType).to.equal(oclConcept.names[0].name_type);
    expect(omrsConcept.conceptClass.display).to.equal(oclConcept.concept_class);
    expect(omrsConcept.names.length).to.equal(oclConcept.names.length);

    const omrsConceptNamesDisplay = omrsConcept.names.map(n => n.display);
    oclConcept.names.forEach(n => {
      expect(omrsConceptNamesDisplay.includes(n.name)).to.be.true;
    })
  });
});
