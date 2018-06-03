package com.github.blackjack.service.gamelogic.implementation;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;
import com.github.blackjack.service.gamelogic.Logic;

import java.util.List;

public class FixedGameLogicStrategy implements Logic {
    private SpecialCase specialCase;
    private boolean supports;

    public FixedGameLogicStrategy(SpecialCase specialCase, boolean supports){
        this.specialCase = specialCase;
        this.supports = supports;
    }

    @Override
    public SpecialCase proccess(Player currentTurn, List<Card> deck, Move moveType) {
        return this.specialCase;
    }

    @Override
    public boolean supports(Move moveType) {
        return this.supports;
    }
}
