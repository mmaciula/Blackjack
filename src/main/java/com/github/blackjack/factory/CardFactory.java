package com.github.blackjack.factory;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.enums.CardSuit;

public interface CardFactory {
    Card createCard(CardSuit suit, String cardRank);
}
