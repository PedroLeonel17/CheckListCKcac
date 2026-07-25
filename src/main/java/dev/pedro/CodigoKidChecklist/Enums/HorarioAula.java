package dev.pedro.CodigoKidChecklist.Enums;

public enum HorarioAula {

    H08m00("08:00"),
    H10m00("10:00"),
    H12m00("12:00"),
    H13m30("13:30"),
    H15m30("15:30"),
    H17m30("17:30");

    private final String value;

    HorarioAula(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
