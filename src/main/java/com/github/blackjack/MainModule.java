package com.github.blackjack;

import com.github.blackjack.factory.CardFactory;
import com.github.blackjack.factory.DeckFactory;
import com.github.blackjack.factory.GameFactory;
import com.github.blackjack.factory.PlayerFactory;
import com.github.blackjack.factory.implementation.BlackjackCardCreator;
import com.github.blackjack.factory.implementation.BlackjackDeckCreator;
import com.github.blackjack.model.*;
import com.github.blackjack.provider.*;
import com.github.blackjack.service.PointsCalculator;
import com.github.blackjack.service.Randomizer;
import com.github.blackjack.service.StatisticPrinter;
import com.github.blackjack.service.decision.DecisionTaker;
import com.github.blackjack.service.gamelogic.Logic;
import com.github.blackjack.service.gamelogic.implementation.HitGameLogicStrategy;
import com.github.blackjack.service.gamelogic.implementation.StandGameLogicStrategy;
import com.github.blackjack.service.implementation.BlackjackCardRandomizer;
import com.github.blackjack.service.implementation.BlackjackPointsCalculator;
import com.github.blackjack.service.implementation.ConsoleStatisticPrinter;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class MainModule extends AbstractModule {
    protected void configure(){
        bind(DeckFactory.class).to(BlackjackDeckCreator.class);
        bind(CardFactory.class).to(BlackjackCardCreator.class);
        bind(PointsCalculator.class).to(BlackjackPointsCalculator.class);
        bind(Logic.class).toProvider(CompositeGameLogicStrategyProvider.class);
        bind(new TypeLiteral<Randomizer<Card>>() {
        }).to(BlackjackCardRandomizer.class);
        bind(DecisionTaker.class).toProvider(CompositeDecisionTakerProvider.class);
        bind(StatisticPrinter.class).to(ConsoleStatisticPrinter.class);
        bind(HitGameLogicStrategy.class).toProvider(HitGameLogicProvider.class);
        bind(StandGameLogicStrategy.class).toProvider(StandGameLogicProvider.class);
        install(new FactoryModuleBuilder().build(GameFactory.class));
        install(new FactoryModuleBuilder()
                .implement(Player.class, BlackjackPlayer.class)
                .build(PlayerFactory.class));
        bind(StatisticsTemplate.class).toProvider(StatisticsTemplateProvider.class);
    }
}
