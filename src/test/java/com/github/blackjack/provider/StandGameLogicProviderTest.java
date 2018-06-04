package com.github.blackjack.provider;

import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.gamelogic.implementation.StandGameLogicStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StandGameLogicProviderTest {
    private Move supportedMoveType = Move.STAND;
    private StandGameLogicProvider provider;

    @Before
    public void setup() {
        this.provider = new StandGameLogicProvider();
    }

    @Test
    public void shouldReturnStandGameLogic() {
        StandGameLogicStrategy expected = new StandGameLogicStrategy(supportedMoveType);
        StandGameLogicStrategy result = provider.get();
        assertThat(result).isEqualToComparingFieldByField(expected);
    }
}