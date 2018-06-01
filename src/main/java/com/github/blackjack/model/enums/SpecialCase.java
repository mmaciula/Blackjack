package com.github.blackjack.model.enums;

import java.util.Optional;
import java.util.function.Predicate;

public enum SpecialCase {
    FINE(points -> (points >= 0 && points < 21), "OK."),
    BURNED(points -> points > 21, "Burned!"),
    BLACKJACK(points -> (points.equals(21)), "Blackjack!");

    private final Predicate<Integer> predicate;
    private final String action;

    SpecialCase(Predicate<Integer> predicate, String action){
        this.predicate = predicate;
        this.action = action;
    }

    public static Optional<SpecialCase> get(Integer points) {
        SpecialCase resultValue = null;

        for (SpecialCase specialCase : values()) {
            if (specialCase.predicate.test(points)) {
                resultValue = specialCase;
            }
        }
        return Optional.ofNullable(resultValue);
    }

    @Override
    public String toString() {
        return this.action;
    }
}
