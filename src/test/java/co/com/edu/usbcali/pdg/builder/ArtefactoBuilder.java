package co.com.edu.usbcali.pdg.builder;

import java.util.Optional;

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.domain.TipoArtefacto;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.utility.Constantes;

public class ArtefactoBuilder {

	public static ArtefactoDTO getArtefactoDTOVacio() {
		ArtefactoDTO artefactoDTO = new ArtefactoDTO();

		artefactoDTO.setArteId(null);
		artefactoDTO.setCodigo(null);
		artefactoDTO.setCodigoUsuario(null);
		artefactoDTO.setEstado(null);
		artefactoDTO.setNombreTipoArtefacto(null);
		artefactoDTO.setTiarId_TipoArtefacto(null);
		artefactoDTO.setUrl(null);
		artefactoDTO.setUsuaId_Usuario(null);

		return artefactoDTO;
	}

	public static Artefacto getArtefacto() {
		Artefacto artefacto = new Artefacto();

		artefacto.setArteId(1L);
		artefacto.setCodigo("ARTE1");
		artefacto.setEstado(Constantes.ESTADO_ACTIVO);
		artefacto.setUrl("URL");

		Usuario usuario = UsuarioBuilder.getUsuario();
		artefacto.setUsuario(usuario);

		TipoArtefacto tipoArtefacto = TipoArtefactoBuilder.getTipoArtefacto();
		artefacto.setTipoArtefacto(tipoArtefacto);

		return artefacto;

	}

	public static ArtefactoDTO getArtefactoDTO() {
		ArtefactoDTO artefactoDTO = new ArtefactoDTO();

		artefactoDTO.setArteId(1L);
		artefactoDTO.setCodigo("ARTE1");
		artefactoDTO.setCodigoUsuario("j@j.com");
		artefactoDTO.setEstado(Constantes.ESTADO_ACTIVO);
		artefactoDTO.setNombreTipoArtefacto("TOMA CORRIENTE");
		artefactoDTO.setTiarId_TipoArtefacto(1L);
		artefactoDTO.setUrl("URL");
		artefactoDTO.setUsuaId_Usuario(1L);

		return artefactoDTO;
	}

	public static Optional<Artefacto> getArtefactoOpt() {

		Artefacto artefacto = getArtefacto();

		Optional<Artefacto> OptionalReturn = Optional.of(artefacto);

		return OptionalReturn;
	}
}
