package br.com.async.domain.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyFile {

	private static Properties prop;
	private static FileOutputStream outputStream;
	private static InputStream inputStream;
	private static File file;

//	public static Properties getInstance() throws IOException {
//		if (prop == null) {
//			prop = new Properties();
////			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
//			file = new File("src/main/resources/application.properties");
//			inputStream = new FileInputStream(file);
//			String str = getStringFromInputStream(inputStream);
//			inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
//			prop.load(inputStream);
//		}
//		return prop;
//	}
	public static Properties getInstance() throws IOException {
		if (prop == null) {
			file = new File("src/main/resources/application.properties");
			prop = new Properties();
			inputStream = new FileInputStream(file);
			prop.load(inputStream);
		}
		return prop;
	}

	public static void save() throws FileNotFoundException {

		try {
			outputStream = new FileOutputStream(file);
			prop.store(outputStream, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}

}
