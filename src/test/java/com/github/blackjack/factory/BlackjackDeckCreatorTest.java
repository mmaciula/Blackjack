package com.github.blackjack.factory;

import com.github.blackjack.factory.implementation.BlackjackCardCreator;
import com.github.blackjack.factory.implementation.BlackjackDeckCreator;
import com.github.blackjack.model.Card;
import com.github.blackjack.model.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackDeckCreatorTest {
    private CardFactory cardFactory;
    private DeckFactory deckFactory;

    @Before
    public void setup(){
        cardFactory = new BlackjackCardCreator();
        deckFactory = new BlackjackDeckCreator(cardFactory);
    }

    @Test
    public void shouldCreate52CardDeck(){
        Deck deck = deckFactory.createDeck();

        assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @Test
    public void shouldCreateDeckContainingDistinctCarts(){
        Deck deck = deckFactory.createDeck();

        assertThat(deck.getCards()).doesNotHaveDuplicates();
    }

    @Test
    public void shouldCreateDeckWithAceValuedAs11(){
        Deck deck = deckFactory.createDeck();
        List<Card> aces = new ArrayList<>();
        for (Card card : deck.getCards()){
            if (card.getCardRank().equalsIgnoreCase("ace")){
                aces.add(card);
            }
        }

        assertThat(aces).extracting("cardValue").containsOnly(11);
    }
}
