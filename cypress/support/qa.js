
Cypress.Commands.add('visitUrl', (url) => {
    cy.task('getProperty', 'webapp.url').then((rootUrl) => {
        cy.visit(rootUrl + url);
    });
});

Cypress.Commands.add('typeInPropertyIntoElement', (prop, element) => {
    cy.task('getProperty', prop).then((value) => {
        cy.get(element).type(value);
    });
});

Cypress.Commands.add('clickOnPropertyAsId', (prop) => {
    cy.task('getProperty', prop).then((value) => {
        cy.get('#' + value).click();
    });
});