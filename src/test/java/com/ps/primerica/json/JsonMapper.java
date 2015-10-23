package com.ps.primerica.json;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonMapper {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public <T> T getObjectFromJson(String filePath, Class<T> type ) {
    	T object = null;
       	InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
    	try {
    		object = mapper.readValue(inputStream, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return object;
    }
}