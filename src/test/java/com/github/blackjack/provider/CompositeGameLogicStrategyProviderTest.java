package com.github.blackjack.provider;

import com.github.blackjack.service.gamelogic.implementation.CompositeGameLogicStrategy;
import com.github.blackjack.service.gamelogic.implementation.HitGameLogicStrategy;
import com.github.blackjack.service.gamelogic.implementation.StandGameLogicStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CompositeGameLogicStrategyProviderTest {
    @Mock
    private HitGameLogicStrategy hitGameLogicStrategy;
    @Mock
    private StandGameLogicStrategy standGameLogicStrategy;
    @InjectMocks
    private CompositeGameLogicStrategyProvider provider;

    @Test
    public void shouldReturnCompositeGameLogicWithTwoStrategies() {
        CompositeGameLogicStrategy expected = CompositeGameLogicStrategy.builder()
                .registerStrategy(hitGameLogicStrategy)
                .registerStrategy(standGameLogicStrategy)
                .build();
        CompositeGameLogicStrategy result = provider.get();
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}