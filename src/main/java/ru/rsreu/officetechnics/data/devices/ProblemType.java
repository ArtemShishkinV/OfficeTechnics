package ru.rsreu.officetechnics.data.devices;

import java.util.Arrays;

public enum ProblemType {

    PROCESSING(0, "Обработка"),
    COMPLETED(1, "Выполнено"),
    NOT_REPAIRED(2, "Устройство ремонту не подлежит");

    private final int id;
    private final String text;

    ProblemType(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public static ProblemType getType(int id) {
        return Arrays.stream(ProblemType.values()).filter(problemType -> problemType.getId() == id).findFirst().orElse(null);
    }
}
