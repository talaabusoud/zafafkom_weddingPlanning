Feature: Information for the product
  Scenario: empty information
    When user click on save and flag is 'true'
    Then the field 'Name' shoud be an error
    And the field i Quantity' shoud be an error
    And the field 'Size' shoud be an error
    And the field 'Color' shoud be an error
    And theimage'Picture' shoud be an error