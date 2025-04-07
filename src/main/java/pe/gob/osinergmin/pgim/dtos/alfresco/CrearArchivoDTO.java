package pe.gob.osinergmin.pgim.dtos.alfresco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearArchivoDTO {

    private String rutaRelativa;

    private String nombreArchivo;

    private Boolean autoRenombrar;
}
