package com.github.blackjack.service.decision;

import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.decision.implementation.CompositeDecisionTaker;
import com.github.blackjack.service.decision.implementation.FixedDecisionTaker;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CompositeDecisionTakerTest {
    private DecisionTaker compositeDecisionTaker;
    private List<DecisionTaker> strategies;
    private Player player;
    private Player dealer;

    @Before
    public void setup(){
        strategies = new ArrayList<>();
        compositeDecisionTaker = new CompositeDecisionTaker(strategies);
        player = mock(Player.class);
        dealer = mock(Player.class);
    }

    @Test
    public void shouldInvokeMethodOnOneStrategyAndReturnDecision(){
        Move expDecision = Move.HIT;
        DecisionTaker fixedDecisionTaker = new FixedDecisionTaker(expDecision, true);
        strategies.add(fixedDecisionTaker);
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));

        Move result = compositeDecisionTaker.getDecision(player, dealer, player);
        assertThat(result).isEqualTo(expDecision);
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrownExceptionWhenNoStrategyFound(){
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.HIT, false));
        strategies.add(new FixedDecisionTaker(Move.HIT, false));

        compositeDecisionTaker.getDecision(player, dealer, dealer);
    }

    @Test
    public void shouldReturnTrueIfStrategyFound(){
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, true));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));

        boolean result = compositeDecisionTaker.supports(player, dealer, dealer);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfSupportedStrategyNotFound(){
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));
        strategies.add(new FixedDecisionTaker(Move.STAND, false));

        boolean result = compositeDecisionTaker.supports(player, dealer, dealer);
        assertThat(result).isFalse();
    }
}
