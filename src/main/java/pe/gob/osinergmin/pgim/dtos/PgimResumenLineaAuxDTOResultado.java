package pe.gob.osinergmin.pgim.dtos;

public class PgimResumenLineaAuxDTOResultado extends PgimResumenLineaAuxDTO {

    /**
     * 
     * @param idLineaProgramaAux
     * @param idLineaPrograma
     * @param idUnidadMinera
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param enero
     * @param febrero
     * @param marzo
     * @param abril
     * @param mayo
     * @param junio
     * @param julio
     * @param agosto
     * @param septiembre
     * @param octubre
     * @param noviembre
     * @param diciembre
     * @param eneroOtros
     * @param febreroOtros
     * @param marzoOtros
     * @param abrilOtros
     * @param mayoOtros
     * @param junioOtros
     * @param julioOtros
     * @param agostoOtros
     * @param septiembreOtros
     * @param octubreOtros
     * @param noviembreOtros
     * @param diciembreOtros
     */
    public PgimResumenLineaAuxDTOResultado(Long idLineaProgramaAux, Long idLineaPrograma, Long idUnidadMinera,
            String coUnidadMinera, String noUnidadMinera, Long enero, Long febrero, Long marzo, Long abril, Long mayo,
            Long junio, Long julio, Long agosto, Long septiembre, Long octubre, Long noviembre, Long diciembre,
            Long eneroOtros, Long febreroOtros, Long marzoOtros, Long abrilOtros, Long mayoOtros, Long junioOtros,
            Long julioOtros, Long agostoOtros, Long septiembreOtros, Long octubreOtros, Long noviembreOtros,
            Long diciembreOtros) {
        super();

        this.setIdLineaProgramaAux(idLineaProgramaAux);
        this.setIdLineaPrograma(idLineaPrograma);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);

        this.setEnero(enero);
        this.setFebrero(febrero);
        this.setMarzo(marzo);
        this.setAbril(abril);
        this.setMayo(mayo);
        this.setJunio(junio);
        this.setJulio(julio);
        this.setAgosto(agosto);
        this.setSeptiembre(septiembre);
        this.setOctubre(octubre);
        this.setNoviembre(noviembre);
        this.setDiciembre(diciembre);

        this.setEneroOtros(eneroOtros);
        this.setFebreroOtros(febreroOtros);
        this.setMarzoOtros(marzoOtros);
        this.setAbrilOtros(abrilOtros);
        this.setMayoOtros(mayoOtros);
        this.setJunioOtros(junioOtros);
        this.setJulioOtros(julioOtros);
        this.setAgostoOtros(agostoOtros);
        this.setSeptiembreOtros(septiembreOtros);
        this.setOctubreOtros(octubreOtros);
        this.setNoviembreOtros(noviembreOtros);
        this.setDiciembreOtros(diciembreOtros);

    }

}
