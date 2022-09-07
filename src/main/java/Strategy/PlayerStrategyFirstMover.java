package Strategy;

import models.Card;
import Strategy.helper.CardWinTuple;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerStrategyFirstMover {

    public static CardWinTuple playBestCard(List<Card> cardList) {
        Collections.sort(cardList, new suitAgnosticSort());
        return new CardWinTuple(true, cardList.get(0));
    }

    private static class suitAgnosticSort implements Comparator<Card> {

        @Override
        public int compare(Card c1, Card c2) {
            return Integer.compare(c2.getCardVal(), c1.getCardVal());
        }
    }

}
