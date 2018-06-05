package com.github.blackjack;

import com.github.blackjack.factory.DeckFactory;
import com.github.blackjack.factory.GameFactory;
import com.github.blackjack.factory.PlayerFactory;
import com.github.blackjack.factory.implementation.BlackjackDeckCreator;
import com.github.blackjack.model.Deck;
import com.github.blackjack.model.Player;
import com.github.blackjack.service.Game;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main (String[] args){
        try {
            MainModule module = new MainModule();
            Injector injector = Guice.createInjector(module);

            GameFactory gameFactory = injector.getInstance(GameFactory.class);
            DeckFactory blackjackDeckFactory = injector.getInstance(BlackjackDeckCreator.class);
            PlayerFactory playerFactory = injector.getInstance(PlayerFactory.class);

            Player player = playerFactory.createPlayer("Paul");
            Player dealer = playerFactory.createPlayer("Dealer", true);

            Deck deck = blackjackDeckFactory.createDeck();

            Game game = gameFactory.createGame(player, dealer, deck);
            game.start();
        }catch(Exception exp){
            exp.printStackTrace();
        }
    }
}