# openmrs-contrib-qaframework

[Bamboo CI](https://ci.openmrs.org/browse/CONTRIB-QA) (Chrome): ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/CONTRIB-QA)

[Travis CI](https://travis-ci.org/github/openmrs/openmrs-contrib-qaframework) (Firefox): [![Build Status](https://travis-ci.org/openmrs/openmrs-contrib-qaframework.svg?branch=master)](https://travis-ci.org/openmrs/openmrs-contrib-qaframework/branches)

[![Run QA Framework tests on Github Actions](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml)

OpenMRS [BDD](https://en.wikipedia.org/wiki/Behavior-driven_development) QA framework, Currently containing reference application end to end tests

## Building without running tests
- `mvn clean install -DskipTests=true`

## Configuration
Set Your test configurations in `src/test/resources/org/openmrs/uitestframework/test.properties`.
MySQL password should be the same for initialSetupTests as openmrs password

## Running selenium tests
- `mvn test -Dcucumber.filter.tags='@selenium and not @initialSetup'`

## Running Simple Installation test
- `mvn test -Dcucumber.filter.tags='@selenium and @initialSetup and @simpleInstall'`

## Running Advanced Installation test
- `mvn test -Dcucumber.filter.tags='@selenium and @initialSetup and @advancedInstall'`

## Running Testing Installation test
- `mvn test -Dcucumber.filter.tags='@selenium and @initialSetup and @testingInstall'`

## Running Upgrade test
- `mvn test -Dcucumber.filter.tags='@selenium and @initialSetup and @upgrade'`

## Running cypress tests
- `npm install-test`
