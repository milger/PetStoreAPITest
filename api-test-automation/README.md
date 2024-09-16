# API test automation
This practice uses Serenity BDD, cucumber and Java for its purposes. Additionally, it applies serenity screenplay pattern so you'll find the project structure adapted for that.

### Taks details
1. Follow this link for getting information about the Swagger Pet store project.
2. Set up the API Pet store and run it locally.
3. Propose a list of test cases for automation.
4. Automate the proposed test cases.
5. Provide a brief explanation of the solution you have implemented.
   
API under test:  Swagger Petstore - OpenAPI 3.0 http://localhost:8080/

### Tests Cases
The high level test case are written in: `/src/test/resources/manualTestCases/hltc-pets.txt`. All of them have been automated and they can be found in the `features` folder. 

### The project directory structure (Screenplay pattern)
The project applies Screenplay pattern, so the project has the following structure in order to do that:
```Gherkin
src
  + test
    + java
      + api
        + model                        Model classes
        + hooks                        Cucumber hooks
        + tasks                        Tasks that actors can perform. They are called from step definition
        + questions                    Question classes that are used to validate the data
        + stepdefinitions              Step definitions
        + helper                       Utils, Constants, Custom Matchers and common methods
    + resources
      + features                       Feature files
      + data                           Test data used by some scenarios
```
The endpoints are placed in the serenity.conf file in order to avoid hardcoded data.

### Plataform
The automated tests have been implemented and executed on: 
> OS: Windows 11
> 
> Java version: 21 LTS
> 
> Gradle: 8.5 (Already embeded in the project as gradlew)
> 
> IntelliJIdea Community Edition

### Executing the tests
To run this project, you can either just run the `AcceptanceTestSuite` test runner class or `gradle test` from the command line.

```json
$ ./gradlew clean test
```

The test results will be recorded in the `target/site/serenity` directory.

**Note: In case there is no chance to run the tests, the serenity reports are included in the `report` folder (a compressed file tha contains all generated files by serenity)**

### Test result
**Total:** 34  tests, 
**Failures:**: 12 failed,
**Duration:** 6.569s, 
**64% successful test**

The failed tests are issues that need to be prioritized and analyzed in order to assign a corresponding severity.
