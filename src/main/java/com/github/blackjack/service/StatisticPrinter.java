package com.github.blackjack.service;

import com.github.blackjack.model.Player;

public interface StatisticPrinter {
    void printStatistics(Player player, Player dealer);
}
