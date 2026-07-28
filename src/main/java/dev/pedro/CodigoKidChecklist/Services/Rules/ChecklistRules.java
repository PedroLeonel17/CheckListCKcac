package dev.pedro.CodigoKidChecklist.Services.Rules;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public final class ChecklistRules {
    
    private static LocalDate dataAtual;
    private static LocalTime horaAtual;

    public static boolean validarPeriodosValidosPreenchimentoChecklist(){

        if(dataAtual == null)
            setLocalDate();
        if(horaAtual == null)
            setLocalTime();

        if(!RegrasPeriodo.verificarDiasValidos(DayOfWeek.TUESDAY, DayOfWeek.SATURDAY, dataAtual)){
            return false;
        }

        boolean horarioManha = RegrasPeriodo.verificarHoraValida(LocalTime.of(8, 0), LocalTime.of(12, 0), horaAtual);
        boolean horarioTarde = RegrasPeriodo.verificarHoraValida(LocalTime.of(13, 30), LocalTime.of(17, 30), horaAtual);
        
        if(!horarioManha && !horarioTarde){
            return false;
        }

        if(dataAtual.getDayOfWeek() == DayOfWeek.SATURDAY && !horarioManha){
            return false;
        }

        return true;
    }


    private static void setLocalDate(){
        dataAtual = LocalDate.now();
    }

    private static void setLocalTime(){
        horaAtual = LocalTime.now();
    }

    public  static void setLocalDate(LocalDate date){
        dataAtual = date;
    }

    public static void setLocalTime(LocalTime time){
        horaAtual = time;
    }
    public static LocalDate getDataAtual() {
        return dataAtual;
    }

    public static LocalTime getHoraAtual() {
        return horaAtual;
    }
}
