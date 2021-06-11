package com.publica.grupo1.restapi.routes.security;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.collaborator.CollaboratorDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		MessageGenerator messageGenerator = new MessageGenerator();
		Map<String, String> responseObj = new HashMap<>();

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);

			Collaborator collab = getCollaborator(authenticationRequest.getUsername());

			responseObj.put("accessLevel", collab.getPermission().getAccessLevel().toString());
			responseObj.put("id", String.valueOf(collab.getIdCollaborator()));
			responseObj.put("token", token);

			return messageGenerator.generate(responseObj, HttpStatus.OK);

		} catch (Exception e) {
			return messageGenerator.generateIdNotFoundError();
		}
	}

	private Collaborator getCollaborator(String username) {
		Session session = DBConnection.getSession();
		return CollaboratorDAO.getInstance(session).selectByCPF(username);
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
