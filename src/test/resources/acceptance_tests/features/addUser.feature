Feature: The application is capable to manage multiple users and handle sessions with logged users.

    Background:
        Given that the application has been started

    Scenario: Add a new user and login with it
        Given I have successfully logged in as admin
        When I add an user with name "pepe" and password "argento"
        Then I should be able to logout from admin account
        And I should be able to login as "pepe" with password "argento"
