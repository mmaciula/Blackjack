package com.github.blackjack.service.implementation;

import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;
import com.github.blackjack.service.PointsCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackPointsCalculator implements PointsCalculator {
    @Override
    public Integer calculatePoints(List<Card> cards) {
        return cards.stream()
                .filter(card -> !((BlackjackCard) card).isHidden())
                .map(card -> ((BlackjackCard) card).getCardValue())
                .collect(Collectors.toList())
                .stream()
                .reduce(0, (left, right) -> {
                    if (isAce(right) && (left + right) > 21){
                        left += 1;
                    } else {
                        left += right;
                    }
                    return left;
                });
    }

    private boolean isAce(Integer points) {
        return (points >= 11);
    }
}
