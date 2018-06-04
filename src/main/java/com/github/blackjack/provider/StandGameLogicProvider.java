package com.github.blackjack.provider;

import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.gamelogic.implementation.StandGameLogicStrategy;
import com.google.inject.Provider;

public class StandGameLogicProvider implements Provider<StandGameLogicStrategy> {
    private static final Move SUPPORTED_MOVE_TYPE = Move.STAND;
    private Move supportedMoveType = SUPPORTED_MOVE_TYPE;

    @Override
    public StandGameLogicStrategy get() {
        return new StandGameLogicStrategy(supportedMoveType);
    }

    public void setSupportedMoveType(Move supportedMoveType){
        this.supportedMoveType = supportedMoveType;
    }
}