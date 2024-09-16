Feature: Find Pets
API user is able to retrieve the saved pets

  Scenario: E.1. Pets with available status are returned by default when no param is provided
    Given Milger is an API user
    When he finds pets by status without any param
    Then he should get a 200 status code
      And he should get a list of pets with "available" status by default


  Scenario: E.2. Pets with the specified status are returned when using a single param
    Given Milger is an API user
    When he finds pets by status using the following status param:
      | pending |
    Then he should get a 200 status code
    And he should get a list of pets with "pending" status


  Scenario: E.3. Pets with the specified status are returned when using multiple params
    Given Milger is an API user
    When he finds pets by status using the following status param:
      | sold, pending |
    Then he should get a 200 status code
    And he should get a list of pets with following status:
      | sold    |
      | pending |


  Scenario: E.4. Deleted pets are not returned when finding pets by status
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls                    | tags                      | status |
        | 501 | Silvestre | 1           | Cat           | http://test.data.com/cat.jpg | [{"id":1, "name":"test"}] | sold   |
      And he has deleted the pet with "501" id
    When he finds pets by status using the following status param:
      | sold |
    Then he should get a 200 status code
    And he should not see the following data in the list of pets returned:
      | id  | name      | category id | category name | photoUrls                    | tags                      | status |
      | 501 | Silvestre | 1           | Cat           | http://test.data.com/cat.jpg | [{"id":1, "name":"test"}] | sold   |


  Scenario: F.1. Pets with the specified tag are returned when using a single filtering criteria
    Given Milger is an API user
    When he finds pets by tags using the following tags params:
      | test |
    Then he should get a 200 status code
    And he should get a list of pets that has "test" tag


  Scenario: F.2. Pets with the specified tags are returned when using multiple filtering criteria
    Given Milger is an API user
    When he finds pets by tags using the following tags params:
      | test |
      | new  |
    Then he should get a 200 status code
    And he should get a list of pets that has any of the following tags:
      | test |
      | new  |


  Scenario: F.3. Tags param is mandatory for finding pets by tag
    Given Milger is an API user
    When he finds pets by tags without any tags param
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        No tags provided. Try again?
        """

  Scenario: F.4. Deleted pets are not returned when finding pets by tag
    Given Milger is an API user
      And he has created a pet with following information:
        | id  | name      | category id | category name | photoUrls                    | tags                            | status |
        | 502 | Silvestre | 1           | Cat           | http://test.data.com/cat.jpg | [{"id":1, "name":"Automation"}] | sold   |
      And he has deleted the pet with "502" id
    When he finds pets by tags using the following tags params:
      | Automation |
    Then he should get a 200 status code
    And he should not see the following data in the list of pets returned:
      | id  | name      | category id | category name | photoUrls                    | tags                      | status |
      | 502 | Silvestre | 1           | Cat           | http://test.data.com/cat.jpg | [{"id":1, "name":"Automation"}] | sold   |


  Scenario: G.2. No pet data is returned when invalid Id is provided
    Given Milger is an API user
    When he finds a pet with id equal to "NegativeTest"
    Then he should get a 400 status code
      And he should get the following message as response:
        """
        {"code":400,"message":"Input error: couldn't convert `NegativeTest` to type `class java.lang.Long`"}
        """


  Scenario: G.3. No pet data is returned when unexisting Id is provided
    Given Milger is an API user
    When he finds a pet with id equal to "500000003"
    Then he should get a 404 status code
      And he should get the following message as response:
        """
        Pet not found
        """
