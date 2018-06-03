package com.github.blackjack.model;

import com.github.blackjack.factory.CardFactory;
import com.github.blackjack.factory.DeckFactory;
import com.github.blackjack.factory.implementation.BlackjackCardCreator;
import com.github.blackjack.factory.implementation.BlackjackDeckCreator;
import com.github.blackjack.model.enums.CardSuit;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackDeckTest {
    private CardFactory cardFactory;
    private DeckFactory deckFactory;
    private Deck deck;

    @Before
    public void setup(){
        cardFactory = new BlackjackCardCreator();
        deckFactory = new BlackjackDeckCreator(cardFactory);
        deck = deckFactory.createDeck();
    }

    @Test
    public void shouldRemoveCardFromTheDeck(){
        CardSuit suit = CardSuit.DIAMONDS;
        String cardRank = "10";
        Card card = new BlackjackCard(suit, cardRank, 10);
        Integer expDeckSize = 51;

        deck.removeCard(suit, cardRank);

        assertThat(deck.getCards()).doesNotContain(card);
        assertThat(deck.getCards().size()).isEqualTo(expDeckSize);
    }
}
