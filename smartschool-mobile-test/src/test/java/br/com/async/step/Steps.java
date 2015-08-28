package br.com.async.step;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.masterthought.cucumber.ReportBuilder;

import org.junit.Assert;

import br.com.async.BaseTest;
import br.com.async.DesiredCapabilitiesBuilder;
import br.com.async.Driver;
import br.com.async.server.Server;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps extends BaseTest{
	
	AndroidDriver<AndroidElement> driver;
	protected Server server = new Server();
	
	@Before("@device")
	public void before() throws InterruptedException, IOException{
		quitDriver();
		driver = Driver.getDriver(DesiredCapabilitiesBuilder.Build());
	}
	
	@After("@device")
	public void after() throws Exception{
		quitDriver();
	}
	
	@Given("^I open AppTest$")
	public void i_open_app_test() throws Throwable{
		driver = Driver.getDriver(DesiredCapabilitiesBuilder.Build());
	}
	
	@When("^I fill name with \"(.*)\"$")
	public void i_fill_name_with(String name) throws Throwable{
		AndroidElement element = driver.findElementById("etName");
		element.sendKeys(name);
		
	}
	@When("^I fill music with \"(.*)\"")
	public void i_fill_music_with(String music) throws Throwable{
		AndroidElement element = driver.findElementById("etMusic");
		element.sendKeys(music);
	}
	
	@When("^I click on send button$")
	public void i_click_on() throws Throwable{
		AndroidElement element = driver.findElementById("btnSend");
		element.click();
	}
	
	@Then("I should see \"(.*)\"$")
	public void i_should_see(String text) throws Throwable{
//		String xpath = String.format("//TextView[@text=%s]", text);
//		driver.findElementByXPath(xpath);
//		Thread.sleep(1000);
		AndroidElement element = driver.findElementById("tvResult");
		Assert.assertEquals(text, element.getText());

		
	}
}
