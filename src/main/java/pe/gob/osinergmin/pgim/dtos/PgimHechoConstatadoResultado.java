package pe.gob.osinergmin.pgim.dtos;

public class PgimHechoConstatadoResultado extends PgimHechoConstatadoDTO {

    public PgimHechoConstatadoResultado(Long descIdHechoConstatado, 
    		String descDeHechoConstatado, String descDeComplementoObservacion,
    		String descDeSustento, String descNoValorParametro) {
        super();
        this.setIdHechoConstatado(descIdHechoConstatado);
        this.setDeHechoConstatadoT(descDeHechoConstatado);
        this.setDeComplementoObservacion(descDeComplementoObservacion);
        this.setDeSustentoT(descDeSustento);
        this.setDescNoValorParametro(descNoValorParametro);
    }
    
    public PgimHechoConstatadoResultado(Long descIdHechoConstatado, 
    		String descDeObligacionNormativa, String descDeNormaItem, String descNoValorParametro) {
        super();
        this.setIdHechoConstatado(descIdHechoConstatado);
        this.setDescDeObligacionNormativa(descDeObligacionNormativa);
        this.setDescDeNormaItem(descDeNormaItem);
        this.setDescTipoCumplimiento(descNoValorParametro);
    }
}