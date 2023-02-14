package br.com.quinino.domain.enums;

public enum Plans {

    FALEMAIS_30(30),
    FALEMAIS_60(60),
    FALEMAIS_120(120);

    private final int value;

    Plans(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
