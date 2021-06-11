package model.dao.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import model.entities.person.Collaborator;
import model.entities.person.Contact;
import model.entities.person.Endereco;
import model.entities.security.AccessLevel;
import model.entities.security.Role;

/**
 * CRUD using JDBC for Collaborator Objects.<br>
 * 
 * This extends the DBAccess class so it must get the instance of the Connection instead 
 * of creating a new one.<br>
 * 
 * It implements every method defined in DBAccess to handle selecting, inserting.
 * updating and deleting Collaborators in the database.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class CollaboratorDB extends DBAccess {

	/**
	 * Private constructor to avoid creating multiple instances of DBAccess (multiple
	 * connections).
	 * 
	 * @author Caio Shimada
	 * 
	 * @param host     a string of the IP of the host of the database
	 * @param user     a string of the username of the database
	 * @param password a string of the password of the database
	 * @param port     an int of the port used to connect to the database
	 * @param dbName   a string of the name of the database name
	 * @throws SQLException
	 */
	private CollaboratorDB(String host, String user, String password, int port, String dbName) throws SQLException {
		super(host, user, password, port, dbName);
	}

	/**
	 * This method returns verifies if a connection already exists, if it does it
	 * returns it (a DBAccess Object), if not it creates a new one with the current
	 * DB Class and assigns it.<br>
	 * 
	 * When called, a cast must be made on the returned instance to the current 
	 * class. In this case, to CollaboratorDB.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return a DBAccess object with the connection
	 */
	public static DBAccess getInstance() {
		if (instance == null) {
			try {
				instance = new CollaboratorDB("localhost", "root", null, 3306, "grupo3_hr");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/**
	 * Finds a specific Collaborator given a field and a value, using LEFT JOINS when
	 * necessary to retrieve foreign data.<br>
	 * 
	 * @author Caio Shimada
	 * 
	 * @param field the string of the field to be used on the WHERE
	 * @param value the string of the value to be compared to
	 * 
	 * @return the Collaborator found
	 */
	@Override
	public Collaborator find(String field, String value) throws SQLException {
		String query = String.format("SELECT collaborators.*, "
				+ "roles.id AS roleId, roles.name AS role, roles.access_id, "
				+ "enderecos.*, enderecos.id AS addressId, "
				+ "contacts.*,  contacts.id  AS contactId  "
				+ "FROM collaborators "
				+ "LEFT JOIN enderecos ON collaborators.address_id = enderecos.id "
				+ "LEFT JOIN contacts  ON collaborators.contact_id = contacts.id  "
				+ "LEFT JOIN roles     ON collaborators.role_id    = roles.id     "
				+ "WHERE collaborators.%s = %s;", field, value);

		Map<String, String> result = select(query).get(0);

		return assignToObject(result);
	}

	/**
	 * Retrieves every Collaborator in the database while using LEFT JOINS when 
	 * necessary to get foreign data.<br>
	 * 
	 * @author Caio Shimada
	 * 
	 * @return an ArrayList of every Collaborator object in the database 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> ArrayList<T> getAll() throws SQLException {
		String query = "SELECT collaborators.*, "
				+ "roles.id AS roleId, roles.name AS role, roles.access_id, "
				+ "enderecos.*, enderecos.id AS addressId, "
				+ "contacts.*,  contacts.id  AS contactId  "
				+ "FROM collaborators "
				+ "LEFT JOIN enderecos ON collaborators.address_id = enderecos.id "
				+ "LEFT JOIN contacts  ON collaborators.contact_id = contacts.id  "
				+ "LEFT JOIN roles     ON collaborators.role_id    = roles.id;    ";

		ArrayList<Map<String, String>> results = select(query);
		ArrayList<Collaborator> collabs = new ArrayList<Collaborator>();

		for (Map<String, String> result : results) {
			collabs.add(assignToObject(result));
		}

		return (ArrayList<T>) collabs;
	}

	/**
	 * Inserts a new Collaborator in the database given a Collaborator object.<br>
	 * 
	 * This method does not verify if the given Address and Contact exists in the table,
	 * it simply inserts a new one and assigns its id to the Collaborator.<br>
	 * 
	 * Also, a default Role already in the database is assigned to the Collaborator.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param collaborator the Collaborator object to be inserted
	 */
	@Override
	public void insert(Object collaborator) throws SQLException {
		Collaborator collab = (Collaborator) collaborator;

		int address_id = insertAddress(collab.getEndereco());
		int contact_id = insertContact(collab.getContact());
		int role_id = findRole();

		String name = collab.getName();
		String cpf = collab.getCpf();
		String pis = collab.getPis();
		String pwd_hash = collab.getPwdHash();
		int work_hours = collab.getWorkHours();
		int salary = (int) collab.getSalary();
		String admission_date     = adaptDateToDb(collab.getAdmissionDate());
		String last_vacation_date = adaptDateToDb(collab.getLastVacationDate());

		String query = "INSERT INTO collaborators VALUES (NULL, "
				+ "'" + name 			   + "', "
				+ "'" + cpf 			   + "', "
				+ "'" + pis 			   + "', "
				+ "'" + pwd_hash 		   + "', "
				+ " " + address_id 		   + " , "
				+ " " + contact_id 		   + " , "
				+ " " + role_id 		   + " , "
				+ " " + work_hours 		   + " , "
				+ " " + salary 			   + " , "
				+ "'" + admission_date 	   + "', "
				+ "'" + last_vacation_date + "');";

		update(query);

	}

	/**
	 * Updates a Collaborator in the database given a Collaborator object.<br>
	 * 
	 * This method does not verify if the given Address and Contact exists in the table,
	 * it simply inserts a new one and assigns its id to the Collaborator.<br>
	 * 
	 * Also, a default Role already in the database is assigned to the Collaborator.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param collaborator the Collaborator object to be updated
	 * @param id           an int of the id of the collaborator to be updated
	 */
	@Override
	public void update(Object collaborator, int id) throws SQLException {
		Collaborator collab = (Collaborator) collaborator;

		int address_id = insertAddress(collab.getEndereco());
		int contact_id = insertContact(collab.getContact());
		int role_id = findRole();

		String name = collab.getName();
		String cpf = collab.getCpf();
		String pis = collab.getPis();
		String pwd_hash = collab.getPwdHash();
		int work_hours = collab.getWorkHours();
		int salary = (int) collab.getSalary();
		String admission_date = adaptDateToDb(collab.getAdmissionDate());
		String last_vacation_date = adaptDateToDb(collab.getLastVacationDate());

		String query = "UPDATE collaborators SET "
				+ "name 			  = '" + name     			+ "', "
				+ "cpf 				  = '" + cpf      			+ "', "
				+ "pis 				  = '" + pis      			+ "', "
				+ "pwd_hash 		  = '" + pwd_hash 			+ "', "
				+ "address_id 		  =  " + address_id 		+ " , "
				+ "contact_id 		  =  " + contact_id 		+ " , "
				+ "role_id 			  =  " + role_id 			+ " , "
				+ "work_hours 		  =  " + work_hours 		+ " , "
				+ "salary 			  =  " + salary 			+ " , "
				+ "admission_date 	  = '" + admission_date 	+ "', "
				+ "last_vacation_date = '" + last_vacation_date + "'  "
				+ "WHERE id 		  =  " + id 			    + ";  ";

		update(query);

	}

	/**
	 * Deletes a Collaborator from the database given a field and a value.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void delete(String field, String value) throws SQLException {
		String query = String.format("DELETE FROM collaborators WHERE %s = %s;", field, value);
		update(query);
	}

	/**
	 * A auxiliary method to create a Collaborator object given a Map with 
	 * its data.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param map a Map with collaborator data
	 * @return a Collaborator object
	 */
	private Collaborator assignToObject(Map<String, String> map) {
		Collaborator collab = new Collaborator();
		collab.setId(Integer.parseInt(map.get("id")));
		collab.setName(map.get("name"));
		collab.setCpf(map.get("cpf"));
		collab.setPis(map.get("pis"));
		collab.setPwdHash(map.get("pwd_hash"));
		collab.setWorkHours(Integer.parseInt(map.get("work_hours")));
		collab.setSalary(Double.parseDouble(map.get("salary")));
		collab.setAdmissionDate(adaptDateFromDb(map.get("admission_date")));
		collab.setLastVacationDate(adaptDateFromDb(map.get("last_vacation_date")));

		Role role = new Role();
		role.setId(Integer.parseInt(map.get("roleId")));
		role.setName(map.get("role"));
		switch (Integer.parseInt(map.get("access_id"))) {
		case 1 -> role.setAccessLevel(AccessLevel.BASIC);
		case 2 -> role.setAccessLevel(AccessLevel.MEDIUM);
		case 3 -> role.setAccessLevel(AccessLevel.TOTAL);
		}
		collab.setRole(role);

		Endereco end = new Endereco();
		end.setId(Integer.parseInt(map.get("addressId")));
		end.setCEP(map.get("cep"));
		end.setBairro(map.get("bairro"));
		end.setComplemento(map.get("complemento"));
		end.setLocalidade (map.get("localidade"));
		end.setLogradouro (map.get("logradouro"));
		collab.setEndereco(end);

		Contact con = new Contact();
		con.setId(Integer.parseInt(map.get("contactId")));
		con.setEmail(map.get("email"));
		con.setTelephone(map.get("telephone"));
		collab.setContact(con);

		return collab;
	}

	/**
	 * Converts a date String dd/MM/yyyy to yyyy-MM-dd.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param date a string of the date to be adapted
	 * @return the adapted string of the date
	 */
	private String adaptDateToDb(String date) {
		String[] date_split = date.split("/");
		return date_split[2] + "-" + date_split[1] + "-" + date_split[0];
	}

	/**
	 * Converts a date String yyyy-MM-dd to dd/MM/yyyy.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param date a string of the date to be adapted
	 * @return the adapted string of the date
	 */
	private String adaptDateFromDb(String date) {
		String[] date_split = date.split("-");
		return date_split[2] + "/" + date_split[1] + "/" + date_split[0];
	}

	/**
	 * Inserts a new Address in the database and returns its id.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param end the Endereco object to be inserted
	 * @return the int of the id of the inserted registry
	 * @throws SQLException
	 */
	private int insertAddress(Endereco end) throws SQLException {
		String query = "INSERT INTO enderecos VALUES (NULL, "
				+ "'" + end.getCEP()         + "', "
				+ "'" + end.getLocalidade()  + "', "
				+ "'" + end.getBairro()      + "', "
				+ "'" + end.getLogradouro()  + "', "
				+ "'" + end.getComplemento() + "');";
		update(query);
		return lastId("enderecos");
	}

	/**
	 * Inserts a new Contact in the database and returns its id.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param con the Contact object to be inserted
	 * @return the int of the id of the inserted registry
	 * @throws SQLException
	 */
	private int insertContact(Contact con) throws SQLException {
		String query = "INSERT INTO contacts VALUES (NULL, "
				+ "'" + con.getEmail()     + "', "
				+ "'" + con.getTelephone() + "');";
		update(query);
		return lastId("contacts");
	}

	/**
	 * Finds a default Role already in the database ('Presidente') 
	 * to be used in this JDBC CRUD.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return the int of the id the role
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private int findRole() throws NumberFormatException, SQLException {
		String query = String.format("SELECT id FROM roles WHERE name = '%s' LIMIT 1;", "Presidente");
		return Integer.parseInt(select(query).get(0).get("id"));
	}

	public int lastId(String table) throws NumberFormatException, SQLException {
		String query = String.format("SELECT id FROM %s ORDER BY id DESC LIMIT 1;", table);
		return Integer.parseInt(select(query).get(0).get("id"));
	}

}
