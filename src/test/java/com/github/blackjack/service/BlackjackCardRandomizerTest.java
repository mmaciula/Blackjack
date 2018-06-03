package com.github.blackjack.service;

import com.github.blackjack.factory.CardFactory;
import com.github.blackjack.factory.implementation.BlackjackCardCreator;
import com.github.blackjack.model.Card;
import com.github.blackjack.model.enums.CardSuit;
import com.github.blackjack.service.implementation.BlackjackCardRandomizer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackCardRandomizerTest {
    private Randomizer randomizer;
    private CardFactory cardFactory;

    @Before
    public void setup(){
        cardFactory = new BlackjackCardCreator();
        randomizer = new BlackjackCardRandomizer();
    }

    @Test
    public void shouldReturnRandomCard(){
        List<Card> deck = new ArrayList<>();
        deck.add(cardFactory.createCard(CardSuit.DIAMONDS, "2"));
        deck.add(cardFactory.createCard(CardSuit.HEARTS, "5"));
        deck.add(cardFactory.createCard(CardSuit.CLUBS, "Jack"));

        Card result = randomizer.randomizeCard(deck);

        assertThat(result).isIn(deck);
    }
}
