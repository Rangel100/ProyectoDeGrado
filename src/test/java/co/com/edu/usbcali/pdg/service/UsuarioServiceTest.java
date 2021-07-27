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
import co.com.edu.usbcali.pdg.builder.TipoUsuarioBuilder;
import co.com.edu.usbcali.pdg.builder.UsuarioBuilder;
import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.mapper.ArtefactoMapper;
import co.com.edu.usbcali.pdg.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	@InjectMocks
	UsuarioServiceImpl usuarioServiceImpl;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@Mock
	UsuarioService usuarioService;
	
	@Mock
	TipoUsuarioService tipoUsuarioService;
	
	@Mock
	ArtefactoService artefactoService;
	
	@Mock
	ArtefactoMapper artefactoMapper;
	
	@Nested
	class crearUsuarioTests {
		
		@Test
		void debeLanzarExeptionUsuarioDTOSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = null;
			
			String messageExpected = "El usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiusIdSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setTiusId_TipoUsuario(null);
			
			String messageExpected = "El tipo de usuario se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiusIdNoExista() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			
			when(tipoUsuarioService.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El tipo de usuario seleccionado no exíste.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setCodigo(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaVacio() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setCodigo("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoYaExista() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarios.add(usuario);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "El codigo ingresado ya se encuentra en uso.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionDireccionSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setDireccion(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "La direccion se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionDireccionSeaVacio() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setDireccion("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "La direccion se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setNombre(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaVacio() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setNombre("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeCrearUsuario() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.crearUsuario(usuarioDTO);
			
			// Assert
			verify(usuarioRepository).save(any());
			
		}
		
	}
	
	@Nested	
	class actualizarUsuarioTests {
		
		@Test
		void debeLanzarExeptionUsuarioDTOSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = null;
			
			String messageExpected = "El usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUsuaIdSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setUsuaId(null);
			
			String messageExpected = "El identificador del usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUsuarioNoExista() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			
			when(usuarioRepository.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El usuario no fue encontrado.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiusIdSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setTiusId_TipoUsuario(null);
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			String messageExpected = "El tipo de usuario se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionTiusIdNoExista() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El tipo de usuario seleccionado no exíste.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setCodigo(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoSeaVacio() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setCodigo("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			String messageExpected = "El codigo se encuentra nulo o vacío";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionCodigoYaExista() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarios.add(usuario);
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "El codigo ingresado ya se encuentra en uso.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionDireccionSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setDireccion(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "La direccion se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionDireccionSeaVacio() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setDireccion("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "La direccion se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setNombre(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionNombreSeaVacio() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setNombre("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "El nombre se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeCrearUsuario() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(tipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			
			// Assert
			verify(usuarioService).update(any());
			
		}
		
	}
	
	@Nested	
	class eliminarUsuarioTests {
		
		@Test
		void debeLanzarExeptionUsuarioDTOSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = null;
			
			String messageExpected = "El usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.eliminarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUsuaIdSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setUsuaId(null);
			
			String messageExpected = "El identificador del usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.eliminarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUsuarioNoExista() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			
			when(usuarioRepository.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El usuario no fue encontrado.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.eliminarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeEliminarUsuarioYSusArtefactos() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setTiusId_TipoUsuario(null);
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			// Act
			usuarioServiceImpl.eliminarUsuario(usuarioDTO);
			
			// Assert
			verify(usuarioService).update(any());
			verify(artefactoService).eliminarArtefactosPorUsuario(any());		
			
		}
		
	}
	
	@Nested	
	class consultarUsuarioTests {
		
		@Test
		void debeLanzarExeptionUsuaIdSeaNull() {
			// Arrange
			Long usuaId = null;
			
			String messageExpected = "El identificador del usuario esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.consultarUsuario(usuaId);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultarUsuarioSinArtefactos() throws Exception {
			// Arrange
			Long usuaId = 1L;
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTOSinArteList();
			
			when(usuarioRepository.consultarUsuario(any(),any())).thenReturn(usuarioDTO);
			
			// Act
			UsuarioDTO usuarioDTOResult = usuarioServiceImpl.consultarUsuario(usuaId);
			
			// Assert
			assertEquals(usuarioDTO, usuarioDTOResult);
			
		}
		
		@Test
		void debeLanzarExeptionConArtefactosYNoExista() throws Exception {
			// Arrange
			Long usuaId = 1L;
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			
			when(usuarioRepository.consultarUsuario(any(),any())).thenReturn(usuarioDTO);
			
			when(artefactoService.findById(any())).thenReturn(Optional.empty());
			
			String messageExpected = "El artefacto no exíste.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.consultarUsuario(usuaId);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConArtefactos() throws Exception {
			// Arrange
			Long usuaId = 1L;
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<Artefacto> artefactoOpt = ArtefactoBuilder.getArtefactoOpt();
			ArtefactoDTO artefactoDTO = ArtefactoBuilder.getArtefactoDTO();
			
			when(usuarioRepository.consultarUsuario(any(),any())).thenReturn(usuarioDTO);
			
			when(artefactoService.findById(any())).thenReturn(artefactoOpt);
			
			when(artefactoMapper.artefactoToArtefactoDTO(any())).thenReturn(artefactoDTO);
			
			// Act
			UsuarioDTO usuarioDTOResult = usuarioServiceImpl.consultarUsuario(usuaId);
			
			// Assert
			assertEquals(usuarioDTO, usuarioDTOResult);
			
		}
		
	}
	
	@Nested	
	class consultarUsuariosPorTipoUsuarioTests {
		
		@Test
		void debeLanzarExeptionUsuaIdSeaNull() {
			// Arrange
			Long tiusId = null;
			
			String messageExpected = "El identificador del tipo de usuario se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.consultarUsuariosPorTipoUsuario(tiusId);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultar() throws Exception {
			// Arrange
			Long tiusId = 1L;
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarios.add(usuario);
			
			when(usuarioRepository.findByTipoUsuario_tiusIdAndEstado(any(),any())).thenReturn(usuarios);
			
			// Act
			List<Usuario> usuariosResult = usuarioServiceImpl.consultarUsuariosPorTipoUsuario(tiusId);
			
			// Assert
			assertEquals(usuarios, usuariosResult);
			
		}
		
	}
	
	@Nested	
	class consultarUsuariosPorCodigoTests {
		
		@Test
		void debeLanzarExeptionCodigoSeaNull() {
			// Arrange
			String codigo = null;
			
			String messageExpected = "El codigo se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.consultarUsuariosPorCodigo(codigo);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultar() throws Exception {
			// Arrange
			String codigo = "j@j.com";
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarios.add(usuario);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			List<Usuario> usuariosResult = usuarioServiceImpl.consultarUsuariosPorCodigo(codigo);
			
			// Assert
			assertEquals(usuarios, usuariosResult);
			
		}
		
	}
	
	@Nested	
	class consultarUsuariosTests {
		
		@Test
		void debeLanzarExeptionUsuarioDTOSeaNull() {
			// Arrange
			UsuarioDTO usuarioDTO = null;
			
			String messageExpected = "El usuario está nulo";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.consultarUsuarios(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultarNombreSeaNull() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setNombre(null);
			List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
			UsuarioDTO usuarioDTO2 = UsuarioBuilder.getUsuarioDTO();
			usuarioDTOs.add(usuarioDTO2);
			
			when(usuarioRepository.consultarUsuarios(any(),any(),any())).thenReturn(usuarioDTOs);
			
			// Act
			List<UsuarioDTO> usuarioDTOsReuslt = usuarioServiceImpl.consultarUsuarios(usuarioDTO);
			
			// Assert
			assertEquals(usuarioDTOs, usuarioDTOsReuslt);
			
		}
		
		@Test
		void debeConsultarNombreSeaVacio() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setNombre("");
			List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
			UsuarioDTO usuarioDTO2 = UsuarioBuilder.getUsuarioDTO();
			usuarioDTOs.add(usuarioDTO2);
			
			when(usuarioRepository.consultarUsuarios(any(),any(),any())).thenReturn(usuarioDTOs);
			
			// Act
			List<UsuarioDTO> usuarioDTOsReuslt = usuarioServiceImpl.consultarUsuarios(usuarioDTO);
			
			// Assert
			assertEquals(usuarioDTOs, usuarioDTOsReuslt);
			
		}
		
		@Test
		void debeConsultarCodigoSeaNull() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setCodigo(null);
			List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
			UsuarioDTO usuarioDTO2 = UsuarioBuilder.getUsuarioDTO();
			usuarioDTOs.add(usuarioDTO2);
			
			when(usuarioRepository.consultarUsuarios(any(),any(),any())).thenReturn(usuarioDTOs);
			
			// Act
			List<UsuarioDTO> usuarioDTOsReuslt = usuarioServiceImpl.consultarUsuarios(usuarioDTO);
			
			// Assert
			assertEquals(usuarioDTOs, usuarioDTOsReuslt);
			
		}
	
		@Test
		void debeConsultarCodigoSeaVacio() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setCodigo("");
			List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
			UsuarioDTO usuarioDTO2 = UsuarioBuilder.getUsuarioDTO();
			usuarioDTOs.add(usuarioDTO2);
			
			when(usuarioRepository.consultarUsuarios(any(),any(),any())).thenReturn(usuarioDTOs);
			
			// Act
			List<UsuarioDTO> usuarioDTOsReuslt = usuarioServiceImpl.consultarUsuarios(usuarioDTO);
			
			// Assert
			assertEquals(usuarioDTOs, usuarioDTOsReuslt);
			
		}
		
		@Test
		void debeConsultarConTodosLosDatos() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
			UsuarioDTO usuarioDTO2 = UsuarioBuilder.getUsuarioDTO();
			usuarioDTOs.add(usuarioDTO2);
			
			when(usuarioRepository.consultarUsuarios(any(),any(),any())).thenReturn(usuarioDTOs);
			
			// Act
			List<UsuarioDTO> usuarioDTOsReuslt = usuarioServiceImpl.consultarUsuarios(usuarioDTO);
			
			// Assert
			assertEquals(usuarioDTOs, usuarioDTOsReuslt);
			
		}
		
	}
	
	@Nested	
	class consultarUsuariosPorCodigoOrmTests {
		
		@Test
		void debeLanzarExeptionCodigoSeaNull() {
			// Arrange
			String codigo = null;
			
			String messageExpected = "El codigo se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.consultarUsuariosPorCodigoOrm(codigo);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeConsultar() throws Exception {
			// Arrange
			String codigo = "j@j.com";
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			
			when(usuarioRepository.consultarUsuariosPorCodigoOrm(any(),any())).thenReturn(usuarioDTO);
			
			// Act
			UsuarioDTO usuarioDTOResult = usuarioServiceImpl.consultarUsuariosPorCodigoOrm(codigo);
			
			// Assert
			assertEquals(usuarioDTO, usuarioDTOResult);
			
		}
		
	}
	

}
