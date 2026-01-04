Feature: Create supporter account


  Scenario: Successful registration
    Given that I am on the registration page
    When I fill in all required registration fields correctly
    And I submit the registration form
    Then the registration should be completed successfully







