# Tasks Overviwew

## Postman API Testing.

1. Install Postman REST Client

2. Create Account in Trello using PERSONAL mail (not Grid)

3. Sign In to Account

4. Get and save API Key: https://trello.com/app-key

5. Get token (Use token button)

6. Check that everything works By typing URL to address line: https://api.trello.com/1/members/me?key={YOUR_KEY}&token={YOUR_TOKEN}

7. If Step 6 returned JSON go to Steap 8 if not go to Google

8. Create Postmen collection for:

- Creating a new board (POST)

- Getting board by ID (GET)

- Updating board (PUT)

- Remove board


USE DOC: https://developer.atlassian.com/cloud/trello/rest/api-group-boards/#api-boards-id-memberships-get

## Practice task - Create API tests on Java 

Now please automate previous task using the Java 8. You will need to implement automation tests using 3 different API Automation Libraries.


Clarification: useful guide: rest api automation guide. Project reference template: link2

- Tests should extend BaseTest which will hide all API related manipulation as well as basic api validations.

- Test should be in the test package, models (Pojos) should be in main package

- rest client (based on RestAssured) should be created before test started and should be 1 for all tests

- Use TestSteps utility classes to hide implementation of basic steps:

BuildRequest
SendRequest
CheckResponseIsValid
PrepareActualResponse
PrepareExpectedResponse
CheckActualVsExpectedResponses
- Try to use both TestNg Assertions and AssertJ assertions, to see the difference and the advantages of each



1. Create new Maven project

2. Add dependency to Maven: TestNG (search on Maven Repo new version) ApacheHTTPClient, Jackson

3. Create the model classes for serialization and deserialization of API calls - json to pojo tool

4. Create corresponding step classes to implement PostMan task from above.

- Creating a new board (POST)

- Getting board by ID (GET)

- Updating board (PUT)

- Remove board (DELETE)


5. Each test should contain Assertion (you should check not only response code)

6. Add REST Assured library to your Maven

7. Create test class with the same verifications using REST Assured library

8. Add Retrofit and OKHTTP libraries

9. Create test class with the same verifications using Retrofit and OKHTTPClient libraries



Test questions: 

- What tool you liked more

- What tools supports WevSocket

# Project Description

Tests for automating Trello API HTTP Requests have been developed using 3 different libraries:
 - Rest Assured
 
 - OkHttp
 
 - Retrofit
 
 For logging the results, ExtentReports library is used.
 
 In order to run the tests, inside the TestRunners folder, 3 xml test suite files can be found
 
 
 
