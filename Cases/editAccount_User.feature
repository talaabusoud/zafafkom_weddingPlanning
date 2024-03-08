Feature: User Requests
  Description: The user modifies his personal information for his account.
  Actor: user

  Scenario: Edit user information
    Given the user with id "2" is logged in
    When the user modifies his information with new values:
      | PhoneNumber    | Name   | Email           | Address      | City      | Street | Password  |
      | 05923815502    | Ahmad  | ahmad@gmail.com | building 22  | Ramallah  | tere   | ahmad123  |
    When the user ensures the modification is without errors
    Then the user's information is successfully updated
