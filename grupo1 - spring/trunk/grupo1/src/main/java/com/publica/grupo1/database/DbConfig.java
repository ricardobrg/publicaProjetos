package com.publica.grupo1.database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.contact.Contact;
import com.publica.grupo1.model.entities.contact.NumberPhone;
import com.publica.grupo1.model.entities.endereco.CEP;
import com.publica.grupo1.model.entities.endereco.Endereco;
import com.publica.grupo1.model.entities.gender.Genre;
import com.publica.grupo1.model.entities.internalevents.InternalEvents;
import com.publica.grupo1.model.entities.payroll.Discount;
import com.publica.grupo1.model.entities.payroll.Payroll;
import com.publica.grupo1.model.entities.permissions.AccessLevel;
import com.publica.grupo1.model.entities.permissions.Permission;
import com.publica.grupo1.model.entities.permissions.role.Department;
import com.publica.grupo1.model.entities.permissions.role.Role;
import com.publica.grupo1.model.entities.point.Point;
import com.publica.grupo1.model.entities.point.PointType;
import com.publica.grupo1.model.entities.vacations.TimeOffTracker;

/**
 * Populate and config the db test.
 * 
 * @author Diego Leon
 *
 */
public class DbConfig {
	public Collaborator collab, nocturnalCollab;
	public Permission permission;
	public Endereco endereco;
	public NumberPhone numberPhone;
	public Contact contact;
	public Genre genre;
	public AccessLevel accessLevel;
	public InternalEvents intEvents;
	public Role role;
	public Department department;
	public CEP cep;
	public TimeOffTracker timeOfftracker;
	public Payroll payroll;
	public Discount fixedDiscount, percentualDiscount;

	public PointType entry, exit;
	public ArrayList<Point> points = new ArrayList<Point>();

	public LocalDate admissionDate, bithDate, lastVacationDate;

	public final String CPF = "08238433940";

	public final String HASHED_PASSWORD = "$2a$10$52Wn26CcmmCK1w1GyKVTPeNygTDYitZ2AFo5DmkFMb0m5ZC7s2iqO";
	public final String PASSWORD = "123";

	Session session;
	SessionFactory factory;

	public DbConfig(Session session) {
		this.session = session;
		createObjects();
	}

	/**
	 * create all objects to insert in the database.
	 */
	private void createObjects() {
		accessLevel = AccessLevel.ADMIN;
		role = new Role(1, 8, "Cozinheiro");
		department = new Department(1, "Teste");
		permission = new Permission(1, accessLevel, role, department);

		cep = new CEP(1, "12345000", "Localidade", "Bairro", "Ribeirinho", "Do lado do bar");
		endereco = new Endereco(1, cep, "Blumenau", "Vila Nova", "Casa do zé", "Limoeiro");

		numberPhone = new NumberPhone("47", "991029444");
		numberPhone.setId(1);

		NumberPhone[] phoneList = new NumberPhone[2];
		phoneList[0] = numberPhone;

		contact = new Contact("josue@joestar.com");
		contact.setPhoneList(phoneList);

		LocalDateTime dateHourEvent = LocalDateTime.of(2022, 12, 12, 20, 30);
		LocalDateTime finalHourEvent = LocalDateTime.of(2020, 12, 1, 18, 18, 0);

		ArrayList<Collaborator> collaborators = new ArrayList<Collaborator>();
		collaborators.add(collab);
		ArrayList<Collaborator> organizers = collaborators;

		intEvents = new InternalEvents(1,
				"https://i.pinimg.com/originals/24/40/f9/2440f9e27310e5917f753ad4ce65696c.jpg", "Nome Do Evento",
				dateHourEvent, finalHourEvent, endereco, organizers, "Descrição do Eventooooooo!!!!", collaborators);

		genre = new Genre();
		genre.setNameGenre("Boi");
		genre.setIdGenre(1);

		lastVacationDate = LocalDate.of(2020, 1, 10);
		admissionDate = lastVacationDate;

		collab = new Collaborator(1, permission, endereco, contact, genre, admissionDate, bithDate, lastVacationDate,
				2340.0, true, "4321284", "11232343212", "Joana", "João", "solteiro", "pomerode", "Brasil", "123",
				"Josue", "Joestar", genre);
		
		collab.setCpf(CPF);
		collab.setPassword(HASHED_PASSWORD);
		
		timeOfftracker = new TimeOffTracker(LocalDate.of(2020, 12, 15), 15, 1, collab);
		timeOfftracker.setIdTimeOffTracker(1);

		fixedDiscount = new Discount(1, "fixed", false, 25.0);
		percentualDiscount = new Discount(2, "percentual", true, 5.0);
		List<Discount> list = new ArrayList<Discount>();
		list.add(fixedDiscount);
		list.add(percentualDiscount);
		payroll = new Payroll(1, collab, list);

		LocalDateTime entryDate = LocalDateTime.of(2020, 12, 1, 8, 8, 0);
		LocalDateTime exitDate = LocalDateTime.of(2020, 12, 1, 18, 18, 0);

		entry = PointType.ENTRY;
		exit = PointType.EXIT;

		boolean isLeapYear = entryDate.toLocalDate().isLeapYear();

		for (int i = 0; i < entryDate.getMonth().length(isLeapYear); i++) {
			points.add(new Point(collab, entryDate.plusDays(i), entry));
			points.add(new Point(collab, exitDate.plusDays(i), exit));
		}
	}

