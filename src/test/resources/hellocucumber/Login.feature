Feature: Login
  BoraTech Community Application Login

  Scenario: Log in with valid credentials
    Given I'm on BoraTech Community homepage
    When I click on LogIn button
    And I enter username "murad@test.com" and password "murad001"
    And submit
    Then I should be logged in
    And See the Dashboard page

  Scenario Outline: Log in with invalid credentials
    Given I'm on BoraTech Community homepage
    When I click on LogIn button
    And I enter username "<email>" and password "<password>"
    And submit
    Then I should not be logged in
    And I will not see the Dashboard page

    Examples: 
      | email                      | password             |
      | murad@test.com             | thisIsAWrongPassword |
      | muradFakeUserName@test.com | murad001             |
