package com.github.blackjack.service.logic;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.gamelogic.Logic;
import com.github.blackjack.service.gamelogic.implementation.StandGameLogicStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StandGameLogicStrategyTest {
    private Logic standGameLogicStratedy;
    private Move supportedMoveType;
    private Player player;

    @Before
    public void setup(){
        player = mock(Player.class);
        supportedMoveType = Move.STAND;
        standGameLogicStratedy = new StandGameLogicStrategy(supportedMoveType);
    }

    @Test
    public void shouldSetPlayerMoveFlagToTrue(){
        List<Card> deck = new ArrayList<>();
        doCallRealMethod().when(player).setEndedTurn(anyBoolean());
        when(player.isEndedTurn()).thenCallRealMethod();
        standGameLogicStratedy.proccess(player, deck, supportedMoveType);

        assertThat(player.isEndedTurn()).isTrue();
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrownExceptionIfNotStrategyFound(){
        when(player.getPoints()).thenReturn(-1);
        standGameLogicStratedy.proccess(player, new ArrayList<>(), supportedMoveType);
    }
}
