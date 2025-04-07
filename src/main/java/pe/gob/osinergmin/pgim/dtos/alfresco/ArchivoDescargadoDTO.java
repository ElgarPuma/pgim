package pe.gob.osinergmin.pgim.dtos.alfresco;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArchivoDescargadoDTO {

    private String nombreArchivo;

    private byte[] archivoData;


    public ArchivoDescargadoDTO(String nombreArchivo, byte[] archivoData) {
        this.nombreArchivo = nombreArchivo;
        this.archivoData = archivoData;
    }

}
