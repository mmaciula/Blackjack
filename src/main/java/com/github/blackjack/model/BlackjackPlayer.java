package com.github.blackjack.model;

import com.github.blackjack.service.PointsCalculator;
import com.google.inject.assistedinject.AssistedInject;

public class BlackjackPlayer extends Player {

    @AssistedInject
    public BlackjackPlayer(PointsCalculator pointsCalculator, String name){
        super(pointsCalculator, name);
    }

    @AssistedInject
    public BlackjackPlayer(PointsCalculator pointsCalculator, String name, boolean isDealer){
        super(pointsCalculator, name, isDealer);
    }
}