	/**
	 * Populate all tables.
	 * 
	 * @author Diego Leon
	 */
	public void populateAllTables() {
		populateDepartmentTable();
		populateRoleTable();
		populatePermissionTable();

		populateCepTable();
		populateEnderecoTable();
		populateContactTable();
		populateGenreTable();
		populateCollaboratorTable();
		populatePayrollAndDiscounts();
		populateTimeOffTracker();

		populateInternalEventsTable();
		populatePointsTable();
	}

	/**
	 * Clear all tables
	 * 
	 * @author Diego Leon
	 * @author Pablo Mafessoli
	 */
	public void clearTables() {
		deleteFromPayrollAndRelatedTables();

		deleteFromPointsAndRelatedTables();
		deleteFromTable(TimeOffTracker.class.getSimpleName());
		deleteFromTable(Collaborator.class.getSimpleName());
		deleteFromTable(Genre.class.getSimpleName());

		deleteFromPermissionAndRelatedTables();
		deleteFromEnderecoAndRelatedTables();
		deleteFromContactAndRelatedTables();
		deleteFromInternalEvents();
	}

	public void deleteFromPointsAndRelatedTables() {
		deleteFromTable(Point.class.getSimpleName());
	}

	/**
	 * Delete from the table and others that uses that fk.
	 * 
	 * @author Diego Leon
	 */
	public void deleteFromPermissionAndRelatedTables() {
		deleteFromTable(Permission.class.getSimpleName());
		deleteFromTable(Department.class.getSimpleName());
		deleteFromTable(Role.class.getSimpleName());
	}

	/**
	 * Delete from the table and others that uses that fk.
	 * 
	 * @author Pablo Mafessoli
	 * @author Diego Leon
	 */
	public void deleteFromInternalEvents() {
		deleteFromTable(InternalEvents.class.getSimpleName());

	}

	/**
	 * Delete from the table and others that uses that fk.
	 * 
	 * @author Pablo Mafessoli
	 * @author Diego Leon
	 */
	public void deleteFromTimeOffTrackerAndRelatedTables() {
		deleteFromTable(Collaborator.class.getSimpleName());
		deleteFromTable(TimeOffTracker.class.getSimpleName());

	}

