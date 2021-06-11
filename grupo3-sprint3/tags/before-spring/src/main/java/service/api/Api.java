package service.api;

import javax.ws.rs.core.Response;

import model.entities.security.AccessLevel;

public abstract class Api {

	public abstract Response index();
	public abstract Response insert(Object object);
	public abstract Response update(String id, Object object);
	public abstract Response delete(String id);
	public abstract Response find(String id);
	
	public AccessLevel auth() {
	  return AccessLevel.TOTAL;
	}
}
