package pe.gob.osinergmin.pgim.utils;

/**
 * Enumerado que lista los valores de parametro disponibles en la aplicación
 */
public enum EValorParametro {

    /**
     * METODO_MINADO: Subterráneo
     */
    MEMIN_SUBTRNEO,

    /**
     * METODO_MINADO: Superficial
     */
    MEMIN_SPRFCIAL,

    /**
     * METODO_MINADO: No aplica
     */
    MEMIN_NO_APLCA,

    /**
     * TIPO_SUSTANCIA: Metálica
     */
    SUSTA_METALICA,

    /**
     * TIPO_SUSTANCIA: No metálica
     */
    SUSTA_NO_METALICA,

    /**
     * TIPO_DOCUMENTO_IDENTIDAD: DNI
     */
    DOIDE_DNI,

    /**
     * TIPO_DOCUMENTO_IDENTIDAD: RUC
     */
    DOIDE_RUC,

    /**
     * TIPO_DOCUMENTO_IDENTIDAD: CE
     */
    DOIDE_CE,

    /**
     * TAMANIO_EMPRESA: Microempresa
     */
    TAEMP_MCRO_EMPRSA,

    /**
     * TAMANIO_EMPRESA: Pequeña empresa
     */
    TAEMP_PQUENIA_EMPRSA,

    /**
     * TAMANIO_EMPRESA: Mediana empresa
     */
    TAEMP_MDIANA_EMPRSA,

    /**
     * TAMANIO_EMPRESA: Gran empresa
     */
    TAEMP_GRN_EMPRSA,

    /**
     * TAMANIO_EMPRESA: Instituciones del estado
     */
    TAEMP_INSTTCIONES_ESTDO,

    /**
     * TAMANIO_EMPRESA: Minería artesanal
     */
    TAEMP_MNRIA_ARTSNAL,

    /**
     * TAMANIO_EMPRESA: Pequeña minería
     */
    TAEMP_PQUNIA_MNRIA,

    /**
     * TAMANIO_EMPRESA: Mediana y gran minería
     */
    TAEMP_MDIANA_GRN_MNRIA,

    /**
     * DIVISION_SUPERVISORA: DSGM
     */
    DISUP_DSGM,

    /**
     * DIVISION_SUPERVISORA: DSMM
     */
    DISUP_DSMM,

    /**
     * SITUACION_UNIDAD_MINERA: Almacenamiento
     */
    SIUMI_ALMCNMIENTO,

    /**
     * SITUACION_UNIDAD_MINERA: Beneficio
     */
    SIUMI_BNFCIO,

    /**
     * SITUACION_UNIDAD_MINERA: Cierre final
     */
    SIUMI_CIERRE_FNAL,

    /**
     * SITUACION_UNIDAD_MINERA: Cierre progresivo
     */
    SIUMI_CIERRE_PRGRSVO,

    /**
     * SITUACION_UNIDAD_MINERA: Cierre post-cierre (definitivo)
     */
    SIUMI_CIERRE_PST_CIERRE,

    /**
     * SITUACION_UNIDAD_MINERA: Cateo y prospección
     */
    SIUMI_CTEO_PRSPCCION,

    /**
     * SITUACION_UNIDAD_MINERA: Exploración
     */
    SIUMI_EXPLRCION,

    /**
     * SITUACION_UNIDAD_MINERA: Explotación
     */
    SIUMI_EXPLTCION,

    /**
     * SITUACION_UNIDAD_MINERA: Explotación y beneficio
     */
    SIUMI_EXPLTCION_Y_BNFCIO,

    /**
     * SITUACION_UNIDAD_MINERA: Paralizada
     */
    SIUMI_PARLZDA,

    /**
     * SITUACION_UNIDAD_MINERA: Preparación y desarrollo
     */
    SIUMI_PRPRCION_Y_DSRRLLO,

    /**
     * SITUACION_UNIDAD_MINERA: Sin actividad minera
     */
    SIUMI_SIN_ATVDAD_MNRA,

    /**
     * SITUACION_UNIDAD_MINERA: Suspensión
     */
    SIUMI_SSPNSION,

    /**
     * TIPO_ACTIVIDAD: Exploración
     */
    ACTIV_EXPLRCION,

    /**
     * TIPO_ACTIVIDAD: Explotación
     */
    ACTIV_EXPLTCION,

    /**
     * TIPO_ACTIVIDAD: Beneficio
     */
    ACTIV_BNFCIO,

    /**
     * TIPO_ACTIVIDAD: Almacenamiento
     */
    ACTIV_ALMCNMIENTO,

    /**
     * TIPO_ACTIVIDAD: Explotación y beneficio
     */
    ACTIV_EXPLTCION_BNFICIO,

    /**
     * TIPO_ACTIVIDAD: Transporte
     */
    ACTIV_TRNSPRTE,

    /**
     * TIPO_ACTIVIDAD: No aplica
     */
    ACTIV_NO_APLCA,

    /**
     * TIPO_UNIDAD_MINERA: 1. UEA
     */
    UNMIN_UEA,

    /**
     * TIPO_UNIDAD_MINERA: 2. Derecho minero
     */
    UNMIN_DRCHO_MNRO,

    /**
     * TIPO_UNIDAD_MINERA: 2.1 Concesión
     */
    UNMIN_CONCSION,

    /**
     * TIPO_UNIDAD_MINERA: 2.1.1 Concesión de beneficio
     */
    UNMIN_CONCSION_BNFCIO,

    /**
     * TIPO_UNIDAD_MINERA: 2.1.2 Concesión de transporte
     */
    UNMIN_CONCSION_TRNSPRTE,

    /**
     * TIPO_UNIDAD_MINERA: 2.1.3 Concesión minera
     */
    UNMIN_CONCSION_MNRA,

    /**
     * TIPO_UNIDAD_MINERA: 2.2 Acumulación
     */
    UNMIN_ACMLCION,

    /**
     * TIPO_UNIDAD_MINERA: 3. Depósito de concentrados
     */
    UNMIN_DPSTO_CNCNTRDOS,

    /**
     * TIPO_YACIMIENTO: Albita-Greisen
     */
    YACIM_ALBTA_GREISN,

    /**
     * TIPO_YACIMIENTO: Carbonatitas
     */
    YACIM_CRBNTTAS,

    /**
     * TIPO_YACIMIENTO: Epitermales
     */
    YACIM_EPTRMLES,

    /**
     * TIPO_YACIMIENTO: Hidrotermales
     */
    YACIM_HDRTRMLES,

    /**
     * TIPO_YACIMIENTO: Intemperismo
     */
    YACIM_INTMPRSMO,

    /**
     * TIPO_YACIMIENTO: Magmáticos
     */
    YACIM_MGMTCOS,

    /**
     * TIPO_YACIMIENTO: Mississippi Valley
     */
    YACIM_MSSSSPPI_VLLEY,

    /**
     * TIPO_YACIMIENTO: Mesotermales
     */
    YACIM_MSTRMLES,

    /**
     * TIPO_YACIMIENTO: Metamórficos
     */
    YACIM_MTMRFCOS,

    /**
     * TIPO_YACIMIENTO: Metamorfizados
     */
    YACIM_MTMRFZDOS,

    /**
     * TIPO_YACIMIENTO: Pegmatíticos
     */
    YACIM_PGMTCOS,

    /**
     * TIPO_YACIMIENTO: Placer
     */
    YACIM_PLACER,

    /**
     * TIPO_YACIMIENTO: Pórfidos
     */
    YACIM_PRFDOS,

    /**
     * TIPO_YACIMIENTO: Sedimentarios
     */
    YACIM_SDMNTRIOS,

    /**
     * TIPO_YACIMIENTO: Skarn
     */
    YACIM_SKRAN,

    /**
     * TIPO_YACIMIENTO: Sulfuros Masivos
     */
    YACIM_SLFROS_MSVOS,

    /**
     * TIPO_COMPONENTE_MINERO: Planta de beneficio
     */
    COMIN_PLNTA_BNFICIO,

    /**
     * TIPO_COMPONENTE_MINERO: Tajo abierto
     */
    COMIN_TJO_ABIERTO,

    /**
     * TIPO_COMPONENTE_MINERO: Relavera
     */
    COMIN_RLVRA,

    /**
     * TIPO_COMPONENTE_MINERO: Pila de lixiviación
     */
    COMIN_PLA_LXVIACION,

    /**
     * TIPO_COMPONENTE_MINERO: Desmontera
     */
    COMIN_DSMNTRA,

    /**
     * TIPO_COMPONENTE_MINERO: Cantera
     */
    COMIN_CNTRA,

    /**
     * TIPO_COMPONENTE_MINERO: Pique
     */
    COMIN_PIQUE,

