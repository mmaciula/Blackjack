package com.github.blackjack.model;

import com.github.blackjack.factory.CardFactory;
import com.github.blackjack.factory.implementation.BlackjackCardCreator;
import com.github.blackjack.model.enums.CardSuit;
import com.github.blackjack.service.PointsCalculator;
import com.github.blackjack.service.implementation.BlackjackPointsCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private PointsCalculator pointsCalculator;
    private CardFactory cardFactory;
    private Player player;

    @Before
    public void setup(){
        pointsCalculator = new BlackjackPointsCalculator();
        cardFactory = new BlackjackCardCreator();
        player = new Player(pointsCalculator, "Player1");
    }

    @Test
    public void shouldAddCardToHandAndUpdatePoints(){
        Card cardToAdd = cardFactory.createCard(CardSuit.DIAMONDS, "5");
        Integer expPoints = 5;
        Integer expDeckSize = 1;

        player.addCardToHand(cardToAdd);

        assertThat(player.getPoints()).isGreaterThanOrEqualTo(expPoints);
        assertThat(player.getCardsInHand().size()).isGreaterThanOrEqualTo(expDeckSize);
    }
}
