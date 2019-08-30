/**
 * Data: 24 de ago de 2019
 */
package br.teresafernandes.evoluaserver.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author Teresa Fernandes
 *
 */
public class StringUtils {

	public static String toMD5(String texto) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(texto.getBytes(), 0, texto.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String stringAleatoria() {
		Random random = new Random();
		return new BigInteger(130, random).toString(32);
	}

}
