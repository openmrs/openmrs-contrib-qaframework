import uuid from 'uuid'

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
    const apiUrl = Cypress.env('API_BASE_URL');
    const username = Cypress.env('ADMIN_USERNAME');
    const password = Cypress.env('ADMIN_PASSWORD');
    const location = Cypress.env('DEFAULT_LOCATION_UUID');
    const token = window.btoa(`${username}:${password}`);
    cy.request({
        method: 'POST',
        url: `${apiUrl}/session`,
        body: {sessionLocation: location},
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${token}`,
        },
    })
});
