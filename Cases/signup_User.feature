Feature: Signup User
  Description: a user signup by id, name, phoneNumber, address, city, street, email and password
  Actor:user

  Scenario Outline: One or More Empty Term
    Given you are in signup page
    When you write the "<id>", "<name>", "<PhoneNumber>", "<address>", "<city>", "<street>", "<email>" and "<password>"
    Then you should show please fill all informations
    Examples:
      | id   | name | PhoneNumber | address | city     | street | email | password |
      | 111  |      | 416         |         | Nablus   | rafedya|       | 123456   |

  Scenario Outline: duplicate id
    Given you are in signup page
    When you write the "<id>", "<name>", "<PhoneNumber>", "<address>", "<city>", "<street>", "<email>" and "<password>"
    Then You should Show please enter another id
    Examples:
      | id  | name  | PhoneNumber | address     | city     | street | email              | password |
      | 111 | tala  | 0594176240  | building 22 | Nablus   | rafedya| tala1234@gmail.com | 123456   |

  Scenario Outline: weak password
    Given you are in signup page
    When you write the "<id>", "<name>", "<PhoneNumber>", "<address>", "<city>", "<street>", "<email>" and "<password>"
    Then You should Show please enter more than five character
    Examples:
      | id  | name  | PhoneNumber | address     | city     | street | email              | password |
      | 111 | tala  | 0594176240  | building 22 | Nablus   | rafedya| tala1234@gmail.com | 123      |

  Scenario Outline: Added Successfully
    Given you are in signup page
    When you write the "<id>", "<name>", "<PhoneNumber>", "<address>", "<city>", "<street>", "<email>" and "<password>"
    Then The User Added Successfully
    Examples:
      | id  | name  | PhoneNumber | address     | city    | street  | email             | password |
      | 111 | tala  | 0594176240  | building 22 | Nablus  | rafedya | tala1234@gmail.com| 123456   |


