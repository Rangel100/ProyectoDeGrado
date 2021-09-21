package co.com.edu.usbcali.pdg.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.utility.Constantes;

public class UsuarioBuilder {

	public static UsuarioDTO getUsuarioDTOVacio() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setApellido(null);
		usuarioDTO.setArtefactosList(null);
		usuarioDTO.setCodigo(null);
		usuarioDTO.setDireccion(null);
		usuarioDTO.setEstado(null);
		usuarioDTO.setNombre(null);
		usuarioDTO.setNombreTipoUsuario(null);
		usuarioDTO.setTiusId_TipoUsuario(null);
		usuarioDTO.setUsuaId(null);		
		usuarioDTO.setArtefactoDTOs(null);

		return usuarioDTO;
	}

	public static Usuario getUsuario() {
		Usuario usuario = new Usuario();

		usuario.setCodigo("j@j.com");
		usuario.setDireccion("Calle 15 # 99-39");
		usuario.setEstado(Constantes.ESTADO_ACTIVO);
		usuario.setNombre("Jhonatan");
		usuario.setUsuaId(1L);
		usuario.setPss("12dad13");
		
//		TipoUsuario tipoUsuario = TipoUsuarioBuilder.getTipoUsuario();
		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setTiusId(1L);
		usuario.setTipoUsuario(tipoUsuario);
		
		List<Artefacto> artefactos = new ArrayList<>();
//		Artefacto artefacto = ArtefactoBuilder.getArtefacto();
		Artefacto artefacto = new Artefacto();
		artefacto.setArteId(1L);
		artefactos.add(artefacto);
		usuario.setArtefactos(artefactos);

		return usuario;
	}

	public static UsuarioDTO getUsuarioDTO() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setApellido("Rangel");
		usuarioDTO.setArtefactosList("1,2,3");
		usuarioDTO.setCodigo("j@jh.com");
		usuarioDTO.setDireccion("Calle 15 # 99-39");
		usuarioDTO.setEstado(Constantes.ESTADO_ACTIVO);
		usuarioDTO.setNombre("Jhonatan");
		usuarioDTO.setNombreTipoUsuario("USUARIO");
		usuarioDTO.setTiusId_TipoUsuario(1L);
		usuarioDTO.setUsuaId(1L);
		usuarioDTO.setPss("123456");
		
		List<ArtefactoDTO> artefactoDTOs = new ArrayList<>();
		ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
		artefactoDTOs.add(artefactoDTO);
		usuarioDTO.setArtefactoDTOs(artefactoDTOs);
		
		return usuarioDTO;
	}
	
	public static UsuarioDTO getUsuarioDTOSinArteList() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setApellido("Rangel");
		usuarioDTO.setArtefactosList(null);
		usuarioDTO.setCodigo("j@j.com");
		usuarioDTO.setDireccion("Calle 15 # 99-39");
		usuarioDTO.setEstado(Constantes.ESTADO_ACTIVO);
		usuarioDTO.setNombre("Jhonatan");
		usuarioDTO.setNombreTipoUsuario("USUARIO");
		usuarioDTO.setTiusId_TipoUsuario(1L);
		usuarioDTO.setUsuaId(1L);
		
//		List<ArtefactoDTO> artefactoDTOs = new ArrayList<>();
//		ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
//		artefactoDTOs.add(artefactoDTO);
//		usuarioDTO.setArtefactoDTOs(artefactoDTOs);
		
		return usuarioDTO;
	}

	public static Optional<Usuario> getUsuarioOpt() {

		Usuario usuario = getUsuario();

		Optional<Usuario> OptionalReturn = Optional.of(usuario);

		return OptionalReturn;
	}
}
