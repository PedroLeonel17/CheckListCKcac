package dev.pedro.CodigoKidChecklist.Enums;

import java.time.LocalTime;

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

    public static HorarioAula obterHorarioInicio(LocalTime hora) {
    if (!hora.isBefore(LocalTime.of(8, 0)) && hora.isBefore(LocalTime.of(10, 0))) {
        return H08m00;
    }

    if (!hora.isBefore(LocalTime.of(10, 0)) && hora.isBefore(LocalTime.of(12, 0))) {
        return H10m00;
    }

    if (!hora.isBefore(LocalTime.of(13, 30)) && hora.isBefore(LocalTime.of(15, 30))) {
        return H13m30;
    }

    if (!hora.isBefore(LocalTime.of(15, 30)) && hora.isBefore(LocalTime.of(17, 30))) {
        return H15m30;
    }

    throw new IllegalArgumentException("Não existe período para " + hora);
}
}
