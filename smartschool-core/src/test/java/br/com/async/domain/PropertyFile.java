package br.com.async.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFile {
	
	private static Properties prop;
	
	public static Properties getInstance() throws IOException{
		if(prop == null){
			prop = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
			prop.load(in);
		}
		return prop;
	}
	

}
