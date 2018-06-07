package com.github.blackjack.model;

import com.github.blackjack.service.PointsCalculator;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class BlackjackPlayer extends Player {

    @AssistedInject
    public BlackjackPlayer(PointsCalculator pointsCalculator, @Assisted String name){
        super(pointsCalculator, name);
    }

    @AssistedInject
    public BlackjackPlayer(PointsCalculator pointsCalculator, @Assisted String name, @Assisted boolean isDealer){
        super(pointsCalculator, name, isDealer);
    }
}
