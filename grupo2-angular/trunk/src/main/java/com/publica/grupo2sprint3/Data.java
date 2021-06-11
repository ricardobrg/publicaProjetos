package com.publica.grupo2sprint3;

import com.publica.grupo2sprint3.model.dao.AddressDAO;
import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.ContactDAO;
import com.publica.grupo2sprint3.model.dao.DepartmentDAO;
import com.publica.grupo2sprint3.model.dao.ServiceProviderDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class Data {
	public static void main(String[] args) {
//		addDepartments();
		//addProviders();
//		addAddress();
//		addContact();
		addCollaborators();
	}

	public static void addDepartments() {
		DepartmentDAO deptodao = DepartmentDAO.getInstance();

		Department dpt1 = new Department("Administrativo");
		Department dpt2 = new Department("Recursos Humanos");
		Department dpt3 = new Department("Financeiro");
		Department dpt4 = new Department("Marketing");
		Department dpt5 = new Department("Desenvolvimento");
		Department dpt6 = new Department("Suporte");

		deptodao.add(dpt1);
		deptodao.add(dpt2);
		deptodao.add(dpt3);
		deptodao.add(dpt4);
		deptodao.add(dpt5);
		deptodao.add(dpt6);
	}

	public static void addProviders() {

		ServiceProviderDAO providerdao = ServiceProviderDAO.getInstance();

		Contact contact1 = new Contact("(47) 99752-0622", "friotechmaster@gmail.com");
		Contact contact2 = new Contact("(47) 99304-0360", "liptalkidiomas@gmail.com");
		Contact contact3 = new Contact("(47) 3322-3344", "prowayinfo@gmail.com");
		Contact contact4 = new Contact("(47) 90248-5493", "jardinagemflor@gmail.com");
		Contact contact5 = new Contact("(47) 90952-2835", "cleanmaster@gmail.com");

		ServiceProvider provider1 = new ServiceProvider("Friotech", contact1, "Friotech Master Ltda",
				"00.578.165/0001-45");
		ServiceProvider provider2 = new ServiceProvider("LipTalk Idiomas", contact2, "LipTalk Idiomas",
				"05.762.431/0001-09");
		ServiceProvider provider3 = new ServiceProvider("Proway", contact3, "Proway Info", "41.062.482/0001-91");
		ServiceProvider provider4 = new ServiceProvider("Jardinagem Flor do Caribe", contact4,
				"Jardinagem Flor do Caribe", "59.740.604/0001-17");
		ServiceProvider provider5 = new ServiceProvider("Clean Master", contact5, "Clean Master Ltda",
				"05.740.261/0001-61");
		
		
		provider1.setPrice(500);
		provider2.setPrice(500);
		provider3.setPrice(500);
		provider4.setPrice(500);
		provider5.setPrice(500);

		// Endere�o: 89026-420 / Rua Ourinhos / Progresso / Blumenau / SC
		// Descri��o: Instala��o e Manuten��o de Ar Condicionado

		// Endere�o: 89037-002 / Rua Frei Estanislau Schaette / �gua Verde /Blumenau /SC
		// Descri��o: Escola de idiomas

		// Endere�o: 89010-204 / Rua 7 de Setembro / Centro / Blumenau / SC
		// Descri��o: Centro de treinamento

		// Endere�o: 89060-069 / Rua Luiz Krutzsch / Fortaleza Alta / Blumenau / SC
		// Descri��o: Manuten��o de Jardins e Paisagismo

		// Endere�o: 89040-190 / Rua Flor�nia / Velha Central / Blumenau / SC
		// Descri��o:Limpeza

		providerdao.add(provider1);
		providerdao.add(provider2);
		providerdao.add(provider3);
		providerdao.add(provider4);
		providerdao.add(provider5);
	}

	public static void addAddress() {
		AddressDAO addressDao = AddressDAO.getInstance();

		Address address1 = new Address(new Cep("89074-540"));
		Address address2 = new Address(new Cep("89030-107"));
		Address address3 = new Address(new Cep("89022-140"));
		Address address4 = new Address(new Cep("89036-220"));
		Address address5 = new Address(new Cep("89030-065"));
		Address address6 = new Address(new Cep("89030-310"));
		Address address7 = new Address(new Cep("89046-000"));
		Address address8 = new Address(new Cep("89055-200"));
		Address address9 = new Address(new Cep("89026-267"));
		Address address10 = new Address(new Cep("89066-698"));

		addressDao.add(address1);
		addressDao.add(address2);
		addressDao.add(address3);
		addressDao.add(address4);
		addressDao.add(address5);
		addressDao.add(address6);
		addressDao.add(address7);
		addressDao.add(address8);
		addressDao.add(address9);
		addressDao.add(address10);
	}

	public static void addContact() {
		ContactDAO contactDao = ContactDAO.getInstance();

		Contact contact1 = new Contact("(47) 99158-3316", "aliceblue@gmail.com");
		Contact contact2 = new Contact("(47) 99724-6091", "clovespurple@gmail.com");
		Contact contact3 = new Contact("(47) 99095-6462", "carlosgreen@gmail.com");
		Contact contact4 = new Contact("(47) 99922-4885", "robsonblack@gmail.com");
		Contact contact5 = new Contact("(47) 99105-1341", "ricardowhite@gmail.com");
		Contact contact6 = new Contact("(47) 99799-5785", "anayellow@gmail.com");
		Contact contact7 = new Contact("(47) 99102-6094", "rebecablue@gmail.com");
		Contact contact8 = new Contact("(47) 99189-2338", "robertobrown@gmail.com");
		Contact contact9 = new Contact("(47) 99370-4317", "thiagopom@gmail.com");
		Contact contact10 = new Contact("(47) 99001-2137", "gabrielpom@gmail.com");

		contactDao.add(contact1);
		contactDao.add(contact2);
		contactDao.add(contact3);
		contactDao.add(contact4);
		contactDao.add(contact5);
		contactDao.add(contact6);
		contactDao.add(contact7);
		contactDao.add(contact8);
		contactDao.add(contact9);
		contactDao.add(contact10);
	}

	public static void addCollaborators() {
		CollaboratorDAO collabDAO = CollaboratorDAO.getInstance();

		Role role = new Role("Desenvolvedor de Sistemas", new Department("Desenvolvimento"), 2500.0, AccessLevel.BASIC);

		Contact contact = new Contact("(47) 99273-6587", "alicefigueira@gmail.com");
		Address address = new Address(new Cep("89037-005"));

		Contact contact2 = new Contact("(47) 99345-2104", "carlosgreen@gmail.com");
		Address address2 = new Address(new Cep("89066-698"));

		Contact contact3 = new Contact("(47) 99457-0264", "rebecablue@gmail.com");
		Address address3 = new Address(new Cep("89026-267"));

		Contact contact4 = new Contact("(47) 99120-9014", "robertobrown@gmail.com");
		Address address4 = new Address(new Cep("89055-200"));

		Contact contact5 = new Contact("(47) 99345-0987", "claradark@gmail.com");
		Address address5 = new Address(new Cep("89046-000"));

		Collaborator collab1 = new Collaborator("Alice Figueira", contact, address, "757.173.580-75", "191.94541.78-0",
				role, "03/01/2021", false, 200.0, 8);

		Collaborator collab2 = new Collaborator("Carlos Green", contact2, address2, "740.162.930-57", "206.32074.79-0",
				role, "01/01/2021", false, 195.0, 8);

		Collaborator collab3 = new Collaborator("Rebeca Blue", contact3, address3, "622.188.990-19", "654.99295.85-9",
				role, "01/01/2021", false, 170.0, 8);

		Collaborator collab4 = new Collaborator("Roberto Brown", contact4, address4, "372.044.310-81", "412.33231.09-6",
				role, "08/01/2021", false, 170.0, 8);

		Collaborator collab5 = new Collaborator("Clara Dark", contact5, address5, "150.053.280-09", "685.91851.56-2",
				role, "11/01/2021", false, 170.0, 8);

		collabDAO.add(collab1);
		collabDAO.add(collab2);
		collabDAO.add(collab3);
		collabDAO.add(collab4);
		collabDAO.add(collab5);
	}

}
