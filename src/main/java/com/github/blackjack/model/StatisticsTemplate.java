package com.github.blackjack.model;

import java.util.List;

public class StatisticsTemplate {
    private String template;
    private String role;
    private String nickname;
    private List<Card> cards;
    private Integer points;

    public StatisticsTemplate(String template){
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String format() {
        String cardsInfo = prepareCards();
        return String.format(template, role, nickname, cardsInfo, points);
    }

    private String prepareCards() {
        StringBuilder cardsInfo = new StringBuilder();

        for (Card card : cards) {
            cardsInfo.append("(");
            if (((BlackjackCard)card).isHidden()) {
                cardsInfo.append("hidden");
            } else {
                cardsInfo.append(card.getCardRank())
                        .append(" ")
                        .append(card.getCardSuit());
            }
            cardsInfo.append(")").append(" ");
        }

        return cardsInfo.toString();
    }
}
