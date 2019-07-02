
package br.teresafernandes.evoluaserver.exception;

import java.util.List;

/**
 * @author Teresa Fernandes
 * */

public class ServiceBusinessException extends EvoluaException {

    private static final long serialVersionUID = 1L;

    public ServiceBusinessException(String message) {
        super(message);
    }
    
    public ServiceBusinessException(List<String> messages) {
    	super(messages);
    }

}
