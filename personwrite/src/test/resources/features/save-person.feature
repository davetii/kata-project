Feature: Testing Kata Project PersonWrite API

  Scenario Outline: PersonWrite API returns expected
    Given server is running for PersonWrite
    When PersonWrite put is called with "<id>", "<firstname>" , "<lastname>" and "<role>"
    Then PersonWrite api returns "<id>", "<firstname>" , "<lastname>" and "<role>"
    Examples:
      | id      | firstname | lastname    | role    |
      | abc123  | Bob       | Johnson     | DEV     |
      | abc124  | Nancy     | Johnson     | MGR     |
      | abc125  | Andy      | Johnson     | BA      |
      | abc126  | Jose      | Johnson     | TESTER  |
