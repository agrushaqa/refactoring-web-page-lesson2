Feature: Translate words from english to russian

  Scenario: Translate word from english to russian
    Given word for translate
    When I search translate for "mother"
    Then translation page contains "матушка"

  Scenario: Translate word from russian to english
    Given word for translate
    When I search translate for "папа"
    Then translation page contains "father"