package pe.gob.osinergmin.pgim.utils;

import java.math.BigDecimal;

import pe.gob.osinergmin.pgim.dtos.PgimPlanSupervisionDTO;

public class ConstantesUtil {

	/**
	 * Parámetro que sostiene el valor de identificación interno del tipo de Acta
	 * involucrado
	 */
	public static final Long PARAM_TIP_ACTA_INVOLUCRADO = 30L;

	public static final Long PARAM_TIP_ACTA_INICIO = 317L;

	public static final Long PARAM_TIP_ACTA_SUPERVISION = 318L;

	public static final Long PARAM_TIP_ACTA_REQUERIMIENTO_DOC = 357L;

	public static final Long PARAM_TIP_ACTA_RECEPCION_DOC = 358L;

	/**
	 * Define la precisión matemática para las operaciones
	 */
	public static final int PRECISION_DECIMAL = 6;

	/**
	 * Tipo de origen del dato de riesgo igual a fiscalización
	 */
	public static final Long PARAM_TIPO_ORIGEN_DATO_RIESGO_FISCA = 372L;
	
	/**
	 * Tipo de inclusión de ranking igual a "manual"
	 */
	public static final Long PARAM_TIPO_INCLUSION_RANKING_MANUAL = 479L;

	public static final String FORMATO_FECHA_CORTO = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_RAYA_CORTO = "dd-MM-yyyy";
	public static final String FORMATO_FECHA_LARGO = "dd/MM/yyyy HH:mm:ss";

	public static final String IND_ACTIVO = "1";
	public static final String IND_INACTIVO = "0";
	
	public static final String IND_ACTIVO_A = "A";
	public static final String IND_INACTIVO_I = "I";

	public static final String INCLUIDO_ACTA_SUPERVISION_SI = "1";
	public static final String INCLUIDO_ACTA_SUPERVISION_NO = "0";
	
	public static final String FL_IND_SI= "1";
	public static final String FL_IND_NO = "0";

	/**
	 * Parámetro tipo de documento de identidad "TIPO_DOCUMENTO_IDENTIDAD"
	 * registrado en la tabla PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_DOCUMENTO_IDENTIDAD = "TIPO_DOCUMENTO_IDENTIDAD";
	public static final String PARAM_TIPO_DOCUMENTO_IDENTIDAD_RUC = "TIPO_DOCUMENTO_IDENTIDAD_RUC";
	public static final String PARAM_TIPO_DOCUMENTO_IDENTIDAD_DNI_CE = "TIPO_DOCUMENTO_IDENTIDAD_DNI_CE";
	public static final String PARAM_TIPO_DOCUMENTO_IDENTIDAD_DNI = "TIPO_DOCUMENTO_IDENTIDAD_DNI";
	public static final String PARAM_TIPO_DOCUMENTO_IDENTIDAD_CE = "TIPO_DOCUMENTO_IDENTIDAD_CE";

	public static final String PARAM_TIPO_INVOLUCRADO = "TIPO_INVOLUCRADO";
	public static final String PARAM_TIPO_PREFIJO = "TIPO_PREFIJO";

	/**
	 * Parámetro que sostiene el valor de identificación interno del tipo de Prefijo
	 * involucrado
	 */
	public static final Long PARAM_TIP_PREFIJO_INVOLUCRADO = 29L;

	public static final Long PARAM_TIP_REL_PASO_CANCELARFLUJO = 291L;
	public static final Long PARAM_TIP_REL_PASO_FINALIZADOFLUJO = 292L;

	/**
	 * Parámetro que sostiene el valor de identificación interno del tipo de
	 * Involucrado
	 */
	public static final Long PARAM_TIP_INVOLUCRADO = 28L;
	/**
	 * Parámetro que sostiene el valor de identificación interno del tipo de
	 * Involucrado
	 */
	public static final Long PARAM_TIP_ESTADO_CONFIGURACION = 44L;

	/**
	 * Parámetro división supervisora "DIVISION_SUPERVISORA" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_DIVISION_SUPERVISORA = "DIVISION_SUPERVISORA";

	/**
	 * Parámetro división supervisora "SITUACION_UNIDAD_MINERA" registrado en la
	 * tabla PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_SITUACION_UNIDAD_MINERA = "SITUACION_UNIDAD_MINERA";

	/**
	 * Parámetro tipo de actividad "TIPO_ACTIVIDAD" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_ACTIVIDAD = "TIPO_ACTIVIDAD";

	/**
	 * Parámetro tipo de actividad "ESTADO_UM" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_ESTADO_UM = "ESTADO_UM";

	/**
	 * Parámetro división supervisora "TIPO_UNIDAD_MINERA" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_UNIDAD_MINERA = "TIPO_UNIDAD_MINERA";

	/**
	 * Parámetro división supervisora "METODO_MINADO" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_METODO_MINADO = "METODO_MINADO";

	/**
	 * Parámetro división supervisora "TIPO_YACIMIENTO" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_YACIMIENTO = "TIPO_YACIMIENTO";

	/**
	 * Parámetro división supervisora "TIPO_SUSTANCIA" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_SUSTANCIA = "TIPO_SUSTANCIA";

	/**
	 * Parámetro categoría ocupacional "CATEGORIA_OCUPACIONAL" registrado en la
	 * tabla PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_CATEGORIA_OCUPACIONAL = "CATEGORIA_OCUPACIONAL";

	/**
	 * Parámetro tipo de documento de identidad "TIPO_ACTIVIDAD_CIIU" registrado en
	 * la tabla PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_ACTIVIDAD_CIIU = "TIPO_ACTIVIDAD_CIIU";

	/**
	 * Parámetro tipo de origen de documento "TIPO_ORIGEN_DOCUMENTO" registrado en
	 * la tabla PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_ORIGEN_DOCUMENTO = "TIPO_ORIGEN_DOCUMENTO";

	/**
	 * Parámetro tipo de cumplimiento "TIPO_CUMPLIMIENTO" registrado en
	 * la tabla PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_CUMPLIMIENTO = "TIPO_CUMPLIMIENTO";

	/**
	 * Parámetro No aplica de la entidad tipo de cumplimiento "TIPO_CUMPLIMIENTO"
	 * registrado en
	 * la tabla PGIM_TP_VALOR_PARAMETRO
	 */

	public static final Long PARAM_COD_NINGUNO_TC = 0L;
	public static final Long PARAM_COD_NO_APLICA_TC = 1L;
	public static final Long PARAM_COD_CUMPLE_TC = 2L;
	public static final Long PARAM_COD_NO_CUMPLE_TC = 3L;

	public static final Long PARAM_ID_NO_APLICA_TC = 309L;
	public static final Long PARAM_ID_CUMPLE_TC = 307L;
	public static final Long PARAM_ID_NO_CUMPLE_TC = 308L;

	/**
	 * Parámetro especialidad "ESPECIALIDAD_SUPERVISION" registrado en la tabla
	 * PGIM_TM_ESPECIALIDAD
	 */
	public static final String PARAM_TIPO_ESPECIALIDAD_SUPERVISION = "ESPECIALIDAD_SUPERVISION";

	/**
	 * Parámetro especialidad "ESPECIALIDAD_SUPERVISION" registrado en la tabla
	 * PGIM_TM_ESPECIALIDAD
	 */
	public static final String PARAM_TIPO_ESTADO_CONFIGURACION = "ESTADO_CONFIGURACION";

	/**
	 * Parámetro relacionado con los plazos de revisión de informes de supervisión.
	 */
	public static final String PARAM_PLAZOS_REVISION_INFORME = "PLAZOS_REVISION_INFORME";

	/**
	 * Valor del parámetro de la entrega del informe para revisión.
	 */
	public static final Long PARAM_PLAZOS_REVINFORME_ENTREGA_CO_CLAVE = 1L;

	/**
	 * Parámetro de tipo supervision "TIPO_SUPERVISION" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_SUPERVISION = "TIPO_SUPERVISION";

	/**
	 * Parámetro de nombre de programa supervision "NOMBRE_PROGRAMA_SUPERVISION"
	 * registrado en la tabla PGIM_TC_PRGRM_SUPERVISION
	 */
	public static final String PARAM_NOMBRE_PROGRAMA_SUPERVISION = "NOMBRE_PROGRAMA_SUPERVISION";

	/**
	 * Parámetro de nombre de contrato supervision "NOMBRE_CONTRATO_SUPERVISION"
	 * registrado en la tabla PGIM_TC_CONTRATO
	 */
	public static final String PARAM_NOMBRE_CONTRATO_SUPERVISION = "NOMBRE_CONTRATO_SUPERVISION";

	/**
	 * Parámetro de nombre fase proceso "FASE_PROCESO" registrado en la tabla
	 * PGIM_FASE_PROCESO
	 */
	public static final String PARAM_NOMBRE_FASE_PROCESO = "FASE_PROCESO";

	/**
	 * Parámetro de nombre paso proceso "PASO_PROCESO" registrado en la tabla
	 * PGIM_PASO_PROCESO
	 */
	public static final String PARAM_NOMBRE_PASO_PROCESO = "PASO_PROCESO";
	
	public static final Long TIPO_SUPERVISION = 21L;
	
	public static final Long PARAM_TIPO_SUPERVISION_PROGRAMADA = 287L;

	public static final Long PARAM_TIPO_SUPERVISION_NOPROGRAMADA = 288L;


	/**
	 * Subtipo de supervisión
	 */
	public static final Long PARAM_SUBTIPO_PROG_CON_EST_ESTABILIDAD = 5L;
	public static final Long PARAM_SUBTIPO_NOPROG_CON_EST_ESTABILIDAD = 17L;


	/**
	 * Parámetro que sostiene el valor de identificación interno del tipo de
	 * documento RUC.
	 */
	public static final Long PARAM_TDI_RUC_DNI_CE = 3L;
	public static final Long PARAM_TDI_DNI = 5L;
	public static final Long PARAM_TDI_RUC = 6L;
	public static final Long PARAM_TDI_CE = 7L;

	public static final String PARAM_TIPO_SEGURO = "TIPO_SEGURO";

	public static final String PARAM_TIPO_ALERTA = "TIPO_ALERTA";

	public static final String PARAM_TIPO_EVENTO = "TIPO_EVENTO";

	public static final String PARAM_AGENTE_CAUSANTE = "AGENTE_CAUSANTE";

	public static final String PARAM_TIPO_ACCIDENTE = "TIPO_ACCIDENTE";

	public static final String PARAM_TIPO_INCIDENTE = "TIPO_INCIDENTE";

	public static final String PARAM_TIPO_AUTORIZACION = "TIPO_AUTORIZACION";

	public static final String PARAM_TIPO_COMPONENTE_MINERO = "TIPO_COMPONENTE_MINERO";

	public static final String PARAM_ESTADO_COMPONENTE_MINERO = "ESTADO_CM";
	
	/**
	 * Nombre del Estrato que sostiene el valor de identificación interno del
	 * idEstrato
	 */
	public static final String NOMBRE_ESTRATO_AGENTE_SUPERVISADO = "NOMBRE_ESTRATO";

	/**
	 * Número de contrato
	 * nuContrato
	 */
	public static final String NUMERO_CODIGO_CONTRATO = "CODIGO_CONTRATO";

	/**
	 * Parámetro que sostiene el valor de identificación interno del tipo unidad
	 * minera de concesión de beneficio.
	 */
	public static final Long PARAM_TUM_ID_CONCESION_BENEFICIO = 36L;
	
	/**
	 * Parámetro del tipo de empresa involucrada
	 */
	public static final Long TIPO_EMPRESA_INVOLUCRADA = 12L;
	
