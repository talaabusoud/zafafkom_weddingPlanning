Feature: Show and Delete Services by Admin
  As an admin

  Background:
    Given the admin is logged into the admin panel

  Scenario: Admin chooses to show services
    When the admin chooses to view the list of services
    Then the admin should see a list of all services

  Scenario: Admin chooses to delete a service
    Given a list of services is displayed
    When the admin chooses to delete a service by entering its ID
    Then the service should be deleted successfully
    And the updated list of services is displayed

  Scenario: Admin enters an invalid option
    When the admin enters an invalid option
    Then an error message is displayed indicating the choice is invalid

  Scenario: Admin enters an invalid service ID to delete
    Given a list of services is displayed
    When the admin attempts to delete a service with an invalid ID
    Then an error message is displayed indicating no service was found with that ID
