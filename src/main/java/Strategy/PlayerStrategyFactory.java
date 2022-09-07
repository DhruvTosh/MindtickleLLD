package Strategy;

import Strategy.helper.CardWinTuple;
import models.Card;
import models.SUIT;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlayerStrategyFactory {

    public PlayerStrategyFactory() {

    }

    public CardWinTuple playCard(@NotNull final List<Card> currentPlayerCardList, SUIT handSuit, Card currWinningCard, SUIT trumpSuit)  {
        CardWinTuple cardWinTuple;
        if(handSuit == null) {
            cardWinTuple = PlayerStrategyFirstMover.playBestCard(currentPlayerCardList);
        }
        else {
            cardWinTuple = PlayerStrategySubsequentMover.playBestCard(handSuit, currentPlayerCardList, currWinningCard, trumpSuit);
        }
        return cardWinTuple;
    }

}
