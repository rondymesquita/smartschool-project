package br.com.async.server;

import java.io.IOException;

import br.com.async.Logger;
import br.com.utils.Console;

/**
 * Created by rondymesquita on 28/08/2015
 *
 */
public class Server {

	/**
	 * 
	 */
	private static final String APPIUM_STARTED_ON = "Appium REST http interface listener started on";
	private Thread thread;
	private int timeoutInSeconds = 30;
	private ServerRunnable runnable;
	
	public void startup() throws InterruptedException, IOException{
		runnable = new ServerRunnable();
		thread = new Thread(runnable);
		thread.start();
		
		int timeout = 0;
		while(!ServerRunnable.line.contains(APPIUM_STARTED_ON)){
			Thread.sleep(1000);
			Logger.logInfo("Waiting server to start!");
			ServerRunnable.isRunning = true;
			if(timeout++ == timeoutInSeconds){
				break;
			}
		}
		
		if(ServerRunnable.line.contains(APPIUM_STARTED_ON))
			Logger.logInfo("Server Started!");
		else
			shutdown();
	}
	
	public void shutdown() throws IOException {
		
		runnable.stop();
		
		if(thread.isAlive()){
			thread.stop();
			ServerRunnable.isRunning = false;
			Logger.logInfo("Server stopped!");
		}
	}
	
}