	/**
	 * Parámetro que sostiene el valor de identificación interno de la empresa
	 * involucrada como TITULAR_MINERO.
	 */
	public static final Long PARAM_TEI_TITULAR_MINERO = 65L;

	/**
	 * Parámetro que sostiene el valor de identificación interno de la empresa
	 * involucrada como CONTRATISTA.
	 */
	public static final Long PARAM_TEI_CONTRATISTA = 66L;

	public static final Long PARAM_TE_ACCIDENTE_MORTAL = 63L;

	public static final Long PARAM_TE_INCIDENTE_PELIGROSO = 64L;

	public static final String PARAM_TAMANIO_EMPRESA = "TAMANIO_EMPRESA";

	public static final Long PARAM_TUM_CONCESION_BENEFICIO = 36L;

	// Constantes de Procesos

	public static final Long PARAM_PROCESO_EVENTO = 1L;

	public static final Long PARAM_PROCESO_AUTORIZACION = 6L;

	public static final Long PARAM_PROCESO_DENUNCIA = 7L;

	public static final Long PARAM_PROCESO_SUPERVISION = 2L;

	public static final Long PARAM_PROCESO_UNIDAD_MINERA = 3L;

	public static final Long PARAM_PROCESO_FISCALIZACION = 4L;

	public static final Long PARAM_PROCESO_LIQUIDACION = 10L;

	public static final Long PARAM_PROCESO_CONTRATO = 5L;

	public static final Long PARAM_PROCESO_PROGRAMACION = 9L;

	public static final Long PARAM_PROCESO_RANKING_RIESGOS = 11L;

	public static final Long PARAM_PROCESO_CONFIGURACION_RIESGO = 12L;

	public static final Long PARAM_PROCESO_MEDIDA_ADM = 13L;

	public static final Long PARAM_MEDIDA_ADM_UNIDAD_MINERA = 405L;

	public static final Long PARAM_MEDIDA_ADM_SUPERVISION = 406L;

	public static final Long PARAM_MEDIDA_ADM_FISCALIZACION = 407L;
	

	public static final String PARAM_TABLA_TC_SUPERVISION = "PGIM_TC_SUPERVISION";
	public static final String PARAM_TABLA_TM_EVENTO = "PGIM_TM_EVENTO";
	public static final String PARAM_TABLA_TM_AUTORIZACION = "PGIM_TM_AUTORIZACION";
	public static final String PARAM_TABLA_TM_DENUNCIA = "PGIM_TM_DENUNCIA";
	public static final String PARAM_TABLA_TM_UNIDAD_MINERA = "PGIM_TM_UNIDAD_MINERA";
	public static final String PARAM_TABLA_TC_CONTRATO = "PGIM_TC_CONTRATO";
	public static final String PARAM_TABLA_TC_LIQUIDACION = "PGIM_TC_LIQUIDACION";
	public static final String PARAM_TABLA_TC_PAS = "PGIM_TC_PAS";
	public static final String PARAM_TABLA_TC_PRGRM_SUPERVISION = "PGIM_TC_PRGRM_SUPERVISION";
	public static final String PARAM_TABLA_TC_RANKING_RIESGO = "PGIM_TC_RANKING_RIESGO";
	public static final String PARAM_TABLA_TM_CONFIGURA_RIESGO = "PGIM_TM_CONFIGURA_RIESGO";
	public static final String PARAM_TABLA_TC_MEDIDA_ADM = "PGIM_TC_MEDIDA_ADM";
	
	
	/**
	 * Constantes de Motivos de supervisión
	 */
	public static final Long MOTIVO_ACCIDENTE_MORTAL = 2L;
	
	/**
	 * Constantes de Tipos de Motivos de supervisión
	 */
	public static final Long TIPO_MOTIVO_EVENTO = 499L;
	public static final Long TIPO_MOTIVO_DENUNCIA = 500L;
	public static final Long TIPO_MOTIVO_MEDIDA_ADM = 501L;
	

	/**
	 * Parámetros de integración SYM.
	 */
	public static final String PARAM_SYM_WEB_INICIO_SYM = "pages/inicioSYM/inicio";
	public static final String PARAM_SYM_WEB_MAS_INFORMACION = "pages/infExpediente/inicio";
	public static final String PARAM_SYM_REST_TOKEN = "remote/sym/symSeguridad/getToken";
	public static final String PARAM_SYM_REST_LISTAR_EXP_REPORTE = "remote/sym/expediente/listarExpedienteParaReporteBean/";
	public static final String PARAM_SYM_REST_BUSCAR_INFRACCION = "remote/sym/infraccion/buscarInfraccion/";
	public static final String PARAM_SYM_INVOKER = "PGIM";
	public static final String PARAM_SYM_SESSION = "$2a$10$0tpuXgJVlTMR4xNnKejyZ.Qmkoi01KLiK25HJow/4Jl";// llave para
																										// encriptar en
																										// la pgim, y
																										// para
																										// desencriptar
																										// en el SYM-WEB

	/**
	 * Parámetro de integración SIGED.
	 */
	// public static final String PARAM_SIGED_EXPEDIENTE_DOCUMENTO =
	// "remote/expediente/documentos/{nroexp}/{files}";
	// public static final String PARAM_SIGED_EXPEDIENTE_CREAR =
	// "remote/expediente/crear";
	// public static final String PARAM_SIGED_DOCUMENTO_ARCHIVO =
	// "remote/archivo/list/{iddocumento}";
	 public static final String PARAM_SIGED_AGREGAR_DOCUMENTO = "remote/expediente/agregarDocumento/{versionar}";
	 public static final String PARAM_SIGED_AGREGAR_ARCHIVO = "remote/documento/agregarArchivo/{versionar}";
	 public static final String PARAM_SIGED_DESCARGAR_ARCHIVO = "remote/archivo/descarga/{idarchivo}";
	// public static final String PARAM_SIGED_ANULAR_ARCHIVO =
	// "remote/nuevodocumento/anularArchivo";
	// public static final String PARAM_SIGED_ANULAR_DOCUMENTO =
	// "remote/documento/anular";
	// public static final String PARAM_SIGED_OBTENER_USUARIO =
	// "remote/usuario/obtenerUsuario";

	public static final String PARAM_SIGED_REVERTIR_FIRMA = "remote/firmaDigital/revertirFirma";

	public static final String PARAM_SIGED_ENUMERAR_DOCUMENTO = "remote/nuevodocumento/enumerarDocumento/{nroExpediente}/{idDocumento}/{idUsuarioEnumerador}";
	public static final String PARAM_SIGED_EXPEDIENTE_REENVIAR = "remote/expediente/reenviar/{aprobacion}";
	public static final String PARAM_SIGED_EXPEDIENTE_REENVIAR_SUBFLUJO = "remote/expediente/reenviarSubFlujo";
	public static final String PARAM_SIGED_ES_FERIADO = "remote/ubigeo/esFeriado";

	public static final String PARAM_SIGED_EXPEDIENTE_LISTAR_POR_USUARIO = "remote/expediente/listByUser/{iduser}/{estado}";

	public static final String PARAM_SIGED_DEVOLVER_EXPEDIENTE = "remote/nuevodocumento/devolverExpediente";
	public static final String PARAM_SIGED_APROBAR_EXPEDIENTE = "remote/expediente/aprobar/{paraAprobacion}";
	public static final String PARAM_SIGED_ARCHIVAR_EXPEDIENTE = "remote/expediente/archivar";
	public static final String PARAM_SIGED_REABRIR_EXPEDIENTE = "remote/expediente/reabrir/{nroexp}";

	public static final String PARAM_SIGED_CLIENTE_CONSULTA = "remote/cliente/find";
	public static final String PARAM_SIGED_LISTADO_AREAS = "remote/unidad/list";
	
	/**
	 * Nuevos métodos Siged-rest-old
	 */
	public static final String PARAM_SIGED_ARCHIVOS_CON_VERSIONES = "remote/documento/archivosConVersiones/V2/{iddocumento}";
	public static final String PARAM_SIGED_DESCARGAR_VERSION_ARCHIVO = "remote/archivo/descarga/{IdArchivo}/{LabelVersion}";
	public static final String PARAM_SIGED_LISTADO_DOCUMENTO_V2 = "remote/documento/listar/v2";
	public static final String PARAM_SIGED_TIPO_DOCUMENTO = "remote/tipodocumento/list";
	public static final String PARAM_SIGED_EXPEDIENTE_LISTAR_POR_USUARIO_V2= "remote/expediente/listByUser/V2/{iduser}/{estado}/{idproceso}";
	public static final String PARAM_SIGED_PROCESOS = "remote/proceso/list"; //AUN NO SE ENCUENTRA EN EL EXCEL

	
	/**
	 * Nombres de métodos Siged utilizados para mensajes y logs
	 */	
	public static final String PARAM_SIGED_NOM_METODO_ARCHIVOS_CON_VERSIONES = "LISTAR_ARCHIVOS_CON_VERSIONES";	
	public static final String PARAM_SIGED_NOM_METODO_DESCARGAR_VERSION_ARCHIVO = "DESCARGAR_VERSION_ARCHIVO";	
	public static final String PARAM_SIGED_NOM_METODO_LISTADO_DOCUMENTO_V2 = "LISTAR_DOCUMENTOS_V2";	
	public static final String PARAM_SIGED_NOM_METODO_TIPO_DOCUMENTO = "LISTAR_TIPO_DOCUMENTO";
	public static final String PARAM_SIGED_NOM_METODO_EXP_LISTAR_POR_USUARIO_V2 = "LISTAR_EXPEDIENTES_POR_USUARIO_V2";
	public static final String PARAM_SIGED_NOM_METODO_PROCESOS = "LISTAR_PROCESOS_SIGED"; //REVISAR Y CAMBIAR POR EL CORRECTO, AUN NO SE A ACTUALIZADO EL EXCEL 
	
	
	public static final String PARAM_SURVEY_ADD_FEATURES = "FeatureServer/1/addFeatures";
	public static final String PARAM_SURVEY_UPDATE_FEATURES = "FeatureServer/1/updateFeatures";

	public static final String PARAM_REQUEST_METHOD_GET = "GET";
	public static final String PARAM_REQUEST_METHOD_POST = "POST";
	public static final String PARAM_REQUEST_PROPERTY_ACCEPT_ENCODING = "Accept-Encoding";
	public static final String PARAM_REQUEST_PROPERTY_CONTENT_TYPE = "Content-Type";
	public static final String PARAM_REQUEST_PROPERTY_USER_AGENT = "User-Agent";
		

	/**
	 * Parámetro resultante de la operación correcta tras la llamada del método del
	 * servicio web del Siged.
	 */
	public static final String PARAM_RESULTADO_SUCCESS = "1";
	public static final String PARAM_RESULTADO_FAIL = "0";
	public static final String PARAM_RESULTADO_FAIL_DOS = "2";
	public static final String PARAM_RESULTADO_ERROR_EXCEPTION = "500";
	public static final String PARAM_appNameInvokes = "pgim";

	public static final String PARAM_codigoTipoIdentificacion = "1";
	public static final String PARAM_razonSocial = "Osinergmin";
	public static final String PARAM_RUC_OSINERGMIN = "20376082114";
	public static final String PARAM_tipoCliente = "3";
	public static final String PARAM_NOM_PROCESO_SIGED_905 = "GFM – Supervisión y Fiscalización";
	

	public static final String PARAM_estaEnFlujo = "S";
	
	/**
	 * Otras constantes de integración con el Siged
	 */
	public static final Integer PARAM_LONGITUD_MAX_NOMBRE_ARCHIVO = 170; //Incluye extensión de archivo

