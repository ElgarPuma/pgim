package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Entidad para la tabla PGIM_VW_PROGRAM_PROPUESTO_RNK:
 * 
 * @descripción: Generación de programa de supervisión a partir de ranking
 *
 * @author: palominovega
 * @version 1.0
 * @fecha_de_creación: 05/01/2024
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimVwProgramPropuestoRnkDTO {

	private Long idGenPrograma;

	private Long idProgramaSupervision;

	private String flConforme;

	private Date feGeneracion;

	private Integer nuMaxFiscaAnualXuf;

	private Integer nuMaxFiscaMensual;

	private Integer nuEnero;

	private Integer nuFebrero;

	private Integer nuMarzo;

	private Integer nuAbril;

	private Integer nuMayo;

	private Integer nuJuno;

	private Integer nuJulio;

	private Integer nuAgosto;

	private Integer nuSeptiembre;

	private Integer nuOctubre;

	private Integer nuNoviembre;

	private Integer nuDiciembre;

	private Integer nuOrden;

	private Long idGenPrgRanking;

	private Long idRankingRiesgo;

	private String noRanking;

	private BigDecimal moPuntajeRiesgo;

	private BigDecimal moPuntajeTotalRiesgo;

	private BigDecimal moPuntajeProporcion;

	private Integer moProporcionEntera;

	private Integer caVisitas;

	private Long idRankingUm;

	private Long idUnidadMinera;

	private String noUnidadMinera;

	private Integer enero;

	private Integer febrero;

	private Integer marzo;

	private Integer abril;

	private Integer mayo;

	private Integer juno;

	private Integer julio;

	private Integer agosto;

	private Integer septiembre;

	private Integer octubre;

	private Integer noviembre;
	
	private Integer diciembre;

	private String flConformePrgMes;

	private String deMensajePrgMes;

}
