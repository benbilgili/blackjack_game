Feature: Blackjack

  Background:
    Given I have initialised a new game
    And I have shuffled the cards
    And I complete the initial deal

  Scenario: Deal and verify card count
    Then The player should have 2 cards
    And The dealer should have 2 cards

  Scenario: Deal and confirm if either player has blackjack
    Then I confirm whether either player has blackjack

  Scenario: Take turn and assert card count, bust status and blackjack
    When Both players take a turn
    Then The player should have 3 cards
    And The dealer should have 3 cards
    And I confirm whether either player has blackjack
    And I confirm whether either player is bust

  Scenario: Take turn, stick and assess winner
    When Both players take a turn
    And Player sticks
    And Dealer sticks
    Then I check who wins