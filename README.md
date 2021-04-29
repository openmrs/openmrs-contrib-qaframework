# openmrs-contrib-qaframework

OpenMRS [BDD](https://en.wikipedia.org/wiki/Behavior-driven_development) QA framework, Currently tracking;
___
[![Platform Simple Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-simple.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-simple.yml)

[![Platform Advanced Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-advanced.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-advanced.yml)

[Legacy Selenium Ref App](https://ci.openmrs.org/browse/REFAPP-UI) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/REFAPP-UI)

[![Cucumber Ref App](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml)
___

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

## Before Releasing
- [ ] For the platform, manually run both Installation and upgrade workflows again.
- [ ] Check all relevant builds to the release above to be sure they pass