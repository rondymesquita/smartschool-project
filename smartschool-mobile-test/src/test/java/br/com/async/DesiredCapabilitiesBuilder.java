package br.com.async;

import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by rondymesquita on Aug 1, 2015
 *
 */
public class DesiredCapabilitiesBuilder {

	private static final String APP_ACTIVITY = "br.com.async.apptest.MainActivity";
	private static final String APP_PACKAGE = "br.com.async.apptest";
	private static final String PLATFORM_NAME = "Android";
	private static final String DEVICE_NAME = "MotoX";
	private static DesiredCapabilities desiredCapabilities;

	/**
	 * @return
	 */
	public static DesiredCapabilities Build() {
		
		if(desiredCapabilities == null){
			File appDir = new File("../");
			File appFile = new File(appDir, "AppTest.apk");
	
			desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
			desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
			desiredCapabilities.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
			desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "5.1");
			
			desiredCapabilities.setCapability(MobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
			desiredCapabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
		}
		Logger.logInfo("Capabilities Configured!");
		return desiredCapabilities;
	}

}
