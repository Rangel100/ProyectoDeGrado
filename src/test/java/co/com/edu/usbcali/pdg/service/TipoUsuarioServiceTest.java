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

import co.com.edu.usbcali.pdg.builder.TipoUsuarioBuilder;
import co.com.edu.usbcali.pdg.builder.UsuarioBuilder;
import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.TipoUsuarioDTO;
import co.com.edu.usbcali.pdg.entity.service.ZatTipoUsuarioService;
import co.com.edu.usbcali.pdg.mapper.TipoUsuarioMapper;
import co.com.edu.usbcali.pdg.repository.TipoUsuarioRepository;




@ExtendWith(MockitoExtension.class)
class TipoUsuarioServiceTest {

	@InjectMocks
	TipoUsuarioServiceImpl tipoUsuarioServiceImpl;
	
	@Mock
	TipoUsuarioRepository tipoUsuarioRepository;
	
	@Mock
	TipoUsuarioService tipoUsuarioService;
	
	@Mock
	ZatTipoUsuarioService zatTipoUsuarioService;
	
	@Mock
	UsuarioService usuarioService;
	
	@Mock
	TipoUsuarioMapper tipoUsuarioMapper;
	
	@Nested	
	class crearTipoUsuarioTests {
		
		@Test
		void debeLanzarExeptionTipoUsuarioDTOSeaNull() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = null;
			
			String messageExpected = "El tipo de usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.crearTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaNull() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			tipoUsuarioDTO.setNombre(null);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.crearTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaVacio() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			tipoUsuarioDTO.setNombre("");
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.crearTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreExista() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			
			when(tipoUsuarioRepository.findByNombre(any())).thenReturn(tipoUsuarioOpt);
			
			String messageExpected = "Ya exíste un tipo de usuario con ese nombre.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.crearTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeCrearTipoArtefacto() throws Exception {
		// Arrange
		TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
		
		when(tipoUsuarioRepository.findByNombre(any())).thenReturn(Optional.empty());
		
		// Act
		tipoUsuarioServiceImpl.crearTipoUsuario(tipoUsuarioDTO);
	
		// Assert
		verify(tipoUsuarioRepository).save(any());
			
		}
		
	}
	
	@Nested
	class actualizarTipoUsuarioTests {
		
		@Test
		void debeLanzarExeptionTipoUsuarioDTOSeaNull() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = null;
			
			String messageExpected = "El tipo de usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.actualizarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiusIdSeaNull() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			tipoUsuarioDTO.setTiusId(null);
			
			String messageExpected = "El identificador del tipo de usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.actualizarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTipoUsuarioNoExista() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			
			when(tipoUsuarioRepository.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El tipo de usuario no fue encontrado.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.actualizarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaNull() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();;
			tipoUsuarioDTO.setNombre(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			
			when(tipoUsuarioRepository.findById(any())).thenReturn(tipoUsuarioOpt);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.actualizarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaVacio() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();;
			tipoUsuarioDTO.setNombre("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			
			when(tipoUsuarioRepository.findById(any())).thenReturn(tipoUsuarioOpt);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.actualizarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreExista() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			
			when(tipoUsuarioRepository.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(tipoUsuarioRepository.findByNombre(any())).thenReturn(tipoUsuarioOpt);
			
			String messageExpected = "Ya exíste un tipo de usuario con ese nombre.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.actualizarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeCrearTipoArtefacto() throws Exception {
		// Arrange
		TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
		Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
		
		when(tipoUsuarioRepository.findById(any())).thenReturn(tipoUsuarioOpt);
		
		when(tipoUsuarioRepository.findByNombre(any())).thenReturn(Optional.empty());
		
		// Act
		tipoUsuarioServiceImpl.actualizarTipoUsuario(tipoUsuarioDTO);
	
		// Assert
		verify(zatTipoUsuarioService).update(any());
			
		}
		
	}
	
	@Nested
	class eliminarTipoUsuarioTests {
		
		@Test
		void debeLanzarExeptionTipoUsuarioDTOSeaNull() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = null;
			
			String messageExpected = "El tipo de usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.eliminarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiusIdSeaNull() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			tipoUsuarioDTO.setTiusId(null);
			
			String messageExpected = "El identificador del tipo de usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.eliminarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTipoUsuarioNoExista() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			
			when(tipoUsuarioRepository.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El tipo de usuario no fue encontrado.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.eliminarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTipoUsuarioTegaUsuariosAsociados() {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarios.add(usuario);
			
			when(tipoUsuarioRepository.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioService.consultarUsuariosPorTipoUsuario(any())).thenReturn(usuarios);
			
			String messageExpected = "El tipo de usuario tiene usuarios asociados.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.eliminarTipoUsuario(tipoUsuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeEliminatTipoUsuario() throws Exception {
			// Arrange
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(tipoUsuarioRepository.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioService.consultarUsuariosPorTipoUsuario(any())).thenReturn(usuarios);
			
			// Act
			tipoUsuarioServiceImpl.eliminarTipoUsuario(tipoUsuarioDTO);
			
			// Assert
			verify(zatTipoUsuarioService).update(any());
			
		}
	}
	
	@Nested
	class consultarTipoUsuarioTests {
		
		@Test
		void debeLanzarExeptionTiarIdSeaNull() {
			// Arrange
			Long tiusId = null;
			
			String messageExpected = "El identificador del tipo de usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				tipoUsuarioServiceImpl.consultarTipoUsuario(tiusId);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultarArtefacto() throws Exception {
			// Arrange
			Long tiusId = 1L;
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			
			when(tipoUsuarioRepository.consultarTipoUsuario(any(), any())).thenReturn(tipoUsuarioDTO);
			
			// Act
			TipoUsuarioDTO tipoUsuarioDTOResult = tipoUsuarioServiceImpl.consultarTipoUsuario(tiusId);

			// Assert
			assertEquals(tipoUsuarioDTO, tipoUsuarioDTOResult);
			
		}
	}
	
	@Nested
	class consultarTipoUsuarioActivosTests {
		
		@Test
		void debeConsultarTipoUsuarios() throws Exception {
			// Arrange
			List<TipoUsuarioDTO> tipoUsuarioDTOs = new ArrayList<>();
			TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioBuilder.getTipoUsuarioDTO();
			tipoUsuarioDTOs.add(tipoUsuarioDTO);
			
			when(tipoUsuarioMapper.listTipoUsuarioToListTipoUsuarioDTO(any())).thenReturn(tipoUsuarioDTOs);
			
			// Act
			List<TipoUsuarioDTO> tipoUsuarioDTOsResult = tipoUsuarioServiceImpl.consultarTipoUsuarioActivos();

			// Assert
			assertEquals(tipoUsuarioDTOs, tipoUsuarioDTOsResult);
			
		}
	}

}
