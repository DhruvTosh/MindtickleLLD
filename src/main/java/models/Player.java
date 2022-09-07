package models;

import Exceptions.NoSuchCardException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {// abstract??
    String playerName;
    int playerId;
    List<Card> cardList = new ArrayList<>();

    public Player(final String playerName, final int playerId, List<Card> cardList) {
        this.playerName = playerName;
        this.cardList = cardList;
        this.playerId = playerId;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    private void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void displayCardsInHand() {
        printShuffledCardList(this.cardList);
    }

    private void printShuffledCardList(List<Card> cardList) {
        for(int i = 0; i<cardList.size();i++) {
            System.out.println(cardList.get(i).getCardVal() + " of " + cardList.get(i).getSuit());
        }
    }

    //remove cards from cardList
    public void removeCard(Card card) throws NoSuchCardException {
        List<Card> oldCardList = new ArrayList<>();
        oldCardList.addAll(this.getCardList());
        this.cardList.clear();
        List<Card> newCardList = new ArrayList<>();
        for(int i = 0; i<oldCardList.size(); i++) {
            if (oldCardList.get(i) != card) {
                newCardList.add(oldCardList.get(i));
            }
        }
        setCardList(newCardList);
    }
}
