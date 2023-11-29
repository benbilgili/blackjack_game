package blackjack_game;


import java.util.Scanner;

public class PlayBlackjack {
    public static void main(String[] args) throws InterruptedException {

        // Initialise Players and Game
        Player player = new Player();
        Dealer dealer = new Dealer();
        Game game = new Game(dealer, player);

        // Shuffle New Deck
        game.setDeckId(dealer.shufflesCards());
        // System.out.println(game.getDeckId());

        // Deal 2 cards to each player
        game.initialDeal();

        // Check if either player has blackjack
        game.determineBlackJack();



        while ((!dealer.getBustStatus() || !dealer.getHasStuck()) && (!player.getBustStatus() || !player.getHasStuck())) {

                Boolean playerDecision = false;
                if (!player.getHasStuck()) {
                    playerDecision = player.takeTurn();
                }
                if (playerDecision) {
                    System.out.println("PLAYER: " + player.drawCard(dealer.dealCard(game.getDeckId())));
                    if (player.getHand().calculateHandValue() > 21) {
                        System.out.println("Commiserations.. You lose" );
                        System.exit(0);
                    }
                } else {
                    player.setHasStuck(true);
                }



                Boolean dealerDecision = false;
                if (!dealer.getHasStuck()) {
                    dealerDecision = dealer.takeTurn();
                }
                if (dealerDecision) {
                    System.out.println("DEALER: " + dealer.drawCard(dealer.dealCard(game.getDeckId())));
                    if (dealer.getHand().calculateHandValue() > 21) {
                        System.out.println("Dealer Bust!");
                        System.out.println("Congrats! You win on " + String.valueOf(player.getHand().calculateHandValue()) );
                        System.exit(0);
                    }
                } else {
                    dealer.setHasStuck(true);
                }


            // If both players have stuck, determine the winner and exit game
            if (dealer.getHasStuck() && player.getHasStuck()) {
                System.out.println("Dealer Sticks");
                game.bothStuckDetermineWinner();
            }
        }
    }
}

