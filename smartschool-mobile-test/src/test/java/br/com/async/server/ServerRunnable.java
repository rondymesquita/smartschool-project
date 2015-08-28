package br.com.async.server;

import java.io.IOException;
import java.util.Random;

import br.com.async.Logger;
import br.com.utils.Console;

/**
 * Created by rondymesquita on 28/08/2015
 *
 */
public class ServerRunnable implements Runnable{
	
	public static volatile boolean isRunning = false;
	public static volatile String line = "";
	public static volatile String ip = "0.0.0.0";
	public static volatile String port = "4723";
	
	@Override
	public void run() {
		try {
			port = generatePort();
			String command = "appium -a "+ip+" -p "+port;
			Console console = new Console();
			console.executeCommand(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(10);
	}
	
	private String generatePort(){
		String port = "";
		
		for (int i = 0; i < 4; i++) {
			port = port + getRandomNumber();
		}
		
		Logger.logInfo("Port: "+port);
		return port;
	}

	/**
	 * 
	 */
	public void stop() {
		try {
			Console console = new Console();
			console.executeCommand("killall node");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