	/**
	 * Constante que señala si se señala como firmado o no el documento. Se ha
	 * colocado el valor 78 porque N, indicada por la especificación del Siged no
	 * funciona como debe.
	 */
	public static final String PARAM_negativo = "78";

	public static final String PARAM_afirmativo = "83";

	public static final String PARAM_creaExpediente = "N";

	public static final String PARAM_creaExpedienteSi = "S";

	public static final String PARAM_nroFolios = "0";

	public static final String PARAM_publico = "N";

	// public static final String PARAM_usuarioCreador = "7748";
	// public static final String PARAM_usuarioCreador = "2530";

	public static final String PARAM_ubigeo = "150101";

	public static final String PARAM_expedienteFisico = "N";

	public static final String PARAM_historico = "N";

	// public static final String PARAM_destinatario = "7748";

	public static final String PARAM_documentoPrincipal = "83";

	public static final String PARAM_noPropietario = "701";

	public static final String PARAM_mismoPropietario = "702";

	public static final String PARAM_noConfigurado = "703";

	public static final String MENSAJE_ErrorSiged = "Siged reporta un problema al ejecutar %s, el código del error es: [%s] y el mensaje: [%s]";

	public static final String MENSAJE_ErrorSNE = "SNE reporta un problema al ejecutar %s, el código del error es: [%s] y el mensaje: [%s]";
	
	public static final String MENSAJE_ErrorAlfresco = "Alfresco reporta un problema al ejecutar %s, el código del error es: [%s] y el mensaje: [%s]";

	/**
	 * Códigos de error Siged
	 */
	public static final String SIGED_COD_ERR_EXP_ARCHIVADO= "6003";
	
	/**
	 * Constante para la generación de documentos.
	 */
	public static final Long PARAM_tipo_doc_DJI = 92L;

	public static final Long PARAM_TIPO_DOC_OTROS = 4L;
	
	public static final Long PARAM_TIPO_DOC_ACTA_CONFORMIDAD = 143L;
	
	public static final Long PARAM_TIPO_DOC_FORMATOS = 100L;

	public static final Long PARAM_TIPO_DOC_LIQ = 100L;

	public static final Long PARAM_SUBCAT_DOC_DJIM = 159L;

	/**
	 * Ficha de hechos constatados
	 */
	public static final Long PARAM_SUBCAT_DOC_HC = 31L;

	/**
	 * Ficha de evaluacion de hechos constatados version 1 - con las anoaciones del especialista técnico
	 * legal
	 */
	public static final Long PARAM_SUBCAT_DOC_FEHC_1 = 213L;

	/**
	 * Ficha de hechos constatados - con las anoaciones del especialista técnico
	 * legal
	 */
	public static final Long PARAM_SUBCAT_DOC_FHC = 32L;

	/**
	 * Ficha de instrucción preliminar.
	 */
	public static final Long PARAM_SUBCAT_DOC_FIP = 39L;

	/**
	 * Observaciones al informe de supervisión.
	 */
	public static final Long PARAM_SUBCAT_DOC_OIS = 33L;

	/**
	 * Conformidades al informe de supervisión.
	 */
	public static final Long PARAM_SUBCAT_DOC_CIS = 34L;

	/**
	 * Informe de supervisión.
	 */
	public static final Long PARAM_SUBCAT_DOC_IS = 29L;

	/**
	 * Informe de gestión.
	 */
	public static final Long PARAM_SUBCAT_DOC_IG = 63L;

	/**
	 * Acta de supervisión.
	 */
	public static final Long PARAM_SUBCAT_DOC_AS = 27L;

	/**
	 * Informe de supervisión fallida.
	 */
	public static final Long PARAM_SUBCAT_DOC_ISF = 69L;

	/**
	 * Informe IAIP.
	 */
	public static final Long PARAM_SUBCAT_DOC_IAIP = 41L;
	/**
	 * Formato de Liquidación de actas
	 */
	public static final Long PARAM_SUBCAT_DOC_FLA = 61L;
	/**
	 * Formato de Liquidación de informe
	 */
	public static final Long PARAM_SUBCAT_DOC_FLI = 62L;
	/**
	 * Formato de Liquidación de informe de gestión
	 */
	public static final Long PARAM_SUBCAT_DOC_FLIGES = 63L;
	/**
	 * Formato de Liquidación de informe de supervisión fallida
	 */
	public static final Long PARAM_SUBCAT_DOC_FLISF = 69L;
	/**
	 * Formato de Liquidación de informe
	 */
	public static final Long PARAM_SUBCAT_DOC_PEN = 64L;
	/**
	 * Formato de revisión de antecedentes
	 */
	public static final Long PARAM_SUBCAT_DOC_REV_ANTE = 5L;

	/**
	 * Formato de TDR
	 */
	public static final Long PARAM_SUBCAT_DOC_TDR = 3L;

	/**
	 * Formato de Criterios de fiscalización (TDR para fiscalización propia)
	 */
	public static final Long PARAM_SUBCAT_DOC_CRITERIOS = 208L;

	/**
	 * Autorización de ampliación.
	 */
	public static final Long PARAM_SUBCAT_DOC_AUTOAMP = 36L;

	public static final Long PARAM_SUBCAT_DOC_CREDENCIAL = 7L;

	public static final Long PARAM_TIPO_DOC_TDR = 198L;
	

	public static final Long PARAM_SUBCAT_DOC_DJI = 6L;
//	public static final Long PARAM_SUBCAT_DOC_DJIM = 1000L;

	public static final int OIPAS = 72;    

    public static final int CONSTANCIA_NOTIF_ELECTR_OIPAS = 102;

	public static final Long PARAM_id_rol_SUPERVISOR = 4L;

	/**
	 * Código del rol de proceso interesado.
	 */
	public static final String PROCESO_CO_ROL_INTERESADO = "INTE";

	public static final Long PARAM_TIPO_FECHA_PROGRAMADA = 293L;

	public static final Long PARAM_TIPO_FECHA_REAL = 294L;

	/**
	 * Tiempo de espera de los métodos de integración con el Siged. Se encuentra en
	 * milisegundos.
	 */
	public static final int PARAM_TIMEOUT_SIGED = 60000;

	/**
	 * Parámetro que sostiene la transición de pasos en el marco de un proceso -
	 * flujo de Supervisión
	 */
	public static final Long PARAM_RELACION_ASIGNAR_COORDSUPER = 1L;

	public static final Long PARAM_RELACION_COORDSUPER_FIRMARDJI = 2L;

	public static final Long PARAM_RELACION_COORD_SUPER_CANCELAR_SUPER = 3L;

	public static final Long PARAM_RELACION_FIRMARDJI_GENTDR = 4L;

	public static final Long PARAM_RELACION_GENERA_TDR_CREDENCIAL_CANCELAR_SUPER = 6L;
	
	public static final Long PARAM_RELACION_APROBAR_TDR_COORDSUPER = 7L;
	
	public static final Long PARAM_RELACION_APROBAR_TDR_GENERA_TDR_CREDENCIAL = 8L;

	public static final Long PARAM_RELACION_APROBAR_TDR_CREDENCIAL_DEFINIR_ANTECEDENTES = 10L;

	public static final Long PARAM_RELACION_GESTIONAR_SALDO_CANCELAR_SUPER = 12L;

	public static final Long PARAM_RELACION_DEFINIR_ANTECEDENTES_REV_ANTECEDENTES = 13L;

	public static final Long PARAM_RELACION_DEFINIR_ANTECEDENTES_CANCELAR_SUPER = 14L;
	
	public static final Long PARAM_RELACION_REVISAR_ANTECEDENTES_APROBAR_REV_ANTECEDENTES = 15L;

	public static final Long PARAM_RELACION_APROBAR_REV_ANTECEDENTES_CANCELAR_SUPER = 17L;
	
	public static final Long PARAM_RELACION_INICIASUPERVCAMPO_ELABORAR_INF_FISC_FALLIDA= 18L;

	public static final Long PARAM_RELACION_FIRMARMEMO_COMPLETARSUPFALLIDA = 21L;	

	public static final Long PARAM_RELACION_INICIASUPERV_SOLDOCCAMPO = 22L;

	public static final Long PARAM_RELACION_SOLICITARDOC_REALIZARMEDICIONES = 23L;

	public static final Long PARAM_RELACION_REALIZARMEDICIONES_PRESENTARACTAFISC = 24L;

	public static final Long PARAM_RELACION_PREACTASUPER_ELAINFO = 25L;

	public static final Long PARAM_RELACION_ELABORARINFORME_APROBARINFORME = 26L;

	public static final Long PARAM_RELACION_CONFIRMARHC_CONTINUARPAS = 28L;
	
	public static final Long PARAM_RELACION_GENERA_TDR_CREDENCIAL_APROBAR_TDR = 36L;

	public static final Long PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_ELABORAR_PRESENTAR_INF_FISCALIZACION = 37L;
	
	public static final Long PARAM_RELACION_GENTDR_FIRMARDJI = 44L;

	public static final Long PARAM_RELACION_CONFIRMARHC_CONTINUARARCH = 87L;

	public static final Long PARAM_RELACION_INISUPCAMP_REPROSUP = 88L;

	public static final Long PARAM_RELACION_SOLDOCUM_REPROSUP = 89L;

	public static final Long PARAM_RELACION_MEDICIONES_REPROSUP = 90L;

	public static final Long PARAM_RELACION_PRESENTARACTA_REPROSUP = 91L;

	public static final Long PARAM_RELACION_VISARHC_COMPLETARSUPCONPAS = 97L;

	public static final Long PARAM_RELACION_COORSUPER_GENERARTDR = 98L;

	public static final Long PARAM_RELACION_REVISARHC_CONTINUARARCH = 99L;	

	public static final Long PARAM_RP_APROBAR_SOLICITUD_AMPLIACIÓN_PLAZO_CONF_HECHOS_CONSTATADOS_INFRACCIONES = 42L;

	public static final Long PARAM_RP_VERIF_NOTIF_FISICA_OFICIO_CONCLUSION_CONF_HECHOS_CONSTATADOS_INFRACCIONES = 737L;

	public static final Long PARAM_RP_VERIF_NOTIF_ELECTR_OFICIO_CONCLUSION_CONF_HECHOS_CONSTATADOS_INFRACCIONES = 741L;	

	public static final Long PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF = 45L;

	public static final Long PARAM_RP_CONTINUAR_ARCHIVADO_APROBAR_IAIP = 34L;

	public static final Long PARAM_RP_CONTINUAR_PAS_COMPLETAR_SUPERVISIÓN_INICIO_PAS = 32L;
	
	public static final Long PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD = 726L;

	public static final Long PARAM_RELACION_ELABORAR_FEHV_APROBAR_OCAF = 727L;
	
	public static final Long PARAM_RP_COMPLETAR_FISC_ARCH__FISC_ARCHIVADA = 779L;
	
	public static final Long PARAM_RP_COMPLETAR_FISC_PAS__FISC_COMPLETADA_PAS = 780L;
	
	public static final Long PARAM_RP_APROBAR_REV_ANTECED_PREPARAR_INI_CAMPO = 784L;
	
	public static final Long PARAM_RELACION_PREPARARINICAMPO_INICIARCAMPO = 785L;
	
	public static final Long PARAM_RELACION_APROBAR_REV_ANTECEDENTES_COORDSUPER = 788L;
	
	public static final Long PARAM_RELACION_DEFINIR_ANTECEDENTES_COORDSUPER = 789L;
	
	public static final Long PARAM_RP_APROBAR_CAMBIO_INSTRUM_INICIAR_SUP_CAMPO = 799L;
	
