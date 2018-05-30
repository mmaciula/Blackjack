package com.github.blackjack.factory.implementation;

import com.github.blackjack.factory.CardFactory;
import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.enums.CardSuit;

public class BlackjackCardCreator implements CardFactory {
    public BlackjackCard createCard(CardSuit suit, String cardRank) {
        int value;
        try {
            value = Integer.parseInt(cardRank);
        } catch (NumberFormatException e) {
            if (cardRank.equalsIgnoreCase("ace")) {
                value = 11;
            } else {
                value = 10;
            }
        }
        return new BlackjackCard(suit, cardRank, value);
    }
}
