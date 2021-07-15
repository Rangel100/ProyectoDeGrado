package co.com.edu.usbcali.pdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.edu.usbcali.pdg.domain.TipoArtefacto;
import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface TipoArtefactoRepository extends JpaRepository<TipoArtefacto, Long> {
	
	@Query(nativeQuery = true)
	TipoArtefactoDTO consultarTipoArtefacto(@Param("pTiarId") Long tiarId,
											@Param("pEstado") String estado);
	
	List<TipoArtefacto> findByEstado(String estado);
}
