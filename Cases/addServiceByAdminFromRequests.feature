Feature: the admin add the Service from requests
  Scenario: add houses successfully
    Given that the administrator is logged in
    And request list have Sarvice to rent
    Then the Sarvice will be saved in the Sarvice list with in available state
    And the requests list will be empty

  Scenario: no requests to add Service
    Given that the administrator is logged in
    And request list dosn't have Service to rent
    Then the message appear to tell the admin that request list empty
