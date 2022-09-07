package Strategy.helper;

import models.Card;

public class CardWinTuple {
    boolean playedWinningCard;
    Card playedCard;

    public CardWinTuple(boolean playedWinningCard, Card playedCard) {
        this.playedWinningCard = playedWinningCard;
        this.playedCard = playedCard;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public boolean playedWinningCard() {
        return playedWinningCard;
    }
}
