package pe.gob.osinergmin.pgim.dtos;

public class PgimArchivoDTOResultado extends PgimArchivoDTO {

    public PgimArchivoDTOResultado(Long idArchivo, Long idDocumento, Long descIdInstanciaProceso,
            String noOriginalArchivo, String noNuevoArchivo, Long seArchivo) {
        super();
        this.setIdArchivo(idArchivo);
        this.setIdDocumento(idDocumento);
        this.setDescIdInstanciaProceso(descIdInstanciaProceso);
        this.setNoOriginalArchivo(noOriginalArchivo);
        this.setNoNuevoArchivo(noNuevoArchivo);
        this.setSeArchivo(seArchivo);
    }

    public PgimArchivoDTOResultado(String noNuevoArchivo) {
        super();
        this.setNoNuevoArchivo(noNuevoArchivo);
    }
}
