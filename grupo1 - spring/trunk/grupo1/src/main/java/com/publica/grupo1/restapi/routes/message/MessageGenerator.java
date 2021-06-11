package com.publica.grupo1.restapi.routes.message;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/***
 * Class for common JSON messages in endpoints.
 * 
 * @author Diego Leon
 */
public class MessageGenerator {
	/***
	 * generate a JSON message with the map object and a HTTP message.
	 * 
	 * @param responseObj info in JSON.
	 * @param status      HTTP message.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generate(Map<String, String> responseObj, HttpStatus status) {
		responseObj.put("message", status.toString());
		return new ResponseEntity<Object>(responseObj, status);
	}
<<<<<<< .mine

	/***
	 * generate a JSON message with the map object and a HTTP message. This will
	 * send a OK HTTP message in JSON.
	 * 
	 * @param object to show in the message.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generateInsertedObjectMessage(Object object) {
||||||| .r27
	
	public ResponseEntity<Object> generateInsertedObjectMessage(Object object){
		Map<String, String> responseObj = new HashMap<>();
		
=======
	
	public ResponseEntity<Object> generateInsertedObjectMessage(Object object){
>>>>>>> .r29
		return new ResponseEntity<>(object, HttpStatus.OK);
	}
<<<<<<< .mine

	/***
	 * generate a JSON message with the map object and a HTTP message. This will
	 * send a OK HTTP message in JSON.
	 * 
	 * @param object to show in the message.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generateUpdateSuccessMessage(Object object) {
||||||| .r27
	
	public ResponseEntity<Object> generateUpdateSuccessMessage(Object object){
		Map<String, String> responseObj = new HashMap<>();
		
=======
	
	public ResponseEntity<Object> generateUpdateSuccessMessage(Object object){
>>>>>>> .r29
		return new ResponseEntity<>(object, HttpStatus.OK);
	}

	/***
	 * generate a JSON message with the map object and a HTTP message. This will
	 * send a NOT FOUND HTTP message in JSON.
	 * 
	 * @param message String to show in the message.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generateNotFoundError(String message) {
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("message", message);

		return generate(responseObj, HttpStatus.NOT_FOUND);
	}

	/***
	 * generate a JSON message for ID not found and a HTTP NOT FOUND HTTP message in
	 * JSON.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generateIdNotFoundError() {
		return generateNotFoundError("ID not found");
	}

	/***
	 * generate a JSON message for empty list and a HTTP NOT FOUND HTTP message in
	 * JSON.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generateEmptyListError() {
		return generateNotFoundError("Empty list");
	}

	/***
	 * generate a JSON message for unauthorized.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generateUnauthorizedError() {
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("message", HttpStatus.UNAUTHORIZED.toString());

		return generate(responseObj, HttpStatus.UNAUTHORIZED);
	}

	/***
	 * generate a JSON message for delete succefull operationand a HTTP OK HTTP
	 * message in JSON.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generateDeleteSuccessMessage(int id) {
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("deleted object with id", String.valueOf(id));

		return generate(responseObj, HttpStatus.OK);
	}

	/***
	 * generate a genereic JSON message with a HTTP OK message in JSON.
	 * 
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> generateOkMessage(Map<String, String> responseObj) {
		return generate(responseObj, HttpStatus.OK);
	}
}
