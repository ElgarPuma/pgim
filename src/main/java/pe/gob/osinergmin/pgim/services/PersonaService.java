package pe.gob.osinergmin.pgim.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaConsorcioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiCargoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonaConsorcio;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsi;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsiCargo;
import pe.gob.osinergmin.pgim.siged.Usuario;
import pe.gob.osinergmin.pgim.siged.UsuarioSiged;

/**
 * Interfaz para la gestión de los servicios relacionados con las personas.
 * 
 * @descripción: Unidad minera
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/06/2020
 */
@Service
@Transactional(readOnly = true)
public interface PersonaService {

    /**
     * Permite obtener la persona por tipo y número de documento de identidad.
     * 
     * @param idTipoDocumentoIdentidad
     * @param coDocumentoIdentidad
     * @return
     */
    PgimPersona obtenerPorTipoYNumero(Long idTipoDocumentoIdentidad, String coDocumentoIdentidad);

    /**
     * Permite obtener la persona por tipo y número de documento de identidad.
     * 
     * @param idTipoDocumentoIdentidad
     * @param coDocumentoIdentidad
     * @return
     */
    PgimPersona obtenerPorTipoYRuc(String noRazonSocial);

    @Transactional(readOnly = false)
    PgimPersona salvar(PgimPersona pgimPersona);

    /**
     * Permite obtener el ID de la persona
     * 
     * @param idPersona
     * @return
     */
    PgimPersona getByIdPersona(Long idPersona);

    /**
     * Permite filtrar la persona juridica o natural por su nombre o palabra clave
     * 
     * @param palabraClave
     * @return
     */
    List<PgimPersonaDTO> listarPersonaJuridicaPorPalabraClave(String palabraClave);

    /**
     * Permite listar por persona clave para el presonal de contrato
     * 
     * @param palabraClave
     * @return
     */
    List<PgimPersonaDTO> listarPorPersona(String palabraClave);

    /**
     * Permite obtener el codigo de usuario Siged
     * 
     * @param usuario
     * @return
     * @throws Exception
     */
    public UsuarioSiged obtenerUsuarioSiged(Usuario usuario) throws Exception;

    /**
     * Permite listar las Persona natirales y juridicas con las propiedades de
     * filtros según corresponda.
     * 
     * @param filtroPersona
     * @param paginador
     * @return
     */
    Page<PgimPersonaDTO> listarPersonas(PgimPersonaDTO filtroPersona, Pageable paginador);

    /***
     * Permite listar por número de documento de identidad
     * 
     * @param palabra Palabra clave utilizada para buscar en la lista de personas
     * @return
     */
    List<PgimPersonaDTO> listarPorCoDocumentoIdentidad(String palabra);

    /***
     * Permite listar por nombre de Razon social
     * 
     * @param palabra Palabra clave utilizada para buscar en la lista de personas
     * @return
     */
    List<PgimPersonaDTO> listarPorNoRazonSocial(String palabra);

    /***
     * Permite obtener la lista de personas jurídicas cuyo RUC o razón social coincida por aproximación con la palabra.
     * 
     * @param palabra Palabra clave utilizada para buscar en la lista de personas jurídicas
     * @return
     */
    List<PgimPersonaDTO> listarPorRazonSocial(String palabra);

    /**
     * Permirte crear una persona juridica o natural
     *
     * @param pgimPersonaDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    PgimPersonaDTO crearPersona(PgimPersonaDTO pgimPersonaDTO, AuditoriaDTO auditoriaDTO) throws Exception;

    /***
     * Permite modificar una persona juridica o natural
     * 
     * @param pgimPersonaDTO
     * @param pgimPersona
     * @param auditoriaDTO
     * @return
     */
    PgimPersonaDTO modificarPersona(PgimPersonaDTO pgimPersonaDTO, PgimPersona pgimPersona, AuditoriaDTO auditoriaDTO)
            throws Exception;

    /**
     * Permite obtener las propiedades necesaria poer el ID de una persona juridica
     * o natural
     * 
     * @param idPersona
     * @return
     */
    PgimPersonaDTO obtenerPersonalNatuJuriPorId(Long idPersona);

    /**
     * Permite obtener las propiedades de una persona juridica o natural para la
     * tarjeta de informacion
     * 
     * @param idPersona
     * @return
     */
    PgimPersonaDTO obtenerPersona(Long idPersona);

    /***
     * Permite eliminar una persona juridica o natural
     * 
     * @param pgimPersonaActual
     * @param auditoriaDTO
     */
    void eliminarPersona(PgimPersona pgimPersonaActual, AuditoriaDTO auditoriaDTO);

    /**
     * Permnite verificar si la persona natural o juridica existe o no.
     * 
     * @param idPersona
     * @param idTipoDocumento
     * @param numeroDocumento
     * @return
     */
    List<PgimPersonaDTO> existePersona(Long idPersona, Long idTipoDocumento, String numeroDocumento);

    List<PgimPersonaDTO> listarPersonalNaturalPorNoRazonSocial(Long idAgenteSupervisado, String palabra);

