package com.github.blackjack.service.gamelogic;

import com.github.blackjack.model.Card;
import com.github.blackjack.model.Player;
import com.github.blackjack.model.enums.Move;
import com.github.blackjack.model.enums.SpecialCase;

import java.util.List;

public interface Logic {
    SpecialCase proccess(Player currentTurn, List<Card> deck, Move moveType);
    boolean supports(Move moveType);
}