    /**
     * TIPO_EVENTO: Accidente mortal
     */
    EVENT_ACCIDNTE_MRTAL,

    /**
     * TIPO_EVENTO: Incidente peligroso
     */
    EVENT_INCDNTE_PLGRSO,

    /**
     * TIPO_EMPRESA_INVOLUCRADA: Titular minero
     */
    EMINV_TTLAR_MNRO,

    /**
     * TIPO_EMPRESA_INVOLUCRADA: Contratista
     */
    EMINV_CNTRTSTA,

    /**
     * TIPO_ACTIVIDAD_CIIU: Agricultura
     */
    ACCIU_AGRCLTRA,

    /**
     * TIPO_ACTIVIDAD_CIIU: Pesca
     */
    ACCIU_PESCA,

    /**
     * TIPO_ACTIVIDAD_CIIU: Minas y canteras
     */
    ACCIU_MICANT,

    /**
     * TIPO_ACTIVIDAD_CIIU: Manufactura
     */
    ACCIU_MNFCTRA,

    /**
     * TIPO_ACTIVIDAD_CIIU: Electricidad, gas y agua
     */
    ACCIU_ELCTRCDAD_GAS_Y_AGUA,

    /**
     * TIPO_ACTIVIDAD_CIIU: Construcción
     */
    ACCIU_CNSTRCCION,

    /**
     * TIPO_ACTIVIDAD_CIIU: Comercio
     */
    ACCIU_CMRCIO,

    /**
     * TIPO_ACTIVIDAD_CIIU: Hoteles y Restaurantes
     */
    ACCIU_HTLES_Y_RSTAURNTES,

    /**
     * TIPO_ACTIVIDAD_CIIU: Transporte, Almacenamiento y Comunicaciones
     */
    ACCIU_TRNSPRTE_ALMCNMIENTO_Y_CMNCCIONES,

    /**
     * TIPO_ACTIVIDAD_CIIU: Intermediación Financiera
     */
    ACCIU_INTRMDIACION_FNNCIERA,

    /**
     * TIPO_ACTIVIDAD_CIIU: Activ. inmob. empresarial
     */
    ACCIU_ACTVDAD_INMBLIARIA_EMPRSRIAL,

    /**
     * TIPO_ACTIVIDAD_CIIU: Administración Pública y Defensa
     */
    ACCIU_ADMNSTRCION_PBLCA_Y_DFNSA,

    /**
     * TIPO_ACTIVIDAD_CIIU: Enseñanza
     */
    ACCIU_ENSNIANZA,

    /**
     * TIPO_ACTIVIDAD_CIIU: Servicios Sociales y de Salud
     */
    ACCIU_SRVCIOS_SCIALES_SLUD,

    /**
     * TIPO_ACTIVIDAD_CIIU: Servicios
     */
    ACCIU_SERVIC,

    /**
     * CATEGORIA_OCUPACIONAL: Funcionario/a
     */
    CAOCU_FNCIONRIO,

    /**
     * CATEGORIA_OCUPACIONAL: Empleado/a
     */
    CAOCU_EMPLEADO,

    /**
     * CATEGORIA_OCUPACIONAL: Jefe/a de planta
     */
    CAOCU_JFE_PLNTA,

    /**
     * CATEGORIA_OCUPACIONAL: Capataz
     */
    CAOCU_CPTAZ,

    /**
     * CATEGORIA_OCUPACIONAL: Técnico/a
     */
    CAOCU_TCNCO,

    /**
     * CATEGORIA_OCUPACIONAL: Operario/a
     */
    CAOCU_OPRRIO,

    /**
     * CATEGORIA_OCUPACIONAL: Oficial
     */
    CAOCU_OFCIAL,

    /**
     * CATEGORIA_OCUPACIONAL: Peón/a
     */
    CAOCU_PEON,

    /**
     * CATEGORIA_OCUPACIONAL: Otros
     */
    CAOCU_OTROS,

    /**
     * AGENTE_CAUSANTE: 1. Máquinas
     */
    AGCAU_MQUINAS,

    /**
     * AGENTE_CAUSANTE: 11. Generadores de energía, excepto motores eléctricos
     */
    AGCAU_GNRDRES_ENRGIA_EXCPTO_MTRES_ELCTRCOS,

    /**
     * AGENTE_CAUSANTE: 111.  Máquinas de vapor
     */
    AGCAU_MQUNAS_VPOR,

    /**
     * AGENTE_CAUSANTE: 112.  Máquinas de combustión interna
     */
    AGCAU_MQUNAS_CMBSTION,

    /**
     * AGENTE_CAUSANTE: 119. Otros
     */
    AGCAU_OTROS_1,

    /**
     * AGENTE_CAUSANTE: 12. Sistemas de transmisión
     */
    AGCAU_SSTMA_TRNSMSION,

    /**
     * AGENTE_CAUSANTE: 121.  Árboles de transmisión
     */
    AGCAU_ABLES_TRNSMSION,

    /**
     * AGENTE_CAUSANTE: 122.  Correas, cables, poleas, cadenas, engranajes
     */
    AGCAU_CRREAS_CBLES_PLEAS_CDNAS_ENGRNJES,

    /**
     * AGENTE_CAUSANTE: 129. Otros
     */
    AGCAU_OTROS_2,

    /**
     * AGENTE_CAUSANTE: 13. Máquinas para el trabajo del metal
     */
    AGCAU_MQUNAS_TRBJO_MTAL,

    /**
     * AGENTE_CAUSANTE: 131.  Prensas mecánicas
     */
    AGCAU_PRNSAS_MCNCAS,

    /**
     * AGENTE_CAUSANTE: 132.  Tomos
     */
    AGCAU_TMOS,

    /**
     * AGENTE_CAUSANTE: 133.  Fresadoras
     */
    AGCAU_FRSDRAS,

    /**
     * AGENTE_CAUSANTE: 134.  Rectificadoras y muelas
     */
    AGCAU_RCTFCDRAS_Y_MUELAS,

    /**
     * AGENTE_CAUSANTE: 135.  Cizallas
     */
    AGCAU_CZLLAS,

    /**
     * AGENTE_CAUSANTE: 136.  Forjadoras
     */
    AGCAU_FRJDRAS,

    /**
     * AGENTE_CAUSANTE: 137.  Laminadoras
     */
    AGCAU_LMNDRAS,

    /**
     * AGENTE_CAUSANTE: 139. Otras
     */
    AGCAU_OTRAS_1,

    /**
     * AGENTE_CAUSANTE: 14. Máquinas para trabajar la madera y otras materias
     * similares
     */
    AGCAU_MQUINAS_TRBJAR_MDRA_Y_MTRIALES_SMLRES,

    /**
     * AGENTE_CAUSANTE: 141.  Sierras circulares
     */
    AGCAU_SIERRAS_CRCLRES,

    /**
     * AGENTE_CAUSANTE: 142.  Otras sierras
     */
    AGCAU_OTRAS_CIERRAS,

    /**
     * AGENTE_CAUSANTE: 143.  Máquinas de moldurar
     */
    AGCAU_MQUINAS_MLDRAR,

    /**
     * AGENTE_CAUSANTE: 144.  Cepilladoras
     */
    AGCAU_CPLLDRAS,

    /**
     * AGENTE_CAUSANTE: 149. Otras
     */
    AGCAU_OTRAS_2,

    /**
     * AGENTE_CAUSANTE: 15. Máquinas agrícolas
     */
    AGCAU_MQUNAS_AGRCLAS,

    /**
     * AGENTE_CAUSANTE: 151.  Segadoras, incluso segadoras-trilladoras
     */
    AGCAU_SEGADORAS_TRLLDRAS,

    /**
     * AGENTE_CAUSANTE: 152.  Trilladoras
     */
    AGCAU_TRLLDRAS,

    /**
     * AGENTE_CAUSANTE: 159. Otras
     */
    AGCAU_OTRAS_3,

    /**
     * AGENTE_CAUSANTE: 16. Máquinas para el trabajo en las minas
     */
    AGCAU_MQUINAS_TRBJO_MNAS,

    /**
     * AGENTE_CAUSANTE: 161. Máquinas de rozar
     */
    AGCAU_MQUINAS_RZAR,

    /**
     * AGENTE_CAUSANTE: 169. Otras
     */
    AGCAU_OTRAS_4,

    /**
     * AGENTE_CAUSANTE: 19. Otras máquinas no clasificadas bajo otros epígrafes
     */
    AGCAU_OTRAS_MQUINAS_NO_CLSFCDAS,

    /**
     * AGENTE_CAUSANTE: 191.  Máquinas para desmontes, excavaciones, etc., a
     * excepción de los medios de transporte
     */
    AGCAU_MQUINAS_DSMNTE_EXCVCIONES,

