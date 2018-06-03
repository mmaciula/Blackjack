package com.github.blackjack.service.logic;

import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.CardSuit;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.PointsCalculator;
import com.github.blackjack.service.gamelogic.Logic;
import com.github.blackjack.service.gamelogic.implementation.StandGameLogicStrategy;
import com.github.blackjack.service.implementation.BlackjackPointsCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StandGameLogicStrategyTest {
    private Logic standGameLogicStratedy;
    private Move supportedMoveType;
    private PointsCalculator pointsCalculator;
    private Player player;

    @Before
    public void setup(){
        pointsCalculator =  new BlackjackPointsCalculator();
        player = new Player(pointsCalculator, "Player1");
        supportedMoveType = Move.STAND;
        standGameLogicStratedy = new StandGameLogicStrategy(supportedMoveType);
    }

    @Test
    public void shouldSetPlayerMoveFlagToTrue(){
        List<Card> deck = new ArrayList<>();
        standGameLogicStratedy.proccess(player, deck, supportedMoveType);

        assertThat(player.isEndedTurn()).isTrue();
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrownExceptionIfNotStrategyFound(){
        Card card = new BlackjackCard(CardSuit.SPADES, "7", -5);
        player.addCardToHand(card);
        standGameLogicStratedy.proccess(player, new ArrayList<>(), supportedMoveType);
    }
}
