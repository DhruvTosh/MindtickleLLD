package models;

public class Card {
    private SUIT suit;
    private int cardVal;

    public Card( final SUIT suit, final int cardVal) {
        this.suit = suit;
        this.cardVal = cardVal;
    }

    public int getCardVal() {
        return cardVal;
    }

    public SUIT getSuit() {
        return suit;
    }
}
