package com.publica.grupo2sprint3.auth.controller;

import java.util.HashMap;

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

import com.publica.grupo2sprint3.auth.model.JwtRequest;
import com.publica.grupo2sprint3.auth.model.JwtResponse;
import com.publica.grupo2sprint3.auth.service.JwtUserDetailsService;
import com.publica.grupo2sprint3.auth.util.JwtTokenUtil;
import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

@CrossOrigin(origins = "*")
@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest loginData) throws Exception {
		authenticate(loginData.getCpf(), loginData.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginData.getCpf());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		Collaborator foundCollab = (Collaborator) CollaboratorDAO.getInstance().findByIdentifier(loginData.getCpf());
		HashMap<String, Object> responseObj = new HashMap<String, Object>();
		responseObj.put("token", new JwtResponse(token));
		responseObj.put("loggedObject", foundCollab);
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