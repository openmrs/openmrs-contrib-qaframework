import uuid from 'uuid'

const apiUrl = Cypress.env('API_URL') || 'https://api.staging.openconceptlab.org';

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

Cypress.Commands.add('login', (
  username = Cypress.env('USERNAME') || 'haddy315',
  password = Cypress.env('PASSWORD') || 'mamanamutebi315') => {
  cy.get('#username').type(username);
  cy.get('#password').type(password);
  cy.get('button[type="submit"]').click();
  const token = Cypress.env('TOKEN');

  const store = window.store;

  if (store.getState().auth.profile?.username === username &&
    store.getState().auth.token) {
    return;
    }

  if (!token) {
    cy.request({
      method: 'POST',
      url: `${apiUrl}/users/login/`,
      body: {
        "username": username,
        "password": password
      }
    }).then((response) => {
      store
        .dispatch({ type: 'LOGIN_ACTION', payload: { token: response.body['token'] } });
    });
  } else {
    store.dispatch({ type: 'LOGIN_ACTION', payload: { token: token} });
  }
});

Cypress.Commands.add('logout', () => {
  cy.get('div.MuiListItem-button').click();
  cy.get('button.MuiButton-textSecondary').click();
});
