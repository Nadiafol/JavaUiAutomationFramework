Feature: Register Flow Feature Test Suite
  Scenario: Access the account page after successful registration
    Given Home page is accessed
    And RegisterPage is accessed from HomePage menu
    When the registration form is completed with valid random data
    And the privacyToggle is enabled
    And continueButton is clicked
    Then the new page url contains "success" keyword


    Scenario: User is on the Register Page when the continue btn is not clicked during the register flow
      Given Home page is accessed
      And RegisterPage is accessed from HomePage menu
      When the registration form is completed with valid random data
      And the privacyToggle is enabled
      Then the new page url contains "register" keyword

