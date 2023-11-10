package blackjack_game;

import java.util.ArrayList;

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


}
