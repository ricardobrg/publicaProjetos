package model.entities.security;

/**
 * This class provides pre-defined values for controlling access level.
 * It will be used for checking if a certain type of user could make some
 * actions inside the system.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
 *
 */
public enum AccessLevel {
	BASIC(1),
	MEDIUM(2),
	TOTAL(3);

	private final int value;
	
	AccessLevel(int i) {
		this.value = i;
	}
	
	public int getValue() {
		return value;
	}
	
}