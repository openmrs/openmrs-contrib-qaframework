# Project Status
___
## Platform (core)
[![Platform Simple Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-simple.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-simple.yml)

[![Platform Advanced Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-advanced.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-advanced.yml)

[![Platform Upgrade](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-upgrade.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-upgrade.yml)

## Reference Application
[Legacy Selenium Ref App](https://ci.openmrs.org/browse/REFAPP-UI) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/REFAPP-UI)

[![Cucumber Ref App](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml)
___

# openmrs-contrib-qaframework

OpenMRS [BDD](https://en.wikipedia.org/wiki/Behavior-driven_development) QA framework, Currently tracking;

## Installing dependencies
- `mvn clean install -DskipTests=true`

## Configuration
Set Your test configurations in `src/test/resources/org/openmrs/uitestframework/test.properties`.
MySQL password should be the same for initialSetupTests as openmrs password

## Running test projects

### Running Ref app selenium tests
- `npm run refappSelenium`

### Running Platform Simple Installation test
- `npm run simpleCoreInstall`

### Running Platform Advanced Installation test
- `npm run advancedCoreInstall`

### Running Platform Testing Installation test
- `npm run testingCoreInstall`

### Running Platform Upgrade test
- `npm run coreUpgrade`

### Running ocl tests
- `npm run ocl`

### Running microfrontend tests
- `npm run microfrontend`

## Before Releasing
- [ ] For the platform, manually run both Installation and upgrade workflows again.
- [ ] Check all relevant builds to the release above to be sure they pass