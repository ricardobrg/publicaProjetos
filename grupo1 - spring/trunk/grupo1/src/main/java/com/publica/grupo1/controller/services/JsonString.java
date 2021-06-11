package com.publica.grupo1.controller.services;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonString {
	
	/**
	 * Convertes Json to String
	 *  
	 * @param bufferedReader : it has the reading data from the Json 
	 * @return formated string
	 * @throws IOException
	 * 
	 * @author Ana Caroline
	 */
	public static String parseJsonToString(BufferedReader bufferedReader) throws IOException{
		String answer, jsonToString = "";
		while((answer = bufferedReader.readLine()) != null) {
			jsonToString += answer;
		}
		
		return jsonToString;
	}	
}
