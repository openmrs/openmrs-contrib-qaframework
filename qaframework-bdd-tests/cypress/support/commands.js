import uuid from 'uuid';
import 'cypress-file-upload';

const API_BASE_URL = Cypress.env('API_BASE_URL');
const ADMIN_USERNAME = Cypress.env('ADMIN_USERNAME');
const ADMIN_PASSWORD = Cypress.env('ADMIN_PASSWORD');
const DEFAULT_LOCATION_UUID = Cypress.env('DEFAULT_LOCATION_UUID');
const TOKEN = window.btoa(`${ADMIN_USERNAME}:${ADMIN_PASSWORD}`);

export const OCL_USER_NAME = Cypress.env('OCL_USER_NAME');
export const OCL_USER_PASSWORD = Cypress.env('OCL_USER_PASSWORD');
export const OPENMRS_USER_NAME = Cypress.env('OPENMRS_USER_NAME');
export const OPENMRS_USER_PASSWORD = Cypress.env('OPENMRS_USER_PASSWORD');
export const OCL_USER_TOKEN = Cypress.env('OCL_USER_TOKEN');

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

Cypress.Commands.add('generateIdentifier', () => {
    cy.request({
        method: 'POST',
        url: `${API_BASE_URL}/idgen/identifiersource/8549f706-7e85-4c1d-9424-217d50a2988b/identifier`,
        body: {},
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${TOKEN}`,
        },
    }).then((response) => {
       cy.wrap(response.body['identifier']);
    });
});

Cypress.Commands.add('generateLabResults', (uuid) => {
    const labResults = {
        patient: uuid,
        encounterDatetime: "2012-02-05T04:03:02",
        location: '6351fcf4-e311-4a19-90f9-35667d99a8af',
        encounterProviders: [
          {
            provider: "1e38d425-7e1b-4ee9-9f60-da80bce21699",
            encounterRole: "240b26f9-dd88-4172-823d-4a8bfeb7841f"
          }
        ],
        encounterType: "ca3aed11-1aa4-42a1-b85c-8332fc8001fc",
        obs: [
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1731,
            obsDatetime: "2012-02-05T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1631,
            obsDatetime: "2012-04-29T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1572,
            obsDatetime: "2012-07-22T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1257,
            obsDatetime: "2012-10-14T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1114,
            obsDatetime: "2013-01-06T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1314,
            obsDatetime: "2013-03-31T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1631,
            obsDatetime: "2013-06-23T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1830,
            obsDatetime: "2013-09-15T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1460,
            obsDatetime: "2013-12-08T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 1601,
            obsDatetime: "2014-03-02T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 918,
            obsDatetime: "2014-05-25T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 693,
            obsDatetime: "2014-08-17T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 627,
            obsDatetime: "2014-11-09T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 562,
            obsDatetime: "2015-02-01T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 386,
            obsDatetime: "2015-04-26T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 260,
            obsDatetime: "2015-07-19T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 218,
            obsDatetime: "2015-10-11T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 173,
            obsDatetime: "2016-01-03T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 147,
            obsDatetime: "2016-03-27T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 150,
            obsDatetime: "2016-06-19T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 146,
            obsDatetime: "2016-09-11T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 83,
            obsDatetime: "2016-12-04T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 94,
            obsDatetime: "2017-02-26T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 86,
            obsDatetime: "2017-05-21T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 74,
            obsDatetime: "2017-08-13T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 100,
            obsDatetime: "2017-11-05T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 94,
            obsDatetime: "2018-01-28T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 93,
            obsDatetime: "2018-04-22T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 67,
            obsDatetime: "2018-07-15T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 67,
            obsDatetime: "2018-10-07T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 73,
            obsDatetime: "2018-12-30T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 37,
            obsDatetime: "2019-03-24T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 42,
            obsDatetime: "2019-06-16T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 42,
            obsDatetime: "2019-09-08T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 47,
            obsDatetime: "2019-12-01T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 44,
            obsDatetime: "2020-02-23T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 36,
            obsDatetime: "2020-05-17T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 32,
            obsDatetime: "2020-08-09T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 26,
            obsDatetime: "2020-11-01T04:03:02"
          },
          {
            concept: "161481AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            value: 20,
            obsDatetime: "2021-01-24T04:03:02"
          }
        ]
    }
    
    cy.request({
        method: 'POST',
        url: `${API_BASE_URL}/encounter`,
        body: labResults,
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${TOKEN}`,
        },
    }).then((resp) => {
        cy.wrap(resp.body);
    });
})

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