	public static final Long PARAM_RP_APROBAR_CAMBIO_INSTRUM_SOLICITAR_DOCUM = 800L;
	
	public static final Long PARAM_RP_APROBAR_CAMBIO_INSTRUM_REALIZAR_MEDICIONES = 801L;
	
	public static final Long PARAM_RP_APROBAR_CAMBIO_INSTRUM_PRESENTAR_ACTA_FISC = 802L;
	
	public static final Long PARAM_RP_DETERMINAR_REQ_ARCHIVAR__CONFIRMAR_HC_INFRAC = 815L;
	
	public static final Long PARAM_RP_DETERMINAR_REQ_ARCHIVAR__COMPLETAR_FISC_ARCHIVANDO = 816L;
	
	public static final Long PARAM_RP_FISC_COMPL_PAS__CONFIRMAR_HC_INFRACCIONES = 821L;
	
	public static final Long PARAM_RP_FISC_COMPL_INICIO_PAS__FISC_COMPLETADA_PAS = 822L;	
	
	public static final Long PARAM_RELACION_APROBAR_CAMBIOS_INSTRUMENTOS_MEDICION = 856L;
	
	public static final Long PARAM_RELACION_REVIS_APROB_INFORME_FISC__ELAB_INFORME_CONDICIONES_OFRECIDAS = 980L;
	
	
	/**
	 * Parámetro que sostiene la transición de pasos en el marco de un proceso -
	 * flujo de PAS
	 */	
	public static final Long PARAM_RP_ADJ_CARGO_NOTFISICA_OIPAS__VERIF_VALIDEZ_NOTFISICA = 118L;
	
	public static final Long PARAM_RP_NOTIF_ELECTRONICA_OIPAS__VERIF_VALIDEZ_NOTIFELECTRONICA = 121L;
	
	public static final Long PARAM_RP_VERIF_VALIDEZ_NOTFISICA__ESPERAR_DESCARGOS_OIPAS = 123L;
	
	public static final Long PARAM_RP_VERIF_VALIDEZ_NOTIFELECTRONICA__ESPERAR_DESCARGOS_OIPAS = 124L;
	
	public static final Long PARAM_RP_VERIF_NOTIF_ELECTR_PIDIDO_AMPLIACION__ESPERAR_DESCARGOS_OIPAS = 141L;
	
	public static final Long PARAM_RP_VERIF_NOTIF_FISICA_PIDIDO_AMPLIACION__ESPERAR_DESCARGOS_OIPAS = 145L;
	
	public static final Long PARAM_RP_FIRMAR_AMPLIACION_CADUCIDAD__NOTIF_RESOL_AMPLIACION_PLAZO = 198L;
	
	public static final Long PARAM_RP_VERIF_NOTIF_FISICA_RES_SANCION__ESPERAR_RECURSO_IMPUGNATIVO = 230L;
	
	public static final Long PARAM_RP_VERIF_NOTIF_ELECTR_RES_SANCION__ESPERAR_RECURSO_IMPUGNATIVO = 233L;

	public static final Long PARAM_RP_FIRMAR_RES_MEMO_TASTEM_NOTIFICAR_RES_DE_CALIFICACION = 282L;
	
	public static final Long PARAM_RP_ARCHIVAR_PAS_RESOL_ARCHIVO__PAS_ARCHIVADO_RESOL_ARCHIVO = 298L;
	
	public static final Long PARAM_RP_VERIF_PAGO_MULTA_INFRAC__FORMULAR_FICHA_RESUMEN_Y_MEMO_CONF = 307L;
	
	public static final Long PARAM_RP_ARCHIVAR_INFRAC_NULIDAD_TOTAL_FUNDADO_TASTEM__INFRAC_ARCHIV_NULIDAD_TASTEM = 312L;
	
	public static final Long PARAM_RP_DERIVAR_EJEC_COACTIVA__EXPED_DERIVADO_CAOACTIVA = 331L;
	
	public static final Long PARAM_RP_ARCHIVAR_EXP_NULIDAD_TOTAL_FUNDADO_PJ__EXP_ARCHIV_NULIDAD_PJ = 334L;
	
	public static final Long PARAM_RP_ARCHIVAR_EXP_PAGO__EXP_ARCHIV_MULTA_PAGADA = 336L;
	
	public static final Long PARAM_RP_VERIF_PAGO_MULTA_INFRAC__RESPONDER_ESCRITO_DERIV_COACTIVA = 644L;
	
	public static final Long PARAM_RP_PREPARAR_DOC_PAS__CONFIRMAR_HC_INFRACCIONES_PAS = 824L;
	
	public static final Long PARAM_RP_CONFIRMAR_HC_INFRACCIONES_PAS__PREPARAR_DOC_PAS = 825L;
	
	public static final Long PARAM_RP_VERIF_VALID_NOTIFELECTRONICA_DERIV_TASTEM__CONTINUAR_TRAM_PAS= 881L;
	
	public static final Long PARAM_RP_VERIF_VALID_NOTFISICA_DERIV_TASTEM__CONTINUAR_TRAM_PAS= 882L;
	
	public static final Long PARAM_RP_VERIF_PAGO_MULTA_INFRAC__ARCHIVAR_INFRAC_PAGO = 886L;

	public static final Long PARAM_RP_FIRMAR_AMPLIACION_CADUCIDAD_F2__NOTIF_RESOL_AMPLIACION_PLAZO_F2 = 913L;
	
	public static final Long PARAM_RP_ARCHIVAR_INFRAC_RR_FUNDADO__EXP_ARCHIVADO_FUNDADO = 972L;

	public static final Long PARAM_RP_FIRMAR_RES_MEMO_TASTEM_CONTINUAR_TRAMITE_PAS = 1267L;



	/**
	 * parámetros de relación de pasos del flujo de Programación
	 */
	public static final Long PARAM_RELACION_ELABORAR_PROG_VISAR_PROG = 50L;

	public static final Long PARAM_RELACION_VISAR_PROG_REVISAR_PROG = 51L;

	public static final Long PARAM_RELACION_APROBAR_PROG_REALIZAR_SEGUIMIENTO = 55L;

	public static final Long PARAM_RELACION_REALIZAR_SEGUIMIENTO_CERRAR_PROG = 57L;

	public static final Long PARAM_RELACION_REALIZAR_SEGUIMIENTO_ELABORAR_PROG = 58L;

	public static final Long PARAM_RELACION_VISAR_PROG_ANULAR_LB = 82L;

	public static final Long PARAM_RELACION_ANULAR_LB_REALIZAR_SEGUIMIENTO = 84L;

	/**
	 * parámetros de relación de pasos del flujo de liquidación
	 */

	public static final Long PARAM_RELACION_ELABORARSOLLQ_REVISARSOLLIQ = 60L;

	public static final Long PARAM_RELACION_APROBARLIQ_TRAMITARLIQ = 65L;

	public static final Long PARAM_RELACION_ELABORARSOLLQ_LIQANULADA = 61L;

	public static final Long PARAM_RELACION_TRAMITARLIQ_REGISPENALIDAD = 67L;

	public static final Long PARAM_RELACION_REVISARPENAL_ENVIARLIQPAGO = 73L;

	public static final Long PARAM_RELACION_REVISAROBSPNENAL_ENVIARLIQPAGO = 76L;

	public static final Long PARAM_RELACION_ENVIARLIQPAGO_REVISARSOLLIQ = 77L;

	public static final Long PARAM_RELACION_ENVIARLIQPAGO_TRAMFACTURA = 78L;

	public static final Long PARAM_RELACION_COMPLETARLIQ_LIQCOMPLETA = 81L;

	public static final Long PARAM_RELACION_TRAMILIQ_ENVIARLIQPAGO = 85L;

	/**
	 * Parámetro que sostiene la transición de pasos en el marco de un proceso -
	 * flujo de Generación de Ranking
	 */
	public static final Long PARAM_RELACION_ASIGNAR_CALIFICARIESGO = 500L;

	public static final Long PARAM_RELACION_CALIFICARIESGO_CANCELARANKING = 501L;

	public static final Long PARAM_RELACION_CANCELARANKING_RANKINGCANCELADO = 502L;

	public static final Long PARAM_RELACION_CALIFICARIESGO_APRUEBARANKING = 503L;

	public static final Long PARAM_RELACION_APRUEBARANKING_RANKINGAPROBADO = 504L;

	public static final Long PARAM_RELACION_APRUEBARANKING_CALIFICARIESGO = 505L;

	/**
	 * Parámetros para la gestión de alertas
	 */
	public static final String PARAM_ENLACE_REENVIAR_SUP = "/supervision/supervisiones/2/%s/1/0";

	public static final String PARAM_ENLACE_REENVIAR_LIQ = "/contratos/liquidacion/2/%s/1/0";
	
	public static final String PARAM_ENLACE_REENVIAR_PRO = "/programacion/programa-lista/2/%s/1/0";
	
	public static final String PARAM_ENLACE_REENVIAR_PAS = "/fiscalizacion/pas/2/%s/1/0";

	public static final String PARAM_ENLACE_REENVIAR_RNK = "/riesgos/ranking/2/%s/1/0";

	public static final String PARAM_ENLACE_REENVIAR_CONF = "/riesgos/configuraciones/2/%s/1/0";
	
		// public static final String PARAM_ENLACE_REENVIAR_PRO =
		// "/programacion/2/%s/0";

	public static final Long PARAM_ALERTA_PASO = 299L;

	public static final Long PARAM_ALERTA_SUP_INI = 300L;

	public static final Long PARAM_ALERTA_SUP_FALL = 301L;

	public static final Long PARAM_ALERTA_SUPER_FALLIDA_REL = 18L;

	public static final Long PARAM_ALERTA_SUP_FIN = 302L;

	public static final Long PARAM_ALERTA_SUPER_FINALIZADA_REL = 25L;

	public static final Long PARAM_ALERTA_SUP_CAN = 303L;

	public static final Long PARAM_ALERTA_SUPER_CANCELADA_REL1 = 3L;

	public static final Long PARAM_ALERTA_SUPER_CANCELADA_REL2 = 6L;

	public static final Long PARAM_ALERTA_SUPER_CANCELADA_REL3 = 12L;

	public static final Long PARAM_ALERTA_SUPER_CANCELADA_REL4 = 14L;

	public static final Long PARAM_ALERTA_SUPER_CANCELADA_REL5 = 17L;

	public static final Long PARAM_ALERTA_SUP_IAIP = 304L;

	public static final Long PARAM_DETALLE_ALERTA_ASIGNACION = 305L;

	public static final Long PARAM_DETALLE_ALERTA_SUP_CONOCIMIENTO = 306L;

	public static final Long PARAM_ALERTA_SUPER_FINALIZADA_IAIP_REL = 35L;

	public static final String PARAM_NO_ALERTA_SUP = "Fiscalización: %s - Código %s";

	public static final String PARAM_NO_ALERTA_LIQ = "Liquidación: %s - %s";

	public static final String PARAM_NO_ALERTA_PRO = "Programa: %s - %s";

	public static final String PARAM_NO_ALERTA_FIS = "PAS-%s: %s - %s";

	public static final String PARAM_NO_ALERTA_RNK = "Ranking: %s - %s";

	public static final String PARAM_NO_ALERTA_CONF = "Configuración: %s - %s";

	public static final String PARAM_ALERTA_TRANS_PASO = "Asignación de paso realizada: <br><b>%s</b> (%s) ---> <b>%s</b> (%s)";

	public static final String PARAM_ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD = "Alerta de cumplimiento de plazo de caducidad, vence el %s";

