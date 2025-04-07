package pe.gob.osinergmin.pgim.dtos.alfresco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArchivoReemplazadoDTO {

    private String idAlfresco;

    private String idAlfrescoPadre;

    private String nombreArchivoReemplazado;

    private String tipoMime;

}
