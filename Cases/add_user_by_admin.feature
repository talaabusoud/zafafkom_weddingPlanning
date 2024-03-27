Feature: Add user by admin

  Scenario: Admin adds a new admin successfully
    Given the admin is logged in to the admin panel
    When the admin chooses to add a new user
    And selects the admin user type
    And enters the new admin's name, email, phone, address, and password
    Then the new admin should be added to the system

  Scenario: Admin adds a new service provider successfully
    Given the admin is logged in to the admin panel
    When the admin chooses to add a new user
    And selects the service provider user type
    And enters the new service provider's name, email, phone, address, and password
    Then the new service provider should be added to the system

  Scenario: Admin adds a new user successfully
    Given the admin is logged in to the admin panel
    When the admin chooses to add a new user
    And selects the user user type
    And fills in the sign-up form with valid data
    Then the new user should be added to the system

  Scenario: Admin chooses to exit the add user process
    Given the admin is logged in to the admin panel
    When the admin chooses to add a new user
    And selects the option to exit
    Then no new user should be added and the admin returns to the admin menu

  Scenario: Admin enters invalid input when adding a new admin
    Given the admin is logged in to the admin panel
    When the admin chooses to add a new user
    And selects the admin user type
    And enters an invalid email for the new admin
    Then the system should prompt the admin to enter a valid email