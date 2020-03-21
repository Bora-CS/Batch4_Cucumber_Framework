Feature: Add Experience

  Scenario Outline: [UI & API] Add Experience API - UI end to end - Happy Path
    Given User sends a request to log in with username: "<username>" & password: "<password>" and have a valid token
    And User send a request to add experience with data: "<title>", "<company>", "<location>", "<from>", "<to>", "<current>", "<description>"
    And User receives a valid response status code: "<status code>"
    When User logs into BoraCommunity with username: "<username>" & password: "<password>"
    Then User should see the expeirence with detail: "<title>", "<company>", "<from>", "<to>" listed under Experience Credentials

    Examples: 
      | username       | password | title    | company | location   | from       | to | current | description                                           | status code |
      | murad@test.com | murad001 | Director | Google  | Reston, VA | 2019/02/22 |    | true    | I'm happy, and I'm rich, and my husband works for me! |         200 |
