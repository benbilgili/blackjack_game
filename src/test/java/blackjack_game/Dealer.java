package blackjack_game;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static javax.swing.UIManager.get;

public class Dealer extends Player {
    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    // methods

    public String shufflesCards() {
        return given().log().all().
                    when().
                    get("https://www.deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1").
                    then().extract().path("deck_id");
    }


    public Card dealCard(String deckId) {

        Response response =

                given().pathParam("deckId", deckId).
                when().
                get("https://www.deckofcardsapi.com/api/deck/{deckId}/draw/?count=1");

        Map<String, Object> apiResponse = response.as(Map.class);
        Card card = Card.fromApiResponse(apiResponse);
        return card;
    }

    public Boolean takeTurn() {
//        System.out.println(getHand().calculateHandValue());
        if (getHand().calculateHandValue() == 17 && getHand().getNumOfAces() == 1 ) { // haven't tested this! They hit on a soft 17 - they might hit if they have 17 and two aces..
            return true;
        } else {
            return getHand().calculateHandValue() <= 16;
        }
        // need a check to see the number of aces the dealer has. (if total = 17 and numOfAces > 0, deal hits - return true? )

    }

}
