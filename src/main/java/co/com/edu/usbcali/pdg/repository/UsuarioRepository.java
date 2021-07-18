package co.com.edu.usbcali.pdg.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	List<Usuario> findByTipoUsuario_tiusIdAndEstado(Long tiusId, String estado);
	
	List<Usuario> findByCodigo(String codigo);
	
	@Query(nativeQuery = true)
	Page<UsuarioDTO> consultarUsuarios( @Param("pEstado") String estado,
										@Param("pNombre") String nombre, 
										@Param("pCodigo") String codigo, 
										Pageable pageable);
}