    /**
     * AGENTE_CAUSANTE: 192.  Máquinas de hilar, de tejer y otras máquinas para la
     * industria textil
     */
    AGCAU_MQUINAS_HILAR_INDSTRIA_TXTIL,

    /**
     * AGENTE_CAUSANTE: 193.  Máquinas para la manufactura de productos alimenticios
     * y bebidas
     */
    AGCAU_MQUINAS_MNFCTRA_PRDCTOS_ALMNTCIOS,

    /**
     * AGENTE_CAUSANTE: 194.  Máquinas para la fabricación del papel
     */
    AGCAU_MQUINAS_FBRCCION_PPEL,

    /**
     * AGENTE_CAUSANTE: 195.  Máquinas de imprenta
     */
    AGCAU_MQUINAS_IMPRNTA,

    /**
     * AGENTE_CAUSANTE: 199. Otras
     */
    AGCAU_OTRAS_5,

    /**
     * AGENTE_CAUSANTE: 2. Medios de transporte y de manutención
     */
    AGCAU_MDIOS_TRNSPRTE_MNTNCION,

    /**
     * AGENTE_CAUSANTE: 21. Aparatos de izar
     */
    AGCAU_APRTOS_IZAR,

    /**
     * AGENTE_CAUSANTE: 211.  Grúas
     */
    AGCAU_GRUAS,

    /**
     * AGENTE_CAUSANTE: 212.  Ascensores, montacargas
     */
    AGCAU_ASCNSRES_MNTCRGAS,

    /**
     * AGENTE_CAUSANTE: 213.  Cabrestantes
     */
    AGCAU_CBRSTNTES,

    /**
     * AGENTE_CAUSANTE: 214.  Poleas
     */
    AGCAU_PLEAS,

    /**
     * AGENTE_CAUSANTE: 219. Otros
     */
    AGCAU_OTROS_3,

    /**
     * AGENTE_CAUSANTE: 22. Medios de transporte por vía férrea
     */
    AGCAU_MDIOS_TRNSPRTE_VIA_FRREA,

    /**
     * AGENTE_CAUSANTE: 221.  Ferrocarriles interurbanos
     */
    AGCAU_FRRCRRILES_INTRRBNOS,

    /**
     * AGENTE_CAUSANTE: 222.  Equipos de transporte por vía férrea utilizados en las
     * minas, las galerías, las canteras, los establecimientos industriales, los
     * muelles, etc.
     */
    AGCAU_EQUPOS_TRNSPRTE_VIA_FRREA_MNAS,

    /**
     * AGENTE_CAUSANTE: 229. Otros
     */
    AGCAU_OTROS_4,

    /**
     * AGENTE_CAUSANTE: 23. Medios de transporte rodantes, a excepción de los
     * transportes por vía férrea
     */
    AGCAU_MDIOS_TRNSPRTE_RDNTES,

    /**
     * AGENTE_CAUSANTE: 231.  Tractores
     */
    AGCAU_TRCTRES,

    /**
     * AGENTE_CAUSANTE: 232.  Camiones
     */
    AGCAU_CMIONES,

    /**
     * AGENTE_CAUSANTE: 233.  Carretillas motorizadas
     */
    AGCAU_CRRTLLAS_MTRZDAS,

    /**
     * AGENTE_CAUSANTE: 234.  Vehículos motorizados no clasificados bajo otros
     * epígrafes
     */
    AGCAU_VHCLOS_MTRZDOS_NO_CLSFCDOS,

    /**
     * AGENTE_CAUSANTE: 235.  Vehículos de tracción animal
     */
    AGCAU_VHCLOS_TRCCION_ANMAL,

    /**
     * AGENTE_CAUSANTE: 236.  Vehículos accionados por la fuerza del hombre
     */
    AGCAU_VEHCLOS_ACCIONDOS_FUERZA_HMBRE,

    /**
     * AGENTE_CAUSANTE: 239. Otros
     */
    AGCAU_OTROS_5,

    /**
     * AGENTE_CAUSANTE: 24.  Medios de transporte por aire
     */
    AGCAU_MDIOS_TRNSPRTE_AIRE,

    /**
     * AGENTE_CAUSANTE: 25.  Medios de transporte acuático
     */
    AGCAU_MDIOS_TRNSORTE_ACUATCO,

    /**
     * AGENTE_CAUSANTE: 251.  Medios de transporte por agua con motor
     */
    AGCAU_MDIOS_TRNSPRTE_AGUA_MOTOR,

    /**
     * AGENTE_CAUSANTE: 252.  Medios de transporte por agua sin motor
     */
    AGCAU_MDIOS_TRNSPRTE_AGUA_SIN_MTOR,

    /**
     * AGENTE_CAUSANTE: 26.  Otros medios de transporte
     */
    AGCAU_OTROS_MDIOS_TRNSPRTE,

    /**
     * AGENTE_CAUSANTE: 261.  Transportadores aéreos por cable
     */
    AGCAU_TRNSPRTDRES_AEREOS_CBLE,

    /**
     * AGENTE_CAUSANTE: 262.  Transportadores mecánicos a excepción de los
     * transportadores aéreos por cable
     */
    AGCAU_TRNSPRTDRES_MCNCOS_EXCPCION_TRNSPRTDRAS_AREAS_CABLE,

    /**
     * AGENTE_CAUSANTE: 269. Otros
     */
    AGCAU_OTROS_6,

    /**
     * AGENTE_CAUSANTE: 3. Otros aparatos
     */
    AGCAU_OTROS_APRTOS,

    /**
     * AGENTE_CAUSANTE: 31. Recipientes de presión
     */
    AGCAU_RCPIENTES_PRSION,

    /**
     * AGENTE_CAUSANTE: 311.  Calderas
     */
    AGCAU_CLDRAS,

    /**
     * AGENTE_CAUSANTE: 312.  Recipientes de presión sin fogón
     */
    AGCAU_RCPIENTES_PRSION_SIN_FGON,

    /**
     * AGENTE_CAUSANTE: 313.  Cañerías y accesorios de presión
     */
    AGCAU_CNIERIAS_ACCSRIOS_PRCSION,

    /**
     * AGENTE_CAUSANTE: 314.  Cilindros de gas
     */
    AGCAU_CLNDROS_GAS,

    /**
     * AGENTE_CAUSANTE: 315.  Cajones de aire comprimido, equipo de buzo
     */
    AGCAU_CJNES_AIRE_CMPRMDO_EQUPO_BZO,

    /**
     * AGENTE_CAUSANTE: 319. Otros
     */
    AGCAU_OTROS_7,

    /**
     * AGENTE_CAUSANTE: 32. Hornos, fogones, estufas
     */
    AGCAU_HRNOS_FGNES_ESTFAS,

    /**
     * AGENTE_CAUSANTE: 321.  Altos hornos
     */
    AGCAU_ALTOS_HRNOS,

    /**
     * AGENTE_CAUSANTE: 322.  Hornos de refinería
     */
    AGCAU_HRNOS_RFNRIA,

    /**
     * AGENTE_CAUSANTE: 323.  Otros hornos
     */
    AGCAU_OTROS_HRNOS,

    /**
     * AGENTE_CAUSANTE: 324.  Estufas
     */
    AGCAU_ESTUFAS,

    /**
     * AGENTE_CAUSANTE: 325. Fogones
     */
    AGCAU_FGNES,

    /**
     * AGENTE_CAUSANTE: 33.  Plantas refrigeradoras
     */
    AGCAU_PLNTAS_RFRGRDRAS,

    /**
     * AGENTE_CAUSANTE: 34.  Instalaciones eléctricas, incluidos los motores
     * eléctricos pero con exclusión de las herramientas eléctricas manuales
     */
    AGCAU_INSTLCIONES_ELCTRCAS_INCLUIDOS_MTRES_ELCTRCOS,

    /**
     * AGENTE_CAUSANTE: 341.  Máquinas giratorias
     */
    AGCAU_MQUNAS_GRTRIAS,

    /**
     * AGENTE_CAUSANTE: 342.  Conductores y cables eléctricos
     */
    AGCAU_CNDCTRES_CBLES_ELCTRCOS,

    /**
     * AGENTE_CAUSANTE: 343.  Transformadores
     */
    AGCAU_TRNSFRMDRES,

    /**
     * AGENTE_CAUSANTE: 344.  Aparatos de mando y de control
     */
    AGCAU_APRTOS_MNDO_CNTROL,

    /**
     * AGENTE_CAUSANTE: 349. Otros
     */
    AGCAU_OTROS_8,

    /**
     * AGENTE_CAUSANTE: 35.  Herramientas eléctricas manuales
     */
    AGCAU_HRRMIENTAS_ELCTRCAS_MNUALES,

