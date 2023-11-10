package blackjack_game;


public class PlayBlackjack {
    public static void main(String[] args) {
        Player winner;
        Player player = new Player();
        Dealer dealer = new Dealer();

        Game game = new Game(dealer, player);
        game.setDeckId(dealer.shufflesCards());

        System.out.println(game.getDeckId());

        System.out.println("PLAYER: " + player.drawCard(dealer.dealCard(game.getDeckId())));
        System.out.println("DEALER: " + dealer.drawCard(dealer.dealCard(game.getDeckId())));
        System.out.println("PLAYER: " + player.drawCard(dealer.dealCard(game.getDeckId())));
        System.out.println("DEALER: " + dealer.drawCard(dealer.dealCard(game.getDeckId())));

        if (player.getHand().calculateHandValue() == 21 && dealer.getHand().calculateHandValue() == 21) {
            System.out.println("WOW! Game tied with blackjack");
            System.exit(0);
        } else if (player.getHand().calculateHandValue() == 21) {
            winner = player;
            System.out.println("Blackjack! You win!");
            System.exit(0);
        } else if (dealer.getHand().calculateHandValue() == 21) {
            winner = dealer;
            System.out.println("Commiserations.. You lose" );
            System.exit(0);
        }

        while (!dealer.getBustStatus() && !player.getBustStatus()) {
            Boolean playerDecision = false;

            if (!player.getHasStuck()) {
                playerDecision = player.takeTurn();
            }

            if (playerDecision) {
                System.out.println("PLAYER: " + player.drawCard(dealer.dealCard(game.getDeckId())));
                if (player.getHand().calculateHandValue() > 21) {
                    winner = dealer;
                    System.out.println("Commiserations.. You lose" );
                    break;
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
                    winner = player;
                    System.out.println("Congrats! You win on " + String.valueOf(player.getHand().calculateHandValue()) );
                    break;
                }
            } else {
                dealer.setHasStuck(true);
            }

            if (dealer.getHasStuck() && player.getHasStuck()) {

                if (player.getHand().calculateHandValue() > dealer.getHand().calculateHandValue()) {
                    winner = player;
                    System.out.println("Congrats! You win" );
                    break;
                } else if (dealer.getHand().calculateHandValue() > player.getHand().calculateHandValue()) {
                    winner = dealer;
                    System.out.println("Commiserations.. You lose" );
                    break;
                } else {
                    System.out.println("Game tied on " +  String.valueOf(player.getHand().calculateHandValue()));
                    break;
                }
            }

        }


    }
}
