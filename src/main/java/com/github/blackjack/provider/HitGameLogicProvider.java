package com.github.blackjack.provider;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.service.Randomizer;
import com.github.blackjack.service.gamelogic.implementation.HitGameLogicStrategy;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class HitGameLogicProvider implements Provider<HitGameLogicStrategy> {
    private static final Move SUPPORTED_MOVE_TYPE = Move.HIT;
    private Move supportedMoveType = SUPPORTED_MOVE_TYPE;
    private Randomizer<Card> cardRandomizer;

    @Inject
    public HitGameLogicProvider(Randomizer<Card> cardRandomizer) {
        this.cardRandomizer = cardRandomizer;
    }

    @Override
    public HitGameLogicStrategy get() {
        return new HitGameLogicStrategy(cardRandomizer, supportedMoveType);
    }

    public void setSupportedMoveType(Move supportedMoveType){
        this.supportedMoveType = supportedMoveType;
    }
}