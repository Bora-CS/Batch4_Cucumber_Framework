Feature: Login
  BoraTech Community Application Login

  Scenario: Log in with valid credentials
    Given I'm on BoraTech Community homepage 
    When I click on LogIn button
    And I enter valid username and valid password
    And submit
    Then I should be logged in
    And See the Dashboard page
    
  Scenario: Log in with valid credentials
    Given I'm on BoraTech Community homepage 
    When I click on LogIn button
    And I enter valid username and valid password
    And submit
    Then I should be logged in
    And See the Dashboard page