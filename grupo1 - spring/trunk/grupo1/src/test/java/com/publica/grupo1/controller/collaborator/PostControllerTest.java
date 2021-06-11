package com.publica.grupo1.controller.collaborator;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo1.model.entities.collaborator.Collaborator;


public class PostControllerTest {
	Collaborator collab = new Collaborator();
	
	@Test
	public void fillingCollaboratorFieldsTest() {
		collab.setName("Nome Teste");
		collab.setCpf("117.762.604-73");
		PostController.fillingCollaboratorFields(collab);
		Assert.assertEquals(collab.getAdmissionDate(), LocalDate.now());
		Assert.assertEquals(collab.getLastVacationDate(), LocalDate.now());
	}
}
