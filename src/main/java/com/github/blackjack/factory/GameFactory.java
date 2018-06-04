package com.github.blackjack.factory;

import com.github.blackjack.model.Deck;
import com.github.blackjack.model.Player;
import com.github.blackjack.service.Game;

public interface GameFactory {
    Game createGame(Player player, Player dealer, Deck blackcjackDeck);
}
