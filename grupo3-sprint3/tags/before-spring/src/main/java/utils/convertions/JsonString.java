package utils.convertions;

import java.io.BufferedReader;
import java.io.IOException;

/***
 * This class contains a method to convert json to String.
 * 
 * @version 0.1.1
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
public class JsonString {
	
	/***
	 * This method takes in a BufferedReader and returns everything
	 * in a String.
	 *
	 * @param bufferedReader
	 * @return <code>String</code>
	 * @throws IOException
	 */
	public static String converteJsonEmString(BufferedReader bufferedReader) throws IOException{
		String resposta, jsonEmString = "";
		while((resposta = bufferedReader.readLine()) != null) {
			jsonEmString += resposta;
		}
		return jsonEmString;
	}	
}
