Feature: Admin Login

  Scenario: Successful Login with Valid Credentials
    Given the admin is on the login page
    When the admin enters valid username and password
    And clicks on the login button
    Then the admin should be successfully logged in

  Scenario Outline: Error Handling for Invalid Input
    Given the admin is on the login page
    When the admin enters invalid username "<username>" or password "<password>"
    And clicks on the login button
    Then the appropriate "<login_result>" message should be displayed

    Examples:
      | username  | password | login_result                                             |
      | wrong     | 123      | "Invalid username or password provided"                 |
      | mohamad   | wrong    | "Invalid username or password provided"                 |

  Scenario: Empty Username and Password
    Given the admin is on the login page
    When the admin leaves the username and password fields empty
    And clicks on the login button
    Then an error message should be displayed prompting the admin to enter both username and password
