Feature: Delete Pets
API user deletes existing pets

  Scenario: 1. Existing pet is deleted
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls                    | tags                      | status    |
        | 601 | Silvestre | 1           | Cat           | http://test.data.com/cat.jpg | [{"id":1, "name":"test"}] | available |
    When he deletes the pet with "601" id
    Then he should get a 200 status code
      And he should get the following message as response:
        """
        Pet deleted
        """

  Scenario: 2. Proper message is returned when invalid Id is provided
    Given Milger is an API user
    When he deletes the pet with "NegativeTest" id
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        {"code":400,"message":"Input error: couldn't convert `NegativeTest` to type `class java.lang.Long`"}
        """


  Scenario: 3. Proper message is returned when unexisting Id is provided
    Given Milger is an API user
    When he deletes the pet with "600000002" id
    Then he should get a 404 status code
      And he should get the following message as response:
        """
        Pet not found
        """
