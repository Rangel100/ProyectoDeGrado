package co.com.edu.usbcali.pdg.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.edu.usbcali.pdg.builder.ArtefactoBuilder;
import co.com.edu.usbcali.pdg.builder.TipoUsuarioBuilder;
import co.com.edu.usbcali.pdg.builder.UsuarioBuilder;
import co.com.edu.usbcali.pdg.domain.Artefacto;
import co.com.edu.usbcali.pdg.domain.TipoUsuario;
import co.com.edu.usbcali.pdg.domain.Usuario;
import co.com.edu.usbcali.pdg.dto.ArtefactoDTO;
import co.com.edu.usbcali.pdg.dto.UsuarioDTO;
import co.com.edu.usbcali.pdg.entity.service.ZatArtefactoService;
import co.com.edu.usbcali.pdg.entity.service.ZatTipoUsuarioService;
import co.com.edu.usbcali.pdg.entity.service.ZatUsuarioService;
import co.com.edu.usbcali.pdg.mapper.ArtefactoMapper;
import co.com.edu.usbcali.pdg.repository.UsuarioRepository;
import co.com.edu.usbcali.pdg.utility.Constantes;
import co.com.edu.usbcali.pdg.utility.SendMail;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	@InjectMocks
	UsuarioServiceImpl usuarioServiceImpl;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@Mock
	UsuarioService usuarioService;
	
	@Mock
	ZatUsuarioService zatUsuarioService;
	
	@Mock
	TipoUsuarioService tipoUsuarioService;
	
	@Mock
	ZatTipoUsuarioService zatTipoUsuarioService;
	
	@Mock
	ArtefactoService artefactoService;
	
	@Mock
	ZatArtefactoService zatArtefactoService;
	
	@Mock
	ArtefactoMapper artefactoMapper;
	
	@Mock
	SendMail sendMail;
	
	@Captor
	ArgumentCaptor<Usuario> usuarioCaptor;
	
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(Optional.empty());
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
		void debeLanzarExeptionPssEsNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setPss(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "La contraseña se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.crearUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionPssEstaVacio() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setPss("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "La contraseña se encuentra nulo o vacío.";
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
		void debeCrearUsuarioApellidoSeaNull() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setApellido(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			Usuario usuarioEnviado;
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.crearUsuario(usuarioDTO);
			
			// Assert
			verify(usuarioRepository).save(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(tipoUsuarioOpt.get(), usuarioEnviado.getTipoUsuario()),
					
					() -> assertEquals(usuarioDTO.getCodigo(), usuarioEnviado.getCodigo()),
					
					() -> assertEquals(usuarioDTO.getDireccion(), usuarioEnviado.getDireccion()),
					
					() -> assertEquals(usuarioDTO.getNombre(), usuarioEnviado.getNombre()),
					
					() -> assertNotEquals(usuarioDTO.getPss(), usuarioEnviado.getPss()),
					
					() -> assertEquals(Constantes.ESTADO_ACTIVO, usuarioEnviado.getEstado())
					
					);
			
		}
		
		@Test
		void debeCrearUsuarioApellidoSeaVacio() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setApellido("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			Usuario usuarioEnviado;
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.crearUsuario(usuarioDTO);
			
			// Assert
			verify(usuarioRepository).save(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(tipoUsuarioOpt.get(), usuarioEnviado.getTipoUsuario()),
					
					() -> assertEquals(usuarioDTO.getCodigo(), usuarioEnviado.getCodigo()),
					
					() -> assertEquals(usuarioDTO.getDireccion(), usuarioEnviado.getDireccion()),
					
					() -> assertEquals(usuarioDTO.getNombre(), usuarioEnviado.getNombre()),
					
					() -> assertNotEquals(usuarioDTO.getPss(), usuarioEnviado.getPss()),
					
					() -> assertEquals(Constantes.ESTADO_ACTIVO, usuarioEnviado.getEstado())
					
					);
			
		}
		
		@Test
		void debeCrearUsuarioConApellido() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			Usuario usuarioEnviado;
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.crearUsuario(usuarioDTO);
			
			// Assert
			verify(usuarioRepository).save(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(tipoUsuarioOpt.get(), usuarioEnviado.getTipoUsuario()),
					
					() -> assertEquals(usuarioDTO.getCodigo(), usuarioEnviado.getCodigo()),
					
					() -> assertEquals(usuarioDTO.getDireccion(), usuarioEnviado.getDireccion()),
					
					() -> assertEquals(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido(), usuarioEnviado.getNombre()),
					
					() -> assertNotEquals(usuarioDTO.getPss(), usuarioEnviado.getPss()),
					
					() -> assertEquals(Constantes.ESTADO_ACTIVO, usuarioEnviado.getEstado())
					
					);
			
		}
		
		@Test
		void debeCrearUsuario() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			
			Usuario usuarioEnviado;
	
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.crearUsuario(usuarioDTO);
			
			// Assert
			verify(usuarioRepository).save(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(tipoUsuarioOpt.get(), usuarioEnviado.getTipoUsuario()),
					
					() -> assertEquals(usuarioDTO.getCodigo(), usuarioEnviado.getCodigo()),
					
					() -> assertEquals(usuarioDTO.getDireccion(), usuarioEnviado.getDireccion()),
					
					() -> assertEquals(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido(), usuarioEnviado.getNombre()),
					
					() -> assertNotEquals(usuarioDTO.getPss(), usuarioEnviado.getPss()),
					
					() -> assertEquals(Constantes.ESTADO_ACTIVO, usuarioEnviado.getEstado())
					
					);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(Optional.empty());
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarios.add(usuario);
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			usuarioDTO.setCodigo("j@j.com");
			usuarioDTO.setDireccion(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
//			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
//			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
		void debeLanzarExeptionPssEsNull() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setPss(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "La contraseña se encuentra nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionPssSeaVacio() {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setPss("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			String messageExpected = "La contraseña se encuentra nulo o vacío.";
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
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
		void debeActualizarUsuarioApellidoSeaNull() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setApellido(null);
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			Usuario usuarioEnviado;
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			
			// Assert
			verify(zatUsuarioService).update(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(tipoUsuarioOpt.get(), usuarioEnviado.getTipoUsuario()),
					
					() -> assertEquals(usuarioDTO.getCodigo(), usuarioEnviado.getCodigo()),
					
					() -> assertEquals(usuarioDTO.getDireccion(), usuarioEnviado.getDireccion()),
					
					() -> assertEquals(usuarioDTO.getNombre(), usuarioEnviado.getNombre())
										
					);
		}
		
		@Test
		void debeActualizarUsuarioApellidoSeaVacio() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			usuarioDTO.setApellido("");
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			Usuario usuarioEnviado;
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			
			// Assert
			verify(zatUsuarioService).update(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(tipoUsuarioOpt.get(), usuarioEnviado.getTipoUsuario()),
					
					() -> assertEquals(usuarioDTO.getCodigo(), usuarioEnviado.getCodigo()),
					
					() -> assertEquals(usuarioDTO.getDireccion(), usuarioEnviado.getDireccion()),
					
					() -> assertEquals(usuarioDTO.getNombre(), usuarioEnviado.getNombre())
										
					);
			
		}
		
		@Test
		void debeActualizarUsuarioConApellido() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			Usuario usuarioEnviado;
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			
			// Assert
			verify(zatUsuarioService).update(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(tipoUsuarioOpt.get(), usuarioEnviado.getTipoUsuario()),
					
					() -> assertEquals(usuarioDTO.getCodigo(), usuarioEnviado.getCodigo()),
					
					() -> assertEquals(usuarioDTO.getDireccion(), usuarioEnviado.getDireccion()),
					
					() -> assertEquals(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido(), usuarioEnviado.getNombre())
										
					);
			
		}
		
		@Test
		void debeActualizarUsuario() throws Exception {
			// Arrange
			UsuarioDTO usuarioDTO = UsuarioBuilder.getUsuarioDTO();
			Optional<TipoUsuario> tipoUsuarioOpt = TipoUsuarioBuilder.getUsuarioOpt();
			List<Usuario> usuarios = new ArrayList<>();
			Optional<Usuario> usuarioOpt = UsuarioBuilder.getUsuarioOpt();
			
			Usuario usuarioEnviado;
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			when(zatTipoUsuarioService.findById(any())).thenReturn(tipoUsuarioOpt);
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarios);
			
			// Act
			usuarioServiceImpl.actualizarUsuario(usuarioDTO);
			
			// Assert
			verify(zatUsuarioService).update(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(tipoUsuarioOpt.get(), usuarioEnviado.getTipoUsuario()),
					
					() -> assertEquals(usuarioDTO.getCodigo(), usuarioEnviado.getCodigo()),
					
					() -> assertEquals(usuarioDTO.getDireccion(), usuarioEnviado.getDireccion()),
					
					() -> assertEquals(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido(), usuarioEnviado.getNombre())
										
					);
			
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
			
			Usuario usuarioEnviado;
			
			when(usuarioRepository.findById(any())).thenReturn(usuarioOpt);
			
			// Act
			usuarioServiceImpl.eliminarUsuario(usuarioDTO);
			
			// Assert
			verify(artefactoService).eliminarArtefactosPorUsuario(any());
			verify(zatUsuarioService).update(usuarioCaptor.capture());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(Constantes.ESTADO_INACTIVO, usuarioEnviado.getEstado())
								
					);
			
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
			
