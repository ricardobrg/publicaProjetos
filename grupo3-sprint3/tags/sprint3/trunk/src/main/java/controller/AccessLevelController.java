package controller;

import model.entities.security.AccessLevel;

/**
 * AccessLevelController. <br></br>
 * This class connects the model and the view,
 * and is used to communicate between classes in the model and view.
 * Searches for the Access Level and returns the list 
 * of currently available Levels.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class AccessLevelController {
	public static void listAll() {
		for (AccessLevel item : AccessLevel.values()) {
			System.out.println(item+" - "+item.getValue());
		}
	}
}
