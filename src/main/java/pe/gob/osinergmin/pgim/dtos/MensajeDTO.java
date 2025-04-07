package pe.gob.osinergmin.pgim.dtos;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.pgim.utils.TipoMensaje;

@Getter
@Setter
public class MensajeDTO implements Serializable {

    private String titulo;

    private String texto;

    private Date fecha;

    private String nombreUsuarioOrigen;

    private String nombreUsuarioDestino;

    private TipoMensaje tipo;
    
}