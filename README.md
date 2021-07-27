# QA Dashboard: Project Status
___
## Platform (core)
* [![Platform Simple Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-simple.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-simple.yml)
* [![Platform Advanced Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-advanced.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-advanced.yml)
* [![Platform Postgres Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-postgres.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-postgres.yml)
* [![Platform Upgrade](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-upgrade.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-upgrade.yml)

## Reference Application

### 2.x RefApp
* All Firefox Tests [![All Firefox Tests](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml)
  * [![.github/workflows/search_registration.yml](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/search_registration.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/search_registration.yml)
  * [![Vitals And Triaging Test](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/vitals-and-triaging.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/vitals-and-triaging.yml) 
  * [![Clinical Visit Test](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/clinical-visit.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/clinical-visit.yml)
  * _Tests in development:_ 
    * Patient Merge
    * Managing User Accounts
    * Discharges & Transfers
    * Reports
    * Security: XSS Checks
* Legacy Tests
  * [Legacy Selenium Chrome](https://ci.openmrs.org/browse/REFAPP-UI) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/REFAPP-UI)
  * [![Legacy Selenium Firefox](https://github.com/openmrs/openmrs-distro-referenceapplication/actions/workflows/ci.yml/badge.svg)](https://github.com/openmrs/openmrs-distro-referenceapplication/actions/workflows/ci.yml)
* New Workflow Tests
  * [BDD Chrome](https://ci.openmrs.org/browse/CONTRIB-QA) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/CONTRIB-QA)
  * [![Vitals And Triaging Test](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/vitals-and-triaging.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/vitals-and-triaging.yml) 


### 3.x RefApp
* [![RefApp 3.x Login](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-login.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-login.yml)
* [![RefApp 3.x Search & Registration](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-registration.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-registration.yml) _In Development_
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

### Running Platform postgres Installation test
- `npm run postgresCoreInstall`

### Running Platform Testing Installation test
- `npm run testingCoreInstall`

### Running Platform Upgrade test
- `npm run coreUpgrade`

### Running microfrontend tests
- `npm run microfrontend`

## Before Releasing
- [ ] For the platform, manually run both Installation and upgrade workflows again.
- [ ] Check all relevant builds to the release above to be sure they pass
