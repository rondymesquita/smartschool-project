package br.com.prisma.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author rondymesquita
 * 
 */
public class Encrypt {
	
	
	/**
	 * @param value
	 * @return
	 * @throws NoSuchAlgorithmException
	 * 
	 * M�todo que retorna o hash MD5 do par�mentro informado
	 */
	public static String md5(String value) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		 md.update(value.getBytes());  
		 BigInteger hash = new BigInteger( 1, md.digest() );  
		 String str = hash.toString( 16 );
		return str;
	}
	
}
