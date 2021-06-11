package com.publica.grupo1.restapi.routes.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageGenerator {
	
	public ResponseEntity<Object> generate(Map<String, String> responseObj, HttpStatus status) {
		responseObj.put("error", status.toString());
		return new ResponseEntity(responseObj, status);
	}
	
	public ResponseEntity<Object> generateNotFoundError(String message){
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("message", message);
		
		return generate(responseObj, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Object> generateIdNotFoundError(){
		return generateNotFoundError("ID not found");
	}
	
	public ResponseEntity<Object> generateEmptyListError(){
		return generateNotFoundError("Empty list");
	}
	
	public ResponseEntity<Object> generateUnauthorizedError(){
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("message", HttpStatus.UNAUTHORIZED.toString());
		
		return generate(responseObj, HttpStatus.UNAUTHORIZED);
	}
	
	public ResponseEntity<Object> generateInsertedObjectMessage(int id){
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("id inserted", String.valueOf(id));
		
		return generate(responseObj, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> generateUpdateSuccessMessage(int id){
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("updated object with id", String.valueOf(id));
		
		return generate(responseObj, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> generateDeleteSuccessMessage(int id){
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("deleted object with id", String.valueOf(id));
		
		return generate(responseObj, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> generateVacationMessage(Map<String, String> responseObj){
		return generate(responseObj, HttpStatus.OK);
	}
}
