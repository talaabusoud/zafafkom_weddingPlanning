Feature: User Reservation Management
  Description: The user can edit and cancel/delete reservations.
  Actor: user

  Background:
    Given the user with id "1" is logged in
    And there exists a reservation with id "45" for the user

  Scenario: Edit reservation
    When the user wants to edit reservation number "45"
    And updates the reservation with new date "26/5/2024", time "3 PM", and duration "1.5h"
    Then the reservation is successfully updated

  Scenario: Edit reservation failed due to date
    When the user wants to edit reservation number "45"
    And updates the reservation with new date "24/5/2024", time "3 PM", and duration "1.5h"
    And the service is not available on that date
    Then the user cannot update the service
    And a message appears to inform the user of the unavailability

  Scenario: Edit reservation failed due to time
    When the user wants to edit reservation number "45"
    And updates the reservation with new date "25/5/2024", time "7 PM", and duration "3h"
    And the service is not available at time "7 PM" or for a duration of "3h"
    Then the user cannot update this service to that time or duration
    And a message appears to inform the user of the unavailability

  Scenario: Cancel/Delete reservation
    When the user wants to cancel/delete reservation number "45"
    And confirms the cancellation
    Then the reservation is successfully canceled/deleted

  Scenario: Attempt to edit non-existent reservation
    When the user wants to edit reservation number "78"
    And updates the reservation with new date "26/5/2024", time "3 PM", and duration "1.5h"
    Then an error message is displayed indicating the reservation does not exist

  Scenario: Attempt to cancel/delete non-existent reservation
    When the user wants to cancel/delete reservation number "78"
    And confirms the cancellation
    Then an error message is displayed indicating the reservation does not exist













