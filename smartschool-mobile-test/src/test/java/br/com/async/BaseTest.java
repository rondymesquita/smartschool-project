package br.com.async;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import br.com.async.server.Server;

/**
 * Created by rondymesquita on 27/08/2015
 *
 */
public class BaseTest {

	protected AndroidDriver<AndroidElement> driver;
	
//	@BeforeClass
//	public static void beforeClass() throws InterruptedException, IOException {
//		server.startup();
//	}
//	
//	@AfterClass
//	public static void afterClass() throws IOException {
//		server.shutdown();
//	}
	
	/*
	 * 
	 */
	protected void quitDriver(){
		if(driver != null){
			driver.closeApp();
			driver.quit();
		}
	}
	
//	protected AndroidElement findElementById(String id){
//		Logger.logInfo("findElementById : "+id);
//		return driver.findElementById(id);
//	}
//	
//	protected void clickOn(String id){
//		Logger.logInfo("clickOn : "+id);
//		driver.findElementById(id).click();
//	}
	
}
