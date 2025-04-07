package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;

import java.util.List;

/**
 * En ésta interface ValorParametroRepository esta conformado pos sus
 * metodos de filtrar por nombres de parametro.
 * 
 * @descripción: Lógica de negocio de la entidad Valor parametro
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 25/05/2020
 * @fecha_de_ultima_actualización: 25/06/2020
 */
public interface ValorParametroRepository extends JpaRepository<PgimValorParametro, Long> {

        /**
         * Permite obtener la lista de valores de parámetro del nombre del parámetro.
         * 
         * @param nombre Nombre del parámetro del que se requieren sus valores.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.coClave, vapa.noValorParametro, "
                        + "vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto "
                        + ") "
                        + "FROM PgimValorParametro vapa "
                        + "WHERE vapa.esRegistro = '1' "
                        + "AND vapa.flActivo = '1' "
                        + "AND vapa.pgimParametro.coParametro = :nombre "
                        + "ORDER BY vapa.nuOrden, vapa.noValorParametro")
        List<PgimValorParametroDTO> filtrarPorNombreParametro(@Param("nombre") String nombre);
        
        /**
         * Permite obtener la lista de valores de parámetro del nombre del parámetro por Cuadro.
         * 
         * @param nombre Nombre del parámetro del que se requieren sus valores.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.coClave, vapa.noValorParametro, "
                        + "vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto "
                        + ") "
                        + "FROM PgimValorParametro vapa "
                        + "WHERE vapa.esRegistro = '1' "
                        + "AND vapa.flActivo = '1' "
                        + "AND vapa.pgimParametro.coParametro = :nombre "
                        + "AND vapa.coClave IN (1,2,3,4) "
                        + "ORDER BY vapa.nuOrden, vapa.noValorParametro")
        List<PgimValorParametroDTO> filtrarPorNombreParametroPorCuadro(@Param("nombre") String nombre);
        
        /**
         * Permite obtener la lista de valores de parámetro del nombre del parámetro por Hecho.
         * 
         * @param nombre Nombre del parámetro del que se requieren sus valores.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.coClave, vapa.noValorParametro, "
                        + "vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto "
                        + ") "
                        + "FROM PgimValorParametro vapa "
                        + "WHERE vapa.esRegistro = '1' "
                        + "AND vapa.flActivo = '1' "
                        + "AND vapa.pgimParametro.coParametro = :nombre "
                        + "AND vapa.coClave NOT IN (4) "
                        + "ORDER BY vapa.nuOrden, vapa.noValorParametro")
        List<PgimValorParametroDTO> filtrarPorNombreParametroPorHecho(@Param("nombre") String nombre);

        /**
         * Permite obtener la lista de valores de parámetro del nombre del parámetro.
         * 
         * @param nombre Nombre del parámetro del que se requieren sus valores.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.coClave, vapa.noValorParametro, vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico)"
                        + "FROM PgimValorParametro vapa WHERE vapa.esRegistro = '1' AND vapa.pgimParametro.coParametro = :nombre "
                        + "AND vapa.flActivo = '1' "
                        + "AND vapa.idValorParametro = :idValorParametro "
                        + "ORDER BY vapa.nuOrden, vapa.noValorParametro")
        List<PgimValorParametroDTO> filtrarPorNombreParametro(@Param("nombre") String nombre,
                        @Param("idValorParametro") Long idValorParametro);

        /**
         * Permite filtrar por subtipo de supervision
         * 
         * @param SUPER_FSCLZCION_NO_PRGRMDA
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' AND (subt.coClaveTexto = :SUPER_FSCLZCION_PRGRMDA OR subt.coClaveTexto = :SUPER_FSCLZCION_NO_PRGRMDA) ")
        List<PgimValorParametroDTO> filtrarPorNombreTipoSupervision(@Param("SUPER_FSCLZCION_NO_PRGRMDA") String SUPER_FSCLZCION_NO_PRGRMDA, @Param("SUPER_FSCLZCION_PRGRMDA") String SUPER_FSCLZCION_PRGRMDA);

        /**
         * Permite filtrar por tipo de involucrado para los representantes de AS y de
         * los trabajadores
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIP_INVOLUCRADO ")
        List<PgimValorParametroDTO> filtrarPorNombreTipoInvolucrado(@Param("nombre") String nombre);

        /**
         * Permite filtrar por tipo de estado de configuracion para el ranking de riesgo
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIP_ESTADO_CONFIGURACION ") // Falta
                                                                                                                                              // el
                                                                                                                                              // parametro
                                                                                                                                              // de
                                                                                                                                              // configuración
        List<PgimValorParametroDTO> filtrarPorNombreTipEstadoConfig(@Param("nombre") String nombre);

        /**
         * Permite filtrar por tipo de nombre prefijo para los representantes de AS y de
         * los trabajadores
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIP_PREFIJO_INVOLUCRADO ")
        List<PgimValorParametroDTO> filtrarPorNombreTipoPrefijo(@Param("nombre") String nombre);

        /**
         * Permite filtrar por tipo de documento de identidad de las personas naturales
         * y juridicas
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TDI_RUC_DNI_CE ")
        List<PgimValorParametroDTO> filtrarPorTipoDocIdentidad(@Param("nombre") String nombre);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
        		+ "subt.idValorParametro, subt.pgimParametro.idParametro, subt.coClave, subt.noValorParametro, subt.deValorParametro, subt.nuOrden, subt.nuValorNumerico, subt.deValorAlfanum, subt.coClaveTexto, subt.flActivo)"
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.coClaveTexto = :DOIDE_RUC ")
        List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadRuc(@Param("DOIDE_RUC") String DOIDE_RUC);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
        		+ "subt.idValorParametro, subt.pgimParametro.idParametro, subt.coClave, subt.noValorParametro, subt.deValorParametro, subt.nuOrden, subt.nuValorNumerico, subt.deValorAlfanum, subt.coClaveTexto, subt.flActivo)"
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.coClaveTexto = :DOIDE_DNI OR subt.coClaveTexto = :DOIDE_CE ")
        List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDniCe(@Param("DOIDE_DNI") String DOIDE_DNI, @Param("DOIDE_CE") String DOIDE_CE);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.coClaveTexto = :DOIDE_DNI ")
        List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDni(@Param("DOIDE_DNI") String DOIDE_DNI);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.coClaveTexto = :DOIDE_CE")
        List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadCe( @Param("DOIDE_CE") String DOIDE_CE);

        /**
         * Permite obtener un registro de la emtidad valor de parámetro
         * 
         * @param idValorParametro
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.coClave, vapa.noValorParametro, vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico)"
                        + "FROM PgimValorParametro vapa WHERE vapa.esRegistro = '1' AND vapa.idValorParametro = :idValorParametro")
        PgimValorParametroDTO obtenerValorParametroPorID(@Param("idValorParametro") Long idValorParametro);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIPO_NORMA ")
        List<PgimValorParametroDTO> filtrarPorTipoNorma(@Param("nombre") String nombre);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_DIVISION_ITEM ")
        List<PgimValorParametroDTO> filtrarPorDivisionItem(@Param("nombre") String nombre);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_MEDIDA_ADMINISTRATIVA ")
        List<PgimValorParametroDTO> filtrarPorNombreTipoMedidaAdm(@Param("nombre") String nombre);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIPO_OBJETO ")
        List<PgimValorParametroDTO> filtrarPorNombreTipoObjeto(@Param("nombre") String nombre);

        /**
         * Me permite filtrar por el nombre de tipo de denuncia
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, subt.noValorParametro ) "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.coParametro = :nombre ")
        List<PgimValorParametroDTO> filtrarPorMedioDenuncia(@Param("nombre") String nombre);

        /**
         * Permite filtrar por tipo de documento de identidad de las personas
         * denunciantes que son naturales y jurídicas
         * 
         * @param DOIDE_RUC
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, "
                        + "CASE WHEN subt.coClaveTexto "
                        + "= :DOIDE_RUC THEN 'Jurídica' ELSE 'Natural' END "
                        + ") "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TDI_RUC_DNI_CE ")
        List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDenuncia(@Param("DOIDE_RUC") String DOIDE_RUC);

        /**
         * Me permite filtrar por el nombre de materia
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, "
                        + "subt.deValorParametro "
                        + ") "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIPO_MATERIA ")
        List<PgimValorParametroDTO> filtrarPorTipoMateria(@Param("nombre") String nombre);

        /**
         * Me permite filtrar por el nobre de reporte
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "subt.idValorParametro, "
                        + "subt.noValorParametro "
                        + ") "
                        + "FROM PgimValorParametro subt WHERE subt.esRegistro = '1' "
                        + "AND subt.pgimParametro.idParametro = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_TIPO_REPORTE ")
        List<PgimValorParametroDTO> filtrarPorTipoReporte(@Param("nombre") String nombre);

        /**
         * Permite obtener la lista de valores activos del parámetro por su
         * identificador.
         * 
         * @param idParametro
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.pgimParametro.idParametro, vapa.coClave, vapa.noValorParametro, vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto, vapa.flActivo)"
                        + "FROM PgimValorParametro vapa WHERE vapa.esRegistro = '1' AND vapa.flActivo = '1' AND vapa.pgimParametro.idParametro = :id ORDER BY vapa.nuOrden, vapa.noValorParametro")
        List<PgimValorParametroDTO> filtrarActivosPorIdParametro(@Param("id") Long idParametro);

        /**
         * Permite obtener la lista de valores no activos del parámetro por su
         * identificador.
         * 
         * @param idParametro
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.pgimParametro.idParametro, vapa.coClave, vapa.noValorParametro, vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto, vapa.flActivo)"
                        + "FROM PgimValorParametro vapa WHERE vapa.esRegistro = '1' AND vapa.flActivo = '0' AND vapa.pgimParametro.idParametro = :id ORDER BY vapa.nuOrden, vapa.noValorParametro")
        List<PgimValorParametroDTO> filtrarNoActivosPorIdParametro(@Param("id") Long idParametro);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.pgimParametro.idParametro, vapa.coClave, vapa.noValorParametro, vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto, vapa.flActivo)"
                        + "FROM PgimValorParametro vapa "
                        + "WHERE vapa.idValorParametro = :idValorParametro "
                        + "AND vapa.esRegistro = '1' ")
        PgimValorParametroDTO obtenerValorParametroPorId(@Param("idValorParametro") Long idValorParametro);

        @Query("SELECT MAX(vapa.coClave) "
                        + "FROM PgimValorParametro vapa "
                        + "WHERE vapa.pgimParametro.idParametro = :idParametro ")
        Long obtenerMaximoCoClave(Long idParametro);

        /**
         * Permite obtener el identificador interno del valor de parámetro que coincida
         * con el valor de coClaveTexto.
         * 
         * @param coClaveTexto
         * @return
         */
        @Query("SELECT vapa.idValorParametro "
                        + "FROM PgimValorParametro vapa "
                        + "WHERE vapa.coClaveTexto = :coClaveTexto "
                        + "AND vapa.esRegistro = '1'")
        Long obtenerIdValorParametro(@Param("coClaveTexto") String coClaveTexto);

