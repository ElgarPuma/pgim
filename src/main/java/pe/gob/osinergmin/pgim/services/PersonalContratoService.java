package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalContrato;

public interface PersonalContratoService {

        /**
         * Permite listar los personales de contrato
         * 
         * @param idContrato
         * @return
         */
        Page<PgimPersonalContratoDTO> listarPersonalContrato(Long idContrato, Pageable paginador);

        /**
         * Permite obtener las propiedades necesarias del personal de contrato
         * 
         * @param idPersonalContrato
         * @return
         */
        PgimPersonalContratoDTO obtenerPersonalContrato(Long idPersonalContrato);

        /**
         * Permirte crear un personal de contrato
         * 
         * @param pgimAccidentadoDTO
         * @return
         */
        PgimPersonalContratoDTO crearPersonalContrato(@Valid PgimPersonalContratoDTO pgimPersonalContratoDTO,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permirte agregar un nuevo personal de contrato
         * 
         * @param pgimAccidentadoDTO
         * @return
         */
        PgimPersonalContratoDTO agregarPersonalContrato(@Valid PgimPersonalContratoDTO pgimPersonalContratoDTO,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Permite obtener un personal de contrato de acuerdo con su identificador
         * interno.
         * 
         * @param idPersonalContrato
         * @return
         */
        PgimPersonalContrato getByIdPersonalContrato(Long idPersonalContrato);

        /**
         * Permite modificar los datos de un personal de contrato
         * 
         * @param pgimPersonalContratoDTO
         * @param pgimPersonalContratoActual
         * @return
         */
        PgimPersonalContratoDTO modificarPersonalContrato(@Valid PgimPersonalContratoDTO pgimPersonalContratoDTO,
                        PgimPersonalContrato pgimPersonalContratoActual, AuditoriaDTO auditoriaDTO);

        /**
         * Permiote eliminar un personal de contrato
         * 
         * @param pgimPersonalContratoActual
         */
        void eliminarPersonalContrato(PgimPersonalContrato pgimPersonalContratoActual, AuditoriaDTO auditoriaDTO);

        /**
         * Permnite verificar si el personal de contrato existe o no.
         * 
         * @param idContrato
         * @param palabraClave
         * @return
         */
        List<PgimPersonalContratoDTO> listarPorPersona(String palabraClave);

        /**
         * Permite modificar los datos de una persona accidentada.
         * 
         * @param pgimPersonalContratoDTO
         * @param auditoriaDTO
         * @return
         */
        PgimPersonalContratoDTO modificarPersonalContratoAsig(@Valid PgimPersonalContratoDTO pgimPersonalContratoDTO,
                        AuditoriaDTO auditoriaDTO);

        /**
         * Este método me permite validar las personas de los Contratos que estan
         * Vigente o no Vigente
         * 
         * @param idContrato
         * @param idPersona
         * @param idPersonalContrato
         * @return
         */
        List<PgimPersonalContratoDTO> existePersonalContrato(Long idContrato, Long idPersona, Long idPersonalContrato);

        /**
         * Permite obtener la lista de personal del contrato por el nombre de usuario.
         * @param userName
         * @param idContrato
         * @return
         */
        List<PgimPersonalContratoDTO> obtenerPersonalContratoPorNombre(String userName, Long idContrato);

        /**
         * Me permite validar si el usuario existe o no en el personal del contrato
         * @param idPersonalContrato
         * @param noUsuario
         * @return
         */
        List<PgimPersonalContratoDTO> existeNoUsuario(Long idPersonalContrato, String noUsuario);
}
