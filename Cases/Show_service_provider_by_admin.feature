Feature: Show service providers by admin

  Background:
    Given the admin is logged in to the admin panel

  Scenario: Admin views a list of service providers
    When the admin chooses to view the list of service providers
    Then the admin should see a list of all service providers
