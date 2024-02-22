Feature: admin logout

  Scenario: logout done successfully
    Given that the admin choose to logout
    Then the admin will be logout

  Scenario: logout fail
    Given that the admin choose to logout
    Then the the admin can't logout