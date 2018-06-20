package com.github.blackjack.service.logic;

import com.github.blackjack.factory.CardFactory;
import com.github.blackjack.factory.implementation.BlackjackCardCreator;
import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.CardSuit;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;
import com.github.blackjack.service.Randomizer;
import com.github.blackjack.service.gamelogic.Logic;
import com.github.blackjack.service.gamelogic.implementation.HitGameLogicStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HitGameLogicStrategyTest {
    @Mock
    private Randomizer<Card> randomizer;
    private Move supportedMoveType;
    private Logic hitGameLogicStrategy;
    private CardFactory cardFactory;
    private Player player;
    private List<Card> deck;

    @Before
    public void setup(){
        cardFactory = new BlackjackCardCreator();
        supportedMoveType = Move.HIT;
        deck = new ArrayList<>();
        player = mock(Player.class);
        hitGameLogicStrategy = new HitGameLogicStrategy(randomizer, supportedMoveType);
        deck.add(cardFactory.createCard(CardSuit.CLUBS, "2"));
        deck.add(cardFactory.createCard(CardSuit.CLUBS, "8"));
        when(randomizer.randomizeCard(anyList())).thenReturn(this.cardFactory.createCard(CardSuit.DIAMONDS, "Ace"));
    }

    @Test
    public void shouldReturnResult(){
        when(player.getPoints()).thenReturn(20);
        SpecialCase result = hitGameLogicStrategy.proccess(player, deck, supportedMoveType);
        assertThat(result).isEqualTo(SpecialCase.FINE);
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrownExceptionWhenNoStrategyFoundForPoints(){
        when(player.getPoints()).thenReturn(-2);
        hitGameLogicStrategy.proccess(player, deck, supportedMoveType);
    }
}
