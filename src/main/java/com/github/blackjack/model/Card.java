package com.github.blackjack.model;

import com.github.blackjack.model.enums.CardSuit;

public class Card {
    private CardSuit suit;
    private String cardRank;

    public Card(String cardRank, CardSuit suit){
        this.suit = suit;
        this.cardRank = cardRank;
    }
}
