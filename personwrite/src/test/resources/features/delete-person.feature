Feature: Testing Kata Project PersonWrite API

  Scenario Outline: PersonWrite delete API returns expected
    Given PersonWrite is running and contains data
    When PersonWrite delete is called for "<id>"
    Then PersonWrite findBy returns empty
    Examples:
      | id      |
      | abc123  |
