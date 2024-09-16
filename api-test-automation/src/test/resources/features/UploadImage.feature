Feature: Upload Image
API user adds an image for an existing Pet through /pet/id/uploadImage services

  @DeleteCreatedPetAfterScenario
  Scenario: 1. Add image for an existing pet
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls                    | tags                      | status    |
        | 401 | Silvestre | 1           | Cat           | http://test.data.com/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he uploads the photo "cat-kitten-pet.jpeg" to pet with id "401"
    Then he should get a 200 status code
      And he should get a pet with 2 photos in the response body


  Scenario: 2. Proper message is returned when invalid Id is provided
    Given Milger is an API user
    When he uploads the photo "cat-kitten-pet.jpeg" to pet with id "NegativeTest"
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        {"code":400,"message":"Input error: couldn't convert `NegativeTest` to type `class java.lang.Long`"}
        """


  Scenario: 3. Proper message is returned when unexisting Id is provided
    Given Milger is an API user
    When he uploads the photo "cat-kitten-pet.jpeg" to pet with id "400000003"
    Then he should get a 404 status code
    And he should get the following message as response:
        """
        Pet not found
        """


  @DeleteCreatedPetAfterScenario
  Scenario Outline: 4. Proper message is returned when invalid file type is provided
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls                    | tags                      | status    |
        | 404 | Silvestre | 1           | Cat           | http://test.data.com/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he uploads the photo "<file>" to pet with id "404"
    Then he should get a 400 status code
      And he should get the following message as response:
          """
          No file uploaded
          """

  Examples:
    | file         |
    | textfile.txt |
