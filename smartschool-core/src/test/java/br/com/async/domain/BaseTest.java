package br.com.async.domain;

import java.io.IOException;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

	@BeforeClass
	public static void beforeClass() throws IOException {
//		Properties prop = PropertyFile.getInstance();
//		prop.setProperty("postgres.url", "jdbc:postgresql://localhost:5432/smartschoolTest?charSet=utf8");
//		PropertyFile.save();
	}
	
	@AfterClass
	public static void afterClass() throws IOException {
//		Properties prop = PropertyFile.getInstance();
//		prop.setProperty("postgres.url", "jdbc:postgresql://localhost:5432/smartschool?charSet=utf8");
//		PropertyFile.save();
	}
	
}
