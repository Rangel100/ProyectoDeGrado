package co.com.edu.usbcali.pdg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.repository.ArtefactoRepository;




@ExtendWith(MockitoExtension.class)
class ArtefactoServiceTest {

	@InjectMocks
	ArtefactoServiceImpl artefactoServiceImpl;
	
	@Mock
	ArtefactoRepository artefactoRepository;
	
	@Mock
	ArtefactoService artefactoService;
	
	@Mock
	TipoArtefactoService tipoArtefactoService;
	
	@Mock
	UsuarioService usuarioService;
	
	@Nested
	class crearArtefactoTests {
		
		@Test
		void debeLanzarExeptionArtefactoDTOSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = null;
			
			String messageExpected = "El artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setCodigo(null);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaVacio() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setCodigo("");
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUrlSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setUrl(null);
			
			String messageExpected = "El url se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUrlSeaVacio() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setUrl("");
			
			String messageExpected = "El url se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiarId_TipoArtefactoSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setTiarId_TipoArtefacto(null);
			
			String messageExpected = "El tipo de artefacto se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiarId_TipoArtefactoNoExista() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			
			when(tipoArtefactoService.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El tipo de artefacto seleccionado no exíste.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoUsuarioSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setCodigoUsuario(null);
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			
			when(tipoArtefactoService.findById(any())).thenReturn(tipoArtefactoOpt);
			
			String messageExpected = "El codigo del usuario se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoUsuarioNoExista() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(tipoArtefactoService.findById(any())).thenReturn(tipoArtefactoOpt);
			
			when(usuarioService.consultarUsuariosPorCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "El usuario seleccionado no exíste.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.crearArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeCrearArtefacto() throws Exception {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarios.add(usuario);
			
			when(tipoArtefactoService.findById(any())).thenReturn(tipoArtefactoOpt);
			
			when(usuarioService.consultarUsuariosPorCodigo(any())).thenReturn(usuarios);
			
			// Act
			artefactoServiceImpl.crearArtefacto(artefactoDTO);

			// Assert
			verify(artefactoRepository).save(any());
			
		}
		
	}
	
	@Nested
	class actualizarArtefactoTests {
		
		@Test
		void debeLanzarExeptionArtefactoDTOSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = null;
			
			String messageExpected = "El artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionArteIdSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setArteId(null);
			
			String messageExpected = "El identificador del artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionArtefactoNoExista() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			
			when(artefactoRepository.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El artefacto seleccionado no exíste";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setCodigo(null);
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaVacio() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setCodigo("");
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUrlSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setUrl(null);
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			String messageExpected = "El url se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUrlSeaVacio() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setUrl("");
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			String messageExpected = "El url se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiarId_TipoArtefactoSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setTiarId_TipoArtefacto(null);
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			String messageExpected = "El tipo de artefacto se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiarId_TipoArtefactoNoExista() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			when(tipoArtefactoService.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El tipo de artefacto seleccionado no exíste.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoUsuarioSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setCodigoUsuario(null);
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			when(tipoArtefactoService.findById(any())).thenReturn(tipoArtefactoOpt);
			
			String messageExpected = "El codigo del usuario se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoUsuarioNoExista() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			when(tipoArtefactoService.findById(any())).thenReturn(tipoArtefactoOpt);
			
			when(usuarioService.consultarUsuariosPorCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "El usuario seleccionado no exíste.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.actualizarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeActualizarArtefacto() throws Exception {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			Optional<TipoArtefacto> tipoArtefactoOpt = TipoArtefactoBuilder.getTipoArtefactoOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarios.add(usuario);
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			when(tipoArtefactoService.findById(any())).thenReturn(tipoArtefactoOpt);
			
			when(usuarioService.consultarUsuariosPorCodigo(any())).thenReturn(usuarios);
			
			// Act
			artefactoServiceImpl.actualizarArtefacto(artefactoDTO);

			// Assert
			verify(artefactoService).update(any());
			
		}
		
	}
	
	@Nested
	class eliminarArtefactoTests {
		@Test
		void debeLanzarExeptionArtefactoDTOSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = null;
			
