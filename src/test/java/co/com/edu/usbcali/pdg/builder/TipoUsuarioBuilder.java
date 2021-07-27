package co.com.edu.usbcali.pdg.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;
import co.com.edu.usbcali.pdg.utility.Constantes;

public class TipoUsuarioBuilder {

	public static TipoUsuarioDTO getTipoUsuarioDTOVacio() {
		TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();

		tipoUsuarioDTO.setEstado(null);
		tipoUsuarioDTO.setNombre(null);
		tipoUsuarioDTO.setTiusId(null);

		return tipoUsuarioDTO;
	}

	public static TipoUsuario getTipoUsuario() {
		TipoUsuario tipoUsuario = new TipoUsuario();

		tipoUsuario.setEstado(Constantes.ESTADO_ACTIVO);
		tipoUsuario.setNombre("ADMINISTRADOR");
		tipoUsuario.setTiusId(1L);
		
		List<Usuario> usuarios = new ArrayList<>();
		Usuario usuario = UsuarioBuilder.getUsuario();
		usuarios.add(usuario);
		tipoUsuario.setUsuarios(usuarios);

		return tipoUsuario;

	}

	public static TipoUsuarioDTO getTipoUsuarioDTO() {
		TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();

		tipoUsuarioDTO.setEstado(Constantes.ESTADO_ACTIVO);
		tipoUsuarioDTO.setNombre("ADMINISTRADOR");
		tipoUsuarioDTO.setTiusId(1L);
		
		return tipoUsuarioDTO;
	}

	public static Optional<TipoUsuario> getUsuarioOpt() {

		TipoUsuario tipoUsuario = getTipoUsuario();

		Optional<TipoUsuario> OptionalReturn = Optional.of(tipoUsuario);

		return OptionalReturn;
	}
	
	public static Optional<TipoUsuario> getUsuarioOptVacio() {

		TipoUsuario tipoUsuario = new TipoUsuario();

		Optional<TipoUsuario> OptionalReturn = Optional.of(tipoUsuario);

		return OptionalReturn;
	}
}
