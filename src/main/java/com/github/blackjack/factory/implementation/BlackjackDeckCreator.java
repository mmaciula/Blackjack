package com.github.blackjack.factory.implementation;

import com.github.blackjack.factory.CardFactory;
import com.github.blackjack.factory.DeckFactory;
import com.github.blackjack.model.BlackjackDeck;
import com.github.blackjack.model.Deck;
import com.github.blackjack.model.enums.CardSuit;
import com.google.inject.Inject;

public class BlackjackDeckCreator implements DeckFactory {
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
                                           "Jack", "Queen", "King", "Ace"};
    private CardFactory blackjackCardFactory;

    @Inject
    public BlackjackDeckCreator(CardFactory blackjackCardFactory){
        this.blackjackCardFactory = blackjackCardFactory;
    }

    @Override
    public Deck createDeck() {
        Deck deck = new BlackjackDeck();
        CardSuit[] cardSuits = CardSuit.values();

        for (String rank : RANKS){
            for (CardSuit suit : cardSuits){
                deck.addCard(blackjackCardFactory.createCard(suit, rank));
            }
        }
        return deck;
    }
}
