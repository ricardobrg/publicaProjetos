package com.publica.grupo2sprint3.model.dto.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo2sprint3.controller.PointController;
import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Point;

/***
 * Class PointResource (API Restfull) <br>
 * 
 * This class (Point Resource) calls the controller (PointController) for the
 * methods bellow to be able to perform the necessary actions.
 * 
 * @version 1.0.0
 * 
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 */
@CrossOrigin
@RestController
@RequestMapping("/collaborator/{id}/point")
public class PointResource {
	PointController controller = PointController.getInstance(new Collaborator());
	ResponseEntity<Object> responseEntity;
	Map<String, String> responseObj = new HashMap<>();

	/**
	 * Adds a new point automatically in database. <br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @return
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			Collaborator collab = CollaboratorDAO.getInstance().findById(Integer.parseInt(id));
			Boolean result = controller.add(collab);

			if (result == false)
				throw new Exception("Erro ao adicionar no banco de dados!");

			responseObj.put("Processamento realizado", "Inserido no banco de dados");
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * Search for a point by its id.<br>
	 * 
	 * If the point is null, an exception will be thrown. Else, the point object
	 * found will be returned.
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id int : point's id.
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();
		try {
			Point point = (Point) controller.find(Integer.parseInt(id));

			if (point == null) {
				responseObj.put("Erro", "Id N�o encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(point, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * Remove a point by its id. <br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id : point's id.
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			boolean bool = controller.remove(Integer.parseInt(id));

			responseObj.put("Sucesso", "Deletado do banco de dados");
			responseObj.put("id", String.valueOf(bool));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	/**
	 * Change the point DayMinute by its id. <br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id : int : Point's id
	 * @param point : Object
	 * @return
	 */
	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Point point) {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			Point pointFound = (Point) controller.find(Integer.parseInt(id));
			if (pointFound == null) {
				responseObj.put("NOT OK", "O ID informado não foi encontrado.");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (pointFound.getDayMinute() == point.getDayMinute()) {
				responseObj.put("OK", "Os minutos são iguais, alteração não necessária");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NO_CONTENT);

			} else {
				point.setId(Integer.parseInt(id));
				Boolean result = controller.editPointHour(pointFound, point.getDayMinute());
				
				if (!result) {
					responseObj.put("NOT OK", "Não foi possível atualizar o ponto");

				} else {
					responseObj.put("OK", "Ponto atualizado");
					responseObj.put("id", id);
					responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
				}
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * Return all existing Points of a collaborator. <br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @return an ArrayList of Points.
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll() {
		Map<String, String> responseObj = new HashMap<>();

		try {
			ArrayList<Point> points = controller.getAllPoints();
			responseEntity = new ResponseEntity<>(points, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
}