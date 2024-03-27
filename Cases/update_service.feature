Feature: Update Service Information
  As a service provider


  Scenario Outline: Successfully updating service information
    Given the service provider is logged into their account
    And the service list has a service with ID <ServiceID> belonging to the provider
    When the service provider chooses to update the service with ID <ServiceID>
    And enters "<NewName>" for the new name
    And enters "<NewType>" for the new type
    And enters "<NewLocation>" for the new location
    And chooses "<StatusChoice>" for the new status
    And enters "<NewPrice>" for the new price
    And enters "<NewPhone>" for the new phone
    And enters "<NewImageURL>" for the new image URL
    Then the service should be updated successfully with the new information

    Examples:
      | ServiceID | NewName     | NewType | NewLocation | StatusChoice | NewPrice | NewPhone   | NewImageURL          |
      | 1         | Event Hall  | Hall    | Downtown    | 1            | 2000     | 1234567890 | http://example.com/hall.jpg |
      | 2         | Catering    | Food    | Uptown      | 2            | 1500     | 0987654321 | http://example.com/food.jpg |

  Scenario: Attempting to update service with invalid ID
    Given the service provider is logged into their account
    When the service provider attempts to update a service with an invalid ID <InvalidID>
    Then an error message is displayed indicating no service was found with that ID
