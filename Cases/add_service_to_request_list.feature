Feature: Add Service information
  Scenario: empty information
    When user click on insert order and flag is 'true'
    Then the field 'ID' should be with error
    Then the field 'type' should be with error
    And  the field 'Name' should be with error
    And  the field 'Price' should be with error
    And  the field 'phone' should be with error
    And  the image 'picture' should be with error

  Scenario: a successful Information
    When user click on insert order and flag is 'true'
    And he fill in  'Id' with '1'
    And he fill in  'Type' with 'Wedding Hall'
    And he fill in  'Name' with 'Royal'
    And he fill in  'phone' with '0595429100'
    And he fill in  'Price' with '1000'
    And he fill in  'Picture' with extension 'png'
    And he presses 'save' and flag is 'true'
    Then  the Service will be saved in the request list

  Scenario Outline: errors with input
    When user click on insert order and flag is 'true'
    And he fill in  'Id' with '<Id>'
    And he fill in  'Type' with '<Type>'
    And he fill in  'Name' with '<Name>'
    And he fill in  'Phone' with '<Phone>'
    And he fill in  'Price' with '<Price>'
    And he fill in  'Picture' with extension '<Picture>'
    And he presses 'save' and flag is 'true'
    Then the user shoude See '<message>'
    Examples:
    |Id|Type         |Name   |Phone       |Price|Picture|message                         |
    |1|Wedding Hall |Ro52yal|0595429100  |100  |png    |Invalid Name, please check it   |
    |1|Wedding Hall |Royal  |0595429100aa|100  |png    |Invalid Phone, please check it  |
    |1|Wedding Hall |Royal  |059542910000|100  |png    |Invalid Phone, please check it  |
    |1|Wedding Hall |Royal  |0595429100  |100ax|png    |Invalid Price, please check it  |
    |1|Wedding Hall |Royal  |0595429100  |100  |abc    |Invalid Picture, please check it|

