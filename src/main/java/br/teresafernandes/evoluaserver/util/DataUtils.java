package br.teresafernandes.evoluaserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Teresa Fernandes
 * */

public class DataUtils {

	
	public static String dataToString(Date data){
		return dataToString(data, "dd/MM/yyyy");
	}
	
	public static String dataToString(Date data, String formato){
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String dataFormatoBanco = sdf.format(data);
		return dataFormatoBanco;
	}
	
	public static Date stringToData(String data){
		if(data == null) {
			return null;
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
			Date dataFormatoBanco = sdf.parse(data);
			return dataFormatoBanco;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
