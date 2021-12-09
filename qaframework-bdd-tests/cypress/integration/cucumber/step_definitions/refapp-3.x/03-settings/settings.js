import {Given, After, Before} from 'cypress-cucumber-preprocessor/steps';

let patient_uuid = null;

Before({tags: '@patient-involved'}, () => {
    cy.createPatient().then((user) => {
        patient_uuid = user.uuid;
    });
});

Given('the user is logged in', () => { 
    cy.on('uncaught:exception', (err, runnable) => {
    	console.log(err);
    	return false;
    });
    cy.login();
})

Given('the user arrives on a patient’s chart', () => {
    cy.visit(`patient/${patient_uuid}/chart`);
});

When('the user navigate back to home by clicking Home breadcrumb', () => {
    cy.get('.breadcrumbs-container').contains('Home').click({force: true});
});

When('the user can change the locale to Spanish', () => {
    cy.get('button[name="Users"]').click({force: true});
    cy.getByLabel('Select locale').select('es', {force: true});
});

Then('the text should change into spanish', () => {
    cy.get('button[name="SearchPatientIcon"]').click({force: true});
    cy.getByPlaceholder('Buscar un paciente');
});

When('the user clicks on the users icon', () => {
    cy.get('button[name="Users"]').click();
});

Then('the current location should be there', () => {
    const defaultLocation = Cypress.env('DEFAULT_LOCATION_NAME');
    cy.get('div[data-extension-id=location-changer]').contains(defaultLocation);
});

When('the user change the location to {string}', (location) => {
    cy.get('div[data-extension-id=location-changer]').contains('Change').click({force: true})
    cy.contains(location).click({force: true});
    cy.contains('Confirm').click({force: true});
});

Then('the user should be on the patient’s chart', () => {
    cy.url().should('include', `patient/${patient_uuid}/chart`);
});

Then('the current location should be {string}', (location) => {
    cy.get('button[name="Users"]').click();
    cy.get('div[data-extension-id=location-changer]').contains(location);
});

When('the user clicks on logout', () => {
    cy.contains('Logout').click({force: true});
});

Then('the user should be on the login page', () => {
    cy.url().should('include', 'login');
});

const changeLocale = (locale) => {
    const apiUrl = Cypress.env('API_BASE_URL');
    const username = Cypress.env('ADMIN_USERNAME');
    const uuid = Cypress.env('ADMIN_UUID');
    const password = Cypress.env('ADMIN_PASSWORD');
    const token = window.btoa(`${username}:${password}`);
    cy.request({
        method: 'POST',
        url: `${apiUrl}/user/${uuid}`,
        body: {
            userProperties: {
                defaultLocale: locale,
            }
        },
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${token}`,
        },
    });
};

After({tags: '@locale'}, () => {
    changeLocale('en');
});

After({tags: '@patient-involved'}, () => {
    cy.deletePatient(patient_uuid);
});