    /**
     * AGENTE_CAUSANTE: 36.  Herramientas, implementos y utensilios, a excepción de
     * las herramientas eléctricas manuales
     */
    AGCAU_HRRMIENTAS_IMPLMNTOS_UTNSLIOS,

    /**
     * AGENTE_CAUSANTE: 361.  Herramientas manuales accionadas mecánicamente a
     * excepción de las herramientas eléctricas manuales
     */
    AGCAU_HRRMIENTAS_MCNCAS_EXCEPTO_ELCTRCAS_MNUALES,

    /**
     * AGENTE_CAUSANTE: 362.  Herramientas manuales no accionadas mecánicamente
     */
    AGCAU_HRRMIENTAS_MNUALES_NO_MCNCAS,

    /**
     * AGENTE_CAUSANTE: 369. Otros
     */
    AGCAU_OTROS_9,

    /**
     * AGENTE_CAUSANTE: 37.  Escaleras, rampas móviles
     */
    AGCAU_ESCLRAS_RMPAS_MVLES,

    /**
     * AGENTE_CAUSANTE: 38.  Andamios
     */
    AGCAU_ANDMIOS,

    /**
     * AGENTE_CAUSANTE: 39.  Otros aparatos no clasificados bajo otros epígrafes
     */
    AGCAU_OTROS_APRTOS_NO_CLSFCDOS,

    /**
     * AGENTE_CAUSANTE: 4. Materiales, sustancias y radiaciones
     */
    AGCAU_MTRIALES_SSTNCIAS_RDIACIONES,

    /**
     * AGENTE_CAUSANTE: 41.  Explosivos
     */
    AGCAU_EXPLSVOS,

    /**
     * AGENTE_CAUSANTE: 42.  Polvos, gases, líquidos y productos químicos, a
     * excepción de los explosivos
     */
    AGCAU_PLVOS_GSES_LQUIDOS_QUIMCOS,

    /**
     * AGENTE_CAUSANTE: 421.  Polvos
     */
    AGCAU_POLVOS,

    /**
     * AGENTE_CAUSANTE: 422.  Gases, vapores, humos
     */
    AGCAU_GSES_VPRES_HMOS,

    /**
     * AGENTE_CAUSANTE: 423.  Líquidos no clasificados bajo otros epígrafes
     */
    AGCAU_LQUIDOS_NO_CLSFCDOS,

    /**
     * AGENTE_CAUSANTE: 424.  Productos químicos no clasificados bajo otros
     * epígrafes
     */
    AGCAU_PRDCTOS_QUIMCOS_NO_CLSFCDOS,

    /**
     * AGENTE_CAUSANTE: 429. Otros
     */
    AGCAU_OTROS_10,

    /**
     * AGENTE_CAUSANTE: 43.  Fragmentos volantes
     */
    AGCAU_FRGMNTOS_VLNTES,

    /**
     * AGENTE_CAUSANTE: 44.  Radiaciones
     */
    AGCAU_RDIACIONES,

    /**
     * AGENTE_CAUSANTE: 441. Radiaciones ionizantes
     */
    AGCAU_RDIACIONES_IONZNTES,

    /**
     * AGENTE_CAUSANTE: 449. Radiaciones de otro tipo
     */
    AGCAU_RDIACIONES_OTRO_TPO,

    /**
     * AGENTE_CAUSANTE: 49. Otros materiales y sustancias no clasificados bajo otros
     * epígrafes
     */
    AGCAU_OTROS_MTRIALES_SUSTNCIAS_NO_CLSFCDAS,

    /**
     * AGENTE_CAUSANTE: 5. Ambiente del trabajo
     */
    AGCAU_AMBIENTE_DEL_TRBJO,

    /**
     * AGENTE_CAUSANTE: 51. Exterior
     */
    AGCAU_EXTRIOR,

    /**
     * AGENTE_CAUSANTE: 511. Condiciones climáticas
     */
    AGCAU_CNDCIONES_CLMTCAS,

    /**
     * AGENTE_CAUSANTE: 512. Superficies de tránsito y de trabajo
     */
    AGCAU_SPRFCIES_TRNSTO_TRBJO,

    /**
     * AGENTE_CAUSANTE: 513. Agua
     */
    AGCAU_AGUA_1,

    /**
     * AGENTE_CAUSANTE: 519. Otros
     */
    AGCAU_OTROS_11,

    /**
     * AGENTE_CAUSANTE: 521. Pisos
     */
    AGCAU_PSOS,

    /**
     * AGENTE_CAUSANTE: 522. Espacios exiguos
     */
    AGCAU_ESPCIOS_EXGUOS,

    /**
     * AGENTE_CAUSANTE: 523. Escaleras
     */
    AGCAU_ECLRAS,

    /**
     * AGENTE_CAUSANTE: 524. Otras superficies de tránsito y de trabajo
     */
    AGCAU_OTRAS_SPRFCIES_TRNSTO_TRBJO,

    /**
     * AGENTE_CAUSANTE: 525. Aberturas en el suelo y en las paredes
     */
    AGCAU_ABRTRAS_SUELO_PRDES,

    /**
     * AGENTE_CAUSANTE: 526. Factores que crean el ambiente (alumbrado, ventilación,
     * temperatura, ruidos, etc.)
     */
    AGCAU_FCTRES_CREAN_AMBIENTE,

    /**
     * AGENTE_CAUSANTE: 529. Otros
     */
    AGCAU_OTROS_12,

    /**
     * AGENTE_CAUSANTE: 53. Interior
     */
    AGCAU_INTRIOR,

    /**
     * AGENTE_CAUSANTE: 531.  Techados y revestimientos de galerías, de túneles,
     * etc.
     */
    AGCAU_TCHDOS_RVSTMIENTOS_GLRIAS_TNLES,

    /**
     * AGENTE_CAUSANTE: 532.  Pisos de galerías, de túneles, etc.
     */
    AGCAU_PSOS_GLRIA_TNLES_ETC,

    /**
     * AGENTE_CAUSANTE: 533.  Frentes de minas, túneles, etc.
     */
    AGCAU_FRNTES_MNAS_TNLES_ETC,

    /**
     * AGENTE_CAUSANTE: 534.  Pozos de minas
     */
    AGCAU_PZOS_MNAS,

    /**
     * AGENTE_CAUSANTE: 535.  Fuego
     */
    AGCAU_FUEGO,

    /**
     * AGENTE_CAUSANTE: 536.  Agua
     */
    AGCAU_AGUA_2,

    /**
     * AGENTE_CAUSANTE: 539. Otros
     */
    AGCAU_OTROS_13,

    /**
     * AGENTE_CAUSANTE: 54. Subterráneos
     */
    AGCAU_SBTRRNEOS,

    /**
     * AGENTE_CAUSANTE: 6. Otros agentes no clasificados bajo otros epígrafes
     */
    AGCAU_OTROS_AGNTES_NO_CLSFCDOS_1,

    /**
     * AGENTE_CAUSANTE: 61. Animales
     */
    AGCAU_ANMLES,

    /**
     * AGENTE_CAUSANTE: 611. Animales vivos
     */
    AGCAU_ANMLES_VVOS,

    /**
     * AGENTE_CAUSANTE: 612. Productos de animales
     */
    AGCAU_PRDCTOS_ANMLES,

    /**
     * AGENTE_CAUSANTE: 69. Otros agentes no clasificados bajo otros epígrafes
     */
    AGCAU_OTROS_AGNTES_NO_CLSFCDOS_2,

    /**
     * AGENTE_CAUSANTE: 7. Agentes no clasificados por falta de datos suficientes
     */
    AGCAU_AGNTES_NO_CLSFCDOS_FLTA_DTOS_SFCIENTES,

    /**
     * TIPO_ACCIDENTE: 1. Caídas de personas
     */
    ACCIC_CAIDAS_PRSNAS,

    /**
     * TIPO_ACCIDENTE: 11. Caídas de personas con desnivelación [caídas desde
     * alturas (árboles, edificios, andamios, escaleras, máquinas de trabajo,
     * vehículos) y en profundidades (pozos, fosos, excavaciones, aberturas en el
     * suelo)]
     */
    ACCIC_CAIDAS_PRSNAS_DSNVLCION,

    /**
     * TIPO_ACCIDENTE: 12. Caídas de personas que ocurren al mismo nivel
     */
    ACCIC_CAIDAS_PRSNAS_OCRREN_MSMO_NVEL,

    /**
     * TIPO_ACCIDENTE: 2. Caídas de objetos
     */
    ACCIC_CAIDAS_OBJTOS,

    /**
     * TIPO_ACCIDENTE: 21. Derrumbe (caídas de masas de tierra, de rocas, de
     * piedras, de nieve)
     */
    ACCIC_DRRMBE,

