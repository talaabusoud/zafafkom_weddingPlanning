Feature: Update Service Information

  Scenario: Admin Cancels Service Update
    Given the admin is on the service update page
    When the admin decides to cancel the update
    And clicks on the 'Cancel' button
    Then all service information fields should be cleared

  Scenario: Admin Updates Service Information Successfully
    Given the admin is on the service update page
    And there is a service with ID 0
    When the admin updates the service information with valid values
    And clicks on the 'Update' button
    Then the service information should be updated successfully

  Scenario Outline: Error Handling for Invalid Service Information
    When the admin updates the service information with invalid values
    And clicks on the 'Update' button
    Then an error '<Message>' should be displayed
    Examples:
      | Type         | Name    | Phone       | Price | Picture | Message                             |
      | <InvalidType>| Ro52yal | 0595429100  | 100   | png     | Invalid Type, please check it       |
      | Wedding Hall | Royal   | 0595429100aa| 100   | png     | Invalid Phone, please check it       |
      | Wedding Hall | Royal   | 059542910000| 100   | png     | Invalid Phone, please check it       |
      | Wedding Hall | Royal   | 0595429100  | 100ax | png     | Invalid Price, please check it       |
      | Wedding Hall | Royal   | 0595429100  | 100   | abc     | Invalid Picture extension, please check it |

