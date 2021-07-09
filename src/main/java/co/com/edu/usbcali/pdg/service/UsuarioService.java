package co.com.edu.usbcali.pdg.service;

import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
public interface UsuarioService extends GenericService<Usuario, Long> {

	void crearUsuario(UsuarioDTO usuarioDTO) throws Exception;
}
