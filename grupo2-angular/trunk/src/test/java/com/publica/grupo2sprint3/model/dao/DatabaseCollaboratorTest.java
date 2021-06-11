package com.publica.grupo2sprint3.model.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.util.ToHash; 


public class DatabaseCollaboratorTest {
	DatabaseCollaborator db = DatabaseCollaborator.getInstance();
	@Test
	public void selectTest() throws SQLException {
		String query = "SELECT * FROM collaborator";
		
		Assert.assertEquals(db.select(query).get(0).get("admissionDate"), "2017-10-04");
	}

	@Test
	public void updateTest() throws SQLException {
		String vacationSize = "0", user_, password_, role, cpf;
		String admissionDate, lastVacation;
		byte inVacation, canVacation;
		double extraSalary;
		int workHours, accessLevel;	
		
		admissionDate = "2017-10-04"; 
		lastVacation = "2018-11-05"; 
		vacationSize = "1"; 
		inVacation = 1; 
		canVacation = 0; 
		extraSalary = 500.00;
		workHours = 8;
		user_ = "Admin"; 
		password_ = ToHash.hashGeneratorPbk(user_, "4789_!");
		role = "Suporte T�cnico";
		cpf = "199.413.830-06";
		accessLevel = 1;

		String query = "INSERT INTO collaborator VALUES ('"
				+ admissionDate + "','" + lastVacation + "'," 
				+ vacationSize +  "," + extraSalary + ",'" 
				+ user_ + "','" + password_ + "'," + inVacation + "," 
				+ canVacation + "," + workHours + ",'" 
				+ role + "','" + cpf + "'," + accessLevel +");";
		

		Assert.assertEquals(db.update(query),1);
	}

	@Test
	public void updateTest2() throws SQLException{
		String query = "UPDATE collaborator SET admissionDate = '2017-10-04' WHERE extraSalary = 500;";
		Assert.assertEquals(db.update(query), 1);
	}

	@Test 
	public void updateTest3()  throws SQLException {
		String query = "DELETE FROM collaborator WHERE cpf = '392.038.510-11';";
		Assert.assertEquals(db.update(query), 1);
	}
}
