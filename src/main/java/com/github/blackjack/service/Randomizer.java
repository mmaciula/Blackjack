package com.github.blackjack.service;

import com.github.blackjack.model.Card;

import java.util.List;

public interface Randomizer<T extends Card> {
    T randomizeCard(List<T> deck);
}
