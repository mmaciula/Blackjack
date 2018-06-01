package com.github.blackjack.model;

import com.github.blackjack.model.enums.CardSuit;

import java.util.Objects;

public class BlackjackCard extends Card {
    private int cardValue;
    private boolean isHidden;

    public BlackjackCard(CardSuit suit, String cardRank, int value){
        super(cardRank, suit);
        cardValue = value;
        isHidden = false;
    }

    public int getCardValue(){
        return cardValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlackjackCard)) return false;
        if (!super.equals(o)) return false;
        BlackjackCard that = (BlackjackCard) o;
        return getCardValue() == that.getCardValue() &&
                isHidden() == that.isHidden();
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getCardValue(), isHidden());
    }

    public boolean isHidden(){
        return isHidden;

    }
}
