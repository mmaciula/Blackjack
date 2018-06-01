package com.github.blackjack.model;

import com.github.blackjack.service.PointsCalculator;
import com.google.inject.assistedinject.AssistedInject;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private PointsCalculator pointsCalculator;
    private List<Card> cardsInHand;
    private int points;
    private String name;
    private boolean isDealer;
    private boolean endedTurn;

    @AssistedInject
    public Player(PointsCalculator blackjackPointsCalculator, String name){
        pointsCalculator = blackjackPointsCalculator;
        cardsInHand = new ArrayList<>();
        points = 0;
        this.name = name;
        isDealer = false;
        endedTurn = false;
    }

    @AssistedInject
    public Player(PointsCalculator blackjackPointsCalculator, String name, boolean isDealer){
        pointsCalculator = blackjackPointsCalculator;
        cardsInHand = new ArrayList<>();
        points = 0;
        this.name = name;
        this.isDealer = isDealer;
        endedTurn = false;
    }

    public void addCardToHand(Card card) {
        cardsInHand.add(card);
        updatePoints();
    }

    private void updatePoints() {
        points = pointsCalculator.calculatePoints(cardsInHand);
    }

    public List<Card> getCardsInHand(){
        return cardsInHand;
    }

    public int getPoints(){
        return points;
    }

    public String getName(){
        return name;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public boolean isEndedTurn() {
        return endedTurn;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDealer(boolean isDealer){
        this.isDealer = isDealer;
    }

    public void setEndedTurn(boolean endedTurn){
        this.endedTurn = endedTurn;
    }
}
