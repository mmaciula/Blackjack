package com.github.blackjack.model;

import com.github.blackjack.model.enums.CardSuit;

import java.util.Objects;

public class Card {
    private CardSuit suit;
    private String cardRank;

    public Card(String cardRank, CardSuit suit){
        this.suit = suit;
        this.cardRank = cardRank;
    }

    public CardSuit getCardSuit(){
        return suit;
    }

    public String getCardRank(){
        return cardRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                Objects.equals(getCardRank(), card.getCardRank());
    }

    @Override
    public int hashCode() {

        return Objects.hash(suit, getCardRank());
    }
}
