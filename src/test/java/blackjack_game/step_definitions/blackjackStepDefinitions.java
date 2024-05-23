package blackjack_game.step_definitions;

import blackjack_game.Card;
import blackjack_game.Dealer;
import blackjack_game.Game;
import blackjack_game.Player;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class blackjackStepDefinitions {
    Dealer dealer;
    Player player;
    Game game;

    @Given("I have initialised a new game")
    public void i_have_initialised_game() {
        dealer = new Dealer();
        player = new Player();
        game = new Game(dealer, player);
    }

    @Given("I have shuffled the cards")
    public void i_have_shuffled_cards() {
        game.setDeckId(dealer.shufflesCards());
    }

    @When("I complete the initial deal")
    public void initial_deal() throws InterruptedException {
        game.initialDeal();
    }

    @Then("The player should have {int} cards")
    public void player_should_have_x_cards(int cardCount) {
        Assert.assertEquals("Player card count accurate", player.getHandLength(), cardCount);
    }

    @Then("The dealer should have {int} cards")
    public void dealer_should_have_x_cards(int cardCount) {
        Assert.assertEquals("Dealer card count accurate", dealer.getHandLength(), cardCount);
    }

    @Then("I confirm whether either player has blackjack")
    public void check_blackjack() {
        boolean gameShouldEndDueToBlackjack = player.getHand().calculateHandValue() == 21 || dealer.getHand().calculateHandValue() == 21;
        Assert.assertEquals("Game correctly assesses blackjack", gameShouldEndDueToBlackjack, game.determineBlackJack());
    }

    @Then("I confirm whether either player is bust")
    public void check_bust() {
        boolean playerBust = player.getHand().calculateHandValue() > 21;
        System.out.println("Player Hand Value: " + player.getHand().calculateHandValue());
        boolean dealerBust = dealer.getHand().calculateHandValue() > 21;
        System.out.println("Dealer Hand Value: " + dealer.getHand().calculateHandValue());
        Assert.assertEquals("Game correctly assesses whether player bust", playerBust, player.getBustStatus());
        Assert.assertEquals("Game correctly assesses whether dealer bust", dealerBust, dealer.getBustStatus());
    }

    @When("Both players take a turn")
    public void players_take_turn() {
        player.drawCard(dealer.dealCard(game.getDeckId()));
        dealer.drawCard(dealer.dealCard(game.getDeckId()));
    }

    @When("Player sticks")
    public void player_sticks() {
        player.setHasStuck(true);
    }

    @When("Dealer sticks")
    public void dealer_sticks() {
        dealer.setHasStuck(true);
    }

    @Then("I check who wins")
    public void check_winner() {
        String expectedWinner = null;
        boolean playerBust = player.getBustStatus();
        boolean dealerBust = dealer.getBustStatus();
        int playerValue = player.getHand().calculateHandValue();
        int dealerValue = dealer.getHand().calculateHandValue();

        System.out.println("Player Hand Value: " + playerValue);
        System.out.println("Dealer Hand Value: " + dealerValue);

        if (playerValue > dealerValue && !playerBust) {
            expectedWinner = "player";
        } else if (dealerValue > playerValue && !dealerBust) {
            expectedWinner = "dealer";
        } else if (dealerValue == playerValue) {
            expectedWinner = "draw";
        }

        Assert.assertEquals("Correct asserts winner", expectedWinner, game.bothStuckDetermineWinner());
    }




}
