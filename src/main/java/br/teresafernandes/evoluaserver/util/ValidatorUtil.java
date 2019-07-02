package br.teresafernandes.evoluaserver.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.teresafernandes.evoluaserver.dominio.EntidadePersistente;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;


public class ValidatorUtil {

	public static void validate(String campo, Object value) throws ServiceBusinessException{
		if(ValidatorUtil.isEmpty(value))
			throw new ServiceBusinessException(campo+": Campo obrigatório não informado.");
	}
	
	public static void validate(String campo, Object value, List<String> erros){
		try {
			validate(campo, value);
		} catch (ServiceBusinessException e) {
			erros.add(e.getMessage());
		}
	}
	
	public static boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object value) {
		if(value == null)
			return true;
		else if(value instanceof String && ((String) value).isEmpty())
			return true;
		else if(value instanceof List && ((ArrayList) value).isEmpty())
			return true;
		else if(value instanceof BigDecimal && ((BigDecimal) value).compareTo(BigDecimal.ZERO) == 0)
			return true;
		else if(value instanceof Double && ((Double) value) == 0)
			return true;
		else if(value instanceof Integer && ((Integer) value) == 0)
			return true;		
		else if (value instanceof EntidadePersistente){
			Object obj = ReflectionUtil.executeMethod(value, "getId");
			 if(obj == null || ((Long) obj).compareTo(0L) <= 0)
				 return true;
		}
		return false;
	}

}
