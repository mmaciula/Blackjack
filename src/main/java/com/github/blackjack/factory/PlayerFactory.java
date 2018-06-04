package com.github.blackjack.factory;

import com.github.blackjack.model.Player;

public interface PlayerFactory {
    Player createPlayer(String name);
    Player createPlayer(String name, boolean isDealer);
}
