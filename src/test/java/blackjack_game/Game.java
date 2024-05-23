package blackjack_game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Dealer dealer;
    private Player player; // eventually make this a list
    private String deckId;

    public Game(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
        this.deckId = "";
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public void initialDeal() throws InterruptedException {

        System.out.println("PLAYER DRAWS CARD");
        player.drawCard(dealer.dealCard(getDeckId()));
        Thread.sleep(1000);

        System.out.println("DEALER DRAWS CARD");
        dealer.drawCard(dealer.dealCard(getDeckId()));
        Thread.sleep(1000);

        System.out.println("PLAYER DRAWS CARD");
        player.drawCard(dealer.dealCard(getDeckId()));
        Thread.sleep(1000);

        System.out.println("DEALER DRAWS CARD");
        System.out.println("DEALER HAS " + dealer.drawCard(dealer.dealCard(getDeckId())));
        Thread.sleep(1000);
    }



    public Boolean determineBlackJack() {
        if (player.getHand().calculateHandValue() == 21 && dealer.getHand().calculateHandValue() == 21) {
            System.out.println("WOW! Game tied with blackjack");
            return true;
        } else if (player.getHand().calculateHandValue() == 21) {
            System.out.println("Blackjack! You win!");
            return true;
        } else if (dealer.getHand().calculateHandValue() == 21) {
            System.out.println("Dealer Blackjack. You lose..." );
            return true;
        } else {
            return false;
        }
    }


    public String bothStuckDetermineWinner() {
        if (player.getHand().calculateHandValue() > dealer.getHand().calculateHandValue()) {
            System.out.println("Congrats! You win" );
            return "player";
        } else if (dealer.getHand().calculateHandValue() > player.getHand().calculateHandValue()) {
            System.out.println("Commiserations.. You lose" );
            return "dealer";
        } else {
            System.out.println("Game tied on " +  String.valueOf(player.getHand().calculateHandValue()));
            return "draw";
        }
    }





}
