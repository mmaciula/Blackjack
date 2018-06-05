package com.github.blackjack.service.decision.implementation;

import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.PointsCalculator;
import com.github.blackjack.service.decision.DecisionTaker;
import com.google.inject.Inject;

import java.util.List;

public class DealerDecisionTaker implements DecisionTaker {
    private PointsCalculator blackjackPointsCalculator;

    @Inject
    public DealerDecisionTaker(PointsCalculator blackjackPointsCalculator){
        this.blackjackPointsCalculator = blackjackPointsCalculator;
    }

    @Override
    public Move getDecision(Player player, Player dealer, Player currentTurn) {
        if (!supports(player, dealer, currentTurn)){
            throw new IllegalArgumentException("Strategy doesn't support!");
        }
        setAllCardsVisible(dealer.getCardsInHand());
        dealer.updatePoints();

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

    private void setAllCardsVisible(List<Card> deck){
        for (Card card : deck){
            ((BlackjackCard) card).setHidden(false);
        }
    }
}
