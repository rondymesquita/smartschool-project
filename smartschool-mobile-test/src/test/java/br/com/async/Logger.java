package br.com.async;

import java.util.logging.Level;

/**
 * Created by rondymesquita on 27/08/2015
 *
 */
public class Logger {
	
	private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Logger.class");
	
	public static void logInfo(String msg){
		//System.out.println(msg);
		logger.log(Level.INFO, msg);
	}
	
	public static void logSevere(String msg){
		//System.out.println(msg);
		logger.log(Level.SEVERE, msg);
	}

}
