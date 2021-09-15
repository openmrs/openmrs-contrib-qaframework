# QA Dashboard: Project Status
___
## Platform (core)
* [![Platform Simple Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-simple.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-simple.yml)
* [![Platform Advanced Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-advanced.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-advanced.yml)
* [![Platform Postgres Installation](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-postgres.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-install-postgres.yml)
* [![Platform Upgrade](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-upgrade.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/platform-upgrade.yml)
* [TRUNK Latest Build](https://ci.openmrs.org/browse/TRUNK-MASTER) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/TRUNK-MASTER)
* [RESTWS Latest Build](https://ci.openmrs.org/browse/RESTWS-RESTWS) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/RESTWS-RESTWS)
* [FHIR FM2 Latest Build](https://ci.openmrs.org/browse/FHIR-FM2) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/FHIR-FM2)

## Reference Application

### 2.x RefApp 
  
* RefApp 2.x Workflow Tests (Firefox) [![All Firefox Tests](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml/badge.svg?branch=master)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/qa.yml)
* RefApp 2.x Workflow Tests (Chrome) [BDD Chrome](https://ci.openmrs.org/browse/CONTRIB-QA) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/CONTRIB-QA)
  * [![RefApp 2.x Clinical Visit](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-clinical-visit.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-clinical-visit.yml)
  * [![RefApp 2.x Vitals And Triaging](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-vitals-and-triaging.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-vitals-and-triaging.yml) 
  * [![RefApp 2.x Inpatient](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-inpatient.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-inpatient.yml)
  * [![RefApp 2.x Registration](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-registration.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-registration.yml)
  * [![RefApp 2.x User Account](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-user-account.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-2x-user-account.yml)
  * Tests in development:
    * Find Patients
    * Security: XSS Checks   
* Other Feature-Specific Tests
  * [Legacy Selenium Chrome](https://ci.openmrs.org/browse/REFAPP-UI) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/REFAPP-UI)
  * [![Legacy Selenium Firefox](https://github.com/openmrs/openmrs-distro-referenceapplication/actions/workflows/ci.yml/badge.svg)](https://github.com/openmrs/openmrs-distro-referenceapplication/actions/workflows/ci.yml)
  * Detailed list of Legacy Selenium Tests [here](https://github.com/openmrs/openmrs-distro-referenceapplication/tree/master/ui-tests/src/test/java/org/openmrs/reference).
  * [Legacy UI Latest Build](https://ci.openmrs.org/browse/LU-LU) ![Build Status](https://ci.openmrs.org/plugins/servlet/wittified/build-status/FHIR-FM2)

### 3.x RefApp
* [![RefApp 3.x Login](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-login.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-login.yml)
* [![RefApp 3.x Search & Registration](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-search-and-registration.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-search-and-registration.yml)
* [![RefApp 3.x User settings](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-settings.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-settings.yml)
* [![RefApp 3.x Clinical Visit](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-clinical-visit.yml/badge.svg)](https://github.com/openmrs/openmrs-contrib-qaframework/actions/workflows/refapp-3x-clinical-visit.yml)
___

### OpenMRS Dictionary Manager
* [![Dictionary Manager](https://github.com/openmrs/openmrs-ocl-client/actions/workflows/dictionary-manager.yml/badge.svg)](https://github.com/openmrs/openmrs-ocl-client/actions/workflows/dictionary-manager.yml)
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

---
# RefApp 3.x  E2E tests

## Setting up the project

1. Clone the project
    ```
     git clone git@github.com:openmrs/openmrs-contrib-qaframework.git
     cd openmrs-contrib-qaframework
    ```
1. Install the dependencies
    ```
    npm install
    ```
You don’t need to set up an OpenMRS instance since we use a [cloud instance](https://openmrs-spa.org/openmrs/spa) for the test backend.

## Running tests

There are two ways of running tests:

1. **Running with cypress runner**
    Open the Cypress runner with
    ```
    cypress open
    ```
    and pick a test from the GUI.
    
1. **Running in command line**
  
    Run the desired test using `npm run`, e.g.
    
    ```
    npm run refapp3Login
    ```
    
    See the `scripts` section of [package.json](https://github.com/openmrs/openmrs-contrib-qaframework/blob/master/package.json).

## File structure
```
.
├── cypress
│   ├── fixtures // Test fixtures (e.g. attachments)
│   │   └── test_image.jpeg
│   ├── integration
│   │   └── cucumber
│   │       └── step_definitions
│   │           ├── refapp-2.x
│   │           │   └── login.js
│   │           └── refapp-3.x // Cypress tests for the refapp 3.x
│   │               ├── 01-login
│   │               │   └── login.js
│   │               ...
│   ├── plugins
│   │   └── index.js
│   ├── support
│   │   ├── commands.js // Custom commands for Cypress
│   │   └── index.js
│   ├── videos  // Screen recordings (set "video": true in cypress.json)
│   └── tsconfig.json
├── src
│   └── test
│       ├── java
│       └── resources
│           ├── features
│           │   ├── platform
│           │   ├── refapp-2.x
│           │   └── refapp-3.x // Cucumber feature files for the refapp 3.x
│           │       ├── 01-login
│           │       │   └── login.feature
│           │       ...
├── target
├── README.md
├── cypress.json // Cypress configuration file
├── package.json
├── pom.xml
```


## Writing a new test
1. Create a new directory with your feature file under `/src/test/resources/features/refapp-3.x/`.
    
    The name of the directory should be `<sequence>-<name>`.
    
    [Example feature file](https://github.com/openmrs/openmrs-contrib-qaframework/blob/master/src/test/resources/features/refapp-2.x/stylesGuide.feature)
    
1. Create a new directory with the same name under  `cypress/integration/cucumber/step_definitions/refapp-3.x/` to store the step definition file.
    See the [cypress-cucumber-preprocessor docs](https://github.com/TheBrainFamily/cypress-cucumber-preprocessor#readme)

1. Run the test using either:
   - Command line: `cypress run --spec <path-to-feature-file>`
   
      (You can simplify the command by adding it to the npm scripts section. See [this example](https://github.com/openmrs/openmrs-contrib-qaframework/blob/f9996d757912ba7ccfb1ff3495379bbafaf89f23/package.json#L19).)
   - Cypress runner: `cypress open` and choose the test

## Creating a GitHub workflow
1. Create a new GitHub workflow file under `.github/workflows/` directory. An example workflow can be found [here](https://github.com/openmrs/openmrs-contrib-qaframework/blob/master/.github/workflows/refapp-3x-login.yml).
1. Add the workflow badge to the readme file under [3.x RefApp](https://github.com/openmrs/openmrs-contrib-qaframework/blob/master/README.md#3x-refapp) section. It should take the following format:
    ```markdown
    [![<workflow name>](<link-to-the-workflow>/badge.svg)](<link-to-the-workflow>)
    ```

## Environment variables

The environment variables are stored in the `cypress.json` file. The variables can be accessed with `Cypress.env()`; e.g.,
```typescript
Cypress.env('API_BASE_URL');
```

See the [Cypress docs](https://docs.cypress.io/guides/guides/environment-variables).


## Before Releasing
- [ ] For the platform, manually run both Installation and upgrade workflows again.
- [ ] Check all relevant builds to the release above to be sure they pass
