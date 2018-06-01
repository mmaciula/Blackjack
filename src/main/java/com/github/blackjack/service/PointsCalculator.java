package com.github.blackjack.service;

import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;

import java.util.List;

public interface PointsCalculator {
    Integer calculatePoints(List<Card> cards);
}
