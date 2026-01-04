Feature: Create supporter account


  Scenario: Successful registration
    Given that I am on the registration page
    When I fill in all required registration fields correctly
    And I submit the registration form
    Then the registration should be completed successfully


  Scenario: Last name missing in user registration
    Given that I am on the registration page
    When I fill in all required registration fields except last name
    And I submit the registration form
    Then an error message should appear informing me that last name is required


  Scenario: Passwords do not match in user registration
    Given that I am on the registration page
    When I fill in all required registration fields but use mismatched passwords
    Then an error message should appear informing me that the passwords do not match



  Scenario: Terms and conditions not accepted
    Given that I am on the registration page
    When I fill in all required registration fields except accepting terms and conditions
    And I submit the registration form
    Then an error message should appear informing me that terms and conditions must be accepted




