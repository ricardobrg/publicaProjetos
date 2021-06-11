//package controller;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.google.gson.JsonObject;
//
//import model.dao.jdbc.CollaboratorDB;
//import model.entities.person.Collaborator;
//import model.entities.person.Contact;
//import model.entities.person.Endereco;
//import model.entities.security.AccessLevel;
//import model.entities.security.Role;
//
//public class CollaboratorControllerTest {
//	
//	CollaboratorController controller;
//	CollaboratorDB db;
//	ArrayList<Collaborator> collabs = new ArrayList<Collaborator>();
//	
//	Role role;
//	Collaborator collab1;
//	Collaborator collab2;
//	
//	@BeforeClass
//	public void setTest() {
//		controller = new CollaboratorController();
//		
//		db = (CollaboratorDB) CollaboratorDB.getInstance();
//		db.setTestDatabase();
//		
//		role = new Role();
//		role.setId(1);
//		role.setName("Presidente");
//		role.setAccessLevel(AccessLevel.TOTAL);
//		
//		collab1 = new Collaborator();
//		collab1.setName("Nicole");
//		collab1.setCpf("068.617.400-39");
//		collab1.setPis("897.20957.37-2");
//		collab1.setPwdHash("senha1");
//		collab1.setEndereco(new Endereco("67130-480", "68"));
//		collab1.setContact(new Contact("email@gmail.com", "91989898989"));
//		collab1.setAdmissionDate("20/12/2020");
//		collab1.setLastVacationDate("20/12/2020");
//		collab1.setWorkHours(8);
//		collab1.setSalary(5000);
//		collab1.setRole(role);
//		
//		collab2 = new Collaborator();
//		collab2.setName("Giovanni");
//		collab2.setCpf("623.663.270-72");
//		collab2.setPis("831.38655.48-3");
//		collab2.setPwdHash("senha2");
//		collab2.setEndereco(new Endereco("89053-210", "462"));
//		collab2.setContact(new Contact("email@gmail.com", "47999999999"));
//		collab2.setAdmissionDate("18/12/2020");
//		collab2.setLastVacationDate("18/12/2020");
//		collab2.setWorkHours(6);
//		collab2.setSalary(6500);
//		collab2.setRole(role);
//		
//		collabs.add(collab1);
//		collabs.add(collab2);
//		
//		resetState();
//	}
//	
//	@BeforeMethod
//	public void populate() {
//		for (Collaborator collab : collabs) {
//			try {
//				db.insert(collab);
//				collab.setId(db.lastId("collaborators"));
//			} catch (SQLException e) {
//				e.printStackTrace();
//				Assert.fail();
//			}
//		}
//	}
//	
//	@AfterMethod
//	public void resetState() {
//		try {
//			for (Collaborator collab : collabs) {
//				db.delete("id", ""+collab.getId());
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//
//	@Test
//	public void addTest() {
//		
//		JsonObject collabJson = new JsonObject();
//		collabJson.addProperty("name", "Fulano");
//		collabJson.addProperty("pis", "479.16260.36-0");
//		collabJson.addProperty("email", "email@gmail.com");
//		collabJson.addProperty("phone", "91 989898989");
//		collabJson.addProperty("cep", "69945-970");
//		collabJson.addProperty("cpf", "655.505.410-76");
//		collabJson.addProperty("roleId", 1);
//		collabJson.addProperty("admissionDate", "20/12/2020");
//		collabJson.addProperty("workHours", 8);
//		collabJson.addProperty("salary", 1000);
//		collabJson.addProperty("pwd", "senha");
//		
//		Assert.assertNotEquals(controller.insert(collabJson), -1);
//		try {
//			db.delete("cpf", "65550541076");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//
//	@Test
//	public void getAllTest() {
//		
//		ArrayList<JsonObject> collabsJson = new ArrayList<JsonObject>();
//		
//		for (int i = 0; i < collabs.size(); i++) {
//			JsonObject json = new JsonObject();
//			json.addProperty("id",   collabs.get(i).getId());
//			json.addProperty("name", collabs.get(i).getName());
//			json.addProperty("cpf",  collabs.get(i).getCpf());
//			json.addProperty("role", collabs.get(i).getRole().getName());
//			collabsJson.add(json);
//		}
//		
//		try {
//			Assert.assertEquals(controller.getAll(), collabsJson);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//	
//	@Test
//	public void findByCpfTest() {
//		String cpf = collab1.getCpf();
//		
//		JsonObject cpfJson = new JsonObject();
//		cpfJson.addProperty("cpf", cpf);
//		
//		Assert.assertEquals(controller.findByCpf(cpfJson).get("cpf").getAsString(), cpf);
//	}
//
//	@Test
//	public void removeTest() {
//		String cpf = collab1.getCpf();
//		
//		JsonObject cpfJson = new JsonObject();
//		cpfJson.addProperty("cpf", cpf);
//		int id = controller.findByCpf(cpfJson).get("id").getAsInt();
//		
//		JsonObject idJson = new JsonObject();
//		idJson.addProperty("id", id);
//		
//		Assert.assertEquals(controller.delete(idJson), id);
//	}
//	
//	@Test
//	public void updateTest() {
//		//TODO
//	}
//}
