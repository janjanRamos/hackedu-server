/**
 * Data: 30 de jun de 2019
 */
package br.teresafernandes.evoluaserver.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * @author Teresa Fernandes
 *
 */
public class EvoluaException extends Exception{
    private static final long serialVersionUID = 1L;
    
	private HttpStatus status;
    private List<String> errors;
    
    public EvoluaException(String error) {
    	super(error);
    	errors = Arrays.asList(error);
        status = HttpStatus.BAD_REQUEST;
    }
 
    public EvoluaException(List<String> errors) {
        super(concatenaListaErros(errors));
        this.errors = errors;
        status = HttpStatus.BAD_REQUEST;
    }
    
    private static String concatenaListaErros(List<String> listaErrors) {
    	String stringErros = "";
    	if(listaErrors != null) {
    		for(String e: listaErrors) {
    			if(!stringErros.isEmpty()) {
    				stringErros += "\n";
    			}
    			stringErros += e;
    		}
    	}
    	return stringErros;
    }
 
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return super.getMessage();
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
    
    
}
