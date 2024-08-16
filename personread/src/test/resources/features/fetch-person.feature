Feature: Testing Kata Project PersonRead API

  Scenario Outline: PresonRead API returns expected
    Given server is running for PersonRead
    When PersonRead is called with "<id>"
    Then PersonRead api returns "<id>", "<firstname>", "<lastname>", "<role>", "<org>" and "<hiredate>"
    Examples:
      | id      | firstname | lastname    | role    | org         | hiredate      |
      | abc123  | Dave      | Turner      | DEV     | IT          | 2020-11-07    |