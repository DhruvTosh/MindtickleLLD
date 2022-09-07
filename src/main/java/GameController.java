import Exceptions.NoSuchCardException;
import Strategy.PlayerStrategyFactory;
import Strategy.helper.CardWinTuple;
import models.Card;
import models.Deck;
import models.Player;
import models.SUIT;

import java.util.*;

public class GameController {

    static int ROUND_SIZE = 13;
    Map<Player, List<Card>> playersCardMap;

    public static void  main(String[] args) throws NoSuchCardException {
        List<Player> playerList = initializeGamePlay();
        SUIT trumpSuit = setTrumpSuit();
        System.out.println("Trump card for this game is " + trumpSuit);
        int winnerId = -1;
        //startGame
        PlayerStrategyFactory playerStrategyFactory = new PlayerStrategyFactory();
        //13 rounds
        for(int round = 1; round <= ROUND_SIZE; round++) {
            List<Player> playerOrderList = randomizePlayerOrder(playerList, winnerId);
            System.out.println("Starting Round" + round + " ->");
            SUIT suitForThisRound = null;
            Player currWinningPlayer = playerOrderList.get(0);
            Card currWinningCard = null;
            Map<Player, Card> removalOfCards = new HashMap<>();
            for(int playerOrder = 0; playerOrder < 4; playerOrder++) {
                Player currPlayer = playerOrderList.get(playerOrder);
                CardWinTuple cardTuple = playerStrategyFactory.playCard(playerOrderList.get(playerOrder).getCardList(), suitForThisRound, currWinningCard, trumpSuit);
                Card playedCard = cardTuple.getPlayedCard();
                if (cardTuple.playedWinningCard()) {
                    currWinningPlayer = currPlayer;
                    currWinningCard = playedCard;
                }
                System.out.println(currPlayer.getPlayerName() + " played " + playedCard.getCardVal() + " of " + playedCard.getSuit());
                removalOfCards.put(currPlayer, playedCard);

                if (suitForThisRound == null) {
                    suitForThisRound = playedCard.getSuit();
                }

            }
            System.out.println("Current Round won by " + currWinningPlayer.getPlayerName());

//            removalOfCards.forEach((a,b) -> {
//                try {
//                    a.removeCard(b);
//                } catch (NoSuchCardException e) {
//                    throw new RuntimeException(e);
//                }
//            });
            playerList.get(0).removeCard(removalOfCards.get(playerList.get(0)));
            playerList.get(1).removeCard(removalOfCards.get(playerList.get(1)));
            playerList.get(2).removeCard(removalOfCards.get(playerList.get(2)));
            playerList.get(3).removeCard(removalOfCards.get(playerList.get(3)));

            winnerId = currWinningPlayer.getPlayerId();
        }
    }

    private static List<Player> initializeGamePlay() {
        Deck deck = new Deck();
        List<Player> playersList = new ArrayList<>();
        List<Card> shuffledCardsOrder = deck.shuffleCards();
        Player p1 = new Player("Player 1", 0, shuffledCardsOrder.subList(0,13));
        Player p2 = new Player("Player 2", 1, shuffledCardsOrder.subList(13, 26));
        Player p3 = new Player("Player 3", 2, shuffledCardsOrder.subList(26, 39));
        Player p4 = new Player("Player 4", 3, shuffledCardsOrder.subList(39, 52));
        playersList.add(p1);
        playersList.add(p2);
        playersList.add(p3);
        playersList.add(p4);
        return playersList;
    }

    private static List<Player> randomizePlayerOrder(List<Player> playerList, int order) {
        if(order == -1) {
            Random random = new Random();
            order = random.nextInt(4);
        }

        List<Player> playerOrder = new ArrayList<>();
        for(int i = 0; i<4; i++) {
            playerOrder.add(playerList.get(order % 4));
            order++;
        }

        return playerOrder;
    }

    private static SUIT setTrumpSuit() {
        Random random = new Random();
        int trump = random.nextInt(4);

        switch(trump) {
            case 0:
                return SUIT.SPADE;
            case 1:
                return SUIT.HEART;
            case 2:
                return SUIT.CLUB;
            case 3:
                return SUIT.DIAMOND;
        }

        return SUIT.SPADE;
    }
}
