package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.models.entity.PgimDocumentoNotifica;

/** 
 * @descripción: Lógica de negocio de la entidad notificaciones por documento
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/11/2020
 */

@Repository
public interface DocumentoNotificaRepository extends JpaRepository<PgimDocumentoNotifica, Long>{

}