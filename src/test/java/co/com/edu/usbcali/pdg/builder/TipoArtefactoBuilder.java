package co.com.edu.usbcali.pdg.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.domain.TipoArtefacto;
import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;
import co.com.edu.usbcali.pdg.utility.Constantes;

public class TipoArtefactoBuilder {

	public static TipoArtefactoDTO getTipoArtefactoDTOVacio() {
		TipoArtefactoDTO tipoArtefactoDTO = new TipoArtefactoDTO();

		tipoArtefactoDTO.setCodigo("TOMA");
		tipoArtefactoDTO.setEstado(Constantes.ESTADO_ACTIVO);
		tipoArtefactoDTO.setNombre("TOMA CORRIENTE");
		tipoArtefactoDTO.setTiarId(1L);

		return tipoArtefactoDTO;
	}

	public static TipoArtefacto getTipoArtefacto() {
		TipoArtefacto tipoArtefacto = new TipoArtefacto();

		tipoArtefacto.setCodigo("TOMA");
		tipoArtefacto.setEstado(Constantes.ESTADO_ACTIVO);
		tipoArtefacto.setNombre("TOMA CORRIENTE");
		tipoArtefacto.setTiarId(1L);
		
		List<Artefacto> artefactos = new ArrayList<>();
//		Artefacto artefacto = ArtefactoBuilder.getArtefacto();
		Artefacto artefacto = new Artefacto();
		artefacto.setArteId(1L);
		artefactos.add(artefacto);
		tipoArtefacto.setArtefactos(artefactos);

		return tipoArtefacto;
	}

	public static TipoArtefactoDTO getTipoArtefactoDTO() {
		TipoArtefactoDTO tipoArtefactoDTO = new TipoArtefactoDTO();

		tipoArtefactoDTO.setCodigo("TOMA");
		tipoArtefactoDTO.setEstado(Constantes.ESTADO_ACTIVO);
		tipoArtefactoDTO.setNombre("TOMA CORRIENTE");
		tipoArtefactoDTO.setTiarId(1L);

		return tipoArtefactoDTO;
	}

	public static Optional<TipoArtefacto> getTipoArtefactoOpt() {

		TipoArtefacto tipoArtefacto = getTipoArtefacto();

		Optional<TipoArtefacto> OptionalReturn = Optional.of(tipoArtefacto);

		return OptionalReturn;
	}
}
