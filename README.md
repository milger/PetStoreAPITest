# Pet Store Testing Practice

This practice uses Serenity BDD, cucumber and Java for its purposes.
 Additionally, it applies serenity screenplay pattern so you'll find the project structure adapted for that.

## Tasks


### 1. API test automation ✅
API under test:  Swagger Petstore - OpenAPI 3.0 http://localhost:8080/
1. Follow this link for getting information about the Swagger Pet store project.
2. Set up the API Pet store and run it locally.
3. Propose a list of test cases for automation.
4. Automate the proposed test cases.
5. Provide a brief explanation of the solution you have implemented.

Note: ONLY THE TEST FOR ADD PETS have been automated because setting up the project structure and good practices takes some additional time, also because I didn't work with this tech stack for a while. (I had to get updated with few tools) 
### 2. API performance test ❌
Unable to complete this due to lack of experience in this area (tried to make it done following some guidances but it couldn't finish in a good job worthy to be sent)

**So the below information in this readme file applies for task 1 only.**

## API test automation 

### Tests Cases
The high level test case are written in: `/src/test/resources/manualTestCases/hltc-pets.txt`

I think that all of them should be automated but due to the limited time only the test for creating pets have been automated.

### The project directory structure (Screenplay pattern)
The project applies Screenplay pattern, so the project has the following structure in order to do that:
```Gherkin
src
  + test
    + java
      + api
        + entities                     Model classes
        + hooks                        Cucumber hooks
        + tasks                        Tasks that actors can perform. They are called from step definition
        + questions                    Question classes that are used to validate the data
        + stepdefinitions              Step definitions
        + helper                       Util and common methods
    + resources
      + features                       Feature files
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

**Note: In case there is no chance to run the tests, the serenity report is included in the `report` folder (without styles)**