	public static final String PARAM_ALERTA_CUMPLIMIENTO_PLAZO_RVSAR_INFRME = "Alerta de cumplimiento de plazo para evaluación de informe de fiscalización, vence el %s";

	public static final String PARAM_ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR = "Alerta de cumplimiento de plazo para iniciar PAS o archivar, vence el %s.";

	public static final String PARAM_ALERTA_CUMPLIMIENTO_PLAZO_PRESENTAR_INFRME = "Alerta de cumplimiento de plazo para presentar informe de fiscalización, vence el %s";
	
	public static final String PARAM_ALERTA_APROBACION_INFORME_FISC_EMP_SUP = "El informe de fiscalización fue aprobado por Osinergmin";

	// public static final String PARAM_DETALLE_ALERTA_CUMPLEMIENTO = "Se le informa, en su calidad de <b>responsable</b> que tiene un plazo de vencimiento en %s</b>, por favor revise el detalle en el objeto de trabajo.";

	public static final String PARAM_DETALLE_ALERTA_CUMPLEMIENTO = "Se le informa, que tiene acciones con una plazo de vencimiento en %s</b>, por favor revise el detalle en el objeto de trabajo.";

	public static final String PARAM_DETALLE_ALERTA_PASO = "Se le ha asignado un <b>nuevo paso en el/la %s</b>, por favor revise el detalle en el objeto de trabajo.";

	public static final String PARAM_DETALLE_ALERTA_RESPONSABLE = "Se le informa, en su calidad de <b>responsable</b> que ha ocurrido una <b>nueva asignación de paso en la %s</b>, por favor revise el detalle en el objeto de trabajo.";

	public static final String PARAM_DETALLE_ALERTA_SUP_INTERESADO = "Se le informa, en su calidad de <b>interesado/a</b> que ha ocurrido una <b>nueva asignación de paso en la supervisión</b>, por favor revise el detalle en el objeto de trabajo.";
	
	public static final String PARAM_DETALLE_ALERTA_APROBACION_INFORME_FISC_EMP_SUP = "Se le informa que <b>el informe de fiscalización ya fue aprobado por Osinergmin</b>, por lo tanto puede proceder con la solicitud de liquidación del entregable respectivo desde el módulo <b>Liquidaciones</b>";

	public static final String PARAM_SIGED_LOG_DE_USO_INI = "SIGED: Inicia llamada al servicio %s";

	public static final String PARAM_SIGED_LOG_DE_USO_FIN = "SIGED: Finaliza llamada al servicio %s";

	public static final String PARAM_SIGED_LOG_DE_USO_FIN_ERROR = "SIGED: Finaliza llamada al servicio con error %s";
	
	public static final String PARAM_SIGED_LOG_SERVICIO_NO_DISPONIBLE = "SIGED: Servicio no disponible. Código petición: %s - Mensaje: %s";

	public static final String PARAM_SURVEY_LOG_DE_USO_INI = "SURVEY: Inicia llamada al servicio %s";

	public static final String PARAM_SURVEY_LOG_DE_USO_FIN = "SURVEY: Finaliza llamada al servicio %s";

	public static final String PARAM_SURVEY_LOG_DE_USO_FIN_ERROR = "SURVEY: Finaliza llamada al servicio con error %s";

	/**
	 * Parámetros de estado del hecho constatado.
	 */
	public static final String PARAM_HC_ESTADO_VIGENTE = "V";

	public static final String PARAM_HC_ESTADO_HISTORICO = "H";

	public static final Long PARAM_HC_ROL_SUPERVISOR = 4L;

	public static final Long PARAM_HC_ROL_ESP_TECNICO = 2L;

	public static final Long PARAM_HC_ROL_ESP_LEGAL = 5L;

	public static final Long PARAM_HC_ROL_ALL = 0L;

	/**
	 * Parámetros de estado de la línea base del programa
	 */
	public static final Long PARAM_LB_CREADA = 343L;

	public static final Long PARAM_LB_APROBADA = 344L;

	public static final Long PARAM_LB_CERRADA = 345L;

	public static final Long PARAM_LB_SUSTITUIDA = 355L;

	public static final Long PARAM_LB_ANULADA = 356L;

	/**
	 * Parámetros de integración SISSEG.
	 */
	public static final String PARAM_SISSEG_VALIDAR_ACCESO = "rest/validarAcceso/";
	public static final String PARAM_SISSEG_PERMISOS_PAGINA = "rest/permisos/{idUsuario}/{idAplicacion}";
	public static final String PARAM_SISSEG_MODULOS = "rest/modulos/{idUsuario}/{idAplicacion}";
	public static final String PARAM_SISSEG_KEY_APLICACION = "rest/obtenerkeyaplicacion/{idAplicacion}";
	public static final String PARAM_SISSEG_ID_USUARIO = "rest/obteneridusuario/{username}";
	public static final String PARAM_SISSEG_VERIFICAR_ACCESO = "rest/verificaracceso/{idUsuario}/{idAplicacion}/{idPagina}";
	public static final String PARAM_SISSEG_DATOS_USUARIO = "rest/usuario/{idUsuario}";
	
	public static final String PARAM_SISSEG_AS_OAUTH2_TOKEN = "oauth2/token";
	public static final String PARAM_SISSEG_RESOURCES_USUARIO = "api/usuario/{idUsuario}";
	public static final String PARAM_SISSEG_RESOURCES_MODULOS = "api/modulos/{idUsuario}/{idAplicacion}";
	public static final String PARAM_SISSEG_RESOURCES_PERMISOS = "api/permisos/{idUsuario}/{idAplicacion}";
	
	public static final String PARAM_REQUEST_PROPERTY_GRANT_TYPE = "grant_type";
	public static final String PARAM_REQUEST_PROPERTY_CLIENT_ID = "client_id";
	public static final String PARAM_REQUEST_PROPERTY_CLIENT_SECRET = "client_secret";
	public static final String PARAM_REQUEST_PROPERTY_SCOPE = "scope";
	public static final String PARAM_REQUEST_PROPERTY_AUTHORIZATION = "Authorization";	
	
	/**
	 * Parámetros de integración con ALFRESCO
	 */	
//	public static final String PARAM_ALFRESCO_RUTA_FISCALIZACION = "FISCALIZACION";
	public static final String PARAM_ALFRESCO_SERVICIO_CREAR_ARCHIVO = "/crearArchivo";
	public static final String PARAM_ALFRESCO_SERVICIO_REEMPLAZAR_ARCHIVO = "/reemplazarArchivo";
	public static final String PARAM_ALFRESCO_SERVICIO_DESCARGAR_ARCHIVO = "/descargarArchivo/{idAlfresco}";
	public static final String PARAM_ALFRESCO_AGREGAR_JSON = "archivoCrear";
	public static final String PARAM_ALFRESCO_AGREGAR_FILE = "archivoData";
	public static final String PARAM_ALFRESCO_NOMBRE_ARCHIVO = "nombreArchivo";
	
	public static final String PARAM_ALFRESCO_LOG_DE_USO_INI = "Alfresco: Inicia llamada al servicio %s";
	public static final String PARAM_ALFRESCO_LOG_DE_USO_FIN = "Alfresco: Finaliza llamada al servicio %s";
	public static final String PARAM_ALFRESCO_LOG_DE_USO_FIN_ERROR = "Alfresco: Finaliza llamada al servicio con error %s";
	
	/**
	 * Nombres de métodos Alfresco utilizados para mensajes y logs
	 */	
	public static final String PARAM_ALFRESCO_NOM_METODO_CREAR_ARCHIVO = "Crear archivo";	
	public static final String PARAM_ALFRESCO_NOM_METODO_REEMPLAZAR_ARCHIVO = "Reemplazar archivo";	
	public static final String PARAM_ALFRESCO_NOM_METODO_DESCARGAR_ARCHIVO = "Descargar archivo";	
	

	/**
	 * Fases del proceso de Supervisión.
	 */
	public static final Long PARAM_SUPERVISION_NINGUNA = 0L;
	public static final Long PARAM_EVENTO_DOCUMENTOS_VARIOS = 1L;
	public static final Long PARAM_AUTORIZACION_DOCUMENTOS_VARIOS = 7L;
	public static final Long PARAM_SUPERVISION_PRE_SUPERVISION = 2L;
	public static final Long PARAM_SUPERVISION_SUPERVISION_CAMPO = 3L;
	public static final Long PARAM_SUPERVISION_POST_SUPERVISION_CAMPO = 4L;
	public static final Long PARAM_SUPERVISION_REV_INFO_SUPERVISION = 5L;
	public static final Long PARAM_SUPERVISION_APROB_RESULTADOS = 6L;

	/**
	 * Fases del proceso administrativo sancionador (PAS).
	 */
	public static final Long PARAM_PAS_INICIO_PAS = 14L;
	public static final Long PARAM_PAS_FORMULACION_IFI = 15L;
	public static final Long PARAM_PAS_RESOL_SANCION = 16L;
	public static final Long PARAM_PAS_RECONSIDERACION = 17L;
	public static final Long PARAM_PAS_COBRANZA_COACTIVA = 18L;

	/**
	 * Fases del proceso de liquidacion 
	 */
	public static final Long PARAM_LIQUI_SOLICITUD = 11L;

	/**
	 * Parámetros de integración PIDO.
	 */
	public static final String PARAM_PIDO_SUNAT_CONSULTAR_RUC = "consultarucdatosprincipales/contribuyentes/consultar/1.1";
	public static final String PARAM_PIDO_SUNAT_CONSULTAR_DNI = "registroidentificacion/ciudadano/obtener/1.1";
	public static final String PARAM_PIDO_RENIEC_CONSULTAR_DNI = "consultaidentificacion/ciudadano/consultar/2.1";
	public static final String PARAM_PIDO_LOG_DE_USO_INI = "PIDO: Inicia llamada al servicio %s";
	public static final String PARAM_PIDO_LOG_DE_USO_FIN = "PIDO: Finaliza llamada al servicio %s";
	public static final String PARAM_PIDO_LOG_DE_USO_FIN_ERROR = "PIDO: Finaliza llamada al servicio con error %s";
	public static final String PARAM_PIDO_TAG_RAIZ = "soap:Envelope";
	public static final String PARAM_PIDO_TAG_ITEM_RESULTADO = "ns2:";
	public static final String PARAM_PIDO_CORESULTADO_OK = "0000";

	public static final Long PROCESO_ROL_NINGUNO = 0L;
	public static final Long PROCESO_ROL_SUPERVISOR = 4L;
	public static final Long PROCESO_ROL_GERENTE_DIVISION = 3L;
	public static final Long PROCESO_ROL_ESP_TECNICO = 2L;
	public static final Long PROCESO_ROL_ESP_LEGAL = 5L;
	public static final Long PROCESO_ROL_ABOGADO = 34L;
	public static final Long PROCESO_ROL_COORDINADOR_SUPERVISION = 36L;
	public static final Long PROCESO_ROL_ADMINISTRATIVO_TASTEM = 37L;
	public static final Long PROCESO_ROL_ADMINISTRATIVO_COACTIVA = 38L;