        @Query("SELECT vapa "
                        + "FROM PgimValorParametro vapa "
                        + "WHERE vapa.coClaveTexto = :coClaveTexto "
                        + "AND vapa.esRegistro = '1'")
        PgimValorParametro obtenerValorParametro(@Param("coClaveTexto") String coClaveTexto);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                + "vapa.idValorParametro, vapa.pgimParametro.idParametro, vapa.coClave, vapa.noValorParametro, vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto, vapa.flActivo)"
                + "FROM PgimValorParametro vapa WHERE vapa.esRegistro = '1' AND vapa.pgimParametro.idParametro = :id ORDER BY vapa.nuOrden, vapa.noValorParametro")
    	List<PgimValorParametroDTO> filtrarPorIdParametro(@Param("id") Long idParametro);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                + "vapa.idValorParametro, vapa.pgimParametro.idParametro, vapa.coClave, vapa.noValorParametro, vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto, vapa.flActivo) "
                + "FROM PgimValorParametro vapa WHERE vapa.coClaveTexto = :coClaveTexto "
                + "AND (:idValorParametro IS NULL OR vapa.idValorParametro != :idValorParametro) ")
    	List<PgimValorParametroDTO> existeCoClaveTexto(@Param("idValorParametro") Long idValorParametro,  @Param("coClaveTexto") String coClaveTexto);
        
        
        
        /**
         * Permite obtener la lista de valores por el campo coClaveTexto
         * 
         * 
         * @param parametro coClaveTexto.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTOResultado( "
                        + "vapa.idValorParametro, vapa.pgimParametro.idParametro, vapa.coClave, vapa.noValorParametro, vapa.deValorParametro, vapa.nuOrden, vapa.nuValorNumerico, vapa.deValorAlfanum, vapa.coClaveTexto, vapa.flActivo)"
                        + "FROM PgimValorParametro vapa WHERE vapa.esRegistro = '1' AND vapa.flActivo = '1' AND vapa.coClaveTexto = :parametro ORDER BY vapa.nuOrden, vapa.noValorParametro")
        List<PgimValorParametroDTO> listarParametrosBycoClaveTexto(@Param("parametro") String parametro);

}