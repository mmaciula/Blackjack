package com.github.blackjack.service.decision.implementation;

import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.decision.DecisionTaker;

public class DealerDecisionTaker implements DecisionTaker {
    @Override
    public Move getDecision(Player player, Player dealer, Player currentTurn) {
        if (!supports(player, dealer, currentTurn)){
            throw new IllegalArgumentException("Strategy doesn't support!");
        }
        dealer.makeAllCardsVisible();

        Integer dealerPoints = dealer.getPoints();
        Integer playerPoints = player.getPoints();

        if (playerPoints > dealerPoints){
            return Move.HIT;
        }
        return Move.STAND;
    }

    @Override
    public boolean supports(Player player, Player dealer, Player currentTurn) {
        return dealer == currentTurn;
    }
}
