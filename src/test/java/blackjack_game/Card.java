package blackjack_game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

public class Card {
    private String value;
    private String suit;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public static Card fromApiResponse(Map<String, Object> apiResponse) {
        List<Map<String, Object>> cards = (List<Map<String, Object>>) apiResponse.get("cards");
        if (cards != null && !cards.isEmpty()) {
            Map<String, Object> cardData = cards.get(0);
            String value = (String) cardData.get("value");
            String suit = (String) cardData.get("suit");
            return new Card(value, suit);
        } else {
            System.out.println("API Response 'cards': " + cards);
            throw new RuntimeException("No cards found in API response");
        }
    }
}
