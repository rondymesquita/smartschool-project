package br.com.async.domain;

import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;

import org.junit.Test;

import br.com.async.utils.Encrypt;

public class EncryptTest extends BaseTest{

	@Test
	public void encryptTest() throws NoSuchAlgorithmException{
		Encrypt encrypt = new Encrypt();		
		Assert.assertEquals("8b43374e4b7bacecb0752fd270212476", encrypt.md5("smartschool"));
	}
	
}
