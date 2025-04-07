package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimContactoSuperDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContactoSuper;

/** 
 * @descripción: Lógica de negocio de la entidad Contacto de empresa supervisora
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 06/02/2022
 */
@Repository
public interface ContactoSuperRepository extends JpaRepository<PgimContactoSuper, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContactoSuperDTOResultado(" 
            + "cosu.idContactoSupervisora, cosu.pgimEmpresaSupervisora.idEmpresaSupervisora, cosu.pgimPersona.idPersona, "
            + "cosu.noCargo, cosu.pgimPersona.deTelefono, cosu.pgimPersona.deCorreo, " 
            + "cosu.pgimPersona.noPersona, cosu.pgimPersona.apPaterno, cosu.pgimPersona.apMaterno, "
            + "cosu.pgimPersona.noRazonSocial "
            + ") "
            + "FROM PgimContactoSuper cosu " 
            + "WHERE cosu.esRegistro = '1' "
            + "AND cosu.pgimEmpresaSupervisora.idEmpresaSupervisora = :idEmpresaSupervisora "
            )
    Page<PgimContactoSuperDTO> listarContactosEmpresaSuperv(
            @Param("idEmpresaSupervisora") Long idEmpresaSupervisora, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContactoSuperDTOResultado(" 
            + "cosu.idContactoSupervisora, cosu.pgimEmpresaSupervisora.idEmpresaSupervisora, cosu.pgimPersona.idPersona, "
            + "cosu.noCargo, cosu.pgimPersona.deTelefono, cosu.pgimPersona.deCorreo, " 
            + "cosu.pgimPersona.noPersona, cosu.pgimPersona.apPaterno, cosu.pgimPersona.apMaterno, "
            + "cosu.pgimPersona.noRazonSocial "
            + ") "
            + "FROM PgimContactoSuper cosu " 
            + "WHERE cosu.esRegistro = '1' "
            + "AND cosu.idContactoSupervisora = :idContactoSupervisora ")
    PgimContactoSuperDTO obtenerContactoSuperPorId(@Param("idContactoSupervisora") Long idContactoSupervisora);
}
