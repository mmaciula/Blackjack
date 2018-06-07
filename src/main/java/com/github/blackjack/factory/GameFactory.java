package com.github.blackjack.factory;

import com.github.blackjack.model.Deck;
import com.github.blackjack.model.Player;
import com.github.blackjack.service.Game;
import com.google.inject.assistedinject.Assisted;

public interface GameFactory {
    Game createGame(@Assisted("player") Player player, @Assisted("dealer") Player dealer, @Assisted Deck blackcjackDeck);
}
