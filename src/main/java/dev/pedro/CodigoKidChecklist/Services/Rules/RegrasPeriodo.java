package dev.pedro.CodigoKidChecklist.Services.Rules;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegrasPeriodo {
    public static boolean verificarDiasValidos(DayOfWeek diaInicio, DayOfWeek diaFim, LocalDate dataAtual) {
        DayOfWeek diaAtual = dataAtual.getDayOfWeek();
        return diaAtual.compareTo(diaInicio) >= 0 && diaAtual.compareTo(diaFim) <= 0;
    }

    public static boolean verificarHoraValida(LocalTime horaInicio, LocalTime horaFim, LocalTime horaAtual) {
        return horaAtual.compareTo(horaInicio) >= 0 && horaAtual.compareTo(horaFim) <= 0;
    }
}
