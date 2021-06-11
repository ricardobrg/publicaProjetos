package api.endpoints.auth.service;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import models.entities.person.Collaborator;
import models.hibernate.CollaboratorHibernate;

/***
 *	@author https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		
		CollaboratorHibernate collabDAO = new CollaboratorHibernate();
		
		Collaborator collab = (Collaborator) collabDAO.find("cpf", cpf);
		
		if (collab != null) {
			return new User(cpf, collab.getPwdHash(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + cpf);
		}	
	}
}