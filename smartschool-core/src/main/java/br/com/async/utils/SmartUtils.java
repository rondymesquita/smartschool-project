package br.com.async.utils;

public class SmartUtils {

public static Integer StringToInteger(String text){
    	
    	try{
    		return Integer.parseInt(text);
    	}catch(Exception e){
    		return -1;
    	}
    	
    }
	
}
