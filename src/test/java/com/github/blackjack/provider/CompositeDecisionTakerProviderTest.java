package com.github.blackjack.provider;

import com.github.blackjack.service.decision.implementation.CompositeDecisionTaker;
import com.github.blackjack.service.decision.implementation.ConsoleDecisionTaker;
import com.github.blackjack.service.decision.implementation.DealerDecisionTaker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CompositeDecisionTakerProviderTest {
    @Mock
    private DealerDecisionTaker dealerDecisionTaker;
    @Mock
    private ConsoleDecisionTaker consoleDecisionTaker;
    @InjectMocks
    private CompositeDecisionTakerProvider provider;

    @Test
    public void shouldReturnDecisionTakerWithTwoStrategies() {
        CompositeDecisionTaker expected = CompositeDecisionTaker
                .builder()
                .registerStrategy(dealerDecisionTaker)
                .registerStrategy(consoleDecisionTaker)
                .build();
        CompositeDecisionTaker result = provider.get();
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}