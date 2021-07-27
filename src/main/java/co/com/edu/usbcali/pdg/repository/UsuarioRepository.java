package co.com.edu.usbcali.pdg.repository;

import java.util.List;

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
	List<UsuarioDTO> consultarUsuarios( @Param("pEstado") String estado,
										@Param("pNombre") String nombre, 
										@Param("pCodigo") String codigo);
	
	@Query(nativeQuery = true)
	UsuarioDTO consultarUsuariosPorCodigoOrm(@Param("pCodigo") String codigo,
											@Param("pEstado") String estado);
}
