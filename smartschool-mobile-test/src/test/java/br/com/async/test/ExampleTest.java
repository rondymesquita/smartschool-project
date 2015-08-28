package br.com.async.test;

import io.appium.java_client.android.AndroidElement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.async.BaseTest;
import br.com.async.DesiredCapabilitiesBuilder;
import br.com.async.Driver;

/**
 * Created by rondymesquita on 27/08/2015
 *
 */
public class ExampleTest extends BaseTest{
	
	@Before
	public void before(){
		quitDriver();
		driver = Driver.getDriver(DesiredCapabilitiesBuilder.Build());
	}
	
	@After
	public void after(){
		quitDriver();
	}
	
	@Test
	public void shouldSeeAMessageWhenValuesAreInserted(){
//		driver.findElementById("etName").sendKeys("Rondy");
//		driver.findElementById("etMusic").sendKeys("Rock");
//		driver.findElementById("btnSend").click();
//		AndroidElement element = driver.findElementById("tvResult");
//		Assert.assertEquals("Rondy likes Rock", element.getText());
		
		findElementById("etName").sendKeys("Rondy");
		findElementById("etMusic").sendKeys("Rock");
		clickOn("btnSend");
		AndroidElement element = findElementById("tvResult");
		Assert.assertEquals("Rondy likes Rock", element.getText());
	}
}
