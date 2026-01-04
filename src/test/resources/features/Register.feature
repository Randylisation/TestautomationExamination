Feature: Create supporter account

  Background:
    Given that I am on the registration page

  Scenario: Successful registration
    When I fill in all required registration fields correctly
    And I submit the registration form
    Then the registration should be completed successfully


  Scenario: Last name missing in user registration
    When I fill in all required registration fields except last name
    And I submit the registration form
    Then an error message should appear informing me that last name is required


  Scenario Outline: Passwords do not match in user registration
    When I fill in all required registration fields but use mismatched passwords "<password>" and "<confirmPassword>"
    Then an error message should appear informing me that the passwords do not match

    Examples:
      | password       | confirmPassword        |
      | MyPassWord!    | YourPassword!          |
      | SpiceGirls4eva | V3ngaboys              |
      | UnsafePwrd5    | TheSafestPwrdInTheWrld3000 |


  #Comittad i Del 2 men görs om till Scenario Outline i Del 3. Kommenteras ut
  # Scenario: Passwords do not match in user registration
   # When I fill in all required registration fields but use mismatched passwords
    # Then an error message should appear informing me that the passwords do not match



  Scenario: Terms and conditions not accepted
    When I fill in all required registration fields except accepting terms and conditions
    And I submit the registration form
    Then an error message should appear informing me that terms and conditions must be accepted



  Scenario: Successful registration in Firefox
    Given that I am on the registration page using "firefox"
    When I fill in all required registration fields correctly
    And I submit the registration form
    Then the registration should be completed successfully
