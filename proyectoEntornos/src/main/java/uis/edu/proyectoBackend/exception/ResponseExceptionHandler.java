package uis.edu.proyectoBackend.exception;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);
	
	@ExceptionHandler (Exception.class)
	//Método genérico para todas las excepciones
	public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception e) {
		
		//Un exception response contiene el tiempo cuando pasó, el mensaje, y los detalles
		ExceptionResponse exResp = new ExceptionResponse(
														 new Date(),
														 "Ocurrió un error",
														 e.getMessage()
														);
		return new ResponseEntity<>(exResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	//Se sobreescribe la respuesta dada cuando un argumento de método es inválido.
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest webRequest){
		
		//Se crea un map de errores y mensajes
		Map<String, String> errors = new HashMap<>();
		
		//Por cada error cometido:
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			//Consiga el nombre del error y asígnelo a fieldName
			String fieldName = ((FieldError) error).getField();
			
			//Consiga el mensaje del error y asígnelo a errorMessage
			String errorMessage = error.getDefaultMessage();
			
			//Añádalos al map
			errors.put(fieldName, errorMessage);	
		});
		
		//Ahora, se crea el exResp como se hizo en el método anterior, con un cambio
		ExceptionResponse exResp = new ExceptionResponse(
			//Fecha del error
			new Date(),
			//Mensaje genérico
			"Ocurrió un error",
			//Mensaje del MethodArgumentNotValidException
			ex.getMessage(),
			//Y, finalmente, el map de errores y mensajes que se creó
			errors
		);
		return new ResponseEntity<>(exResp, HttpStatus.BAD_REQUEST);
	}
}
