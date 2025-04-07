package pe.gob.osinergmin.pgim.dtos.alfresco;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ArchivoCreadoDTO {

    private String idAlfresco;

    private String idAlfrescoPadre;

    private String nombreArchivoCreado;

    private String tipoMime;

}