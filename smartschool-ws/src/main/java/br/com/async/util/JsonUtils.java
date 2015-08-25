package br.com.async.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * Created by rondymesquita on 24/08/2015
 *
 */
public class JsonUtils {

	public static String BLANK_STRING_REGEX = "(\\t|\\s|\\r|\\n)";
	
	public static String toJson(Object object, String replaceString) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(object).replaceAll(replaceString, "");
		return json;
	}
	
	public static String toJson(Object object) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(object);
		return json;
	}
	
	public static Object toObject(String json, Class<?> clazz) throws JsonProcessingException, IOException{
		ObjectReader or = new ObjectMapper().reader().withType(clazz);
		return or.readValue(json);
	}
	
}
