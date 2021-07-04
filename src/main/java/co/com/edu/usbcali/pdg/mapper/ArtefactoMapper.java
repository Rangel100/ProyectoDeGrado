package co.com.edu.usbcali.pdg.mapper;

import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/
@Mapper
public interface ArtefactoMapper {
    @Mapping(source = "tipoArtefacto.tiarId", target = "tiarId_TipoArtefacto")
    @Mapping(source = "usuario.usuaId", target = "usuaId_Usuario")
    public ArtefactoDTO artefactoToArtefactoDTO(Artefacto artefacto);

    @Mapping(source = "tiarId_TipoArtefacto", target = "tipoArtefacto.tiarId")
    @Mapping(source = "usuaId_Usuario", target = "usuario.usuaId")
    public Artefacto artefactoDTOToArtefacto(ArtefactoDTO artefactoDTO);

    public List<ArtefactoDTO> listArtefactoToListArtefactoDTO(
        List<Artefacto> artefactos);

    public List<Artefacto> listArtefactoDTOToListArtefacto(
        List<ArtefactoDTO> artefactoDTOs);
}
