package com.github.blackjack.service.decision.implementation;

import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.decision.DecisionTaker;

import java.util.Scanner;

public class ConsoleDecisionTaker implements DecisionTaker {
    private static final Scanner READER = new Scanner(System.in);
    private static final Move[] availableMoves = Move.values();

    @Override
    public Move getDecision(Player player, Player dealer, Player currentTurn) {
        if (!supports(player, dealer, currentTurn)) {
            throw new IllegalArgumentException("Strategy doesn't supported!");
        }
        informAboutMoves(currentTurn);
        return getDecision();
    }

    @Override
    public boolean supports(Player player, Player dealer, Player currentTurn) {
        return player == currentTurn;
    }

    private void informAboutMoves(Player currentTurn){
        System.out.println("Current turn for : " + currentTurn.getName());
        System.out.println("Please select a move.");

        for (int i = 1; i <= availableMoves.length; i++) {
            Move availableMove = availableMoves[i - 1];
            System.out.println("type " + i + " for " + availableMove);
        }
    }

    private Move getDecision() {
        boolean hasDecision = false;
        Integer decision;
        do {
            decision = READER.nextInt();
            if (decision > 0 && decision <= availableMoves.length) {
                hasDecision = true;
            }
        } while (!hasDecision);
        return availableMoves[decision - 1];
    }
}
