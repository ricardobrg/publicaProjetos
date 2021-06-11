package com.publica.grupo2sprint3.model.person.physicalperson;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class CollaboratorTest {

	Collaborator objectTest = new Collaborator();
	Department department = new Department("Deus");

	@BeforeTest
	public void setTest() {
		objectTest.setCpf("708.150.920-75");
		objectTest.setName("Batman2");
		objectTest.setGrossSalary(3000.0);
	}

	@Test
	public void workedMinutesInDayTest() {
//		Point point1 = new Point(objectTest, "20/01/2021", 480);
//		Point point2 = new Point(objectTest,"20/01/2021", 720);
//		Point point3 = new Point(objectTest,"20/01/2021", 780);
//		Point point4 = new Point(objectTest,"20/01/2021", 1080);

		Assert.assertEquals(objectTest.workedMinutesInDay("01/01/2021"), 9 * 60);
		Assert.assertNotNull(objectTest.workedMinutesInDay("01/01/2021"));
	}

	@Test
	public void workedMinutesInMonthTest() {
		Assert.assertEquals(objectTest.workedMinutesInMonth(01, 2021), 1620);
		Assert.assertNotEquals(objectTest.workedMinutesInMonth(01, 2021), 0);
	}

	@Test
	public void workedMinutesInYearTest() {
		Assert.assertEquals(objectTest.workedMinutesInYear(2021), 2160);
		//Assert.assertNotEquals(objectTest.workedMinutesInYear(1, 2021), 0);
	}
	
	@Test
	public void calculateSalaryBasedOnPointsRegisterTest() {
		int totalMinutes = 9540; 

		double baseSalary = 3000;
		int hoursByWeek = 40;

		double salaryByMinute = (baseSalary / (hoursByWeek * 4)) / 60;
		double salaryResult = totalMinutes * salaryByMinute;

		System.out.println(salaryResult);
		Assert.assertEquals(salaryResult, 2981.25);
	}
	

	@Test
	public void setAdmissionDateTest() {
		objectTest.setAdmissionDate("18/02/1998");
		Assert.assertEquals(objectTest.getAdmissionDate(), "18/02/1998");
		objectTest.setAdmissionDate("32/13/1998");
		Assert.assertEquals(objectTest.getAdmissionDate(), "18/02/1998");
	}

	@Test
	public void getAdmissionDateTest() {
		objectTest.setAdmissionDate("18/02/1998");
		Assert.assertEquals(objectTest.getAdmissionDate(), "18/02/1998");
		Assert.assertNotEquals(objectTest.getAdmissionDate(), "01/12/2001");
		Assert.assertNotNull(objectTest.getAdmissionDate());
	}

	@Test
	public void setRoleTest() {
		Role role = new Role("Novo Cargo", department, 300);
		objectTest.setRole(role);
		Assert.assertEquals(objectTest.getRole(), role);
		Assert.assertNotNull(objectTest.getRole());
	}

	@Test
	public void getRoleTest() {
		Role role = new Role("Novo Cargo", department, 300);
		objectTest.setRole(role);
		Assert.assertEquals(objectTest.getRole(), role);
	}

	@Test
	public void getWorkHoursTest() {
		objectTest.setWorkHours(150);
		Assert.assertEquals(objectTest.getWorkHours(), 150);
		Assert.assertNotEquals(objectTest.getWorkHours(), 123);
		Assert.assertNotEquals(objectTest.getWorkHours(), 1000);
		Assert.assertNotEquals(objectTest.getWorkHours(), 19);
		Assert.assertNotNull(objectTest.getWorkHours());
	}

	@Test
	public void setWorkHoursTest() {
		objectTest.setWorkHours(10);
		Assert.assertEquals(objectTest.getWorkHours(), 10);
	}

	@Test
	public void getExtraSalaryTest() {
		objectTest.setExtraSalary(100);
		Assert.assertEquals(objectTest.getExtraSalary(), 100);
		Assert.assertNotEquals(objectTest.getExtraSalary(), 123);
		Assert.assertNotEquals(objectTest.getExtraSalary(), 1000);
		Assert.assertNotEquals(objectTest.getExtraSalary(), 19);
		Assert.assertNotNull(objectTest.getExtraSalary());
	}

	@Test
	public void setExtraSalaryTest() {
		objectTest.setExtraSalary(1920);
		Assert.assertEquals(objectTest.getExtraSalary(), 1920);
	}
	
	@Test
	public void setContactTest() {
		Contact contact = new Contact("(47) 99181-8521", "carolsantos@publica.com");
		objectTest.setContact(contact);
		Assert.assertEquals(objectTest.getContact().getPhone(), "(47) 99181-8521");
		Assert.assertEquals(objectTest.getContact().getEmail(), "carolsantos@publica.com");
	}
	
	@Test
	public void generateLoginTest() {
		Department department = new Department("Desenvolvimento");
		Role role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		RoleDAO.getInstance().add(role);
		
		Cep cep = Cep.getInstance("72322-108");
		Contact contact = new Contact("(47) 98185-8956", "adminadmin@gmail.com");
		Address address = new Address(cep);
				
				
		Collaborator collab = new Collaborator("Administrador do grupo", contact, address, "741.918.140-36", "530.87976.81-6", role, "20/05/2002", false,  0, 8);
		collab.setPassword("admin");
		
		// TODO: Transform collab's password hash to string.
		//Assert.assertEquals(collab.getPassword(), "admin");
		Assert.assertEquals(collab.getUser(), "adminadmin");
		Assert.assertEquals(collab.getPassword(), "805dfa4c4f6de8bb0bf2dff68f5d62b3");
		Assert.assertEquals(collab.getContact().getPhone().substring(0,2), "(4");
	}
	
	@Test
	public void toStringTest() {
		Cep cep = new Cep("89120-000");
		Address address = new Address(cep);
		Contact contact = new Contact("(91) 98181-8181", "admin@gmail.com");
		Collaborator admin = new Collaborator("Pessoa",contact, address);
		
		String result = "Nome: Pessoa\n"
				+ "Cpf: 000.000.000-00\n"
				+ "Pis: N�o Cadastrado!\n"
				+ "Cargo: N�o Cadastrado!\n"
				+ "Sal�rio Bruto: 0,00\n"
				+ "Email: admin@gmail.com\n"
				+ "Telefone: (91) 98181-8181\n"
				+ "Localidade: Timb�\n"
				+ "Estado: SC\n"
				+ "Bairro: \n"
				+ "Logradouro: \n"
				+ "Complemento: \n"
				+ "Data de Admiss�o: \n";
		
		Assert.assertEquals(admin.toString(), result);
	
	}
}