    /**
     * TIPO_ACCIDENTE: 22. Desplome (de edificios, de muros, de andamios, de
     * escaleras, de pilas de mercancías)
     */
    ACCIC_DSPLME,

    /**
     * TIPO_ACCIDENTE: 23. Caídas de objetos en curso de manutención manual
     */
    ACCIC_CAIDAS_OBJTOS_CRSO_MNTNCION_MNUAL,

    /**
     * TIPO_ACCIDENTE: 24. Otras caídas de objetos
     */
    ACCIC_OTRAS_CAIDAS_OBJTOS,

    /**
     * TIPO_ACCIDENTE: 3. Pisadas sobre, choques contra, o golpes por objetos, a
     * excepción de caídas de objetos
     */
    ACCIC_PSDAS_CHQUES_GLPES,

    /**
     * TIPO_ACCIDENTE: 31. Pisadas sobre objetos
     */
    ACCIC_PSDAS_OBJTOS,

    /**
     * TIPO_ACCIDENTE: 32. Choques contra objetos inmóviles (a excepción de choques
     * debidos a una caída anterior)
     */
    ACCIC_CHQUES_CNTRA_OBJTOS_INMVLES,

    /**
     * TIPO_ACCIDENTE: 33. Choque contra objetos móviles
     */
    ACCIC_CHQUES_CNTRA_OBJTOS_MVLES,

    /**
     * TIPO_ACCIDENTE: 34. Golpes por objetos móviles (comprendidos los fragmentos
     * volantes y las partículas), a excepción de los golpes por objetos que caen
     */
    ACCIC_GLPES_OBJTOS_MVLES,

    /**
     * TIPO_ACCIDENTE: 4. Atrapada por un objeto o entre objetos
     */
    ACCIC_ATRPDA_POR_OBJTO_ENTRE_OBJTOS,

    /**
     * TIPO_ACCIDENTE: 41. Atrapada por un objeto
     */
    ACCIC_ATRPDA_POR_OBJTO,

    /**
     * TIPO_ACCIDENTE: 42. Atrapada entre un objeto inmóvil y un objeto móvil
     */
    ACCIC_ATRPDA_POR_OBJTO_INMVIL_Y_OBJTO_MVIL,

    /**
     * TIPO_ACCIDENTE: 43. Atrapada entre dos objetos móviles (a excepción de los
     * objetos volantes o que caen)
     */
    ACCIC_ATRPDA_ENTRE_DOS_OBJTOS_MVLES,

    /**
     * TIPO_ACCIDENTE: 5. Esfuerzos excesivos o falsos movimientos
     */
    ACCIC_ESFUERZOS_EXCSVOS_O_FLSOS_MVMIENTOS,

    /**
     * TIPO_ACCIDENTE: 51. Esfuerzos físicos excesivos al levantar objetos
     */
    ACCIC_ESFUERZOS_FSCOS_EXCSVOS_LVNTAR_OBJTOS,

    /**
     * TIPO_ACCIDENTE: 52. Esfuerzos físicos excesivos al empujar objetos o tirar de
     * ellos
     */
    ACCIC_ESFUERZOS_FSCOS_EXCSVOS_EMPJAR_OBJTOS,

    /**
     * TIPO_ACCIDENTE: 53. Esfuerzos físicos excesivos al manejar o lanzar objetos
     */
    ACCIC_ESFUERZOS_FSCOS_EXCSVOS_MNJAR_OBJTOS,

    /**
     * TIPO_ACCIDENTE: 54. Falsos movimientos
     */
    ACCIC_FLSOS_MVMIENTOS,

    /**
     * TIPO_ACCIDENTE: 6. Exposición a, o contacto con, temperaturas extremas
     */
    ACCIC_EXPSCION_CNTCTO_TMPRTRAS_EXTRMAS,

    /**
     * TIPO_ACCIDENTE: 61. Exposición al calor (de la atmósfera o del ambiente de
     * trabajo)
     */
    ACCIC_EXPSCION_CLOR,

    /**
     * TIPO_ACCIDENTE: 62. Exposición al frío (de la atmósfera o del ambiente de
     * trabajo)
     */
    ACCIC_EXPSCION_FRIO,

    /**
     * TIPO_ACCIDENTE: 63. Contacto con sustancias u objetos ardientes
     */
    ACCIC_CNTCTO_SSTNCIAS_OBJTOS_ARDIENTES,

    /**
     * TIPO_ACCIDENTE: 64. Contacto con sustancias u objetos muy fríos
     */
    ACCIC_CONTACTO_SUSTENCIAS_OBJETOS_MUY_FRIOS,

    /**
     * TIPO_ACCIDENTE: 7. Exposición a, o contacto con, la corriente eléctrica
     */
    ACCIC_EXPSCION_CNTCTO_CRRIENTE_ELCTRCA,

    /**
     * TIPO_ACCIDENTE: 8. Exposición a, o contacto con, sustancias nocivas o
     * radiaciones
     */
    ACCIC_EXPSCION_CNTCTO_SSTNCIAS_NCVAS_RDIACIONES,

    /**
     * TIPO_ACCIDENTE: 81. Contacto por inhalación, por ingestión o por absorción
     * con sustancias nocivas
     */
    ACCIC_CNTCTO_INHLCION_INGSTION_ABSRCION_SSTNCIAS_NCVAS,

    /**
     * TIPO_ACCIDENTE: 82. Exposición a radiaciones ionizantes
     */
    ACCIC_EXPSCION_RDIACIONES_IONZNTES,

    /**
     * TIPO_ACCIDENTE: 83. Exposición a otras radiaciones
     */
    ACCIC_EXPSCION_OTRAS_RDIACIONES,

    /**
     * TIPO_ACCIDENTE: 9. Otras formas de accidente, no clasificadas bajo otros
     * epígrafes, incluidos aquellos accidentes no clasificados por falta de datos
     * suficientes
     */
    ACCIC_OTRAS_FRMAS_ACCIDNTE,

    /**
     * TIPO_SEGURO: EsSalud
     */
    SEGUR_ESSSALUD,

    /**
     * TIPO_SEGURO: EPS
     */
    SEGUR_EPS,

    /**
     * TIPO_SEGURO: SCTR
     */
    SEGUR_SCTR,

    /**
     * TIPO_INCIDENTE: Atrapamiento sin daño (dentro, fuera, entre, debajo)
     */
    INCID_ATRPMIENTO_SIN_DNIO,

    /**
     * TIPO_INCIDENTE: Caída de un ascensor
     */
    INCID_CAIDA_DE_ASCNSOR,

    /**
     * TIPO_INCIDENTE: Caídas de cables de alta tensión - contacto de maquinarias o
     * parte de ellas con cables de alta tensión
     */
    INCID_CAIDAS_CBLES_ALTA_TNSION,

    /**
     * TIPO_INCIDENTE: Caídas de cargas izadas (contenedores, paquetes descargas,
     * etc)
     */
    INCID_CAIDAS_CRGAS_IZDAS,

    /**
     * TIPO_INCIDENTE: Choque de vehículos de trabajo
     */
    INCID_CHQUE_VEHCLOS_TRBJO,

    /**
     * TIPO_INCIDENTE: Derrumbe de una construcción
     */
    INCID_DRRMBE_CNSTRCCION,

    /**
     * TIPO_INCIDENTE: Derrumbe de una mina
     */
    INCID_DRRMBE_MNA,

    /**
     * TIPO_INCIDENTE: Derrumbes (zanjas, taludes, calzaduras,excavaciones,de
     * terrenos en general, etc)
     */
    INCID_DRRMBES,

    /**
     * TIPO_INCIDENTE: Derrame, escapes, fugas de materiales peligrosos (corrosivos,
     * reactivos, explosivos, tóxicos, inflamable, biológicos patógenos)
     */
    INCID_DRRME_ESCPES_FGAS_MTRIALES_PLGRSOS,

    /**
     * TIPO_INCIDENTE: Desplomes estructuras, instalaciones, productos almacenados
     */
    INCID_DSPLMES_ESTRCTRAS_INSTLCIONES_PRDCTOS_ALMCNDOS,

    /**
     * TIPO_INCIDENTE: Desprendimiento de rocas
     */
    INCID_DSPRNDMIENTO_RCAS,

    /**
     * TIPO_INCIDENTE: Desastres de origen antropogénico (aéreo, marítimo)
     */
    INCID_DSSTRES_ORGEN_ANTRPGNCO,

    /**
     * TIPO_INCIDENTE: Desastres de origen natural (sismos, fluviales, pluviales,
     * terrestre)
     */
    INCID_DSSTRES_ORGEN_NTRAL,

    /**
     * TIPO_INCIDENTE: Epidemias /intoxicaciones masivas
     */
    INCID_EPDMIAS_INTXCCIONES_MSVAS,

