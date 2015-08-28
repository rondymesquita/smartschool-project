package br.com.async;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import br.com.async.server.ServerRunnable;

/**
 * Created by rondymesquita on Aug 1, 2015
 *
 */
public class Driver {
	
	private static AndroidDriver<AndroidElement> driver;
//	public static String APP = "http://0.0.0.0:4723/wd/hub/";
	public static String APP = "http://"+ServerRunnable.ip+":"+ServerRunnable.port+"/wd/hub/";
	
	/**
	 * @return
	 */
	public static AndroidDriver<AndroidElement> getDriver(DesiredCapabilities capabilities) {
		try {
			
			if(driver == null)
				driver = new AndroidDriver<AndroidElement>(new URL(APP), capabilities);
			
			return driver;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
