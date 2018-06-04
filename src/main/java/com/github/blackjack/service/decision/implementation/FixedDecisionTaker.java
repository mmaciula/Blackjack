package com.github.blackjack.service.decision.implementation;

import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.decision.DecisionTaker;

public class FixedDecisionTaker implements DecisionTaker {
    private Move decision;
    private boolean supports;

    public FixedDecisionTaker(Move decision, boolean supports){
        this.decision = decision;
        this.supports = supports;
    }

    @Override
    public Move getDecision(Player player, Player dealer, Player currentTurn) {
        return decision;
    }

    @Override
    public boolean supports(Player player, Player dealer, Player currentTurn) {
        return supports;
    }
}
