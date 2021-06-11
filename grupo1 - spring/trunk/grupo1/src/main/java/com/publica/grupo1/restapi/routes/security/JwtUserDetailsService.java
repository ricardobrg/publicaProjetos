package com.publica.grupo1.restapi.routes.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.collaborator.CollaboratorDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		
		CollaboratorDAO collabDAO = CollaboratorDAO.getInstance(DBConnection.getSession());
		
		Collaborator collab = collabDAO.selectByCPF(cpf);
		
		if (collab != null) {
			return new User(cpf, collab.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + cpf);
		}	
	}
}