    /**
     * TIPO_INCIDENTE: Explosiones
     */
    INCID_EXPLSIONES,

    /**
     * TIPO_INCIDENTE: Incendios
     */
    INCID_INCNDIOS,

    /**
     * TIPO_INCIDENTE: Incursiones terroristas/atentados/sabotajes
     */
    INCID_INCRSIONES_TRRRSTAS,

    /**
     * TIPO_INCIDENTE: Otros
     */
    INCID_OTROS,

    /**
     * TIPO_INCIDENTE: Situaciones de conmoción civil / motines
     */
    INCID_STUACIONES_CNMCION_CVIL_MTNES,

    /**
     * TIPO_INCIDENTE: Tormentas eléctricas inusuales
     */
    INCID_TRMNTAS_ELCTRCAS_INSUALES,

    /**
     * TIPO_INCIDENTE: Volcadura de botellas presurizadas
     */
    INCID_VLCDRA_BTLLAS_PRSRZDAS,

    /**
     * TIPO_INCIDENTE: Volcadura con explosivos sin previo aviso
     */
    INCID_VLCDRA_CON_EXPLSVOS_SIN_PRVIO_AVSO,

    /**
     * TIPO_ORIGEN_DOCUMENTO: Externo
     */
    ORDOC_EXTRNO,

    /**
     * TIPO_ORIGEN_DOCUMENTO: Interno
     */
    ORDOC_INTRNO,

    /**
     * TIPO_FIRMA: Digital
     */
    FIRMA_DGTAL,

    /**
     * TIPO_SUPERVISION: Fiscalización no programada
     */
    SUPER_FSCLZCION_NO_PRGRMDA,

    /**
     * TIPO_SUPERVISION: Fiscalización programada
     */
    SUPER_FSCLZCION_PRGRMDA,

    /**
     * TIPO_RELACION_PASO: Cancelar el flujo
     */
    REPAS_CNCLAR_FLJO,

    /**
     * TIPO_RELACION_PASO: Continuar el flujo
     */
    REPAS_CONTINUAR_FLJO,

    /**
     * TIPO_RELACION_PASO: Excepción en el flujo
     */
    REPAS_EXCPCION_EN_FLJO,

    /**
     * TIPO_RELACION_PASO: Finalizar el flujo
     */
    REPAS_FNLZAR_FLJO,

    /**
     * TIPO_RELACION_PASO: Regresar en el flujo
     */
    REPAS_REGRSAR_FLJO,

    /**
     * TIPO_FECHA_CAMBIADA: Cambio en fechas programadas
     */
    FECAM_CMBIO_FCHAS_PRGRMDAS,

    /**
     * TIPO_FECHA_CAMBIADA: Cambio en fechas reales
     */
    FECAM_CMBIO_FCHAS_REALES,

    /**
     * TIPO_AUTORIZACION: Ampliación de planta
     */
    AUTOU_AMPLIACION_PLNTA,

    /**
     * TIPO_AUTORIZACION: Autorización de inicio de operación
     */
    AUTOU_AUTRZCION_INCIO_OPRCION,

    /**
     * TIPO_AUTORIZACION: Otros
     */
    AUTOU_OTROS,

    /**
     * TIPO_ALERTA: Asignación de paso
     */
    ALERT_ASGNCION_PSO,

    /**
     * TIPO_ALERTA: Contrato por vencer (plazo)
     */
    ALERT_CNTRTO_POR_VNCER_PLZO,

    /**
     * TIPO_ALERTA: Contrato por vencer (saldo)
     */
    ALERT_CNTRTO_POR_VNCER_SLDO,

    /**
     * TIPO_ALERTA: Fiscalización de campo fallida
     */
    ALERT_FSCLIZCION_CMPO_FLLDA,

    /**
     * TIPO_ALERTA: Fiscalización de campo finalizada
     */
    ALERT_FSCLZCION_CMPO_FNLZADA,

    /**
     * TIPO_ALERTA: Fiscalización de campo iniciada
     */
    ALERT_FSCLZCION_CMPO_INCIADA,

    /**
     * TIPO_ALERTA: Fiscalización cancelada
     */
    ALERT_FSCLZCION_CNCLDA,

    /**
     * TIPO_ALERTA: Fiscalización finalizada con IAIP
     */
    ALERT_FSCLZCION_FNLZDA_CON_IAIP,

    /**
     * TIPO_ALERTA: Instrucción preliminar por vencer
     */
    ALERT_INSTRCCION_PRLMNAR_POR_VNCER,

    /**
     * TIPO_ALERTA: Instrucción preliminar por vencer (con ampliación)
     */
    ALERT_INSTRCCION_PRLMNAR_POR_VNCER_AMPLIACION,

    /**
     * TIPO_ALERTA: Presentación de informe de fiscalizacion por vencer
     */
    ALERT_PRSNTCION_INFRME_FSCLZCION_POR_VNCER,
    
    /**
     * TIPO_ALERTA: Alerta de cumplimiento de plazo de caducidad
     */
    ALERT_CMPLMIENTO_PLZO_CDCDAD,
    
    /**
     * TIPO_ALERTA: Alerta de cumplimiento de plazo para iniciar PAS o archivar
     */
    ALERT_CMPLMIENTO_PLZO_INCIAR_PAS_ARCHVAR,
    
    /**
     * TIPO_ALERTA: Alerta de aprobación de informe de fiscalización
     */
    ALERT_APRBCION_INFRME_FSCLZCION,

    /**
     * TIPO_ALERTA: Alerta de cumplimiento de plazo para revisar el informe
     */
    ALERT_CMPLMIENTO_PLZO_RVSAR_INFRME,

    /**
     * TIPO_ALERTA: Alerta de cumplimiento de plazo para revisar el informe
     */
    ALERT_CMPLMIENTO_PLZO_PRESENTAR_INFRME,

    /**
     * TIPO_DETALLE_ALERTA: Para conocimiento
     */
    DEALE_PRA_CNCIMIENTO,

    /**
     * TIPO_DETALLE_ALERTA: Para tomar acción
     */
    DEALE_PRA_TMAR_ACCION,

    /**
     * TIPO_DETALLE_ALERTA: Paso asignado
     */
    DEALE_PSO_ASGNDO,

    /**
     * TIPO_CUMPLIMIENTO: Sí
     */
    CUMPL_SI,

    /**
     * TIPO_CUMPLIMIENTO: No
     */
    CUMPL_NO,

    /**
     * TIPO_CUMPLIMIENTO: No aplica
     */
    CUMPL_NO_APLCA,

    /**
     * TIPO_CUMPLIMIENTO: Archivado
     */
    ARCHIVADO,

    /**
     * TIPO_CUMPLIMIENTO: Archivado por subsanación
     */
    ARCHVD_SUBSANACION,

    /**
     * TIPO_CUMPLIMIENTO: Archivado por falta de indicios
     */
    ARCHVD_FALTA_INDICIOS,

    /**
     * TIPO_CUMPLIMIENTO: Archivado por falta de tipificación
     */
    ARCHVD_FALTA_TIPIFICACION,

    /**
     * TIPO_CUMPLIMIENTO: Archivado - Non bis in ídem
     */
    ARCHVD_NON_BIS_IDEM,

    /**
     * TIPO_CUMPLIMIENTO: Archivado - Otros
     */
    ARCHVD_OTROS,

    /**
     * TIPO_INVOLUCRADO: Representante AF
     */
    INVOL_RPRSNTNTE_AF,

    /**
     * TIPO_INVOLUCRADO: Representante de trabajadores
     */
    INVOL_RPRSNTNTE_TRBJDRES,

    /**
     * TIPO_PREFIJO_INVOLUCRADO: Dr.
     */
    PERIN_DR,

    /**
     * TIPO_PREFIJO_INVOLUCRADO: Dra.
     */
    PERIN_DRA,

    /**
     * TIPO_PREFIJO_INVOLUCRADO: Ing.
     */
    PERIN_ING,

    /**
     * TIPO_PREFIJO_INVOLUCRADO: Sr.
     */
    PERIN_SR,

    /**
     * TIPO_PREFIJO_INVOLUCRADO: Sra.
     */
    PERIN_SRA,

    /**
     * TIPO_ACTA_INVOLUCRADO: Acta de inicio
     */
    ACINV_ACTA_INCIO,

    /**
     * TIPO_ACTA_INVOLUCRADO: Acta de supervisión
     */
    ACINV_ACTA_SPRVSION,

    /**
     * TIPO_ACTA_INVOLUCRADO: Acta de requerimiento de documentación
     */
    ACINV_ACTA_RQUERMIENTO_DCMNTCION,

    /**
     * TIPO_ACTA_INVOLUCRADO: Acta de recepción de documentación
     */
    ACINV_ACTA_RCPCION_DCMNTCION,

