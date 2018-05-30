package com.github.blackjack.factory;

import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.enums.CardSuit;

public interface CardFactory {
    BlackjackCard createCard(CardSuit suit, String cardRank);
}
