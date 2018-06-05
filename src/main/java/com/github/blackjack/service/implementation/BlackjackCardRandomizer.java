package com.github.blackjack.service.implementation;

import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;
import com.github.blackjack.service.Randomizer;

import java.util.List;
import java.util.Random;

public class BlackjackCardRandomizer implements Randomizer<Card> {
    private static final Random RANDOM = new Random();

    @Override
    public Card randomizeCard(List deck) {
        Integer randIndex = RANDOM.nextInt(deck.size());
        return (Card) deck.get(randIndex);
    }
}
