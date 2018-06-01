package com.github.blackjack.service.implementation;

import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;
import com.github.blackjack.service.PointsCalculator;

import java.util.List;

public class BlackjackPointsCalculator implements PointsCalculator {
    @Override
    public Integer calculatePoints(List<Card> cards) {
        Integer result = 0;

        for (Card card : cards){
            if (hiddenCard(card))
                continue;
            else if (isAce(card)){
                if ((result + ((BlackjackCard)card).getCardValue()) > 21){
                    result += 1;
                }
                else{
                    result += ((BlackjackCard)card).getCardValue();
                }
            }
            else{
                result += ((BlackjackCard)card).getCardValue();
            }
        }
        return result;
    }

    private boolean isAce(Card card){
        if (card.getCardRank().equalsIgnoreCase("ace")) return true;
        return false;
    }

    private boolean hiddenCard(Card card){
        if (((BlackjackCard)card).isHidden()) return true;
        return false;
    }
}
