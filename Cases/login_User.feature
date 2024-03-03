Feature: User Login
  Description: The user login to the system

  Scenario Outline: Successful Login with Valid Credentials
    Given the user is on the login page
    When the user enters valid username and password
    And the username for user is "<username>"
    And the password for user is "<password>"
    And clicks on the login button
    Then the user is logged in the app successfully "<login_result>"
    Examples:
      |     username        | password |      login_result     |
      |  jana123@gmail.com  | 12345666 | " login successfully "|



  Scenario Outline: user entered a wrong username
    Given that the user is not logged in the app
    And the username for user is "<wrong username>"
    And the password for user is "<password>"
    Then the user will not login
    And the message appear to tell the user what's wrong "<login_result>"
    Examples:
      | wrong username | password |     login_result    |
      | t1234@gmail.com| 12345678 | " Invalid username "|
      | m21@gmail.com  | 87654321 | " Invalid username "|

  Scenario Outline: user entered a wrong password
    Given that the user is not logged in the app
    And the username for user is "<username>"
    And the password for user is "<wrong password>"
    Then the user will not login
    And the message appear to tell the user what's wrong "<login_result>"
    Examples:
      |      username      | wrong password |     login_result   |
      | taa1234@gmail.com  |    11111111    | " Wrong password " |
      | mohamad21@gmail.com|    00000000    | " Wrong password " |

  Scenario: user entered empty password or email
    Given that the user is not logged in the app
    And the username for user is " "
    And the password for user is " "
    Then the user will not login
    And error message will be displayed prompting the user to enter both username and password
