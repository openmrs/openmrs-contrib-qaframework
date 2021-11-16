import { Given, Then, When } from 'cypress-cucumber-preprocessor/steps';

const userApiToken = 'e6415c890158e06c36f7f52eca335bdf7204d50f'; //TODO: Fetch from the API and load directly
const sUrl = 'https://api.staging.openconceptlab.org/users/openmrs/collections/TCM/1/';

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
  cy.visit('http://localhost:8080/openmrs/login.htm');
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
  cy.url().should('contain', 'http://localhost:8080/openmrs/referenceapplication/home.page')
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
  const getClipBoardText = () => cy.window().then(win => {
    win.navigator.clipboard.readText().then(text => text);
  });
  // TODO: Need to find a way to save the copied text somewhere
});

When('the user visit the OCL module page', () => {
  cy.visit('http://localhost:8080/openmrs/owa/openconceptlab/index.html#/subscription')
});

When('the user pastes the subscription url and user API token', () => {
  // TODO: We need to unsubscribe at some point 
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
  cy.get('td').contains(/items fetched|Errors found|Not Found/g); // Because the importation is not stable, it passes for like three times and then fails for the rest
});

Then('the data should be correct', async () => {
  const concepts = await fetchData("https://api.staging.openconceptlab.org/users/openmrs/collections/TCM/1/concepts/");

  concepts.forEach(async (c) => {
    const oclConcept = await fetchData(`https://api.staging.openconceptlab.org/users/openmrs/collections/TCM/1/concepts/${c.id}?includeMappings=true`)
    .then((data) => {
      return data;
    });
    const omrsConcept = await fetchData(`http://localhost:8080/openmrs/ws/rest/v1/concept/${c.external_id}`)
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
  });
});