    List<PgimPersonaDTO> listarPersonalNaturalPorNoRazonSocialSuper(Long idEmpresaSupervisora, String palabraClave);

    /**
     * Listar los consorcios asociados a personas juridicas
     * 
     * @param pgimPersonaConsorcioDTO
     * @param paginador
     * @return
     */
    Page<PgimPersonaConsorcioDTO> listarConsorcios(Long idPersonaJuridicaConsorcio, Pageable paginador);

    PgimPersonaConsorcioDTO obtenerConsorcioPorId(Long idPersonaConsorcio);

    List<PgimPersonaConsorcioDTO> listarPorPersonaJuridica(Long idPersona, String palabraClave);

    public PgimPersonaConsorcio getByIdPersonaConsorcio(Long idPersonaConsorcio);

    public PgimPersonaConsorcioDTO crearPersonaConsorcio(
            PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO, AuditoriaDTO auditoriaDTO) throws Exception;

    public void eliminarPersonaConsorcio(PgimPersonaConsorcio pgimPersonaConsorcioActual, AuditoriaDTO auditoriaDTO);

    /**
     * Permite validar la duplicación de otra persona juridica con el mismo nombre
     * 
     * @param idPersona
     * @param noRazonSocial
     * @return
     */
    List<PgimPersonaDTO> existePersonaJuridica(Long idPersona, String noRazonSocial);

    /**
     * Permite listar los todos los personales de Osinergmin
     * 
     * @param paginador
     * @return
     */
    Page<PgimPersonalOsiDTO> listarPersonalOsi(PgimPersonalOsiDTO pgimPersonalOsiDTO, Pageable paginador) throws Exception;

    /**
     * Permite obtener la lista de personal del Osinergmin en base a su
     * identificador.
     * 
     * @param idPersonalOsi
     * @return
     */
    PgimPersonalOsiDTO obtenerPersonalOsigPorId(Long idPersonalOsi);

    /**
     * Permite obtener las propiedades del objeto por el identificador.
     * 
     * @param idPersonalOsi
     * @return
     */
    PgimPersonalOsi getByIdPersonalOsig(Long idPersonalOsi);

    PgimPersonalOsiDTO crearPersonalOsi(PgimPersonalOsiDTO pgimPersonalOsiDTO,
            AuditoriaDTO auditoriaDTO) throws Exception;

    PgimPersonalOsiDTO modificarPersonalOsi(PgimPersonalOsiDTO pgimPersonalOsiDTO,
            PgimPersonalOsi pgimPersonalOsi, AuditoriaDTO auditoriaDTO);

    PgimPersonalOsiDTO desactivarPersonalOsi(PgimPersonalOsiDTO pgimPersonalOsiDTO,
            PgimPersonalOsi pgimPersonalOsi, AuditoriaDTO auditoriaDTO);

    List<PgimPersonaDTO> listarPersonaNaturalPorPersonalOsi(String palabra);

    List<PgimPersonalOsiDTO> existeUsuario(Long idPersonalOsi, String noUsuario);

    /**
     *  Permite modificar el flag es principal del consorcio
     * @param pgimPersonaConsorcioDTO
     * @param pgimPersonaConsorcioActual
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    public PgimPersonaConsorcioDTO modificarFlPrincipalConsorcio(PgimPersonaConsorcioDTO pgimPersonaConsorcioDTO, PgimPersonaConsorcio pgimPersonaConsorcioActual, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Me permite listar los cargos del personal de Osinergmin
     * 
     * @param idPersonalOsi
     * @return
     */
    List<PgimPersonalOsiCargoDTO> listarPersonalOsiCargo(Long idPersonalOsi);

    /**
     * Devuelve un objeto PgimPersonalOsiCargoDTO.
     * 
     * @param idPersonalOsiCargo El id del PersonalOsiCargo a obtener.
     */
    PgimPersonalOsiCargoDTO obtenerPersonalOsiCargoPorId(Long idPersonalOsiCargo);

    /**
     * Devuelve el cargo personal osi por id.
     * 
     * @param idPersonalOsiCargo El id del PersonalOsiCargo a recuperar.
     */
    PgimPersonalOsiCargo getPersonalOsiCargoById(Long idPersonalOsiCargo);

    /**
     * Permite crear un nuevo cargo del personal de Osinergmin
     * @param pgimPersonalOsiCargoDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    public PgimPersonalOsiCargoDTO crearPersonalOsiCargo(PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTO, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Permite modificar el cargo del personal de Osinergmin
     * @param pgimPersonalOsiCargoDTO
     * @param pgimPersonalOsiCargo
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    public PgimPersonalOsiCargoDTO modificarPersonalOsiCargo(PgimPersonalOsiCargoDTO pgimPersonalOsiCargoDTO, PgimPersonalOsiCargo pgimPersonalOsiCargo, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Permite eliminar un cargo del personal de Osinergmin
     * @param pgimPersonalOsiCargo
     * @param auditoriaDTO
     */
    public void eliminarPersonalOsiCargo(PgimPersonalOsiCargo pgimPersonalOsiCargo, AuditoriaDTO auditoriaDTO);
}