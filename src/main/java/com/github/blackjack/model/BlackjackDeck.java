package com.github.blackjack.model;

import com.github.blackjack.model.enums.CardSuit;

import java.util.ArrayList;
import java.util.List;

public class BlackjackDeck extends Deck {
    public BlackjackDeck(){
        super(new ArrayList<Card>(), 52);
    }

    public List<Card> getCards(){
        return cards;
    }

    @Override
    public void addCard(Card card){
        if (cards.size() > getMAX_AMOUNT_OF_CARDS()){
            throw new IllegalStateException("Deck is full!");
        }
        cards.add(card);
    }

    @Override
    public void removeCard(CardSuit suit, String cardRank){
        cards.removeIf(card -> card.getCardRank().equalsIgnoreCase(cardRank) && card.getCardSuit().equals(suit));
    }
}
