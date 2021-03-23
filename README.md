# openmrs-contrib-qaframework

[Bamboo CI](https://ci.openmrs.org/browse/CONTRIB-QA) (Chrome): ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/CONTRIB-QA)

[Travis CI](https://travis-ci.org/github/openmrs/openmrs-contrib-qaframework) (Firefox): [![Build Status](https://travis-ci.org/openmrs/openmrs-contrib-qaframework.svg?branch=master)](https://travis-ci.org/openmrs/openmrs-contrib-qaframework/branches)

[![Run QA Framework tests on Github Actions](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml)

OpenMRS [BDD](https://en.wikipedia.org/wiki/Behavior-driven_development) QA framework, Currently containing reference application end to end tests

## Building without running tests
- `mvn clean install -DskipTests=true`

## Running selenium tests
- `mvn clean install`

## Running cypress tests
- `npm install-test`
