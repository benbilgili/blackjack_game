package blackjack_game;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private Hand hand;
    private Boolean isBust;
    private Boolean hasStuck;

    public Player() {
        this.hand = new Hand();
        this.isBust = false;
        this.hasStuck = false;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Boolean getBustStatus() {
        return isBust;
    }

    public void setBustStatus(Boolean bust) {
        isBust = bust;
    }

    public Boolean getHasStuck() {
        return hasStuck;
    }

    public void setHasStuck(Boolean hasStuck) {
        this.hasStuck = hasStuck;
    }

    public int drawCard(Card card) {
        this.hand.addToHand(card);
        System.out.println(card.getValue() + ": " + card.getSuit());
        return this.hand.calculateHandValue();
    }

    public Boolean takeTurn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("You're on " + String.valueOf(this.hand.calculateHandValue()) + ", do you want to draw a new card? (y/n)");
        String input = scanner.nextLine().trim().toLowerCase();

        if ("y".equals(input)) {
            return true;
        } else if ("n".equals(input)) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            return takeTurn(); // Ask again for valid input
        }
    }



}



