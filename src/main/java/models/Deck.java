package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private List<Card> shuffleCards = new ArrayList<>();

    public Deck() {
        for (SUIT suit : SUIT.values()) {
            for (int value = 2; value <= 14; value++) {
                Card card = new Card(suit, value);
                this.cards.add(card);
                this.shuffleCards.add(card);
            }
        }
    }

    public List<Card> shuffleCards() {
        List<Card> shuffledList = new ArrayList<>();

        int deckSize = 52;
        Random random = new Random();
        while(deckSize>0) {
            int randomInt = random.nextInt(deckSize--);
            Card card = shuffleCards.get(randomInt);
            shuffleCards.remove(card);
            shuffledList.add(card);
        }

        return shuffledList;
    }



}
