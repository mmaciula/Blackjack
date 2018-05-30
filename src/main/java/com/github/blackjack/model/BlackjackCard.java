package com.github.blackjack.model;

import com.github.blackjack.model.enums.CardSuit;

public class BlackjackCard extends Card {
    private int cardValue;
    private boolean isHidden;

    public BlackjackCard(CardSuit suit, String cardRank, int value){
        super(cardRank, suit);
        cardValue = value;
        isHidden = false;
    }
}
