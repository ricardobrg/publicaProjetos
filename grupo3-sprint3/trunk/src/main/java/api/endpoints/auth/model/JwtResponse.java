package api.endpoints.auth.model;

import java.io.Serializable;

/***
 *	@author https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 */
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	public String getToken() {
		return this.jwttoken;
	}
}