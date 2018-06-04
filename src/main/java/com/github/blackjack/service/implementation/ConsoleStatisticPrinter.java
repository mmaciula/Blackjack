package com.github.blackjack.service.implementation;

import com.github.blackjack.model.Player;
import com.github.blackjack.model.StatisticsTemplate;
import com.github.blackjack.service.StatisticPrinter;
import com.google.inject.Inject;

public class ConsoleStatisticPrinter implements StatisticPrinter {
    private StatisticsTemplate statisticsTemplate;

    @Inject
    public ConsoleStatisticPrinter(StatisticsTemplate statisticsTemplate) {
        this.statisticsTemplate = statisticsTemplate;
    }

    @Override
    public void printStatistics(Player player, Player dealer) {
        printStatisticsFor(player);
        printStatisticsFor(dealer);
    }

    private void printStatisticsFor(Player member) {
        String role = member.isDealer() ? "Dealer" : "Player";
        statisticsTemplate.setNickname(member.getName());
        statisticsTemplate.setRole(role);
        statisticsTemplate.setCards(member.getCardsInHand());
        statisticsTemplate.setPoints(member.getPoints());
        System.out.println(statisticsTemplate.format());
    }
}
