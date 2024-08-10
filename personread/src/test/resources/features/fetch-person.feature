Feature: Testing Kata Project PersonRead API

  Scenario Outline: PresonRead API returns expected
    Given server is running for PersonRead
    When PersonRead is called with "<id>"
    Then PersonRead api returns "<id>", "<firstname>" , "<lastname>" and "<role>"
    Examples:
      | id      | firstname | lastname    | role    |
      | abc123  | Dave      | Turner      | DEV     |