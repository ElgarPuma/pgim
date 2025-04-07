package pe.gob.osinergmin.pgim.utils;

public enum TipoMensaje {

    NINGUNO("NINGUNO"),
    ASIGNAR_TAREA("ASIGNAR_TAREA"),
    REASIGNAR_TAREA("REASIGNAR_TAREA"),
    ALERTA_TAREA("ALERTA_TAREA"),
    INFORME_APROBADO("INFORME_APROBADO");

    private final String codigo;

    TipoMensaje(String codigo) {
        this.codigo = codigo;
    }

    public String obtenerValor() {
        return codigo;
    }
    
}