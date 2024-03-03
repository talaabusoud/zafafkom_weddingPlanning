Feature: User Requests
  Description: The user modifies his personal information and can delete his account.
  Actor: user

  Scenario: Edit user information
    Given the user with id "2" is logged in
    When the user modifies his information with new values:
      | PhoneNumber    | Name   | Email           | Address      | City      | Street | Password  |
      | 05923815502    | Ahmad  | ahmad@gmail.com | building 22  | Ramallah  | tere   | ahmad123  |
    And the user ensures the modification is without errors
    Then the user's information is successfully updated

  Scenario: Delete user account
    Given the user with id "2" is logged in
    When the user wants to delete his account
    And provides the credentials:
      | Username                | Password   |
      | Ahmad22@gmail.com       | 11223344   |
    And clicks the confirmation button
    Then the account is successfully deleted from the system