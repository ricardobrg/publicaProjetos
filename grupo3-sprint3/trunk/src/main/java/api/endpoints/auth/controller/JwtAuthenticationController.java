package api.endpoints.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.endpoints.auth.config.JwtTokenUtil;
import api.endpoints.auth.model.JwtRequest;
import api.endpoints.auth.service.JwtUserDetailsService;
import controllers.CollaboratorController;

/***
 *	@author https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	Map<String, String> responseObj = new HashMap<>();
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		responseObj.put("token", jwtTokenUtil.generateToken(userDetails));
		responseObj.put("name", new CollaboratorController().findByCpf(authenticationRequest.getUsername()).getName());
		responseObj.put("cpf", new CollaboratorController().findByCpf(authenticationRequest.getUsername()).getCpf());
		responseObj.put("accessLevel", new CollaboratorController().findByCpf(authenticationRequest.getUsername()).getRole().getAccessLevel().getValue() + "");
		
		return ResponseEntity.ok(responseObj);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}