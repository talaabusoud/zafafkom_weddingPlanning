Feature: Admin Profile

  Scenario: Show Admin Profile
    Given that the admin is logged in
    And the admin has the following information:
      | Password  | Phone   | Address | Name    | ID |
      | 000       | 098866  | nablus  | mohamad | 2  |
    Then the admin profile appears with the provided information

  Scenario: Update Admin Profile Successfully
    Given that the admin is logged in
    And the admin wants to update their profile information
    When the admin changes their password to "123456"
    And the admin changes their phone number to "0591234567"
    And the admin changes their address to "Ramallah"
    And the admin changes their name to "mohamad maher"
    And the admin clicks on the 'Save' button
    Then the admin profile should be updated successfully

  Scenario: Update Admin Password
    Given that the admin is logged in
    And the admin wants to update their password
    When the admin enters the current password "000"
    And the admin enters the new password "123456"
    And the admin confirms the new password "123456"
    And the admin clicks on the 'Change Password' button
    Then the admin password should be updated successfully

  Scenario: Update Admin Password with Incorrect Current Password
    Given that the admin is logged in
    And the admin wants to update their password
    When the admin enters an incorrect current password "111"
    And the admin enters the new password "123456"
    And the admin confirms the new password "123456"
    And the admin clicks on the 'Change Password' button
    Then an error message should appear indicating the incorrect current password
