Feature: Admin Profile

  Scenario: Show Admin Profile
    Given that the admin is logged in
    Then the admin profile appears with the following information


  Scenario: Update Admin Profile Successfully
    Given that the admin is logged in
    And the admin wants to update their profile information
    When the admin changes their 'name' to 'Mohamad Maher'
    And the admin changes their 'phone' to '0591234567'
    And the admin changes their 'address' to 'Ramallah'
    And the admin changes their 'password' to '123456'
    And the admin clicks on the 'Save' button
    Then the admin profile should be updated successfully

  Scenario Outline: Errors with Input
    Given that the admin is logged in
    And the admin wants to update their profile information
    When the admin changes their 'name' to '<Name>'
    And the admin changes their 'phone' to '<Phone>'
    And the admin clicks on the 'Save' button
    Then the admin should see '<message>'

    Examples:
      | Name    | Phone       | message                           |
      | Ro52yal | 0595429100  | Invalid Name, please check it     |
      | Royal   | 0595429100aa| Invalid Phone, please check it    |
