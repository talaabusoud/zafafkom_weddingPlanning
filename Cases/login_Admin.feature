Feature: Admin Login

  Scenario: Admin successfully logs in with valid credentials
    Given the admin is not logged in to the app
    When the admin logs in with email "admin@example.com" and password "correctpassword"
    Then the admin should be directed to the admin page

  Scenario: Admin fails to log in with invalid email
    Given the admin is not logged in to the app
    When the admin logs in with email "wrongemail@example.com" and password "correctpassword"
    Then the admin should see a login failed message

  Scenario: Admin fails to log in with invalid password
    Given the admin is not logged in to the app
    When the admin logs in with email "admin@example.com" and password "wrongpassword"
    Then the admin should see a login failed message

  Scenario: Admin re-attempts login after failure
    Given the admin is not logged in to the app
    When the admin logs in with email "admin@example.com" and password "wrongpassword"
    And the admin tries to log in again with email "admin@example.com" and password "correctpassword"
    Then the admin should be directed to the admin page

  Scenario: Admin returns to the home page after failed login
    Given the admin is not logged in to the app
    When the admin logs in with email "admin@example.com" and password "wrongpassword"
    And the admin selects to return to the home page
    Then the admin should see the home page