//			when(zatUsuarioService.findById(any())).thenReturn(Optional.empty());
			
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
			List<ArtefactoDTO> artefactoDTOs = new ArrayList<>();
			artefactoDTOs.add(artefactoDTO);
			
			when(usuarioRepository.consultarUsuario(any(),any())).thenReturn(usuarioDTO);
			
			when(zatArtefactoService.findById(any())).thenReturn(artefactoOpt);
			
			when(artefactoMapper.artefactoToArtefactoDTO(any())).thenReturn(artefactoDTO);
			
			// Act
			UsuarioDTO usuarioDTOResult = usuarioServiceImpl.consultarUsuario(usuaId);
			
			// Assert
			assertEquals(usuarioDTO, usuarioDTOResult);
			assertNotNull(usuarioDTOResult.getArtefactoDTOs());
			
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
			
			when(usuarioRepository.consultarUsuarios(any(),eq("-1"),any())).thenReturn(usuarioDTOs);
			
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
			
			when(usuarioRepository.consultarUsuarios(any(),eq("-1"),any())).thenReturn(usuarioDTOs);
			
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
			
			when(usuarioRepository.consultarUsuarios(any(), any(), eq("-1"))).thenReturn(usuarioDTOs);
			
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
			
			when(usuarioRepository.consultarUsuarios(any(), any(), eq("-1"))).thenReturn(usuarioDTOs);
			
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
	
	@Nested	
	class actualizarEnviarContraseñaTests {
		
		@Test
		void debeLanzarExeptionCorreoSeaNull() {
			// Arrange
			String correo = null;
			
			String messageExpected = "El correo esta nulo o vacío.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarEnviarContraseña(correo);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeLanzarExeptionUsuarioNoExista() {
			// Arrange
			String correo = "correo@correo.com";
			List<Usuario> usuarioList = new ArrayList<>();
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarioList);
			
			String messageExpected = "El usuario no fue encontrado.";
			
			// Act
			Exception exception = assertThrows(Exception.class, () -> {
				usuarioServiceImpl.actualizarEnviarContraseña(correo);
			});
			
			// Assert
			assertEquals(messageExpected, exception.getMessage());
			
		}
		
		@Test
		void debeActualizarYEnviarCorreo() throws Exception {
			// Arrange
			String correo = "j@j.com";
			List<Usuario> usuarioList = new ArrayList<>();
			Usuario usuario = UsuarioBuilder.getUsuario();
			usuarioList.add(usuario);
			
			Usuario usuarioEnviado;
			
			when(usuarioRepository.findByCodigo(any())).thenReturn(usuarioList);
			
			// Act
			usuarioServiceImpl.actualizarEnviarContraseña(correo);
			
			// Assert
			verify(zatUsuarioService).update(usuarioCaptor.capture());
			verify(sendMail).sendNewPassword(eq(correo), any());
			usuarioEnviado = usuarioCaptor.getValue();
			
			assertAll(
					
					() -> assertEquals(correo, usuarioEnviado.getCodigo())
					
					);
			
		}
		
	}
	

}
