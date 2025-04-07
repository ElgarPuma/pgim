package pe.gob.osinergmin.pgim.dtos;

public class PgimRecursoExtraidoAuxDTOResultado extends PgimRecursoExtraidoAuxDTO {

    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.RecursoExtraidoRepository#listarRecursosExtraidosPaginado()
     * 
     * @param anioPro
     * @param mes
     * @param idCliente
     * @param rucCliente
     * @return 
     */
	public PgimRecursoExtraidoAuxDTOResultado(String anioPro, String mes, Long idCliente, String rucCliente,
        String titularMinero, String estrato, String codigo, String noUnidadMinera, String codIntegranteUEA, 
        String noUnidadIntegrante, String noRegion, String noProvincia, String noDistrito, String noRecursoExtraido, 
        String procedencia, String noVendedor, Long idUnidadMedida, String descripcion, Long cantidad, 
        Double PCT_CU, Double PCT_PB, Double PCT_ZN, Double AG_OZ_TC, Double AU_GR_TM, Double PCT_FE, 
        Double PCT_MO, Double PCT_SN, Double PCT_CD, Double PCT_WO3, Double PCT_SB, Double PCT_AS, Double PCT_MN, 
        Double PCT_BI, Double PCT_HG, Double PCT_IN, Double PCT_SE, Double PCT_TE, Double H2SO4, Double PCT_U, Double PCT_NI, Double PCT_MG 
    ) {
        super();
        this.setAnioPro(anioPro);
        this.setMes(mes);
        this.setIdCliente(idCliente);
        this.setRucCliente(rucCliente);       
        this.setTitularMinero(titularMinero);
        this.setEstrato(estrato);
        this.setCodigo(codigo);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setCodIntegranteUEA(codIntegranteUEA);
        this.setNoUnidadIntegrante(noUnidadIntegrante);
        this.setNoRegion(noRegion);
        this.setNoProvincia(noProvincia);
        this.setNoDistrito(noDistrito);
        this.setNoRecursoExtraido(noRecursoExtraido);
        this.setProcedencia(procedencia);
        this.setNoVendedor(noVendedor);
        this.setIdUnidadMedida(idUnidadMedida);
        this.setDescripcion(descripcion);
        this.setCantidad(cantidad);
        this.setPct_CU(PCT_CU);
        this.setPct_PB(PCT_PB);
        this.setPct_ZN(PCT_ZN);
        this.setAg_OZ_TC(AG_OZ_TC);
        this.setAu_GR_TM(AU_GR_TM);
        this.setPct_FE(PCT_FE);
        this.setPct_MO(PCT_MO);
        this.setPct_SN(PCT_SN);
        this.setPct_CD(PCT_CD);
        this.setPct_WO3(PCT_WO3);
        this.setPct_SB(PCT_SB);
        this.setPct_AS(PCT_AS);
        this.setPct_MN(PCT_MN);
        this.setPct_BI(PCT_BI);
        this.setPct_HG(PCT_HG);
        this.setPct_IN(PCT_IN);
        this.setPct_SE(PCT_SE);
        this.setPct_TE(PCT_TE);
        this.setH2SO4(H2SO4);
        this.setPct_U(PCT_U);
        this.setPct_NI(PCT_NI);
        this.setPct_MG(PCT_MG);
    }

    
}
