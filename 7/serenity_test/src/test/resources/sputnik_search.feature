Feature: Search info on https://www.sputnik.ru/

  Scenario: Search info about something on https://www.sputnik.ru/
    Given User open site for search
    When User search info about 'melody'
    Then User see list of result