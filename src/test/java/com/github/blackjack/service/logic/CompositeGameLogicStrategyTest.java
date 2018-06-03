package com.github.blackjack.service.logic;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;
import com.github.blackjack.service.gamelogic.Logic;
import com.github.blackjack.service.gamelogic.implementation.CompositeGameLogicStrategy;
import com.github.blackjack.service.gamelogic.implementation.FixedGameLogicStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CompositeGameLogicStrategyTest {
    private Logic gameLogicStrategy;
    private List<Logic> strategies;
    private Player player;
    private List<Card> deck;
    private Move moveType;

    @Before
    public void setup(){
        moveType = Move.HIT;
        deck = new ArrayList<>();
        strategies = new ArrayList<>();
        player = mock(Player.class);
        gameLogicStrategy = new CompositeGameLogicStrategy(strategies);
    }

    @Test
    public void shouldInvokeMethodOnOneStrategyAndReturnResult(){
        SpecialCase expected = SpecialCase.BLACKJACK;
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BLACKJACK, true));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));

        SpecialCase result = gameLogicStrategy.proccess(player, deck, moveType);
        assertThat(result).isEqualTo(expected);
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrownExceptionWithNoStrategyFound(){
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BLACKJACK, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));

        gameLogicStrategy.proccess(player, deck, moveType);
    }

    @Test
    public void shouldReturnTrueWhenStrategyFound(){
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BLACKJACK, true));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));

        boolean result = gameLogicStrategy.supports(moveType);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenStrategyNotFound(){
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BLACKJACK, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));
        strategies.add(new FixedGameLogicStrategy(SpecialCase.BURNED, false));

        boolean result = gameLogicStrategy.supports(moveType);

        assertThat(result).isFalse();
    }
}
