Feature: Service provider Profile

  Scenario: Show Service provider Profile
    Given that the Service provider is logged in
    And the Service provider has the following information:
      | Password  | Phone   | Address | Name    | ID |
      | 000       | 098866  | nablus  | mohamad | 2  |
    Then the Service provider profile appears with the provided information

  Scenario: Update Service provider Profile Successfully
    Given that the Service provider is logged in
    And the Service provider wants to update their profile information
    When the Service provider changes their password to "123456"
    And the Service provider changes their phone number to "0591234567"
    And the Service provider changes their address to "Ramallah"
    And the Service provider changes their name to "mohamad maher"
    And the Service provider clicks on the 'Save' button
    Then the Service provider profile should be updated successfully

  Scenario: Update Service provider Password
    Given that the Service provider is logged in
    And the Service provider wants to update their password
    When the Service provider enters the current password "000"
    And the Service provider enters the new password "123456"
    And the Service provider confirms the new password "123456"
    And the Service provider clicks on the 'Change Password' button
    Then the Service provider password should be updated successfully

  Scenario: Update Service provider Password with Incorrect Current Password
    Given that the Service provider is logged in
    And the Service provider wants to update their password
    When the Service provider enters an incorrect current password "111"
    And the Service provider enters the new password "123456"
    And the Service provider confirms the new password "123456"
    And the Service provider clicks on the 'Change Password' button
    Then an error message should appear for Service provider indicating the incorrect current password
