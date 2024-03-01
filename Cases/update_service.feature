Feature: Admin updates a service

  Scenario Outline: Successful update process
    Given the service list has a service with ID <ExistingID>
    When the admin updates the service with ID <ExistingID> to have a new price <NewPrice> and status <NewStatus>
    Then the service with ID <ExistingID> should have its price updated to <NewPrice> and its status updated to <NewStatus>

    Examples:
      | ExistingID | NewPrice | NewStatus |
      | 1          | 150.0    | available |
      | 1          | 40.0     | discontinued |

  Scenario Outline: Update existing service with invalid price and status
    Given that the admin wants to update the service with ID <ExistingID> to have an invalid <NewPrice> and/or invalid <NewStatus>
    When the admin updates the service with ID <ExistingID>
    Then the error message should be equal to "<Message>"

    Examples:
      | ExistingID | NewPrice | NewStatus | Message                           |
      | 1          | -20.0    | available | Invalid Price!                    |
      | 1          | 19.5     | available | Invalid Price!                    |
      | 1          | 100      |           | Both Price and Status are missing|
