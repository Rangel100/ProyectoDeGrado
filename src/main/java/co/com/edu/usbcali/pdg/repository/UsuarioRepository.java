package co.com.edu.usbcali.pdg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(nativeQuery = true)
	UsuarioDTO consultarUsuario(@Param("pUsuaId") Long usuaId,
								@Param("pEstado") String estado);
}
