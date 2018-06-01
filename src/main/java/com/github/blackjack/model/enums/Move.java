package com.github.blackjack.model.enums;

public enum Move {
    HIT("hit"),
    STAND("stand");

    private String name;

    Move(String name){
        this.name = name;
    }
}
