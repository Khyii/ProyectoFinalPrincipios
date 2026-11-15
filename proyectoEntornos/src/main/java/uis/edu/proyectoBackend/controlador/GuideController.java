package uis.edu.proyectoBackend.controlador;

import java.util.List;
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
import uis.edu.proyectoBackend.modelo.Guide;
import uis.edu.proyectoBackend.servicio.GuideService;

@RestController
@RequestMapping("api/guias")
@Tag(name = "Controlador de guias")
public class GuideController {

	@Autowired
	private GuideService gService;
	
	
	/*************************************
	 *************************************
	 ***							   ***
	 ***    Mapeo para petición GET    ***
	 ***							   ***
	 *************************************
	 *************************************/
	
	@Operation(summary = "Obtiene todas las guias")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Guias recuperadas exitosamente")
	})
	@GetMapping
	public ResponseEntity<List<Guide>> findAll() {
		return ResponseEntity.ok(gService.findAll());
	}
	
	
	/*************************************
	 *************************************
	 ***							   ***
	 ***    Mapeo para petición POST   ***
	 ***							   ***
	 *************************************
	 *************************************/
	
	@Operation(summary = "Crea una nueva guia")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "201",
						 description = "guia añadida/editada exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación")
	})
	@PostMapping
	public ResponseEntity<Guide> create(@Valid @RequestBody Guide guide) {
		return new ResponseEntity<>(gService.create(guide), HttpStatus.CREATED);
	}
	
	
	/*************************************
	 *************************************
	 ***							   ***
	 ***    Mapeo para petición PUT    ***
	 ***							   ***
	 *************************************
	 *************************************/

	@Operation(summary = "Edita una guia, identificada por su ID")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Guia editada exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación"),
			@ApiResponse(responseCode = "404", description = "Guia no encontrada")
	})
	@PutMapping
	public ResponseEntity<Guide> update(@Valid @RequestBody Guide guide) {
		return gService.findById(guide.getGuideId())
				.map(c -> ResponseEntity.ok(gService.update(guide)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	/**************************************************
	 **************************************************
	 ***											***
	 ***    Mapeo para petición GET con username    ***
	 ***											***
	 **************************************************
	 **************************************************/

	@Operation(summary = "Obtiene una guia por su ID")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Guia recuperada exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación"),
			@ApiResponse(responseCode = "404", description = "Guia no encontrada")
	})
	@GetMapping("/{guideId}")
	public ResponseEntity<Guide> create(
			@Parameter( description = "ID de la guia a recuperar")
			@PathVariable String guideId) {
		return gService.findById(guideId)
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

	@Operation(summary = "Elimina una guia, identificada por su ID")
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Guia eliminada exitosamente"),
			@ApiResponse(responseCode = "400",
			             description = "Solicitud rechazada por fallos de validación"),
			@ApiResponse(responseCode = "404", description = "Guia no encontrada")
	})
	@DeleteMapping("/{guideId}")
	public ResponseEntity<Guide> delete(
			@Parameter( description = "ID de la guia a eliminar")
			@PathVariable("guideId") String gId) {
		return gService.findById(gId)
				.map(c -> {
					gService.delete(gId);
					return ResponseEntity.ok(c);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
