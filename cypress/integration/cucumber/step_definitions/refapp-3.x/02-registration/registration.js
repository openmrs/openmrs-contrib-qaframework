import {Given} from 'cypress-cucumber-preprocessor/steps';

Given('the user login to the Registration Desk', () => {
    cy.loginToLocation('Registration Desk')
})

When('the user search for {string}', patientName => {
    cy.get('button[name=SearchPatientIcon]').click();
    cy.get('input[role=searchbox]').type(patientName);
})

Then('the result should be {string}', result => {
    cy.contains(result);
});


When('the user clicks on the add patient icon', () => {
    cy.get('button[name=AddPatientIcon]').click();
})

When('the user enters {string} details for Andria Faiza', validity => {
    const details = {
        right: {
            givenName: 'Andria',
            middleName: 'Kumbha',
            familyName: 'Faiza',
            gender: 'female',
            birthday: 'January 3, 1970',
            address: '55372,test',
            cityVillage: 'Nairobi',
            stateProvince: 'Nairobi',
            country: 'Kenya',
            postalCode: '00100',
            phoneNumber: '+211 34 567890'
        },
        wrong: {
            givenName: null,
            middleName: 'Kumbha',
            familyName: 'Mwangi',
            gender: 'female',
            birthday: 'January 3, 1970',
            address: null,
            cityVillage: null,
            stateProvince: null,
            country: null,
            postalCode: null,
            phoneNumber: null
        },
        incomplete: {
            givenName: null,
            middleName: null,
            familyName: null,
            gender: 'male',
            birthday: null,
            address: null,
            cityVillage: null,
            stateProvince: null,
            country: null,
            postalCode: null,
            phoneNumber: null
        }
    };
    // Chek for a valid validity parameter
    if (!details.hasOwnProperty(validity)) {
        throw new Error(`Validity '${validity}' is not supported`);
    }
    const user = details[validity];
    if (user.givenName != null) {
        cy.get('#givenName').type(user.givenName, {force: true});
    }
    if (user.middleName != null) {
        cy.get('#middleName').type(user.middleName, {force: true});
    }
    if (user.familyName != null) {
        cy.get('#familyName').type(user.familyName, {force: true});
    }
    if (user.gender != null) {
        cy.get(`#${user.gender}`).click({force: true});
    }
    if (user.birthday != null) {
        cy.get('#birthdate').click({force: true});
        cy.get(`span[aria-label="${user.birthday}"]`).click({force: true});
    }
    if (user.address != null) {
        cy.get('#address1').type(user.address, {force: true});
    }
    if (user.country != null) {
        cy.get('#country').clear({force: true}).type(user.country, {force: true});
    }
    if (user.stateProvince != null) {
        cy.get('#stateProvince').type(user.stateProvince, {force: true});
    }
    if (user.cityVillage != null) {
        cy.get('#cityVillage').type(user.cityVillage, {force: true});
    }
    if (user.postalCode != null) {
        cy.get('#postalCode').type(user.postalCode, {force: true});
    }
    if (user.phoneNumber != null) {
        cy.get('#phone').type(user.phoneNumber, {force: true});
    }
})

When('the user clicks on the create patient button', () => {
    cy.get('button[type="submit"]').click({force: true})
})

Then('the patient registration should be {string}', status => {
    switch (status) {
        case 'successful':
            cy.contains('New Patient Created');
            break;
        case 'unsuccessful':
            cy.get('input[aria-invalid="true"]').should('have.length.greaterThan', 0);
            break;
        case 'inactive':
            cy.get('input[aria-invalid="true"]').should('have.length.greaterThan', 0);
            break;
        default:
            throw new Error(`Status '${status}' is not supported`);
    }
})
