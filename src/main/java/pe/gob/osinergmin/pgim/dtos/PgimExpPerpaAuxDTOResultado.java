package pe.gob.osinergmin.pgim.dtos;

public class PgimExpPerpaAuxDTOResultado extends PgimExpPerpaAuxDTO {
	
	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.ExpPerpaAuxRepository#listarReporteExpPasPerPasoAnioPaginado()
	 * @see pe.gob.osinergmin.pgim.models.repository.ExpPerpaAuxRepository#listarReporteExpPasPerPasoAnio()
	 * @param idPersona
	 * @param deEntidadPersona
	 * @param etiquetaPersona
	 * @param f1Asignarsupe
	 * @param f1Coordinarlasupe
	 * @param f1Firmardji
	 * @param f1Generarcredencialytdr
	 * @param f1Aprobartdrycredencial
	 * @param f1Gestionarsaldo
	 * @param f1Definirantecedentesarevisar
	 * @param f1Revisarantecedentesdelaum
	 * @param f1Aprobarrevantecedentes
	 * @param f1Completarsupecomocancelada
	 * @param f2Iniciarsupedecampo
	 * @param f2Solicitardocumentacionalaum
	 * @param f2Realizarmedyconstatarhechos
	 * @param f2Presentaractadesupe
	 * @param f2Reprogramarsupe
	 * @param f3Elaborarinfdesupefallida
	 * @param f3Elaborarypresentarinfdesupe
	 * @param f4Revisaryaprobarinfsupe
	 * @param f4Elaborarmemodesupefallida
	 * @param f4Aprobarmemosupefallida
	 * @param f4Completarsupecomofallida
	 * @param f5Confirmahconstataeinfrac
	 * @param f5Continuarconelpas
	 * @param f5Supecompletadaconiniciopas
	 * @param f5Continuarconelarchivado
	 * @param f5Aprobarocafarch
	 * @param f5Completarsupearchivandola
	 * @param f5Solicitarampliaciondeip
	 * @param f5Firmarsoldeampliacionplazo
	 * @param f5Aprobarsolampliaciondeplazo
	 * @param f5Visarhechosconstatados
	 */
	public PgimExpPerpaAuxDTOResultado( Long idPersona, 
			String deEntidadPersona, 
			String etiquetaPersona,
			Long f1Asignarsupe,
			Long f1Coordinarlasupe,
			Long f1Firmardji,
			Long f1Generarcredencialytdr,
			Long f1Aprobartdrycredencial,
			Long f1Gestionarsaldo,
			Long f1Definirantecedentesarevisar,
			Long f1Revisarantecedentesdelaum,
			Long f1Aprobarrevantecedentes,
			Long f1Completarsupecomocancelada,
			Long f2Iniciarsupedecampo,
			Long f2Solicitardocumentacionalaum,
			Long f2Realizarmedyconstatarhechos,
			Long f2Presentaractadesupe,
			Long f2Reprogramarsupe,
			Long f3Elaborarinfdesupefallida,
			Long f3Elaborarypresentarinfdesupe,
			Long f4Revisaryaprobarinfsupe,
			Long f4Elaborarmemodesupefallida,
			Long f4Aprobarmemosupefallida,
			Long f4Completarsupecomofallida,
			Long f5Confirmahconstataeinfrac,
			Long f5Continuarconelpas,
			Long f5Supecompletadaconiniciopas,
			Long f5Continuarconelarchivado,
			Long f5Aprobarocafarch,
			Long f5Completarsupearchivandola,
			Long f5Solicitarampliaciondeip,
			Long f5Firmarsoldeampliacionplazo,
			Long f5Aprobarsolampliaciondeplazo,
			Long f5Visarhechosconstatados
) {
		super();
		this.setIdPersona(idPersona); 
		this.setDeEntidadPersona(deEntidadPersona); 
		this.setEtiquetaPersona(etiquetaPersona);
		this.setF1Asignarsupe(f1Asignarsupe);
		this.setF1Coordinarlasupe(f1Coordinarlasupe);
		this.setF1Firmardji(f1Firmardji);
		this.setF1Generarcredencialytdr(f1Generarcredencialytdr);
		this.setF1Aprobartdrycredencial(f1Aprobartdrycredencial);
		this.setF1Gestionarsaldo(f1Gestionarsaldo);
		this.setF1Definirantecedentesarevisar(f1Definirantecedentesarevisar);
		this.setF1Revisarantecedentesdelaum(f1Revisarantecedentesdelaum);
		this.setF1Aprobarrevantecedentes(f1Aprobarrevantecedentes);
		this.setF1Completarsupecomocancelada(f1Completarsupecomocancelada);
		this.setF2Iniciarsupedecampo(f2Iniciarsupedecampo);
		this.setF2Solicitardocumentacionalaum(f2Solicitardocumentacionalaum);
		this.setF2Realizarmedyconstatarhechos(f2Realizarmedyconstatarhechos);
		this.setF2Presentaractadesupe(f2Presentaractadesupe);
		this.setF2Reprogramarsupe(f2Reprogramarsupe);
		this.setF3Elaborarinfdesupefallida(f3Elaborarinfdesupefallida);
		this.setF3Elaborarypresentarinfdesupe(f3Elaborarypresentarinfdesupe);
		this.setF4Revisaryaprobarinfsupe(f4Revisaryaprobarinfsupe);
		this.setF4Elaborarmemodesupefallida(f4Elaborarmemodesupefallida);
		this.setF4Aprobarmemosupefallida(f4Aprobarmemosupefallida);
		this.setF4Completarsupecomofallida(f4Completarsupecomofallida);
		this.setF5Confirmahconstataeinfrac(f5Confirmahconstataeinfrac);
		this.setF5Continuarconelpas(f5Continuarconelpas);
		this.setF5Supecompletadaconiniciopas(f5Supecompletadaconiniciopas);
		this.setF5Continuarconelarchivado(f5Continuarconelarchivado);
		this.setF5Aprobarocafarch(f5Aprobarocafarch);
		this.setF5Completarsupearchivandola(f5Completarsupearchivandola);
		this.setF5Solicitarampliaciondeip(f5Solicitarampliaciondeip);
		this.setF5Firmarsoldeampliacionplazo(f5Firmarsoldeampliacionplazo);
		this.setF5Aprobarsolampliaciondeplazo(f5Aprobarsolampliaciondeplazo);
		this.setF5Visarhechosconstatados(f5Visarhechosconstatados);
	}
	
}
