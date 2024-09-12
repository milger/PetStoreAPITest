Feature: Add new pet to store
API user add new Pet records through /pet service

  @DeleteCreatedPetAfterScenario
  Scenario: 1. Add new Pet
    Given Milger is an API user
    When he creates a new pet with following information:
      | id  | name      | category id | category name | photoUrls                                                  | tags                      | status |
      | 101 | silvestre | 1           | Cat           | http://drive.test.com/media/cat100.jpg, tmp/img/cat100.jpg | [{"id":1, "name":"test"}] | sold   |
    Then he should get a 200 status code
      And he should get the same pet information in the response


  Scenario: 2.1 Proper code and message is returned when Pet with duplicated ID is sent
    Given Milger is an API user
      And he already has an existing pet with following information:
        | id | name  | category id | category name | photoUrls | tags                                              | status    |
        | 1  | Cat 1 | 2           | Cats          | url1,url2 | [{"id":1, "name":"tag1"},{"id":2, "name":"tag2"}] | available |
    When he creates a new pet with following information:
      | id | name     | category id | category name | photoUrls                            | tags                      | status    |
      | 1  | bethoven | 1           | Dogs          | http://drive.test.com/media/dog1.jpg | [{"id":1, "name":"test"}] | available |
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        {"ErrorMessage":"ID already in use"}
        """


  Scenario: 2.2 Existing Pet is not modified when a new pet with same ID is sent
    Given Milger is an API user
      And he already has an existing pet with following information:
        | id | name  | category id | category name | photoUrls | tags                                              | status    |
        | 1  | Cat 1 | 2           | Cats          | url1,url2 | [{"id":1, "name":"tag1"},{"id":2, "name":"tag2"}] | available |
    When he creates a new pet with following information:
        | id | name     | category id | category name | photoUrls                            | tags                      | status    |
        | 1  | bethoven | 1           | Dogs          | http://drive.test.com/media/dog1.jpg | [{"id":1, "name":"test"}] | available |
      And he looks for a pet with id equal to 1
    Then he should get the existing pet information without modifications


  Scenario Outline: 3. Pet is not saved when contains invalid data
    Given Milger is an API user
    When he prepares the following pet information:
        | id  | name      | category id | category name | photoUrls            | tags                      | status |
        | 201 | silvestre | 1           | Cat           | tmp/media/cat100.jpg | [{"id":1, "name":"test"}] | sold   |
      And he modifies the "<field>" pet field to "<invalidValue>"
      And he creates a new pet using the modified information
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        {"code":400,"message":"Input error: unable to convert input to io.swagger.petstore.model.Pet"}
        """
  Examples:
    | field | invalidValue |
    | id    | Test         |
    | tags  | false        |


  Scenario Outline: 4. Pet is not saved when required fields are missing
    Given Milger is an API user
    When he prepares the following pet information:
        | id  | name   | category id | category name | photoUrls            | tags                      | status |
        | 202 | wboots | 1           | Cat           | tmp/media/cat204.jpg | [{"id":1, "name":"test"}] | sold   |
      And he removes the "<field>" pet field
      And he creates a new pet with missing information
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        {"code":400,"message":"Input error: unable to convert input to io.swagger.petstore.model.Pet"}
        """
    Examples:
      | field |
      | id    |
      | name  |