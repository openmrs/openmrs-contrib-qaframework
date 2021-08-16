import {Given, Before} from 'cypress-cucumber-preprocessor/steps';

let identifier = null;
let patient = null;


Before({tags: '@clinical-visit'}, () => {
    identifier = cy.generateIdentifier().then((generatedIdentifier)=>{
        identifier = generatedIdentifier;
        cy.createPatient(identifier).then((generatedPatient)=> {
            patient = generatedPatient;
            cy.startVisit(patient.uuid);
        });
    });

});

Given('the user is logged in', () => {
    cy.login();
});

Given('the user arrives on a patient’s chart page', () => {
    cy.visit(`patient/${patient.uuid}/chart`);
});

Then('the Patient header should be display correct information', () => {
   cy.contains(patient.person.display);
   cy.contains(`${patient.person.age} years`);
   cy.contains(identifier.toString());
});

Then('the user should be able to expand header to see more information', () => {
    cy.contains('Show all details').click({force: true});
    cy.contains('Address');
});

Then('the Patient Summary should load properly', () => {
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Care Programs');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Active Medications');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Biometrics');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Vitals');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Forms');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Conditions');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Notes');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Appointments');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Allergies');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Immunizations');
    cy.get('div[data-extension-slot-name="patient-chart-summary-dashboard-slot"]').contains('Clinical Views');
});

When('the user clicks on the {string} menu', (menu)=>{
    cy.get('div[data-extension-id="patient-chart-nav-items"]').contains(menu).click();
});

Then('the program list should be empty', ()=>{
    cy.contains('There are no program enrollments to display for this patient');
});

When('the user enrolls the patient into a program', ()=>{
    cy.contains('Record program enrollments').click();
    cy.get('#program').select('HIV Care and Treatment');
    cy.get('button[type="submit"').click();
});

Then('the patient should be enrolled into the program', ()=>{
    cy.contains('Program enrollment saved successfully');
    cy.reload();
    cy.contains('HIV Care and Treatment');
});

Then('the allergies list should be empty', ()=>{
    cy.contains('There are no allergy intolerances to display for this patient');
});

When('the user adds an allergy', ()=>{
    cy.contains('Record allergy intolerances').click({force: true});
    cy.contains('ACE inhibitors').click({force: true});
    cy.contains('Mental status change').click({force: true});
    cy.contains('Mild').click({force: true});
    // Click on the first day on the calendar
    cy.getByLabel('Date of first onset').click({force: true});
    cy.get('.dayContainer .flatpickr-day').first().click({force: true});
    cy.contains('Save and Close').click({force: true});
});

Then('the added allergy should be listed', ()=>{
    cy.contains('Allergy saved successfully');
    cy.reload();
    cy.contains('ACE inhibitors');
});

Then('the conditions list should be empty', ()=>{
    cy.contains('There are no conditions to display for this patient');
});

When('the user adds a condition', ()=>{
    cy.contains('Record conditions').click({force: true});
    cy.get('input[role="searchbox"]').type('Fever');
    cy.contains('Fever').click();
    cy.contains('Save & Close').click({force: true});
});

Then('the added condition should be listed', ()=>{
    cy.contains('Condition saved successfully');
    cy.reload();
    cy.contains('Fever');
});

Then('the attachments list should be empty', ()=>{
    cy.contains('There are no attachments to display for this patient');
});

When('the user adds an attachment', ()=>{
    cy.contains('Record attachments').click({force: true});
    cy.get('#uploadPhoto').attachFile('test_image.jpeg');
    cy.contains('Save').click({force: true});
});

Then('the added attachment should be listed', ()=>{
    cy.contains('1 items');
});

Then('the notes list should be empty', ()=>{
    cy.contains('There are no notes to display for this patient');
});

When('the user adds a note', ()=>{
    cy.contains('Record notes').click({force: true});
    cy.get('input[role="searchbox"]').type('Shock');
    cy.contains('Shock').click();
    cy.getByLabel('Write an additional note').type('note');
    cy.contains('Save & Close').click({force: true});
});

Then('the added note should be listed', ()=>{
    cy.contains('Visit note saved successfully');
    cy.reload();
    cy.contains('Visit Note');
})
