Feature: User Reservation
  Description: The user reserves services, and checks reservation status.
  Actor: user

  Feature: Show services in user page
  Scenario: show the services
    Given that the user logged in
    And the user want to show all services in different categories
    Then the services appear to him

  Scenario: Create new reservation
    Given the user with number "5" is logged in
    And the user wants to reserve a service with id "1"
    And the service with id "1" is available
    And the user enters date "25/5/2024", time "2 PM", and duration "1h"
    Then the reservation is registered successfully

  Scenario: Service not available due to date
    Given the user with number "5" is logged in
    And the user wants to reserve the service with id "1", date "1/5/2024", time "3 PM" and duration "2h"
    And the service with id "1" is not available on that date
    Then the user cannot reserve this service
    And a message appears to inform the user of the unavailability

  Scenario: Service not available due to time
    Given the user with number "5" is logged in
    And the user wants to reserve the service with id "1", date "25/5/2024", time "7 PM" and duration
    And the service with id "1" is not available at time "7 PM" or for a duration of "2h"
    Then the user cannot reserve this service
    And a message appears to inform the user of the unavailability

  Scenario: Check reservation status
    Given the user with number "3" is checking the status of reservation number "6"
    Then the reservation status is displayed

  Scenario: Receive reservation and payment
    Given the user with number "2" wants to receive his reservation with number "3" in a ready state
    When the user with number "2" requests to receive reservation number "3" and pay the debt
    Then the application acknowledges receipt, and the debt is paid