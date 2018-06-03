package com.github.blackjack.service.gamelogic;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;

import java.util.List;

public abstract class GameLogicBase implements Logic {
    private Move supportedMoveType;

    public GameLogicBase(Move supportedMoveType){
        this.supportedMoveType = supportedMoveType;
    }

    @Override
    public SpecialCase proccess(Player currentTurn, List<Card> deck, Move moveType) {
        if (!supports(moveType)){
            throw new IllegalArgumentException(moveType + " is not supported!");
        }
        return proccess(currentTurn, deck);
    }

    @Override
    public boolean supports(Move moveType) {
        return supportedMoveType.equals(moveType);
    }

    protected abstract SpecialCase proccess(Player currentTurn, List<Card> deck);
}
