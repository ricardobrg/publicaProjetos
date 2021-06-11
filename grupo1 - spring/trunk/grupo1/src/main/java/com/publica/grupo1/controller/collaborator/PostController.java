package com.publica.grupo1.controller.collaborator;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.publica.grupo1.model.entities.collaborator.Collaborator;

public class PostController {
	
	public static void fillingCollaboratorFields(Collaborator collab) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if (collab.getAdmissionDate() == null)
			collab.setAdmissionDate(LocalDate.now());
		if (collab.getLastVacationDate() == null)
			collab.setLastVacationDate(collab.getAdmissionDate());
		
		collab.setPassword(passwordEncoder.encode(collab.getPassword()));
		
	}
}