	/**
	 * Plantillas.
	 */
	public static final String PLANTILLA_RESERVA_NUMERO = "PLANTILLA_RESERVA_NUMERO.pdf";
	public static final String PARAM_PLANTILLA_TDR = "PLANTILLA_TDR.docx";
	public static final String PARAM_nombre_plantillaid_TDR = "PLANTILLA_TDR.docx";
	public static final String PARAM_PLANTILLA_TDR_FISCALIZACION_PROPIA = "PLANTILLA_TDR_FISCALIZACION_PROPIA.docx";
	public static final String PARAM_PLANTILLA_ACTA_FISCALIZACION = "PLANTILLA_ACTA_FISCALIZACION.docx";
	public static final String PARAM_PLANTILLA_ACTA_INICIO_FISCALIZACION = "PLANTILLA_ACTA_INICIO_FISCALIZACION.docx";
	public static final String PARAM_PLANTILLA_FICHA_HECHOS_VERIFICADOS = "PLANTILLA_FICHA_HECHOS_VERIFICADOS.docx";
	public static final String PARAM_PLANTILLA_FICHA_EVALUA_HECHOS_VERIFICADOS = "FICHA_EVALUACION_HECHOS_VERIFICADOS.docx";
	
	public static final String PARAM_PLANTILLA_FICHA_HECHOS_VERIFICADOS_ESPTL = "PLANTILLA_FICHA_HECHOS_VERIFICADOS_ESPTL.docx";
	public static final String PARAM_PLANTILLA_RESUMEN_INST_PRELIMINAR = "PLANTILLA_RESUMEN_INST_PRELIMINAR.docx";
	public static final String PLANTILLA_FICHA_OBS_INF_FISCALIZACION = "PLANTILLA_FICHA_OBS_INF_FISCALIZACION.docx";
	public static final String PLANTILLA_FICHA_CONF_INF_FISCALIZACION = "PLANTILLA_FICHA_CONF_INF_FISCALIZACION.docx";
	public static final String PLANTILLA_FORMATO_LIQUIDACION_ACTAS = "PLANTILLA_FORMATO_LIQUIDACION_ACTAS.docx";
	public static final String PLANTILLA_FORMATO_LIQUIDACION_INFORME = "PLANTILLA_FORMATO_LIQUIDACION_INFORME.docx";
	public static final String PLANTILLA_FORMATO_LIQUIDACION_PENALIDAD = "PLANTILLA_FORMATO_LIQUIDACION_PENALIDAD.docx";
	public static final String PLANTILLA_FORMATO_CUADRO_VERIFICACION = "PLANTILLA_FORMATO_CUADRO_VERIFICACION.docx";
	public static final String PLANTILLA_REQUERIMIENTO_DOCUMENTACION = "PLANTILLA_REQUERIMIENTO_DOCUMENTACION.docx";
	public static final String PLANTILLA_RECEPCION_DOCUMENTACION = "PLANTILLA_RECEPCION_DOCUMENTACION.docx";
	public static final String PLANTILLA_FORMATO_RIESGOS = "PLANTILLA_FORMATO_RIESGOS.docx";
	public static final String PLANTILLA_REPORTE_HORIZONTAL = "PLANTILLA_REPORTE_HORIZONTAL.docx";
	public static final String PLANTILLA_FICHA_INFORMATIVA_UM = "PLANTILLA_FICHA_INFORMATIVA_UM.docx";
	public static final String PLANTILLA_FICHA_COMPONENTES_UM = "PLANTILLA_FICHA_COMPONENTES_UM.docx";
	public static final String PLANTILLA_DJI = "PLANTILLA_DJI.docx";
	public static final String PLANTILLA_CREDENCIAL = "PLANTILLA_CREDENCIAL.docx";
	public static final String PLANTILLA_PEN_PER_CONTRATO_SUPERVISORA = "PLANTILLA_PENALIDAD_PERIODO_CONTRATO_SUPERVISORA.docx";
	public static final String PLANTILLA_EJECUCION_PRESUPUESTAL = "PLANTILLA_EJECUCION_PRESUPUESTAL.docx";
	public static final String PLANTILLA_CON_SAL_CONTRATO_SUPERVISORA = "PLANTILLA_CONTROL_SALDO_CONTRATO_SUPERVISORA.docx";
	public static final String PLANTILLA_PRESUPUESTO_GASTO_SUPERVISION = "PLANTILLA_PRESUPUESTO_GASTO_SUPERVISION.docx";
	public static final String PLANTILLA_REVISION_ANTECEDENTE_SUPERVISION = "PLANTILLA_REVISION_ANTECEDENTE_SUPERVISION.docx";
	public static final String PLANTILLA_DJ_INSTRUMENTOS_MEDICION = "PLANTILLA_DJ_INSTRUMENTOS_MEDICION.docx";
	public static final String PARAM_PLANTILLA_CADENA_VACIA = "''";
	public static final String PARAM_TIPO_HC_SUP = "SUP";
	public static final String PARAM_TIPO_HC_ESP = "ESP";

	public static final Integer PLAZO_DIAS = 3;

	public static final Long PARAM_FR_FICHA_OBSERVACIONES = 335L;
	public static final Long PARAM_TIPO_REVISION = 337L;
	public static final Long PARAM_TIPO_NUEVA = 336L;

	public static final Long PARAM_DOI_INTERNO = 285L;	
	
	public static final Long PARAM_INFORME_OBSERVADO = 338L;
	public static final Long PARAM_INFORME_APROBADO = 339L;
	public static final Long PARAM_INFORME_JUSTIFICADO = 340L;
	

	/**
	 * Validación de generación de documentos.
	 */
	public static final String MSG_ARCHIVO_FIRMADO = "Se ha detectado un archivo firmado digitalmente, para mantener la consistencia de la información, revierta la firma antes de generar el documento.";

	public static final String MSG_ARCHIVOS_FIRMADOS = "Se han detectado archivos firmados digitalmente, para mantener la consistencia de la información, revierta las firmas antes de generar el documento.";

	/**
	 * Parámetro que sostiene el valor del estado de la línea base del programa
	 */
	public static final Long PARAM_LINEA_PROGRAMA_ESTADO_APROBADA = 344L;

	public static final String PARAM_TIPO_ENTREGABLE = "TIPO_ENTREGABLE";

	public static final Long PARAM_TIPO_ENTREGABLE_ACTAS = 346L;

	public static final Long PARAM_TIPO_ENTREGABLE_INFORMES = 347L;

	public static final Long PARAM_TIPO_ENTREGABLE_INFORMESGESTION = 348L;

	public static final Long PARAM_TIPO_ENTREGABLE_INFORMES_FALLIDA = 359L;

	public static final Long PARAM_TIPO_ESTADIO_CONSUMO_PRECOMPROMETIDO = 349L;

	public static final Long PARAM_TIPO_ESTADIO_CONSUMO_COMPROMETIDO = 350L;

	public static final Long PARAM_TIPO_ESTADIO_CONSUMO_PORLIQUIDAR = 351L;

	public static final Long PARAM_TIPO_ESTADIO_CONSUMO_LIQUIDADO = 352L;

	public static final Long PARAM_TIPO_ESTADIO_CONSUMO_FACTURADO = 353L;

	public static final Long PARAM_TIPO_ESTADIO_CONSUMO_PENALIZADO = 354L;

	public static final Long PARAM_USUARIO_SIGED_CONTABILIDAD = 360L;

	public static final String PARAM_TIPO_ESTADIO_CONSUMO = "TIPO_ESTADIO_CONSUMO";
	/**
	 * Parámetro de integración SOAP- SIGED
	 * 
	 */
	public static final String PARAM_SIGED_SOAP_COD_SISTEMA = "PGIM";
	public static final String PARAM_MODULO_GENERICO = "PGIM-Siged: General de Documentos";
	/*
	 * Servicio listar documento expediente.
	 * endpoint del servicio
	 */
	public static final String PARAM_L_DOCUMENTOS_URL = "SRV_AP403-4_EXPEDIENTE/expediente/listarDocumentos/v1.1";
	public static final String PARAM_L_DOCUMENTOS_ACCION = "expediente/documentos";
	public static final String PARAM_L_DOCUMENTOS_TIPO_ACCION = "L";
	public static final String PARAM_L_DOCUMENTOS_ACCION_SERVICIO = "expediente/documentos";
	public static final String PARAM_TIPO_ACCION = "L";
	// public static final String PARAM_ID_ROL = "5";

	public static final String PARAM_L_TRAZABILIDAD_URL = "SRV_AP403-8_EXPEDIENTE/expediente/listarTrazabilidadDocumentos/v1.1";
	public static final String PARAM_L_TRAZABILIDAD_ACCION_SERVICIO = "trazabilidad/list";

	public static final String PARAM_ID_ROL_USUARIO_FINAL = "5";
	public static final String PARAM_ID_ROL_SUPERVISOR_CONCESIONARIA = "89";

	public static final String PARAM_MSG_NO_L_DOCUMENTOS = "De acuerdo con la configuración de usuarios en el Siged, usted no tiene acceso al listado de documentos del expediente: %s";

	public static final String PARAM_CREAR_EXP_URL = "SRV_AP403-3_EXPEDIENTE/expediente/crearExpediente/v1.1";

	public static final String PARAM_L_ARCHIVOS_URL = "SRV_AP403-5_EXPEDIENTE/expediente/listarArchivos/v1.1";
	public static final String PARAM_L_ARCHIVOS_ACCION_SERVICIO = "archivo/list";

	public static final String PARAM_AGREGAR_DOC_URL = "SRV_AP403-2_EXPEDIENTE/expediente/agregarDocumento/v1.1";
	public static final String PARAM_AGREGAR_DOC_ACCION_SERVICIO = "expediente/agregarDocumento";
	public static final String PARAM_AGREGAR_DOC_TIPO_ACCION = "A";

	public static final String PARAM_AGREGAR_ARCHIVO_URL = "SRV_AP403-6_EXPEDIENTE/expediente/agregarArchivoADocumento/v1.1";
	public static final String PARAM_AGREGAR_ARCHIVO_ACCION_SERVICIO = "expediente/documentos";
	public static final String PARAM_AGREGAR_ARCHIVO_TIPO_ACCION = "L";

	public static final String PARAM_DESCARGA_ARCHIVO_URL = "SRV_AP403-1_EXPEDIENTE/expediente/descargarArchivo/v1.1";
	public static final String PARAM_DESCARGA_ARCHIVO_ACCION_SERVICIO = "archivo/descarga";
	public static final String PARAM_DESCARGA_ARCHIVO_TIPO_ACCION = "L";

	public static final String PARAM_ANULAR_ARCHIVO_URL = "SRV_AP403-11_EXPEDIENTE/expediente/anularArchivo/v1.1";
	public static final String PARAM_ANULAR_ARCHIVO_ACCION_SERVICIO = "nuevodocumento/anularArchivo";
	public static final String PARAM_ANULAR_ARCHIVO_TIPO_ACCION = "A";

	public static final String PARAM_ANULAR_DOCUMENTO_URL = "SRV_AP403-12_EXPEDIENTE/expediente/anularDocumento/v1.1";
	public static final String PARAM_ANULAR_DOCUMENTO_ACCION_SERVICIO = "documento/anular";
	public static final String PARAM_ANULAR_DOCUMENTO_TIPO_ACCION = "A";

	public static final String PARAM_OBTENER_USUARIO_URL = "SRV_AP403-13_EXPEDIENTE/expediente/obtenerUsuario/v1.1";

	public static final String PARAM_ACCESO_EXPEDIENTE_URL = "SRV_AP403-16_EXPEDIENTE/expediente/accesoExpediente/v1.1";
	
	public static final String PARAM_REENVIAR_EXPEDIENTE_URL = "SRV_AP403-18_EXPEDIENTE/expediente/reenviarExpediente/v1.1";

	public static final String PARAM_DEVOLVER_EXPEDIENTE_URL = "SRV_AP403-23_EXPEDIENTE/expediente/devolverExpediente/v1.1";

	public static final String PARAM_APROBAR_EXPEDIENTE_URL = "SRV_AP403-22_EXPEDIENTE/expediente/aprobarExpediente/v1.1";

