package GerenciaPropostas.com.api.infra.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException exception) {
		var erros = exception.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity tratarSqlException(SQLIntegrityConstraintViolationException exception) {
		var mensagem = exception.getMessage();
//		System.out.println("tratarSqlException: "+mensagem);
		return ResponseEntity.badRequest().body(mensagem);
	}
	
	private record DadosErrosValidacao(String campo, String mensagem) {
		public DadosErrosValidacao(FieldError fieldError) {
			this(fieldError.getField(),fieldError.getDefaultMessage());
		}
	}
	
	
	
}
