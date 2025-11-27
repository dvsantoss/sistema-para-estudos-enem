package br.ufrn.tads.model;

import java.time.LocalDate;

public class UserDailyStats {

    private LocalDate data;
    private int quest_certas;
    private int quest_erradas;

    public UserDailyStats(LocalDate data, int quest_certas, int quest_erradas) {
        this.data = data;
        this.quest_certas = quest_certas;
        this.quest_erradas = quest_erradas;
    }

    public LocalDate getData() {
        return data;
    }

    public int getQuest_certas() {
        return quest_certas;
    }

    public int getQuest_erradas() {
        return quest_erradas;
    }
}