    /**
     * TIPO_NORMA: DS
     */
    NORMA_DS,

    /**
     * TIPO_NORMA: LEY
     */
    NORMA_LEY,

    /**
     * TIPO_NORMA: RCD
     */
    NORMA_RCD,

    /**
     * TIPO_NORMA: RD
     */
    NORMA_RD,

    /**
     * TIPO_NORMA: REGLAMENTO
     */
    NORMA_RGLMNTO,

    /**
     * TIPO_NORMA: RM
     */
    NORMA_RM,

    /**
     * TIPO_NORMA: TUO
     */
    NORMA_TUO,

    /**
     * TIPO_NORMA: RCDCT
     */
    NORMA_RCDCT,

    /**
     * TIPO_DIVISION_ITEM: Artículo
     */
    DIITE_ARTCLO,

    /**
     * TIPO_DIVISION_ITEM: Inciso
     */
    DIITE_INCSO,

    /**
     * TIPO_DIVISION_ITEM: Literal
     */
    DIITE_LTRAL,

    /**
     * TIPO_DIVISION_ITEM: Numeral
     */
    DIITE_NMRAL,

    /**
     * TIPO_DIVISION_ITEM: Párrafo
     */
    DIITE_PRRFO,

    /**
     * FUENTE_PERSONA_NATURAL: Carga inicial
     */
    FUPER_CRGA_INCIAL,

    /**
     * FUENTE_PERSONA_NATURAL: Reniec
     */
    FUPER_RENIEC,

    /**
     * FUENTE_PERSONA_NATURAL: Registro manual
     */
    FUPER_RGSTRO_MNUAL,

    /**
     * TIPO_FICHA_REVISION: Ficha de conformidad
     */
    FIREV_FCHA_CNFRMDAD,

    /**
     * TIPO_FICHA_REVISION: Ficha de observaciones
     */
    FIREV_FCHA_OBSRVCIONES,

    /**
     * TIPO_OBSERVACION_FICHA: Nueva
     */
    OBFIC_NUEVA,

    /**
     * TIPO_OBSERVACION_FICHA: Revisión
     */
    OBFIC_RVSION,

    /**
     * TIPO_RELACION_DOCUMENTO: Aprobación
     */
    REDOC_APRBCION,

    /**
     * TIPO_RELACION_DOCUMENTO: Justificación
     */
    REDOC_JSTFCCION,

    /**
     * TIPO_RELACION_DOCUMENTO: Observación
     */
    REDOC_OBSRVCION,

    /**
     * PLAZOS_REVISION_INFORME: Entrega
     */
    REINF_ENTRGA,

    /**
     * PLAZOS_REVISION_INFORME: Absolución
     */
    REINF_ABSLCION,

    /**
     * LINEA_PROGRAMA_ESTADO: Creada
     */
    PREST_CREADA,

    /**
     * LINEA_PROGRAMA_ESTADO: Aprobada
     */
    PREST_APRBDA,

    /**
     * LINEA_PROGRAMA_ESTADO: Cerrada
     */
    PREST_CRRDA,

    /**
     * LINEA_PROGRAMA_ESTADO: Sustituida
     */
    PREST_SSTTUIDA,

    /**
     * LINEA_PROGRAMA_ESTADO: Anulada
     */
    PREST_ANLDA,

    /**
     * TIPO_ENTREGABLE: Actas de fiscalización
     */
    ENTRE_ACTAS_FSCLZCION,

    /**
     * TIPO_ENTREGABLE: Informes de fiscalización
     */
    ENTRE_INFRMES_FSCLZCION,

    /**
     * TIPO_ENTREGABLE: Informes de supervisión fallida
     */
    ENTRE_INFRMES_SPRVSION_FLLDA,

    /**
     * TIPO_ENTREGABLE: Informes de gestión
     */
    ENTRE_INFRMES_GSTION,

    /**
     * TIPO_ESTADIO_CONSUMO: Pre-comprometido
     */
    ESCON_PRE_CMPRMTDO,

    /**
     * TIPO_ESTADIO_CONSUMO: Comprometido
     */
    ESCON_CMPRMTDO,

    /**
     * TIPO_ESTADIO_CONSUMO: Por liquidar
     */
    ESCON_POR_LQUIDAR,

    /**
     * TIPO_ESTADIO_CONSUMO: Liquidado
     */
    ESCON_LQUIDDO,

    /**
     * TIPO_ESTADIO_CONSUMO: Facturado
     */
    ESCON_FCTRDO,

    /**
     * TIPO_ESTADIO_CONSUMO: Penalizado
     */
    ESCON_PNLZDO,

    /**
     * NO_USUARIO_SIGED_CONTABILIDAD: Nombre del usuario principal de contabilidad
     */
    USCON_NMBRE_USUARIO_PRNCPAL_CNTBLDAD,

    /**
     * TIPO_ACCION_SIGED: Reenvío simple
     */
    ACSIG_REENVIO_SMPLE,

    /**
     * TIPO_ACCION_SIGED: Aprobar
     */
    ACSIG_APRBAR,

    /**
     * TIPO_ACCION_SIGED: Devolver
     */
    ACSIG_DEVLVER,

    /**
     * TIPO_ACCION_SIGED: Excepción
     */
    ACSIG_EXCPCION,

    /**
     * TIPO_ORIGEN_DATO_RIESGO: Gabinete
     */
    ORDAR_GBNTE,

    /**
     * TIPO_ORIGEN_DATO_RIESGO: Fiscalización
     */
    ORDAR_FSCLZCION,

    /**
     * TIPO_ESTADO_CONFIGURACION: Creada
     */
    ESCFG_CREADA,

    /**
     * TIPO_ESTADO_CONFIGURACION: Observada
     */
    ESCFG_OBSRVDA,

    /**
     * TIPO_ESTADO_CONFIGURACION: Aprobada
     */
    ESCFG_APRBDA,

    /**
     * TIPO_ESTADO_CONFIGURACION: Archivada
     */
    ESCFG_ARCHVDA,

    /**
     * TIPO_ESTADO_CONFIGURACION: Cancelada
     */
    ESCFG_CNCLDA,

    /**
     * TIPO_ESTADO_CONFIGURACION: Para aprobar
     */
    ESCFG_PRA_APRBAR,

    /**
     * TIPO_ESTADO_CONFIGURACION: Para cancelar
     */
    ESCFG_PRA_CNCLAR,

    /**
     * TIPO_EXTENSION_DOC: DOCX
     */
    EXDOC_DOCX,

    /**
     * TIPO_EXTENSION_DOC: PDF
     */
    EXDOC_PDF,

    /**
     * TIPO_OPERADOR_LOGICO: >=
     */
    OPLOG_MYOR_IGUAL,

    /**
     * TIPO_OPERADOR_LOGICO: <=
     */
    OPLOG_MNOR_IGUAL,

    /**
     * TIPO_OPERADOR_LOGICO: >
     */
    OPLOG_MYOR_QUE,

    /**
     * TIPO_OPERADOR_LOGICO: <
     */
    OPLOG_MNOR_QUE,

    /**
     * TIPO_OPERADOR_LOGICO: <>
     */
    OPLOG_DFRNTE,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 1 factor
     */
    COALE_IC_PRA_1_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 2 factores
     */
    COALE_IC_PRA_2_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 3 factores
     */
    COALE_IC_PRA_3_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 4 factores
     */
    COALE_IC_PRA_4_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 5 factores
     */
    COALE_IC_PRA_5_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 6 factores
     */
    COALE_IC_PRA_6_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 7 factores
     */
    COALE_IC_PRA_7_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 8 factores
     */
    COALE_IC_PRA_8_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 9 factores
     */
    COALE_IC_PRA_9_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 10 factores
     */
    COALE_IC_PRA_10_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 11 factores
     */
    COALE_IC_PRA_11_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 12 factores
     */
    COALE_IC_PRA_12_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 13 factores
     */
    COALE_IC_PRA_13_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 14 factores
     */
    COALE_IC_PRA_14_FCTOR,

    /**
     * IND_CONSISTENCIA_ALEATORIA: IC para 15 factores
     */
    COALE_IC_PRA_15_FCTOR,

    /**
     * IND_RATIO_CONSISTENCIA_ACEPTABLE: 3
     */
    RACOA_TRES,

    /**
     * IND_RATIO_CONSISTENCIA_ACEPTABLE: 4
     */
    RACOA_CUATRO,

    /**
     * IND_RATIO_CONSISTENCIA_ACEPTABLE: 5 <= n <= 15
     */
    RACOA_N_ENTRE_5_Y_15,

    /**
     * TIPO_MEDIDA_ADMINISTRATIVA: Medida cautelar
     */
    MEADM_MDDA_CAUTLAR,

