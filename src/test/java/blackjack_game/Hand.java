package blackjack_game;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int numOfAces;

    public void addToHand(Card card) {
        this.cards.add(card);

        // Increment numOfAces only if the added card is an ACE
        if ("ACE".equals(card.getValue())) {
            numOfAces++;
        }
    }

    public int getNumOfAces() {
        return numOfAces;
    }

    public void setNumOfAces(int numOfAces) {
        this.numOfAces = numOfAces;
    }

    public int calculateHandValue() {
        int handValue = 0;

        for (Card card : cards) {
            String value = card.getValue();

            if ("KING".equals(value) || "QUEEN".equals(value) || "JACK".equals(value)) {
                handValue += 10;
            } else if ("ACE".equals(value)) {
                // do nothing
            } else {
                handValue += Integer.parseInt(value);
            }
        }

        for (int i = 0; i < numOfAces; i++) {
            if (handValue + 11 <= 21) {
                handValue += 11;
            } else {
                handValue += 1;
            }
        }

        return handValue;
    }


}
