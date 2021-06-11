package com.publica.grupo2sprint3.model.person.physicalperson;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;

public class PayrollTest {

	@Test
	public void getCollaboratorTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getIdTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getMonthTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getPriceTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setCollaboratorTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setIdTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setMonthTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setPriceTest() {
		throw new RuntimeException("Test not implemented");
	}
	
	@Test
	public void calculateSalaryBasedOnPointsRegisterTest() {
		Payroll p = new Payroll();
		p.setCollaborator(CollaboratorDAO.getInstance().findById(6));
		double d = p.calculateSalaryBasedOnPointsRegister(01,2021, 01, 20);
		Assert.assertEquals(d, 0.0);
	}
	
	
	@Test
	public void calculateSalaryBasedOnPointsRegister2Test() {
		Payroll p = new Payroll();
		p.setCollaborator(CollaboratorDAO.getInstance().findById(6));
		double d = p.calculateSalaryBasedOnPointsRegister(01,2021);
		Assert.assertEquals(d, 265.0);
	}
	

	@Test
	public void toStringTest() {
		LocalDate date = LocalDate.now();
		Role role = new Role("Desenvolvedor" , 1000);
		Collaborator collaborator = new Collaborator("Carlos", role, 0.00); 	
		Payroll payroll =  new Payroll(date, collaborator);
		
		Double salario = collaborator.getGrossSalary() - Discount.generateInss(collaborator.getGrossSalary());
		String data = String.format("%d/%d/%d", date.getDayOfMonth(), date.getMonth().getValue(), date.getYear());
		
		String result = String.format(""
				+ "Data: " + data + "\n"
				+ "Colaborador: Carlos\n"
				+ "Valor bruto: 1000,00\n"
				+ "INSS: -109,59\n"
				+ "Valor l�quido: %.2f\n", salario);
		
		Assert.assertEquals(payroll.toString(), result);
		
		collaborator.addDiscount("Desconto", -10.00);
		salario-= 10;
		
		String result2 = String.format(""
				+ "Data: " + data + "\n"
				+ "Colaborador: Carlos\n"
				+ "Valor bruto: 1000,00\n"
				+ "Desconto: -10,00\n"
				+ "INSS: -109,59\n"
				+ "Valor l�quido: %.2f\n", salario);
		
		Assert.assertEquals(payroll.toString(), result2);
		
		collaborator.addDiscount("Desconto2", -20.00);
		salario-= 20;
		
		String result3 = String.format(""
				+ "Data: " + data + "\n"
				+ "Colaborador: Carlos\n"
				+ "Valor bruto: 1000,00\n"
				+ "Desconto: -10,00\n"
				+ "INSS: -109,59\n"
				+ "Desconto2: -20,00\n"
				+ "Valor l�quido: %.2f\n", salario);
		
		Assert.assertEquals(payroll.toString(), result3);
	}

}