    /**
     * TIPO_MEDIDA_ADMINISTRATIVA: Medida de seguridad
     */
    MEADM_MDDA_SGRDAD,

    /**
     * TIPO_OBJ_RELACIONADO_MED_ADM: Unidad minera
     */
    OBRMA_UNDAD_MNERA,

    /**
     * TIPO_OBJ_RELACIONADO_MED_ADM: Supervisión
     */
    OBRMA_SPRVSION,

    /**
     * TIPO_OBJ_RELACIONADO_MED_ADM: Fiscalización
     */
    OBRMA_FSCLZCION,

    /**
     * MEDIO_DENUNCIA: Correo
     */
    MEDEN_CRREO,

    /**
     * MEDIO_DENUNCIA: Llamada telefónica
     */
    MEDEN_LLMDA_TLFNCA,

    /**
     * MEDIO_DENUNCIA: Mesa de partes
     */
    MEDEN_MSA_DE_PRTES,

    /**
     * MEDIO_DENUNCIA: Redes sociales
     */
    MEDEN_RDES_SCIALES,

    /**
     * TIPO_REPORTE: CONSOLIDADO
     */
    TIREP_CNSLDDO,

    /**
     * TIPO_REPORTE: DETALLADO
     */
    TIREP_DTLLDO,

    /**
     * MATERIA_REPORTE: expPAS
     */
    MAREP_EXPDIENTE_PAS,

    /**
     * MATERIA_REPORTE: expSup
     */
    MAREP_EXPDIENTE_FSCLZCION,

    /**
     * MATERIA_REPORTE: expInfrac
     */
    MAREP_EXPDIENTE_INFRACCION,

    /**
     * MATERIA_REPORTE: exp
     */
    MAREP_EXPDIENTE,

    /**
     * MATERIA_REPORTE: reportSup
     */
    MAREP_RPRTE_FISCALIZACION,

    /**
     * MATERIA_REPORTE: reportProgr
     */
    MAREP_RPRTE_PRGRMCION_1,

    /**
     * MATERIA_REPORTE: reportPas
     */
    MAREP_RPRTE_PAS,

    /**
     * MATERIA_REPORTE: reportMaestUM
     */
    MAREP_RPRTE_MAESTRO_UNDDES_MNRAS,

    /**
     * MATERIA_REPORTE: reportMaestAS
     */
    MAREP_RPRTE_MAESTRO_AS,

    /**
     * MATERIA_REPORTE: reportMaestEve
     */
    MAREP_RPRTE_MAESTRO_EVNTOS,

    /**
     * MATERIA_REPORTE: reportContr
     */
    MAREP_RPRTE_MAESTRO_CNTRTOS,

    /**
     * MATERIA_REPORTE: reportProgr
     */
    MAREP_RPRTE_PRGRMCION_2,

    /**
     * GRUPO_REPORTE: Fiscalización
     */
    GRREP_FSCLZCION,

    /**
     * GRUPO_REPORTE: Monitoreo de expedientes
     */
    GRREP_MNTREO_EXPDIENTES,

    /**
     * GRUPO_REPORTE: Infracciones
     */
    GRREP_INFRCCIONES,

    /**
     * GRUPO_REPORTE: Programación
     */
    GRREP_PRGRMCION,

    /**
     * GRUPO_REPORTE: PAS
     */
    GRREP_PAS,

    /**
     * GRUPO_REPORTE: Maestro
     */
    GRREP_MAESTRO,

    /**
     * GRUPO_REPORTE: Contrato
     */
    GRREP_CNTRTO,

    /**
     * TIPO_ANTECEDENTE: Informe de fiscalización
     */
    TIANT_INFRME_FSCLZCION,

    /**
     * TIPO_ANTECEDENTE: Autorización
     */
    TIANT_AUTRZCION,

    /**
     * TIPO_ANTECEDENTE: Accidente mortal
     */
    TIANT_ACCIDNTE_MRTAL,

    /**
     * TIPO_ANTECEDENTE: Denuncia
     */
    TIANT_DENNCIA,

    /**
     * TIPO_ANTECEDENTE: Incidente peligroso
     */
    TIANT_INCDNTE_PLGRSO,

    /**
     * TIPO_ANTECEDENTE: Otro
     */
    TIANT_OTRO,

    /**
     * ESTADO_UM: En operación
     */
    ESUMI_EN_OPRCION,

    /**
     * ESTADO_UM: Paralizada
     */
    ESUMI_PRLZDA,

    /**
     * ESTADO_UM: Suspendida
     */
    ESUMI_SSPNDDA,

    /**
     * ESTADO_UM: Cierre progresivo
     */
    ESUMI_CIERRE_PRGRSVO,

    /**
     * ESTADO_UM: Cierre final
     */
    ESUMI_CIERRE_FNAL,

    /**
     * ESTADO_UM: Cierre post-cierre (definitivo)
     */
    ESUMI_CIERRE_POST_CIERRE_DFNTVO,

    /**
     * ESTADO_UM: Sin actividad
     */
    ESUMI_SIN_ACTVDAD,

    /**
     * ESTADO_UM: No aplica
     */
    ESUMI_NO_APLCA,
    
    /**
     * TIPO_CONFIGURACION_BASE: TDR base
     */
    TDR_BASE,
    
    /**
     * TIPO_CONFIGURACION_BASE: Documentos base para solicitud
     */
    DOCUMENTOS_BASE_SOLICITUD,
    
    /**
     * TIPO_CONFIGURACION_BASE: Cuadros base de verificación
     */
    CUADROS_BASE_VERIFICACION,

    /**
     * TIPO_CFG_RIESGO_ENSAYOS: Ensayos
     */
    TIPO_CFG_RIESGO_ENSAYOS,

    /**
     * TIPO_CFG_RIESGO_FISCALIZACION: Fiscalización
     */
    TIPO_CFG_RIESGO_FISCALIZACION,

    /**
     * TIPO_CFG_RIESGO_HISTORICO: Histórico
     */
    TIPO_CFG_RIESGO_HISTORICO,

    /**
     * TIPO_AUTOMATICO: Automática
     */
    TIPO_AUTOMATICO,

    /**
     * TIPO_MANUAL: Manual
     */
    TIPO_MANUAL,

    
    /**
     * TIPO_ESTDO_INSTRMNTO_NO_DISPONIBLE: ESTADO INSTRUMENTO NO_DISPONIBLE
     */
    TIPO_ESTDO_INSTRMNTO_NO_DISPONIBLE,
    
    /**
     * TIPO_ESTDO_INSTRMNTO_REEMPLAZADO: ESTADO INSTRUMENTO REEMPLAZADO
     */
    TIPO_ESTDO_INSTRMNTO_REEMPLAZADO,
    
    /**
     * TIPO_ESTDO_INSTRMNTO_APROBADO: ESTADO INSTRUMENTO APROBADO
     */
    TIPO_ESTDO_INSTRMNTO_APROBADO,
    
    /**
     * TIPO_ESTDO_INSTRMNTO_REGISTRADO: ESTADO INSTRUMENTO REGISTRADO 
     */
    TIPO_ESTDO_INSTRMNTO_REGISTRADO,         

    /**
     * TIPO_ESTDO_INSTRMNTO_MODIFICADO: ESTADO INSTRUMENTO MODIFICADO 
     */
    TIPO_ESTDO_INSTRMNTO_MODIFICADO,

    /**
     * TIPO_ESTDO_INSTRMNTO_RMPLZDO_APROB: ESTADO INSTRUMENTO REEMPLAZADO APROBADO
     */
    TIPO_ESTDO_INSTRMNTO_RMPLZDO_APROB,

    /**
     * TIPO_ESTDO_INSTRMNTO_N_DSPNBLE_APROB: ESTADO INSTRUMENTO NO DISPONIBLE APROBADO 
     */
    TIPO_ESTDO_INSTRMNTO_N_DSPNBLE_APROB,

    /**
     * TIPO_PERIODO_ALERTA: TIPO PERIODO ALERTA MENSUAL
     */
    TIPO_PERIODO_ALERTA_MENSUAL,

    /**
     * TIPO_PERIODO_ALERTA: TIPO PERIODO ALERTA DIARIO
     */
    TIPO_PERIODO_ALERTA_DIARIA,

    /**
     * TIND_TIEMPOS_PROCESAMIENTO: Tiempos de procemiento
     */
    TIND_TIEMPOS_PROCESAMIENTO,
    
    /**
     * TAI_ACTOR_NEGOCIO: Actor de negocio
     */
    TAI_ACTOR_NEGOCIO,

    /**
     * TAI_CARACTER_FISCALIZACION: Característica de la fiscalización
     */
    TAI_CARACTER_FISCALIZACION,
    
}