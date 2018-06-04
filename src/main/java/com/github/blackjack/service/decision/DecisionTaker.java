package com.github.blackjack.service.decision;

import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;

public interface DecisionTaker {
    Move getDecision(Player player, Player dealer, Player currentTurn);
    boolean supports(Player player, Player dealer, Player currentTurn);
}
