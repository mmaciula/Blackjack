package com.github.blackjack.factory;

import com.github.blackjack.model.Player;
import com.github.blackjack.service.PointsCalculator;

public interface PlayerFactory {
    Player createPlayer(String name);
    Player createPlayer(String name, boolean isDealer);
}
