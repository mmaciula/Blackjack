package com.github.blackjack.factory;

import com.github.blackjack.factory.implementation.BlackjackCardCreator;
import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;
import com.github.blackjack.model.enums.CardSuit;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackjackCardCreatorTest {
    private CardFactory cardFactory;

    @Before
    public void setup(){
        cardFactory = new BlackjackCardCreator();
    }

    @Test
    public void createProperCard(){
        CardSuit firstSuit = CardSuit.DIAMONDS;
        String firstCardRank = "Jack";
        Card firstExpCard = new BlackjackCard(firstSuit, firstCardRank,10);

        CardSuit secondSuit = CardSuit.HEARTS;
        String secondCardRank = "5";
        Card secondExpCard = new BlackjackCard(secondSuit, secondCardRank,5);

        Card firstCard = cardFactory.createCard(firstSuit, firstCardRank);
        Card secondCard = cardFactory.createCard(secondSuit, secondCardRank);

        assertThat(firstExpCard).isEqualToComparingFieldByField(firstCard);
        assertThat(secondExpCard).isEqualToComparingFieldByField(secondCard);
    }
}
