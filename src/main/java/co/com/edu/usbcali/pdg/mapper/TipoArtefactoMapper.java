package co.com.edu.usbcali.pdg.mapper;

import co.com.edu.usbcali.pdg.domain.TipoArtefacto;
import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;

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
public interface TipoArtefactoMapper {
    public TipoArtefactoDTO tipoArtefactoToTipoArtefactoDTO(
        TipoArtefacto tipoArtefacto);

    public TipoArtefacto tipoArtefactoDTOToTipoArtefacto(
        TipoArtefactoDTO tipoArtefactoDTO);

    public List<TipoArtefactoDTO> listTipoArtefactoToListTipoArtefactoDTO(
        List<TipoArtefacto> tipoArtefactos);

    public List<TipoArtefacto> listTipoArtefactoDTOToListTipoArtefacto(
        List<TipoArtefactoDTO> tipoArtefactoDTOs);
}
