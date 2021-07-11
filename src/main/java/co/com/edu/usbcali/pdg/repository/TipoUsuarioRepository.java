package co.com.edu.usbcali.pdg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
	
	@Query(nativeQuery = true)
	TipoUsuarioDTO consultarTipoUsuario(@Param("pTiusId") Long tiusId,
										@Param("pEstado") String estado);
	
//	TipoUsuario findByNombre(String nombre);
	
	Optional<TipoUsuario> findByNombre(String nombre);
}
