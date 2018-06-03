package com.github.blackjack.service.gamelogic.implementation;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;
import com.github.blackjack.service.Randomizer;
import com.github.blackjack.service.gamelogic.GameLogicBase;

import java.util.List;

public class HitGameLogicStrategy extends GameLogicBase {
    private Randomizer<Card> cardRandomizer;

    public HitGameLogicStrategy(Randomizer<Card> cardRandomizer, Move supportedMoveType){
        super(supportedMoveType);
        this.cardRandomizer = cardRandomizer;
    }

    @Override
    protected SpecialCase proccess(Player currentTurn, List<Card> deck) {
        Card randomCard = cardRandomizer.randomizeCard(deck);
        currentTurn.addCardToHand(randomCard);
        deck.remove(randomCard);
        Integer playerPoints = currentTurn.getPoints();
        return SpecialCase.get(playerPoints).orElseThrow(() -> new IllegalStateException("No Strategy For " + playerPoints));
    }
}
