package com.github.blackjack.provider;

import com.github.blackjack.service.gamelogic.implementation.CompositeGameLogicStrategy;
import com.github.blackjack.service.gamelogic.implementation.HitGameLogicStrategy;
import com.github.blackjack.service.gamelogic.implementation.StandGameLogicStrategy;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CompositeGameLogicStrategyProvider implements Provider<CompositeGameLogicStrategy> {
    private HitGameLogicStrategy hitGameLogicStrategy;
    private StandGameLogicStrategy standGameLogicStrategy;

    @Inject
    public CompositeGameLogicStrategyProvider(HitGameLogicStrategy hitGameLogicStrategy, StandGameLogicStrategy standGameLogicStrategy) {
        this.hitGameLogicStrategy = hitGameLogicStrategy;
        this.standGameLogicStrategy = standGameLogicStrategy;
    }

    @Override
    public CompositeGameLogicStrategy get() {
        return CompositeGameLogicStrategy.builder()
                .registerStrategy(hitGameLogicStrategy)
                .registerStrategy(standGameLogicStrategy)
                .build();
    }
}