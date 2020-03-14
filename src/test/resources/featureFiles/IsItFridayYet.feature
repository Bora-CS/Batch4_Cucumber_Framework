Feature: Is it friday yet

  Scenario Outline: [UI] Everyone wants to know if its friday yet
    Given today is "<day>"
    When I ask if it's Friday yet
    Then I should be told "<answer>"

    Examples: 
      | day    | answer |
      | Friday | Yes    |
      | Sunday | Nope   |
      | Monday | Nope   |
