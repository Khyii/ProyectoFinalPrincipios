package uis.edu.proyectoBackend.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import uis.edu.proyectoBackend.modelo.LoginDto;
import uis.edu.proyectoBackend.modelo.User;
import uis.edu.proyectoBackend.servicio.UserService;

@RestController
@RequestMapping("api/usuarios")
@Tag(name = "Controlador de usuarios")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	
	/*************************************
	 *************************************
	 ***							   ***
	 ***    Mapeo para petición GET    ***
	 ***							   ***
	 *************************************
	 *************************************/
	
	@Operation(summary = "Obtiene todos los usuarios")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Usuarios recuperados exitosamente")
	})
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(uService.findAll());
	}
	
	
	/*************************************
	 *************************************
	 ***							   ***
	 ***    Mapeo para petición POST   ***
	 ***							   ***
	 *************************************
	 *************************************/
	
	@Operation(summary = "Crea un nuevo usuario")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "201",
						 description = "Usuario añadido/editado exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación")
	})
	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody User user) {
		return new ResponseEntity<>(uService.create(user), HttpStatus.CREATED);
	}
	
	
	/*************************************
	 *************************************
	 ***							   ***
	 ***    Mapeo para petición PUT    ***
	 ***							   ***
	 *************************************
	 *************************************/

	@Operation(summary = "Edita un usuario, identificado por su username")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Usuario editado exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación"),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	@PutMapping
	public ResponseEntity<User> update(@Valid @RequestBody User user) {
		return uService.findById(user.getUsername())
				.map(c -> ResponseEntity.ok(uService.update(user)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	/**************************************************
	 **************************************************
	 ***											***
	 ***    Mapeo para petición GET con username    ***
	 ***											***
	 **************************************************
	 **************************************************/

	@Operation(summary = "Obtiene un usuario por su username")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Usuario recuperado exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación"),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	@GetMapping("/{username}")
	public ResponseEntity<User> create(
			@Parameter( description = "username del usuario a recuperar")
			@PathVariable String username) {
		return uService.findById(username)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	/*****************************************************
	 *****************************************************
	 ***											   ***
	 ***    Mapeo para petición DELETE con username    ***
	 ***											   ***
	 *****************************************************
	 *****************************************************/

	@Operation(summary = "Elimina un usuario, identificado por su username")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación"),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	@DeleteMapping("/{username}")
	public ResponseEntity<User> delete(
			@Parameter( description = "Username del usuario a eliminar")
			@PathVariable("username") String uName) {
		return uService.findById(uName)
				.map(c -> {
					uService.delete(uName);
					return ResponseEntity.ok(c);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	/**********************************************
	 **********************************************
	 ***										***
	 ***    Mapeo para validación de usuario    ***
	 ***										***
	 **********************************************
	 **********************************************/

	@Operation(summary = "Admite o rechaza un login en base a username y usuario")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Usuario juzgado exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación"),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	@PutMapping("/login")
	public ResponseEntity<String> loginValidate(@Valid @RequestBody User user) {
		String respuesta = uService.loginValidate(user);
		return ResponseEntity.ok(respuesta);
	}
}
