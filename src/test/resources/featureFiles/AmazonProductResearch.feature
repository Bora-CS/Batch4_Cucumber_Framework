Feature: Amazon Search

  Scenario Outline: [UI] Product Price Research
    Given I'm on the amazon.com homepage
    When I search for an "<Item>"
    And I get some results related to my search
    Then I will be able to find out the highest price, lowest price and average price

    Examples: 
      | Item        |
      | humidifiers |
      #| iPhone Case |
      #| Shampoo     |
