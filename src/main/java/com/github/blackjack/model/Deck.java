package com.github.blackjack.model;

import com.github.blackjack.model.enums.CardSuit;

import java.util.List;

public abstract class Deck {
    protected List<Card> cards;
    private final int MAX_AMOUNT_OF_CARDS;

    public Deck(List<Card> cards, int maxNumberOfCards){
        this.cards = cards;
        MAX_AMOUNT_OF_CARDS = maxNumberOfCards;
    }

    public int getMAX_AMOUNT_OF_CARDS(){
        return MAX_AMOUNT_OF_CARDS;
    }

    public List<Card> getCards(){
        return cards;
    }

    public abstract void addCard(Card card);
    public abstract void removeCard(CardSuit suit, String cardRank);
}
