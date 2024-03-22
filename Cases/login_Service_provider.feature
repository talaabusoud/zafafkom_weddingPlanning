Feature: owner login

  Scenario Outline: Successful log in
    Given that the owner is not logged in the app
    And the username owner is "<username>"
    And the password owner is "<password>"
    Then  the owner is logged in the app successfully
    Examples:
      |username|password|
      |mo@gmail|1|
      |ta@gmail|000|


  Scenario: owner entered a wrong username
    Given that the owner is not logged in the app
    And the username owner is "wrong"
    And the password owner is "000"
    Then the owner will not login
    And the message appear to tell the owner what's wrong

  Scenario: owner entered a wrong password
    Given that the owner is not logged in the app
    And the username owner is "mo@gmail"
    And the password owner is "wrong"
    Then the owner will not login
    And the message appear to tell the owner what's wrong

  Scenario: owner entered empty password or email
    Given that the owner is not logged in the app
    And the username owner is " "
    And the password owner is " "
    Then the owner will not login
    And the message appear to tell the owner what's wrong