	public static final String PARAM_ENUMERAR_DOCUMENTO_URL = "SRV_AP403-19_EXPEDIENTE/expediente/enumeracionDocumetos/v1.1";

	public static final String PARAM_ES_FERIADO_URL = "SRV_AP403-21_EXPEDIENTE/expediente/esFeriado/v1.1";

	public static final String PARAM_REVERTIR_FIRMA_URL = "SRV_AP403-24_EXPEDIENTE/expediente/revertirFirma/v1.1";

	/**
	 * Servicio Buscar Expediente OSB
	 */ 
	public static final String PARAM_BUSCAR_EXPEDIENTE_URL = "SRV_AP403-7_EXPEDIENTE/expediente/buscarExpediente/v1.1";
	public static final String PARAM_BUSCAR_EXPEDIENTE_ACCION_SERVICIO = "expediente/find";
	public static final String PARAM_BUSCAR_EXPEDIENTE_TIPO_ACCION_SERVICIO = "L";
	public static final String PARAM_BUSCAR_EXPEDIENTE_MODULO = "AP403-07";

	/**
	 * Prueba
	 */
	public static final BigDecimal MO_PENALIDAD = new BigDecimal("0");

	public static final Long PARAM_ESPECIALIDAD_VENTILACION = 5L;
	public static final Long PARAM_ESPECIALIDAD_PLANTA_BENEFICIO = 3L;

	/**
	 * Integración Siges para flujos de aprobación
	 */
	public static final Long PARAM_REENVIAR = 367L;
	public static final Long PARAM_APROBAR = 368L;
	public static final Long PARAM_DEVOLVER = 369L;
	public static final Long PARAM_EXCEPCION = 370L;	

	public static final String IND_REQ_APROBACION_SI = "1";
	public static final String IND_REQ_APROBACION_NO = "0";

	public static final Long PARAM_SC_DOCX = 377L;

	public static final Long PARAM_SC_PDF = 378L;

	/**
	 * Estados de la configuración
	 */
	public static final Long PARAM_ESTADO_CONFIG_RIESGO_CREADA = 373L;
	public static final Long PARAM_ESTADO_CONFIG_RIESGO_OBSERVADA = 374L;
	public static final Long PARAM_ESTADO_CONFIG_RIESGO_APROBADA = 375L;
	public static final Long PARAM_ESTADO_CONFIG_RIESGO_CANCELADA = 376L;

	/**
	 * Subcategorías documentales
	 */
	public static final Long PARAM_SC_ACTA_FISCALIZACION = 27L;
	public static final Long PARAM_SC_ACTA_INICIO_FISCALIZACION = 9L;
	public static final Long PARAM_SC_ACTA_REQUERIMIENTO_DOCUMENTACION = 10L;
	public static final Long PARAM_SC_ACTA_RECEPCION_DOCUMENTACION = 11L;
	public static final Long PARAM_SC_CUADRO_VERIFICACION = 26L;
	public static final Long PARAM_SC_ACTA_FISCALIZACION_FINAL = 27L;
	public static final Long PARAM_SC_INFORME_SUPERVISION = 29L;
	public static final Long PARAM_SC_FICHA_OBSERVACION_INFORME_FISCALIZACION = 33L;
	public static final Long PARAM_SC_FICHA_CONFORMIDAD_INFORME_FISCALIZACION = 34L;
	public static final Long PARAM_SC_RESPUESTA_SOLICITUD_AMPLIACIÓN_PERIODO_SUPERVISION = 36L;
	public static final Long PARAM_SC_INFORME_ARCHIVADO_INSTRUCCIÓN_PRELIMINAR = 41L;
	public static final Long PARAM_SC_INFORME_INSTRUCCION_INICIO_PAS = 42L;
	public static final Long PARAM_SC_FICHA_JUSTIFICACION = 60L;	
	public static final Long PARAM_SC_LIQUIDACION_INFORME_GESTION = 63L;
	public static final Long PARAM_SC_INFORME_SUPERVISION_FALLIDA = 70L;	
	public static final Long PARAM_SC_RECURSO_APELACION = 88L;
	public static final Long PARAM_SC_FICHA_RIESGOS_PARAM_GESTION = 158L;
	public static final Long PARAM_SC_FICHA_RIESGOS_PARAM_TECNICOS = 157L;
	public static final Long PARAM_SC_INFORME_SUPERVISION_PROPIA = 163L;
	public static final Long PARAM_SC_INFORME_SUPERVISION_FALLIDA_PROPIA = 164L;
	public static final Long PARAM_SC_ANEXO_INFORME_INSTRUCCION = 214L;
	public static final Long PARAM_SC_INFORME_GESTION = 215L;
	public static final Long PARAM_SC_RECURSO_IMPUG_POR_CALIFICAR = 227L;
	public static final Long PARAM_SC_ACTA_MEDICION_VELOCIDAD_AIRE = 21L;
	public static final Long PARAM_SC_ANEXO_ACTA_MEDICION_VELOCIDAD_AIRE = 22L;
	public static final Long PARAM_SC_ACTA_MEDICION_EMISION_GASES_CON_MOTORPETR = 23L;
	public static final Long PARAM_SC_ACTA_CALCULOS_COBERTURA_LABORES = 24L;
	public static final Long PARAM_SC_ACTA_DESCRIP_EVALUACION_GASES_Y_HUMOP = 25L;
	public static final Long PARAM_SC_CONTROL_INSPECCION_DEPOSITOS_DESMONTE = 17L;
	public static final Long PARAM_SC_CONTROL_INSPECCION_DEPOSITOS_RELAVES = 18L;
	public static final Long PARAM_SC_CONTROL_INSPECCION_PILAS_LIXIVIACION = 19L;
	public static final Long PARAM_SC_ACTA_MEDICION_CAMPO = 184L;
	public static final Long PARAM_SC_COPIA_ACTA_FISCALIZAC = 202L;
	public static final Long PARAM_SC_COPIA_INFORME_FISC_EMPSUP = 203L;
	public static final Long PARAM_SC_COPIA_FICHA_CONFORMIDAD_INFOFISC = 204L;
	public static final Long PARAM_SC_COPIA_FICHA_OBSERVAC_INFOFISC = 205L;
	public static final Long PARAM_SC_COPIA_INFORME_JUSTIFICACION = 206L;
	public static final Long PARAM_SC_COPIA_INFORME_FISCALIZAC_FALLIDA = 207L;
	public static final Long PARAM_SC_INFORME_SUSTENTATORIO_APLIC_PENALIDAD = 247L;
	public static final Long PARAM_SC_COPIA_INFORME_SUSTENTATORIO_APLIC_PENALIDAD = 246L;
	

	public static final Long PARAM_GRUPO_RIESGO_TECNICO = 1L;
	public static final Long PARAM_GRUPO_RIESGO_GESTION = 2L;

	public static final String PARAM_ESTADO_ACTIVO_CLIENTE_SIGED = "65";

	/**
	 * Estados de registro AGOL / SURVEY
	 */
	public static final String PARAM_SURVEY_ESTADO_SUPERV_INICIADA = "1";
	public static final String PARAM_SURVEY_ESTADO_SUPERV_FINALIZADA = "0";
	
	/**
	 * Estados de los instrumentos de fiscalización
	 */
	public static final Long PARAM_ESTADO_INSTRUMENTO_FISC_REGISTRADO = 480L;
	public static final Long PARAM_ESTADO_INSTRUMENTO_FISC_APROBADO = 481L;
	public static final Long PARAM_ESTADO_INSTRUMENTO_FISC_REEMPLAZADO = 482L;
	public static final Long PARAM_ESTADO_INSTRUMENTO_FISC_NODISPONIBLE = 483L;
	public static final Long PARAM_ESTADO_INSTRUMENTO_FISC_MODIFICADO = 484L;
	public static final Long PARAM_ESTADO_INSTRUMENTO_FISC_REEMPL_APROB = 485L;
	public static final Long PARAM_ESTADO_INSTRUMENTO_FISC_NODISPON_APROB = 486L;

	// public static final String PARAM_SURVEY_REGISTRO_EXITOSO = "true";
	// public static final String PARAM_SURVEY_REGISTRO_ERROR = "false";

	public static final Long PARAM_TIPO_NORMA = 31L;
	public static final Long PARAM_DIVISION_ITEM = 32L;
	public static final Long PARAM_MEDIDA_ADMINISTRATIVA = 49L;
	public static final Long PARAM_TIPO_OBJETO = 50L;
	public static final String PARAM_MEDIO_DENUNCIA = "MEDIO_DENUNCIA";

	public static final Long PARAM_TIPO_MATERIA = 53L;
	public static final Long PARAM_TIPO_REPORTE = 52L;
	public static final String PARAM_MATERIA = "TIPO_MATERIA";
	public static final String PARAM_REPORTE = "TIPO_REPORTE";
	public static final String PARAM_REPORTE_GRUPO = "GRUPO_REPORTE";

	/**
	 * Parámetro nombre de tipo norma "TIPO_NORMA" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_NOMBRE_TIPO_NORMA = "TIPO_NORMA";

	/**
	 * Parámetro nombre de tipo de division ítem "TIPO_DIVISION_ITEM" registrado en
	 * la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_NOMBRE_TIPO_DIVISION_ITEM = "TIPO_DIVISION_ITEM";

	/**
	 * Parámetro de nombre "IND_CONSISTENCIA_ALEATORIA" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_INDICE_CONSISTENCIA_ALEATORIA = "IND_CONSISTENCIA_ALEATORIA";

	/**
	 * Parámetro de nombre "IND_RATIO_CONSISTENCIA_ACEPTABLE" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_INDICE_RATIO_CONSISTENCIA_ACEPTABLE = "IND_RATIO_CONSISTENCIA_ACEPTABLE";

	/**
	 * Parámetro de nombre "TIPO_ORIGEN_DATO_RIESGO" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_ORIGEN_DATO_RIESGO = "TIPO_ORIGEN_DATO_RIESGO";

	/**
	 * Parámetro de nombre "TIPO_MEDIDA_ADMINISTRATIVA" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_MEDIDA_ADMINISTRATIVA = "TIPO_MEDIDA_ADMINISTRATIVA";

	/**
	 * Parámetro de nombre "TIPO_OBJ_RELACIONADO_MED_ADM" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_OBJ_RELACIONADO_MED_ADM = "TIPO_OBJ_RELACIONADO_MED_ADM";
	
	/**
	 * Parámetro de nombre "TIPO_NOTIFICACION" registrado en la tabla PGIM_TP_PARAMETRO
	 */
	public static final Long PARAM_TIPO_NOTIF_ELECTRONICA = 508L;
	public static final Long PARAM_TIPO_NOTIF_FISICA = 509L;
	

	// SNE:
	public static final String PARAM_SNE_ACTIVO = "A";

	public static final String PARAM_SNE_INACTIVO = "I";

	public static final String PARAM_SNE_PENDIENTE_ACTIVACION = "P";

	public static final String PARAM_SNE_NO_ESTA_AFILIADO = "N";

	public static final Long PARAM_RELACION_ASIGNARCONF_REGISTRARCONF = 718L;

	public static final Long PARAM_RELACION_REGISTRARCONF_CANCELARCONF = 719L;

	public static final Long PARAM_RELACION_CANCELARCONF_CONFCANCELADA = 720L;

	public static final Long PARAM_RELACION_REGISTRARCONF_APROBARCONF = 721L;

	public static final Long PARAM_RELACION_APROBARCONF_CONFAPROBADA = 722L;

