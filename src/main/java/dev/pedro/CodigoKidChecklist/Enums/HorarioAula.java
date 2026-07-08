package dev.pedro.CodigoKidChecklist.Enums;

public enum HorarioAula {
    MANHA_8_10("08:00-10:00"),
    MANHA_10_12("10:00-12:00"),
    MANHA_8_12("08:00-12:00");


    // Construtor do Enum
    private final String descricao;

    HorarioAula(String valor) {
        this.descricao = valor;
    }

    public String getValue(){
        return this.descricao;
    }

}
