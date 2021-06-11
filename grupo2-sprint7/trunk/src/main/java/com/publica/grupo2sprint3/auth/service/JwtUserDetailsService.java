package com.publica.grupo2sprint3.auth.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		
		CollaboratorDAO collabDao = CollaboratorDAO.getInstance();
		Collaborator foundCollab = (Collaborator)collabDao.findByIdentifier(cpf);		

		if (foundCollab != null) {
			return new User(cpf, foundCollab.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with the given cpf");
		}
	}
	
	
}
