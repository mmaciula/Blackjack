package com.github.blackjack.provider;

import com.github.blackjack.service.decision.implementation.CompositeDecisionTaker;
import com.github.blackjack.service.decision.implementation.ConsoleDecisionTaker;
import com.github.blackjack.service.decision.implementation.DealerDecisionTaker;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CompositeDecisionTakerProvider implements Provider<CompositeDecisionTaker> {

    private DealerDecisionTaker dealerDecisionTaker;
    private ConsoleDecisionTaker consoleDecisionTaker;

    @Inject
    public CompositeDecisionTakerProvider(DealerDecisionTaker dealerDecisionTaker,
                                          ConsoleDecisionTaker consoleDecisionTaker) {
        this.dealerDecisionTaker = dealerDecisionTaker;
        this.consoleDecisionTaker = consoleDecisionTaker;
    }

    @Override
    public CompositeDecisionTaker get() {
        return CompositeDecisionTaker.builder()
                .registerStrategy(dealerDecisionTaker)
                .registerStrategy(consoleDecisionTaker)
                .build();
    }
}