	/**
	 * Delete from the table and others that uses that fk.
	 * 
	 * @author Diego Leon
	 */
	public void deleteFromPayrollAndRelatedTables() {
		deleteFromTable(Payroll.class.getSimpleName());
		deleteFromTable(Discount.class.getSimpleName());
	}

	/**
	 * Delete from the table and others that uses that fk.
	 * 
	 * @author Diego Leon
	 */
	public void deleteFromEnderecoAndRelatedTables() {
		deleteFromTable(Endereco.class.getSimpleName());
		deleteFromTable(CEP.class.getSimpleName());
	}

	/**
	 * Delete from the table and others that uses that fk.
	 * 
	 * @author Diego Leon
	 */
	public void deleteFromContactAndRelatedTables() {
		deleteFromTable(Contact.class.getSimpleName());
		deleteFromTable(NumberPhone.class.getSimpleName());
	}

	/**
	 * method to delete all data from a table.
	 * 
	 * @param tableName class name
	 * 
	 * @author Diego Leon
	 */
	public void deleteFromTable(String tableName) {
		session.beginTransaction();
		session.createQuery("DELETE FROM " + tableName).executeUpdate();
		session.getTransaction().commit();
	}

	public void populatePointsTable() {
		for (Point point : points)
			point.setId((int) session.save(point));
	}

	/**
	 * Populate the table with the atributte of the same type and set it id.
	 * 
	 * @author Diego Leon
	 */
	public void populateRoleTable() {
		role.setId((int) session.save(role));
	}

	/**
	 * Populate the table with the atributte of the same type and set it id.
	 * 
	 * @author Diego Leon
	 */
	public void populateDepartmentTable() {
		department.setId((int) session.save(department));
	}

	/**
	 * Populate the table with the atributte of the same type and set it id.
	 * 
	 * @author Diego Leon
	 */
	public void populatePermissionTable() {
		permission.setId((int) session.save(permission));
	}

	/**
	 * Populate the table with the atributte of the same type and set it id.
	 * 
	 * @author Diego Leon
	 */
	public void populateCepTable() {
		cep.setId((int) session.save(cep));
	}

	/**
	 * Populate the table with the atributte of the same type and set it id.
	 * 
	 * @author Diego Leon
	 */
	public void populateEnderecoTable() {
		endereco.setId((int) session.save(endereco));
	}

	public void populateNumberPhoneTable() {
		numberPhone.setId((int) session.save(numberPhone));
	}

	/**
	 * Populate the table with the atributte of the same type and set it id.
	 * 
	 * @author Diego Leon
	 */
	public void populateContactTable() {
		populateNumberPhoneTable();
		contact.setId((int) session.save(contact));
	}

	/**
	 * a Populate the table with the atributte of the same type and set it id.
	 * 
	 * @author Diego Leon
	 */
	public void populateGenreTable() {
		genre.setIdGenre((int) session.save(genre));
	}

	public void populateInternalEventsTable() {
		intEvents.setIdInternalEvents((int) session.save(intEvents));
	}

	/**
	 * Populate the table with the atributte of the same type and set it id. This
	 * method is private because the collaborator table needs the others for the
	 * fk's.
	 * 
	 * @author Pablo Mafessoli
	 */
	public void populateTimeOffTracker() {
		timeOfftracker.setIdTimeOffTracker((int) session.save(timeOfftracker));
	}

	/**
	 * a Populate the table with the atributte of the same type and set it id.
	 * 
	 * @author Diego Leon
	 */
	public void populatePayrollAndDiscounts() {
		fixedDiscount.setId((int) session.save(fixedDiscount));
		percentualDiscount.setId((int) session.save(percentualDiscount));

		payroll.setId((int) session.save(payroll));
	}

	/**
	 * Populate the table with the atributte of the same type and set it id. This
	 * method is private because the collaborator table needs the others for the
	 * fk's.
	 * 
	 * @author Diego Leon
	 */

	public void populateCollaboratorTable() {
		collab.setIdCollaborator((int) session.save(collab));
	}
}