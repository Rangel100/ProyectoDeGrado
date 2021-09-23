package co.com.edu.usbcali.pdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface ArtefactoRepository extends JpaRepository<Artefacto, Long> {
	
	List<Artefacto> findByUsuario_usuaId(Long usuaId);
	
	@Query(nativeQuery = true)
	ArtefactoDTO consultarArtefacto(@Param("pArteId") Long arteId,
									@Param("pEstado") String estado);
	
	List<Artefacto> findByTipoArtefacto_tiarIdAndEstado(Long tiarId, String estado);
	
	@Query(nativeQuery = true)
	List<ArtefactoDTO> consultarArtefactosPorUsuario(@Param("pUsuaId") Long usuaId,
													@Param("pEstado") String estado);
}
