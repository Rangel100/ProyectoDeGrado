package co.com.edu.usbcali.pdg.service;

import java.util.List;

import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface UsuarioService extends GenericService<Usuario, Long> {

	void crearUsuario(UsuarioDTO usuarioDTO) throws Exception;

	void actualizarUsuario(UsuarioDTO usuarioDTO) throws Exception;

	void eliminarUsuario(UsuarioDTO usuarioDTO) throws Exception;

	UsuarioDTO consultarUsuario(Long usuaId) throws Exception;

	List<Usuario> consultarUsuariosPorTipoUsuario(Long tiusId);

	List<Usuario> consultarUsuariosPorCodigo(String codigo);

	List<UsuarioDTO> consultarUsuarios(UsuarioDTO usuarioDTO);
}
