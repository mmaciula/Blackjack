package com.github.blackjack.service.gamelogic.implementation;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;
import com.github.blackjack.service.gamelogic.Logic;

import java.util.ArrayList;
import java.util.List;

public class CompositeGameLogicStrategy implements Logic {
    private List<Logic> strategies;

    public CompositeGameLogicStrategy(List<Logic> strategy){
        strategies = strategy;
    }

    @Override
    public SpecialCase proccess(Player currentTurn, List<Card> deck, Move moveType) {
        return strategies.stream()
                .filter(logic -> logic.supports(moveType))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No Strategy For " + moveType))
                .proccess(currentTurn, deck, moveType);
    }

    @Override
    public boolean supports(Move moveType) {
        return strategies.stream().anyMatch(logic -> logic.supports(moveType));
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private List<Logic> strategies;

        public Builder(){
            strategies = new ArrayList<>();
        }

        public Builder registerStrategy(Logic strategy){
            strategies.add(strategy);
            return this;
        }

        public CompositeGameLogicStrategy build(){
            return new CompositeGameLogicStrategy(this.strategies);
        }
    }
}
