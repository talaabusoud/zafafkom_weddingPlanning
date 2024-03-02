Feature: Service Provider Profile

  Scenario: Show Service Provider Profile
    Given that the service provider is logged in
    Then the service provider profile appears with the following information:

  Scenario: Update Service Provider Profile Successfully
    Given that the service provider is logged in
    And the service provider wants to update their profile information
    When the service provider changes their 'name' to 'Mohamad Maher'
    And the service provider changes their 'phone' to '0591234567'
    And the service provider changes their 'address' to 'Ramallah'
    And the service provider changes their 'password' to '123456'
    And the service provider clicks on the 'Save' button
    Then the service provider profile should be updated successfully

  Scenario Outline: Errors with Input
    Given that the service provider is logged in
    And the service provider wants to update their profile information
    When the service provider changes their 'name' to '<Name>'
    And the service provider changes their 'phone' to '<Phone>'
    And the service provider clicks on the 'Save' button
    Then the service provider should see '<message>'

    Examples:
      | Name    | Phone       | message                           |
      | Ro52yal | 0595429100  | Invalid Name, please check it     |
      | Royal   | 0595429100aa| Invalid Phone, please check it    |
