package ru.rsreu.officetechnics.data.devices;

import java.util.Arrays;

public enum TenderType {

    RECEIVING(0, "Заявка на получение"),
    TRANSFERRED(1, "Устройство отправлено работнику"),
    OUT_OF_STOCK(2, "Нет в наличии");

    private final int id;
    private final String text;

    TenderType(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public static TenderType getType(int id) {
        return Arrays.stream(TenderType.values()).filter(tenderType -> tenderType.id == id).findFirst().orElse(null);
    }
}
