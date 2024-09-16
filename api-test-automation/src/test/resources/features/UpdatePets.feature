Feature: Update existing pet
API user updates a Pet records through /pet and /pet/id services

  @DeleteCreatedPetAfterScenario
  Scenario: B.1. Pet data is updated
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls       | tags                      | status    |
        | 301 | Silvestre | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he updates the pet with following information:
      | id  | name   | category id | category name | photoUrls                                               | tags                      | status |
      | 301 | Piolin | 1           | Bird          | http://drive.test.com/media/bird1.jpg,tmp/img/bird1.jpg | [{"id":1, "name":"test"}] | sold   |
    Then he should get a 200 status code
      And he should get the updated pet information in the response body
    When he finds a pet with id equal to "301"
    Then he should get the updated pet information in the response body


  @DeleteCreatedPetAfterScenario
  Scenario Outline: B.2. Pet data is not updated when contains invalid data
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls       | tags                      | status    |
        | 302 | Silvestre | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he prepares the following pet information:
        | id  | name   | category id | category name | photoUrls                                               | tags                      | status |
        | 302 | Piolin | 1           | Bird          | http://drive.test.com/media/bird1.jpg,tmp/img/bird1.jpg | [{"id":1, "name":"test"}] | sold   |
      And he modifies the "<field>" pet field to "<invalidValue>"
      And he updates the pet using the modified information
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        {"code":400,"message":"Input error: unable to convert input to io.swagger.petstore.model.Pet"}
        """
  Examples:
    | field     | invalidValue |
    | photoUrls | 1            |
    | tags      | false        |


  @DeleteCreatedPetAfterScenario
  Scenario Outline: B.3. Pet data is not updated when required fields are missing
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls       | tags                      | status    |
        | 303 | Silvestre | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he prepares the following pet information:
        | id  | name   | category id | category name | photoUrls                                               | tags                      | status |
        | 303 | Piolin | 1           | Bird          | http://drive.test.com/media/bird1.jpg,tmp/img/bird1.jpg | [{"id":1, "name":"test"}] | sold   |
      And he removes the "<field>" pet field
      And he updates the pet with missing information
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        {"code":400,"message":"Input error: unable to convert input to io.swagger.petstore.model.Pet"}
        """
    Examples:
      | field |
      | id    |


  Scenario: B.4. No new pet is saved when pet ID is not found
    Given Milger is an API user
    When he updates the pet with following information:
      | id        | name   | category id | category name | photoUrls                                               | tags                      | status |
      | 300000004 | Piolin | 1           | Bird          | http://drive.test.com/media/bird1.jpg,tmp/img/bird1.jpg | [{"id":1, "name":"test"}] | sold   |
    Then he should get a 404 status code
      And he should get the following message as response:
        """
        Pet not found
        """
    When he finds a pet with id equal to "300000004"
    Then he should get the following message as response:
        """
        Pet not found
        """


  @DeleteCreatedPetAfterScenario
  Scenario: C.1. Pet's Name and Status are updated
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls       | tags                      | status    |
        | 305 | Silvestre | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he updates the pet 305 with following form data:
      | name      | Kitty |
      | status    | sold  |
    Then he should get a 200 status code
      And he should get the following updated pet information:
        | id  | name  | category id | category name | photoUrls       | tags                      | status |
        | 305 | Kitty | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | sold   |


  @DeleteCreatedPetAfterScenario
  Scenario: C.2. Pet's Name only is updated when no Status is provided
    Given Milger is an API user
    And he has created a pet with following information:
      | id  | name      | category id | category name | photoUrls       | tags                      | status    |
      | 306 | Silvestre | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he updates the pet 306 with following form data:
      | name      | Kitty |
    Then he should get a 200 status code
      And he should get the following updated pet information:
        | id  | name  | category id | category name | photoUrls       | tags                      | status    |
        | 306 | Kitty | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | available |


  @DeleteCreatedPetAfterScenario
  Scenario: C.3. Pet's Status only is updated when no Name is provided
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls       | tags                      | status    |
        | 307 | Silvestre | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he updates the pet 307 with following form data:
      | status    | sold  |
    Then he should get a 200 status code
      And he should get the following updated pet information:
        | id  | name      | category id | category name | photoUrls       | tags                      | status |
        | 307 | Silvestre | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | sold   |


  @DeletePrevCreatedPetAfterScenario
  Scenario: C.4 No data is modified when no param is provided
    Given Milger is an API user
    And he has created the following pet previously:
        | id  | name      | category id | category name | photoUrls       | tags                      | status    |
        | 308 | Silvestre | 1           | Cat           | tmp/img/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he updates the pet 308 without any form data
    Then he should get a 400 status code
    When he finds a pet with id equal to "308"
    Then he should get the previous pet information without modifications
