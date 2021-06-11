package com.publica.grupo2sprint3.auth.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.auth.service.JwtUserDetailsService;
import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

public class JwtTokenUtilTest {

	JwtTokenUtil utilToken = new JwtTokenUtil();
	final String CPF = "122.923.519-19";
	private JwtUserDetailsService userDetailsService = new JwtUserDetailsService();
	
	@Test
	public void getCollaboratorWithTokenTest() {
		final UserDetails userDetails = userDetailsService.loadUserByUsername(CPF);
		final String token = utilToken.generateToken(userDetails);
		
		Collaborator foundCollabByTokenUtil = utilToken.getCollaboratorWithToken(token);
		Collaborator foundCollabByDAO = (Collaborator) CollaboratorDAO.getInstance().findByIdentifier(CPF);
		
		Assert.assertEquals(foundCollabByTokenUtil.getId(), foundCollabByDAO.getId());
		
	}
}
