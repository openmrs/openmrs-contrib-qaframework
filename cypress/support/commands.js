import uuid from 'uuid';

const API_BASE_URL = Cypress.env('API_BASE_URL');
const ADMIN_USERNAME = Cypress.env('ADMIN_USERNAME');
const ADMIN_PASSWORD = Cypress.env('ADMIN_PASSWORD');
const DEFAULT_LOCATION_UUID = Cypress.env('DEFAULT_LOCATION_UUID');
const TOKEN = window.btoa(`${ADMIN_USERNAME}:${ADMIN_PASSWORD}`);

Cypress.Commands.add('runAndAwait', (callable, method='GET', addArtificialWait=false) => {
    const requestId = `apiRequest-${uuid()}`;

    cy.server();
    cy.route(method, '**').as(requestId);  // start recording requests
    callable();
    cy.wait(`@${requestId}`);
    cy.route(method, '**').as('untrackedRequest'); // stop recording requests

    // disable eslint warning because we're intentionally waiting
    // eslint-disable-next-line cypress/no-unnecessary-waiting
    if (addArtificialWait) cy.wait(8000);
  });

Cypress.Commands.add('initiateExceptionsLogger', () => {
    Cypress.on('uncaught:exception', (err, runnable) => {
    	console.log(err);
    	return false;
    });
});

Cypress.Commands.add('getByLabel', (label) => {
    cy.contains('label', label)
        .invoke('attr', 'for')
        .then((id) => {
            cy.get('#' + id)
        })
})

Cypress.Commands.add('getByPlaceholder', (placeholder) => {
    cy.get(`input[placeholder="${placeholder}"]`)
})

Cypress.Commands.add('login', () => {
    cy.request({
        method: 'POST',
        url: `${API_BASE_URL}/session`,
        body: {sessionLocation: DEFAULT_LOCATION_UUID},
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${TOKEN}`,
        },
    });
});

Cypress.Commands.add('createPatient', ()=>{
    const patient = {
        person: {
            names: [
                {
                    preferred: true,
                    givenName: 'John',
                    middleName: '',
                    familyName: 'Doe'
                }

            ],
            gender: 'M',
            birthdate: '1997-09-12T18:00:00.000Z',
            birthdateEstimated: false,
            attributes: [],
            addresses: [
                {
                    postalCode: '0100',
                    address2: '',
                    address1: 'Haha',
                    country: 'Sri Lanka',
                    stateProvince: 'Western',
                    cityVillage: 'Colombo 5'
                }
            ],
            dead: false
        },
        identifiers: [
            {
                identifier: null,
                identifierType: '05a29f94-c0ed-11e2-94be-8c13b969e334',
                location: '6351fcf4-e311-4a19-90f9-35667d99a8af',
                preferred: true
            }
        ]
    };

    cy.request({
        method: 'POST',
        url: `${API_BASE_URL}/idgen/identifiersource/691eed12-c0f1-11e2-94be-8c13b969e334/identifier`,
        body: {},
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${TOKEN}`,
        },
    }).then((response) => {
        patient.identifiers[0].identifier = response.body["identifier"];
        cy.request({
            method: 'POST',
            url: `${API_BASE_URL}/patient/`,
            body: patient,
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Basic ${TOKEN}`,
            },
        }).then((resp)=>{
            cy.wrap(resp.body);
        })
    });
});

Cypress.Commands.add('deletePatient', (uuid) => {
    cy.request({
        method: 'DELETE',
        url: `${API_BASE_URL}/patient/${uuid}?purge=true`,
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${TOKEN}`,
        },
    });
});