			String messageExpected = "El artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.eliminarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionArteIdSeaNull() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTO.setArteId(null);
			
			String messageExpected = "El identificador del artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.eliminarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionArtefactoNoExista() {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			
			when(artefactoRepository.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El artefacto seleccionado no exíste";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.eliminarArtefacto(artefactoDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeEliminarArtefacto() throws Exception {
			// Arrange
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			
			when(artefactoRepository.findById(any())).thenReturn(artefactoOpt);
			
			// Act
			artefactoServiceImpl.eliminarArtefacto(artefactoDTO);

			// Assert
			verify(artefactoService).update(any());
			
		}
	}
	
	@Nested
	class consultarArtefactoTests {
		
		@Test
		void debeLanzarExeptionArteIdSeaNull() {
			// Arrange
			Long arteId = null;
			
			String messageExpected = "El identificador del artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.consultarArtefacto(arteId);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultarArtefacto() throws Exception {
			// Arrange
			Long arteId = 1L;
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			
			when(artefactoRepository.consultarArtefacto(any(), any())).thenReturn(artefactoDTO);
			
			// Act
			ArtefactoDTO artefactoDTOResult = artefactoServiceImpl.consultarArtefacto(arteId);

			// Assert
			assertEquals(artefactoDTO, artefactoDTOResult);
			
		}
	}
	
	@Nested
	class eliminarArtefactosPorUsuarioTests {
		
		@Test
		void debeLanzarExeptionUsuarioDTOSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = null;
			
			String messageExpected = "El usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.eliminarArtefactosPorUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUsuarioIdSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setUsuaId(null);
			
			String messageExpected = "El identificador del usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.eliminarArtefactosPorUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeEliminarLosArtefactos() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			List<Artefacto> artefactos = new ArrayList<>();
			Artefacto artefacto = ArtefactoBuilder.getArtefacto();
			artefactos.add(artefacto);
			
			when(artefactoRepository.findByUsuario_usuaId(any())).thenReturn(artefactos);
			
			// Act
			artefactoServiceImpl.eliminarArtefactosPorUsuario(usuarioDTO);

			// Assert
			verify(artefactoService).update(any());
			
		}
		
	}
	
	@Nested
	class consultarArtefactosPorTipoArtefactoTests {
		
		@Test
		void debeLanzarExeptionUsuarioDTOSeaNull() {
			// Arrange
			Long tiarId = null;
			
			String messageExpected = "El identificador del tipo de artefacto esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.consultarArtefactosPorTipoArtefacto(tiarId);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultarLosArtefactos() throws Exception {
			// Arrange
			Long tiarId = 1L;
			List<Artefacto> artefactos = new ArrayList<>();
			Artefacto artefacto = ArtefactoBuilder.getArtefacto();
			artefactos.add(artefacto);
			
			when(artefactoRepository.findByTipoArtefacto_tiarIdAndEstado(any(), any())).thenReturn(artefactos);
			
			// Act
			List<Artefacto> artefactosResult = artefactoServiceImpl.consultarArtefactosPorTipoArtefacto(tiarId);

			// Assert
			assertEquals(artefactos, artefactosResult);
			
		}
		
	}
	
	@Nested
	class consultarArtefactosPorUsuarioTests {
		
		@Test
		void debeLanzarExeptionUsuarioDTOSeaNull() {
			// Arrange
			Long usuaId = null;
			
			String messageExpected = "El identificador del usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				artefactoServiceImpl.consultarArtefactosPorUsuario(usuaId);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultarLosArtefactos() throws Exception {
			// Arrange
			Long usuaId = 1L;
			List<ArtefactoDTO> artefactoDTOs = new ArrayList<>();
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			artefactoDTOs.add(artefactoDTO);
			
			when(artefactoRepository.consultarArtefactosPorUsuario(any(), any())).thenReturn(artefactoDTOs);
			
			// Act
			List<ArtefactoDTO> artefactoDTOsResult = artefactoServiceImpl.consultarArtefactosPorUsuario(usuaId);

			// Assert
			assertEquals(artefactoDTOs, artefactoDTOsResult);
			
		}
		
	}
		
		

}
