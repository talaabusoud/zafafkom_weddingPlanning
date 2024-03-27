Feature: Show reservations and delete option by admin
  As an admin
  I want to view all reservations and have the option to delete any reservation
  So that I can manage reservations effectively

  Background:
    Given the admin is logged in to the admin panel

  Scenario: Admin views all reservations
    When the admin chooses to view the list of reservations
    Then the admin should see a list of all reservations

  Scenario: Admin deletes a reservation
    Given the admin is viewing the list of reservations
    When the admin chooses to delete a reservation
    And the admin enters the ID of the reservation to delete
    Then the reservation should be deleted successfully

  Scenario: Admin attempts to delete a non-existent reservation
    Given the admin is viewing the list of reservations
    When the admin chooses to delete a reservation
    And the admin enters an invalid reservation ID
    Then the system should inform the admin that no reservation was found with the ID

  Scenario: Admin exits the reservation view
    Given the admin is viewing the list of reservations
    When the admin chooses to exit the reservation view
    Then the admin should return to the main menu
