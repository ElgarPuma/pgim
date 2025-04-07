package pe.gob.osinergmin.pgim.dtos;

public class PgimRelacionscNotifDTOResultado extends PgimRelacionscNotifDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.RelacionNotificacionRepository#obtenerRelacionNotificacion()
     * @param idRelacionscNotif
     * @param idSubcatDocConstancia
     * @param idSubcatDocNotificable
     */
    public PgimRelacionscNotifDTOResultado(Long idRelacionscNotif, Long idSubcatDocConstancia,
            Long idSubcatDocNotificable) {
        super();

        this.setIdRelacionscNotif(idRelacionscNotif);
        this.setIdSubcatDocConstancia(idSubcatDocConstancia);
        this.setIdSubcatDocNotificable(idSubcatDocNotificable);
    }

}
