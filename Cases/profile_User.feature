Feature: User Profile

  Scenario: Show user profile
    Given the user with id "2" is logged in
    And the user has the following information:
      | Name       | PhoneNumber  | Address       | City   | Street     | Email                | Password  |
      | Raya       | 0599598746   | Building 11   | Nablus | Amman St   | raya1234@gmail.com   | 44559988  |
    When the user views their profile
    Then the user profile should appear with the provided information
