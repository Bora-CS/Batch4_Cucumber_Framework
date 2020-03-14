Feature: Add Experience API

	@Smoke @API
  Scenario Outline: [API] Add Experience
    Given User is logged in with username: "<username>" & password: "<password>" and have a valid token
    When User send a request to add experience with data: "<title>", "<company>", "<location>", "<from>", "<to>", "<current>", "<description>"
    Then User should receive a response status code: "<status code>"

    Examples: 
      | username             | password    | title    | company | location   | from       | to | current | description                                           | status code |
      | murad@test.com       | murad001    | Director | Google  | Reston, VA | 04-01-2020 |    | true    | I'm happy, and I'm rich, and my husband works for me! |         200 |
      | zeidwaseem@gmail.com | 20214039322 | Director | Google  | Reston, VA | 04-01-2020 |    | true    | I'm happy, and I'm rich, and my wife works for me!    |         200 |
      