package co.com.edu.usbcali.pdg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.edu.usbcali.pdg.builder.ArtefactoBuilder;
import co.com.edu.usbcali.pdg.builder.TipoArtefactoBuilder;
import co.com.edu.usbcali.pdg.builder.UsuarioBuilder;
import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.domain.TipoArtefacto;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.dto.TipoArtefactoDTO;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.mapper.TipoArtefactoMapper;
import co.com.edu.usbcali.pdg.repository.ArtefactoRepository;
import co.com.edu.usbcali.pdg.repository.TipoArtefactoRepository;




@ExtendWith(MockitoExtension.class)
class TipoArtefactoServiceTest {

	@InjectMocks
	TipoArtefactoServiceImpl tipoArtefactoServiceImpl;
	
	@Mock
	TipoArtefactoRepository tipoArtefactoRepository;
	
	@Mock
	TipoArtefactoService tipoArtefactoService;
	
	@Mock
	ArtefactoService artefactoService;
	
	@Mock
	TipoArtefactoMapper tipoArtefactoMapper;
	
	@Mock
	UsuarioService usuarioService;
	
	@Nested
	class crearTipoArtefactoTests {
		
		@Test
		void debeLanzarExeptionTipoArtefactoDTOSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = null;
			
			String messageExpected = "El tipo de artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.crearTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setCodigo(null);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.crearTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaVacio() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setCodigo("");
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.crearTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setNombre(null);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.crearTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaVacio() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setNombre("");
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.crearTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeCrearTipoArtefacto() throws Exception {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			
			// Act
			tipoArtefactoServiceImpl.crearTipoArtefacto(tipoArtefactoDTO);
			
			// Assert
			verify(tipoArtefactoRepository).save(any());
			
		}
		
	}
	
	@Nested
	class actualizarTipoArtefactoTests {
		
		@Test
		void debeLanzarExeptionTipoArtefactoDTOSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = null;
			
			String messageExpected = "El tipo de artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.actualizarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiarIdSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setTiarId(null);
			
			String messageExpected = "El identificador del tipo de artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.actualizarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTipoArtefactoNoExista() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El tipo de artefacto no fue encontrado.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.actualizarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setCodigo(null);
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(tipoArtefactoOpt);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.actualizarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaVacio() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setCodigo("");
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(tipoArtefactoOpt);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.actualizarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setNombre(null);
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(tipoArtefactoOpt);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.actualizarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaVacio() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setNombre("");
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(tipoArtefactoOpt);
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.actualizarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeActualizarTipoArtefacto() throws Exception {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(tipoArtefactoOpt);
			
			// Act
			tipoArtefactoServiceImpl.actualizarTipoArtefacto(tipoArtefactoDTO);
			
			// Assert
			verify(tipoArtefactoService).update(any());
			
		}
		
	}
	
	@Nested
	class eliminarTipoArtefactoTests {
		
		@Test
		void debeLanzarExeptionArtefactoDTOSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = null;
			
			String messageExpected = "El tipo de artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.eliminarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionArteIdSeaNull() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTO.setTiarId(null);
			
			String messageExpected = "El identificador del tipo de artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.eliminarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTipoArtefactoNoExista() {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El tipo de artefacto no fue encontrado.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.eliminarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionSiTieneArtefactosAsociados() throws Exception {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			List<Artefacto> artefactos = new ArrayList<>();
			Artefacto artefacto = ArtefactoBuilder.getArtefacto();
			artefactos.add(artefacto);
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(tipoArtefactoOpt);
			
			when(artefactoService.consultarArtefactosPorTipoArtefacto(any())).thenReturn(artefactos);
			
			String messageExpected = "El tipo de artefacto tiene artefactos asociados.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.eliminarTipoArtefacto(tipoArtefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeEliminarTipoArtefacto() throws Exception {
			// Arrange
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			List<Artefacto> artefactos = new ArrayList<>();
			
			when(tipoArtefactoRepository.findById(any())).thenReturn(tipoArtefactoOpt);
			
			when(artefactoService.consultarArtefactosPorTipoArtefacto(any())).thenReturn(artefactos);
			
			// Act
			tipoArtefactoServiceImpl.eliminarTipoArtefacto(tipoArtefactoDTO);
			
			// Assert
			verify(tipoArtefactoService).update(any());
			
		}
	}
	
	@Nested
	class consultarTipoArtefactoTests {
		
		@Test
		void debeLanzarExeptionTiarIdSeaNull() {
			// Arrange
			Long tiarId = null;
			
			String messageExpected = "El identificador del tipo de artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoArtefactoServiceImpl.consultarTipoArtefacto(tiarId);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultarArtefacto() throws Exception {
			// Arrange
			Long tiarId = 1L;
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			
			when(tipoArtefactoRepository.consultarTipoArtefacto(any(), any())).thenReturn(tipoArtefactoDTO);
			
			// Act
			TipoArtefactoDTO tipoArtefactoDTOResult = tipoArtefactoServiceImpl.consultarTipoArtefacto(tiarId);

			// Assert
			assertEquals(tipoArtefactoDTO, tipoArtefactoDTOResult);
			
		}
	}
	
	@Nested
	class consultarTipoArtefactosActivosTests {
		
		@Test
		void debeConsultarArtefacto() throws Exception {
			// Arrange
			List<TipoArtefactoDTO> tipoArtefactoDTOs = new ArrayList<>();
			TipoArtefactoDTO tipoArtefactoDTO = TipoArtefactoBuilder.getTipoArtefactoDTO();
			tipoArtefactoDTOs.add(tipoArtefactoDTO);
			
			when(tipoArtefactoMapper.listTipoArtefactoToListTipoArtefactoDTO(any())).thenReturn(tipoArtefactoDTOs);
			
			// Act
			List<TipoArtefactoDTO> tipoArtefactoDTOResult = tipoArtefactoServiceImpl.consultarTipoArtefactosActivos();

			// Assert
			assertEquals(tipoArtefactoDTOs, tipoArtefactoDTOResult);
			
		}
	}

}
