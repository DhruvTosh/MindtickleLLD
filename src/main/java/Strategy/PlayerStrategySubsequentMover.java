package Strategy;

import Strategy.helper.CardWinTuple;
import models.Card;
import models.SUIT;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PlayerStrategySubsequentMover {
    public static CardWinTuple playBestCard(SUIT handSuit, @NotNull final List<Card> cardList, Card currentWinningCard, SUIT trumpSuit) {
        boolean isSuitHandAvailable = checkIfSameSuitCardIsAvailable(handSuit, cardList);
        if(isSuitHandAvailable) {
            List<Card> sortedListOfHandSuitCards = sortByHandSuit(handSuit, cardList);
            if(checkIfItIsAWinningChance(currentWinningCard, sortedListOfHandSuitCards.get(0), trumpSuit)) {
                return new CardWinTuple(true, sortedListOfHandSuitCards.get(0));
            }
            else {
                return new CardWinTuple(false, sortedListOfHandSuitCards.get(sortedListOfHandSuitCards.size()-1));
            }
        }
        else {
            List<Card> sortedListOfTrumpCards = sortByTrumpSuit(trumpSuit, cardList);
            if(sortedListOfTrumpCards.size() == 0) {
                Collections.sort(cardList, new suitAgnosticSort());
                return new CardWinTuple(false, cardList.get(cardList.size()-1));
            }
            else if(checkIfItIsAWinningChance(currentWinningCard, sortedListOfTrumpCards.get(0),trumpSuit)) {
                return new CardWinTuple(true, sortedListOfTrumpCards.get(0));
            }
            else {
                return new CardWinTuple(false, sortedListOfTrumpCards.get(sortedListOfTrumpCards.size()-1));
            }
        }
    }

    private static boolean checkIfItIsAWinningChance(Card currentWinningCard, Card highCard, SUIT trumpSuit) {
        boolean winningChance = false;

        if(currentWinningCard.getSuit() == trumpSuit) {
            if(highCard.getSuit() != trumpSuit)
                return false;
            else if(highCard.getCardVal() < currentWinningCard.getCardVal()) {
                return false;
            }
            else {
                winningChance = true;
            }
        } else {
            if(highCard.getSuit() == trumpSuit) {
                winningChance = true;
            }
            else if(highCard.getSuit() == currentWinningCard.getSuit()) {
                if (highCard.getCardVal() < currentWinningCard.getCardVal()) {
                    return false;
                }
            }
        }
        return winningChance;
    }
    private static List<Card> sortByHandSuit(SUIT handSuit, List<Card> cardList) {
        List<Card> listOfHandSuitCardss = new ArrayList<>();
        for(int cardNo = 0; cardNo < cardList.size(); cardNo++) {
            if (cardList.get(cardNo).getSuit() == handSuit) {
                listOfHandSuitCardss.add(cardList.get(cardNo));
            }
        }
        Collections.sort(listOfHandSuitCardss, new suitAgnosticSort());
        return listOfHandSuitCardss;
    }

    public static List<Card> sortByTrumpSuit(SUIT trumpSuit, List<Card> cardList) {
        List<Card> listOfTrumpSuitCards = new ArrayList<>();
        for(int cardNo = 0; cardNo < cardList.size(); cardNo++) {
            if (cardList.get(cardNo).getSuit() == trumpSuit) {
                listOfTrumpSuitCards.add(cardList.get(cardNo));
            }
        }
        Collections.sort(listOfTrumpSuitCards, new suitAgnosticSort());
        return listOfTrumpSuitCards;
    }

    private synchronized static boolean checkIfSameSuitCardIsAvailable(final SUIT handSuit, final @NotNull List<Card> cardList) {
        boolean found;
        found = cardList.stream().anyMatch(card -> card.getSuit().equals(handSuit));
        return found;
    }

    private static class suitAgnosticSort implements Comparator<Card> {

        @Override
        public int compare(Card c1, Card c2) {
            return Integer.compare(c2.getCardVal(), c1.getCardVal());
        }
    }

}
