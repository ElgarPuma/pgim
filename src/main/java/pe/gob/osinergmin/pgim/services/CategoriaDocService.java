package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCategoriaDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;

public interface CategoriaDocService {

     /**
      * Permite obtener la lista de categorias.
      * 
      * @return
      */
     List<PgimCategoriaDocDTO> listarCategoriaDoc();

     /**
      * Permite obtener la lista de subcategorias.
      * 
      * @return
      */
     List<PgimSubcategoriaDocDTO> listarSubcategorias();

     /**
      * Permite obtener la lista de subcategorias por Fase.
      * Si flSoloPaso es true, devolverá las subcategorías que corresponden solo al Paso de idInstanciaPaso. 
      * 
      * @param idProceso
      * @param idFase
      * @param coTablaInstancia
      * @param idInstanciaPaso
      * @param flSoloPaso
      * @return
      * @throws Exception
      */
     List<PgimSubcategoriaDocDTO> listarSubcategoriasFase(Long idProceso, Long idFase, Long coTablaInstancia, Long idInstanciaPaso, boolean flSoloPaso) throws Exception;

     List<PgimSubcategoriaDocDTO> listarSubCategoriasIncluirByProceso(Long idProceso, Long idFase);

     PgimSubcategoriaDoc obtenerSubcategoria(Long id);

     PgimCategoriaDoc obtenerCategoria(Long id);

     List<PgimCategoriaDocDTO> listarCategoriaDocByIdProceso(Long idProceso);

     List<PgimCategoriaDocDTO> listarCategoriaDocByIdProcesoIdFase(Long idProceso, Long idFase);

     // List<PgimCategoriaDocDTO> listarCategoriaDocByIdProcesoIdFase(Long idProceso, Long idFase, Long coTablaInstancia) throws Exception;
     
     /**
      * Permite listar categoria y subcategoria por proceso y fase
      * @param idProceso
      * @param idFase
      * @return
      */
     List<PgimCategoriaDocDTO> listarCategoriaYSubCategoriaByIdProcesoIdFase(Long idProceso, Long idFase);
     
     /**
      * Permite listar categoria y subcategoria por proceso 
      * @param idProceso
      * @return
      */
     List<PgimCategoriaDocDTO> listarCategoriaYSubCategoriaByIdProceso(Long idProceso);
}
