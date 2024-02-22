Feature: Service provider logout

  Scenario: logout done successfully
    Given that the Service provider choose to logout
    Then the Service provider will be logout

  Scenario: logout fail
    Given that the Service provider choose to logout
    Then the the Service provider can't logout