	public static final Long PARAM_RELACION_APROBARCONF_REGISTRARCONF = 725L;

	public static final Long PARAM_RELACION_CONFAPROBADA_APROBARCONF = 781L;

	public static final Long PARAM_RELACION_APROBARCONF_CONFARCHIVADA = 782L;

	public static final Long PARAM_TIPO_ESTADO_CONF_CREADA = 373L;

	public static final Long PARAM_TIPO_ESTADO_CONF_OBSERVADA = 374L;

	public static final Long PARAM_TIPO_ESTADO_CONF_APROBADA = 375L;

	public static final Long PARAM_TIPO_ESTADO_CONF_ARCHIVADA = 443L;

	public static final Long PARAM_TIPO_ESTADO_CONF_CANCELADA = 376L;

	public static final Long PARAM_TIPO_ESTADO_CONF_PARA_APROBAR = 408L;

	public static final Long PARAM_TIPO_ESTADO_CONF_PARA_CANCELAR = 409L;	
	

	public static final Long PARAM_NU_ANIO = new PgimPlanSupervisionDTO().getNuAnio();

	public static final String PARAM_EXTENSION_PDF = "PDF";

	public static final String PARAM_EXTENSION_EXCEL = "XLSX";

	/**
	 * imagen del logo de osinergmin en base64 para hacer usado en los reportes en
	 * formato excel
	 */
	public static final String PARAM_LOGO_OSI = "LOGO_OSI.png";

	/**
	 * categoria de documento: Antecedente
	 */
	public static final Long PARAM_CATEGORIA_DOC_ANTECEDENTE = 26L;

	/**
	 *	
	 */
	public static final Long PARAM_TIPO_ANTECEDENTE_INFORME_SUPERV = 435L;

	/**
	 * Aplica el parametro para obtener la extensión del archivo -> PDF
	 */
	public static final Long PARAM_EXTENSION_COD_PDF = 378L;

	/**
	 * Identificador interno del tipo de ítem de norma cuando este es ARTICULO.
	 */
	public static final Long PARAM_TIPO_ITEM_ID_ARTICULO = 326L;
	
	/*
	 * AAplica el parametro para obtener el tipo de norma correpondiente a cuadro de
	 * tipificación
	 */
	public static final Long PARAM_TIPO_NORMA_TIPIFICACION = 384L;
	
	/**
	 * Parámetro de nombre "TIPO_CONFIGURACION_BASE" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_CONFIGURACION_BASE = "TIPO_CONFIGURACION_BASE";

	/**
	 * Parámetro tipo de configuración de riesgo "TIPO_CONFIGURACION_RIESGO" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String TIPO_CONFIGURACION_RIESGO = "TIPO_CONFIGURACION_RIESGO";

	/**
	 * Parámetro tipo de periodo "TIPO_PERIODO" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_PERIODO = "TIPO_PERIODO";
	
	/**
	 * Parámetro tipo de periodo "TIPO_INDICADOR" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_INDICADOR = "TIPO_INDICADOR";
	
	/**
	 * Parámetro tipo de periodo "TIPO_AGRUPACION_INDICADOR" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_AGRUPACION_INDICADOR = "TIPO_AGRUPACION_INDICADOR";
	
	/**
	 * Parámetro tipo de periodo "TIPO_ACTORN_INDICADOR" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_ACTORN_INDICADOR = "TIPO_ACTORN_INDICADOR";
	
	/**
	 * Parámetro tipo de periodo "TIPO_CARACTER_FISC" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_CARACTER_FISC = "TIPO_CARACTER_FISC";
	
	/**
	 * Parámetro tipo de periodo "TIPO_UNIDAD_MEDIDA" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_UNIDAD_MEDIDA = "TIPO_UNIDAD_MEDIDA";

	/**
	 * Parámetro tipo de periodo "TIPO_MEDIO_INGRESO" registrado en la tabla
	 * PGIM_TP_PARAMETRO
	 */
	public static final String PARAM_TIPO_MEDIO_INGRESO = "TIPO_MEDIO_INGRESO";

	/**
	 * Pasos del proceso de supervisión (ahora fiscalización)
	 */
	public static final Long PARAM_PROCESO_PASO_FIRMAR_DJI = 3L;
	public static final Long PARAM_PROCESO_PASO_REVISAR_ANTECEDENTES_UM = 8L;
	public static final Long PARAM_PROCESO_PASO_INICIAR_SUPERVISION_CAMPO = 10L;
	public static final Long PARAM_PROCESO_PASO_SOLICITAR_DOC_A_UM = 16L;
	public static final Long PARAM_PROCESO_PASO_REALIZAR_MED_Y_CONSTATAR_HEC = 17L;
	public static final Long PARAM_PROCESO_PASO_PRESENTAR_ACTA_SUPER = 18L;
	
	public static final Long PARAM_PROCESO_PASO_PRESENTAR_INFO_SUPER = 19L;
	public static final Long PARAM_PROCESO_PASO_ELABORAR_FICHA_HECHOS_VERIFICADOS = 315L;
	public static final Long PARAM_PROCESO_PASO_CONFIRMAR_HC_E_INFRA=21L;
	public static final Long PARAM_PROCESO_PASO_VISAR_HC = 57L;

	/*
	 * Pasos del proceso PAS 
	 */
	public static final Long PARAM_PROCESO_PASO_CONTINUAR_PAS = 367L;
	public static final Long PARAM_PROCESO_PASO_DERIVAR_EJECUTORIA_COACTIVA = 204L;

	/**
	 * Pasos del proceso de ranking de riesgos
	 */
	public static final Long PARAM_PROCESO_PASO_RANKING_APROBRADO = 305L;
	
		
	/**
	 * Prefijos utilizados como parte de un código de objeto
	 */
	public static final String PREFIJO_CFG_RIESGO = "CFGR";
	public static final String PREFIJO_RANK_RIESGO = "RANK";
	public static final String PREFIJO_PROGRAMA_SUP = "PRGS";
	public static final String PREFIJO_PAS = "PASA";
	
	/***
	 * Usuarios determinados por defecto PAS
	 */
	public static final Long PARAM_NO_USUARIO_TASTEM = 497L;
	
	public static final Long PARAM_NO_USUARIO_COACTIVA = 507L;
	
	public static final Long PARAM_TIPO_SUBFLUJO_PRINCIPAL = 495L;
	
	public static final Long PARAM_TIPO_SUBFLUJO_SECUNDARIO = 496L;
		
	/**
	 * Configuración de permisos
	 */
	public static final String PERMISO_VER_SUPERVISIONES_MI_INTERES = "sp-lst-int_AC"; // Ver supervisiones de mi interes
	public static final String PERMISO_VER_SUPERVISIONES_TODAS = "sp-lst-tod_AC"; // Ver todas las supervisiones	
	public static final String PERMISO_VER_PAS_MI_INTERES =	"fc-interes_MO";// Ver PAS de mi interes
	public static final String PERMISO_VER_PAS_TODAS =	"fc028_AC"; // Ver todos los PAS	
	public static final String PERMISO_VER_MEDIDAS_ADM_TODAS =	"ma004_AC"; // Ver todas las Medidas Administrativas
	public static final String PERMISO_VER_RANKING_RIESGOS_TODAS =	"ri001_AC"; // Ver todos los Rankings de riesgos
	public static final String PERMISO_VER_CONFIG_RIESGOS_TODAS =	"cr001_AC"; // Ver todas las configuraciones de riesgos
	public static final String PERMISO_VER_LIQUIDACIONES_TODAS =	"lq-lst-tod_AC"; // Ver todas las liquidaciones
	public static final String PERMISO_VER_PROGRAMACIONES_TODAS =	"pr001_AC"; // Ver todas las programaciones
	
	public static final String TEXT_BLANK_DOC = " " ;

	/**
	 * Configuración para alinear el texto enriquecido en una celda
	 */
	public static final String ALINEAR_CENTER = "center";
	public static final String ALINEAR_LEFT = "left";
	public static final String ALINEAR_RIGHT = "right";
	public static final String ALINEAR_JUSTIFY = "justify";
	
	/**
	 * Constantes de nombres de métodos con excepción controlada
	 */
	public static final String METODO_ASIGNAR_TAREA = "Asignar tarea";
	public static final String METODO_REENVIAR_EXP_SIGED_CONTABILIDAD = "Reenviar expediente Siged a Contabilidad";
	public static final String METODO_REENVIAR_EXP_SIGED_PARA_ARCHIVAR = "Reenviar expediente Siged para archivarlo";
	public static final String METODO_RETOMAR_PAS = "Retomar el PAS";
	public static final String METODO_RETORNAR_FISC_REEVAL_HV = "Retornar fiscalización para re-evaluación por aviso del PAS";
	public static final String METODO_ARCHIVAR_EXP_SIGED = "Archivar expediente Siged";
	public static final String METODO_ARCHIVAR_FISC_RELACIONADO = "Archivar expediente Siged de la fiscalización relacionada";
	public static final String METODO_ARCHIVAR_PAS_RELACIONADO = "Archivar expediente Siged del PAS relacionado";
	public static final String METODO_REABRIR_EXP_SIGED = "Reabrir expediente Siged";
	
	/**
	 * Constantes de tipo de alertas instancia proceso
	 */
	public static final Long ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD = 1L;
	public static final Long ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD_AMPLIACION = 2L;
	public static final Long ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR = 3L;
	public static final Long ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR_AMPLIACION = 4L;
	public static final Long ALERTA_CUMPLIMIENTO_PLAZO_REVISION_INFORME = 5L;
	public static final Long ALERTA_CUMPLIMIENTO_PLAZO_PRESENTAR_INFORME = 6L;
	
	/**
	 * Constantes auxiliares para Tipo de registro documental
	 */
	public static final String TIPO_REG_DOCUMENTAL_DOCUMENTO = "D";
	public static final String TIPO_REG_DOCUMENTAL_ARCHIVO = "A";
	
	/**
	 * Constantes auxiliares para nombres de tablas de Entidad de negocio sobre Bibliografía
	 */
	public static final String NO_TABLA_ENEGOCIO_AGENTE_SUPERVISADO = "PGIM_TM_AGENTE_SUPERVISADO";
	public static final String NO_TABLA_ENEGOCIO_UNIDAD_MINERA = "PGIM_TM_UNIDAD_MINERA";
	public static final String NO_TABLA_ENEGOCIO_COMPONENTE_MINERO = "PGIM_TM_COMPONENTE_MINERO";
	
	/**
	 * Indicadores de Tiempo de procesamiento
	 */
	public static final String INDTP_01 = "1";
	public static final String INDTP_02 = "2";
	
	/**
	 * Indicadores: Tipo de agrupación para actor de negocio
	 */
	public static final Long PARAM_INDIC_TIPO_ACTORN_AF = 516L;
	public static final Long PARAM_INDIC_TIPO_ACTORN_UM = 517L;
	
	/**
	 * Indicadores: Tipo de agrupación para característica de fiscalización
	 */
	public static final Long PARAM_INDIC_TIPO_CARACTERIST_ESPECIALIDAD = 518L;
	public static final Long PARAM_INDIC_TIPO_CARACTERIST_DS = 519L;
	public static final Long PARAM_INDIC_TIPO_CARACTERIST_ESPEC_Y_DS = 520L;

	/**
	 * Constantes para perfil de usuario
	 */
	public static final String FORMATO_NOMBRE_FOTO_USUARIO = "Foto_id_persona_%s";
	public static final String EXTENSION_NOMBRE_FOTO_USUARIO = ".jpg";

	/*
	 * Tipo de crud
	 */
	public static final Long INCLUIR = 6L;

}
