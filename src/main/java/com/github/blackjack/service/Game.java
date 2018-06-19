package com.github.blackjack.service;

import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;
import com.github.blackjack.model.Deck;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;
import com.github.blackjack.service.decision.DecisionTaker;
import com.github.blackjack.service.gamelogic.Logic;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class Game {
    private static final Integer AMOUNT_OF_CARDS_TO_DRAW_ON_START = 2;

    private Randomizer<Card> cardRandomizer;
    private StatisticPrinter statisticsPrinter;
    private DecisionTaker decisionTaker;
    private Logic gameLogic;

    private Deck deck;
    private Player dealer;
    private Player player;

    private Player currentTurn;
    private boolean isGameOn = false;

    @AssistedInject
    public Game(
            Randomizer<Card> cardRandomizer,
            StatisticPrinter statisticsPrinter,
            DecisionTaker decisionTaker,
            Logic gameLogic,
            @Assisted Deck blackjackDeck,
            @Assisted(value = "player") Player player,
            @Assisted(value = "dealer") Player dealer) {
        this.deck = blackjackDeck;
        this.player = player;
        this.cardRandomizer = cardRandomizer;
        this.statisticsPrinter = statisticsPrinter;
        this.gameLogic = gameLogic;
        this.decisionTaker = decisionTaker;
        this.dealer = dealer;
        this.currentTurn = player;
    }

    public void start() {
        isGameOn = true;
        addRandomizedCardsFromDeckToMembersHand(player);
        addRandomizedCardsFromDeckToMembersHand(dealer);
        statisticsPrinter.printStatistics(player, dealer);
        do {
            Move decision = decisionTaker.getDecision(player, dealer, currentTurn);
            SpecialCase turnResult = gameLogic.proccess(currentTurn, deck.getCards(), decision);
            changeTurnIfPlayerHasEndedTurn();
            statisticsPrinter.printStatistics(player, dealer);

            if (turnResult == SpecialCase.BURNED) {
                endGame(turnResult.toString(), false, false);
            } else if (turnResult == SpecialCase.BLACKJACK) {
                endGame(turnResult.toString(), true, false);
            }

            decideWhoWins();
        } while (isGameOn);
    }

    private void endGame(String reason, boolean currentTurnWinner, boolean draw) {
        this.isGameOn = false;
        if (draw) {
            System.out.println(reason);
            return;
        }

        if (currentTurnWinner) {
            System.out.println(currentTurn.getName() + " wins because " + reason);
        } else {
            System.out.println(currentTurn.getName() + " lose because " + reason);
        }
    }

    private void decideWhoWins() {
        if (player.isEndedTurn() && dealer.isEndedTurn()) {
            if (player.getPoints().equals(dealer.getPoints())) {
                endGame("DRAW", false, true);
            } else if (dealer.getPoints() > player.getPoints()) {
                endGame("HAVE LESS POINTS", true, false);
            } else if (player.getPoints() > dealer.getPoints()) {
                endGame("HAVE MORE POINTS", false, true);
            }
        }
    }

    private void changeTurnIfPlayerHasEndedTurn() {
        if (player.isEndedTurn()) {
            this.currentTurn = dealer;
        }
    }


    private void addRandomizedCardsFromDeckToMembersHand(Player member) {
        for (int i = 0; i < AMOUNT_OF_CARDS_TO_DRAW_ON_START; i++) {
            Card card = cardRandomizer.randomizeCard(deck.getCards());
            deck.removeCard(card.getCardSuit(), card.getCardRank());
            if (member.isDealer() && i == (AMOUNT_OF_CARDS_TO_DRAW_ON_START - 1)) {
                ((BlackjackCard)card).setHidden(true);
            }
            member.addCardToHand(card);
        }
    }
}
