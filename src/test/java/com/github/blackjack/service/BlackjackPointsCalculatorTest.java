package com.github.blackjack.service;

import com.github.blackjack.factory.CardFactory;
import com.github.blackjack.factory.implementation.BlackjackCardCreator;
import com.github.blackjack.model.BlackjackCard;
import com.github.blackjack.model.Card;
import com.github.blackjack.model.enums.CardSuit;
import com.github.blackjack.service.implementation.BlackjackPointsCalculator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BlackjackPointsCalculatorTest {
    private PointsCalculator blackjackPointsCalculator;
    private CardFactory cardFactory;

    @Before
    public void setup(){
        cardFactory = mock(BlackjackCardCreator.class);
        when(this.cardFactory.createCard(Mockito.any(), Mockito.anyString())).thenCallRealMethod();
        blackjackPointsCalculator = new BlackjackPointsCalculator();
    }

    @Test
    public void shouldReturnAmountOfPoints(){
        List<Card> cards = new ArrayList<>();
        cards.add(cardFactory.createCard(CardSuit.HEARTS, "3"));
        cards.add(cardFactory.createCard(CardSuit.DIAMONDS, "Jack"));
        cards.add(cardFactory.createCard(CardSuit.SPADES, "Queen"));

        Integer expected = 23;
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnedAmountOfPointsWithAcesValuedOf1(){
        List<Card> cards = new ArrayList<>();
        cards.add(cardFactory.createCard(CardSuit.HEARTS, "10"));
        cards.add(cardFactory.createCard(CardSuit.SPADES, "Jack"));
        cards.add(cardFactory.createCard(CardSuit.DIAMONDS, "King"));
        cards.add(cardFactory.createCard(CardSuit.CLUBS, "Ace"));

        Integer expected = 31;
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnedAmountOfPointsWithAcesValuedOf10(){
        List<Card> cards = new ArrayList<>();
        cards.add(cardFactory.createCard(CardSuit.HEARTS, "10"));
        cards.add(cardFactory.createCard(CardSuit.DIAMONDS, "King"));
        cards.add(cardFactory.createCard(CardSuit.CLUBS, "Ace"));

        Integer expected = 21;
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnValueWithoutCalculatingHiddenCards(){
        List<Card> cards = new ArrayList<>();
        Card hiddenCard = cardFactory.createCard(CardSuit.HEARTS, "10");
        ((BlackjackCard)hiddenCard).setHidden(true);

        cards.add(cardFactory.createCard(CardSuit.DIAMONDS, "King"));
        cards.add(cardFactory.createCard(CardSuit.CLUBS, "Ace"));
        cards.add(hiddenCard);

        Integer expected = 21;
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnCorrectValueWhenAceIsHidden(){
        List<Card> cards = new ArrayList<>();
        Card hiddenAce = cardFactory.createCard(CardSuit.CLUBS, "ace");
        ((BlackjackCard)hiddenAce).setHidden(true);
        cards.add(cardFactory.createCard(CardSuit.DIAMONDS,"10"));

        Integer expected = 10;
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnCorrectValueWhenAceIsNotHidden(){
        List<Card> cards = new ArrayList<>();
        Card hidden = cardFactory.createCard(CardSuit.DIAMONDS, "5");
        ((BlackjackCard)hidden).setHidden(true);

        cards.add(hidden);
        cards.add(cardFactory.createCard(CardSuit.CLUBS, "ace"));

        Integer expected = 11;
        Integer result = blackjackPointsCalculator.calculatePoints(cards);

        assertThat(result).isEqualTo(expected);
    }
}
