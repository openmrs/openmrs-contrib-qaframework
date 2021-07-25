import uuid from 'uuid';
import 'cypress-file-upload';

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

Cypress.Commands.add('visitPage', (path) => {
    cy.task('getProperty', 'webapp.url').then(baseUrl => {
        cy.visit(`${baseUrl}${path}`);
    });
})

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

Cypress.Commands.add('generateIdentifier', () => {
    cy.request({
        method: 'POST',
        url: `${API_BASE_URL}/idgen/identifiersource/691eed12-c0f1-11e2-94be-8c13b969e334/identifier`,
        body: {},
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${TOKEN}`,
        },
    }).then((response) => {
       cy.wrap(response.body['identifier']);
    });
});

Cypress.Commands.add('createPatient', (identifier = null) => {
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
                identifier,
                identifierType: '05a29f94-c0ed-11e2-94be-8c13b969e334',
                location: '6351fcf4-e311-4a19-90f9-35667d99a8af',
                preferred: true
            }
        ]
    };

    if (identifier) {
        cy.request({
            method: 'POST',
            url: `${API_BASE_URL}/patient/`,
            body: patient,
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Basic ${TOKEN}`,
            },
        }).then((resp) => {
            cy.wrap(resp.body);
        });
    } else {
        cy.generateIdentifier().then((generatedIdentifier) => {
            patient.identifiers[0].identifier = generatedIdentifier;
            cy.request({
                method: 'POST',
                url: `${API_BASE_URL}/patient/`,
                body: patient,
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Basic ${TOKEN}`,
                },
            }).then((resp) => {
                cy.wrap(resp.body);
            });
        });
    }
});

Cypress.Commands.add('startFacilityVisit', (patientUuid) => {
    cy.request({
        method: 'POST',
        url: `${API_BASE_URL}/visit`,
        body: {
            patient: patientUuid,
            startDatetime: new Date().toISOString(),
            visitType: "7b0f5697-27e3-40c4-8bae-f4049abfb4ed",
            location: DEFAULT_LOCATION_UUID
        },
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${TOKEN}`,
        },
    });
})

Cypress.Commands.add('deletePatient', (uuid) => {
    cy.request({
        method: 'DELETE',
        url: `${API_BASE_URL}/patient/${uuid}`,
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${TOKEN}`,
        },
    });
});

