Feature: Testing Kata Project PersonWrite API

  Scenario Outline: PersonWrite API returns expected
    Given server is running for PersonWrite
    When PersonWrite put is called with "<id>", "<firstname>", "<lastname>", "<role>", "<org>" and "<hiredate>"
    Then PersonWrite api returns "<id>", "<firstname>", "<lastname>", "<role>", "<org>" and "<hiredate>"
    Examples:
      | id      | firstname | lastname    | role    | org         | hiredate      | 
      | abc123  | Bob       | Johnson     | DEV     | SALES       | 2020-10-01    |
      | abc124  | Nancy     | Johnson     | MGR     | SERVICE     | 2016-12-31    |
      | abc125  | Andy      | Johnson     | OPS     | MARKETING   | 2024-05-10    | 
      | abc126  | Jose      | Johnson     | ANALYST | HR          | 2020-11-25    |
