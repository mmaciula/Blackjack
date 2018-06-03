package com.github.blackjack.service.gamelogic.implementation;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;
import com.github.blackjack.service.gamelogic.GameLogicBase;
import com.github.blackjack.service.gamelogic.Logic;

import java.util.List;

public class StandGameLogicStrategy extends GameLogicBase {
    public StandGameLogicStrategy(Move supportedMoveType){
        super(supportedMoveType);
    }

    @Override
    protected SpecialCase proccess(Player currentTurn, List<Card> deck) {
        Integer playerPoints = currentTurn.getPoints();
        currentTurn.setEndedTurn(true);
        return SpecialCase.get(playerPoints).orElseThrow(() -> new IllegalStateException("No Strategy For " + playerPoints));
    }
}
