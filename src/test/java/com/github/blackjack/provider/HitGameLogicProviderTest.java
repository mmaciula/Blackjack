package com.github.blackjack.provider;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.Randomizer;
import com.github.blackjack.service.gamelogic.implementation.HitGameLogicStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HitGameLogicProviderTest {
    @Mock
    private Randomizer<Card> cardRandomizer;
    @InjectMocks
    private HitGameLogicProvider hitGameLogicStrategyProvider;
    private Move supportedMoveType = Move.HIT;

    @Test
    public void shouldReturnHitGameLogicStrategy(){
        HitGameLogicStrategy expected = new HitGameLogicStrategy(cardRandomizer, supportedMoveType);
        HitGameLogicStrategy result = hitGameLogicStrategyProvider.get();
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}