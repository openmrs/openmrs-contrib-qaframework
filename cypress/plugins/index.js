const cucumber = require('cypress-cucumber-preprocessor').default
var propertiesReader = require('properties-reader');
var testProperties = propertiesReader('src/test/resources/org/openmrs/uitestframework/test-cypress.properties');

module.exports = (on, config) => {
  on('file:preprocessor', cucumber());
  on('task', {
      getProperty(prop) {
        return testProperties.get(prop);
      }
  });
}

