package com.publica.grupo1.model.dao.point;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.point.Point;

/**
 * Class that makes the basic actions of class Collaborator, like as : insert,
 * searching, updating and deleting.
 * 
 * This class uses only one session provided by {@link DBConnection} or, in test
 * cases, the {@link TestDBConnection} class, this classes close the session.
 * 
 * @author Vinicius
 * 
 * 
 */
public class PointDAO implements HibernateDAO<Point> {
	private static PointDAO instance;
	private Session session;

	/**
	 * Method responsible for instantiating the PointDAO class. Author Pablo
	 * Mafessoli <mafessolip@gmail.com>
	 * 
	 * @return instance
	 */
	public static PointDAO getInstance(Session session) {
		if (instance == null)
			instance = new PointDAO(session);
		return instance;
	}

	private PointDAO(Session session) {
		this.session = session;
	}

	@Override
	public Point selectById(int id) {
		Point Point = session.get(Point.class, id);
		return Point;
	}

	@Override
	public int insert(Point point) {
		int id = (int) session.save(point);
		return id;
	}

	@Override
	public void delete(Point object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	@Override
	public void update(Point object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	@Override
	public List<Point> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Point> criteria = builder.createQuery(Point.class);
		criteria.from(Point.class);

		return session.createQuery(criteria).getResultList();
	}

	/***
	 * search collaborator points
	 * 
	 * @param c : Collaborator
	 * @return all collaborator points
	 * 
	 * @author Diego Leon
	 */
	public List<Point> getCollaboratorPoints(Collaborator c) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Point> criteria = builder.createQuery(Point.class);
		Root<Point> root = criteria.from(Point.class);
		criteria.where(builder.equal(root.get("collaborator"), c));
		return session.createQuery(criteria).list();
	}

	public List<Point> getCollaboratorPointsOnInterval(Collaborator c, LocalDate date1, LocalDate date2) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Point> criteria = builder.createQuery(Point.class);
		Root<Point> root = criteria.from(Point.class);
		criteria.where(builder.equal(root.get("collaborator"), c));
		criteria.where(builder.between(root.get("date"), date1.atTime(0, 0), date2.atTime(0, 0)));
		criteria.orderBy(builder.asc(root.get("date")));
		return session.createQuery(criteria).list();
	}

	/***
	 * search all collaborator points of a month of the date year.
	 * 
	 * @param collaborator
	 * @param month
	 * @param year
	 * @return a hashMap with the days of month as keys and arrayLists of points for
	 *         each day.
	 * 
	 * @author Diego Leon
	 */
	public HashMap<LocalDate, ArrayList<Point>> getCollaboratorPointsByDate(Collaborator collaborator, LocalDate date) {
		HashMap<LocalDate, ArrayList<Point>> points = new HashMap<>();
		//int day;
		
		ArrayList<Point> collabPoints = new ArrayList<Point>(getCollaboratorPoints(collaborator));

		ArrayList<Point> datePoints;
		boolean notEmpty;
		boolean isLeapYear = date.isLeapYear();

		for (int i = 0; i < date.getMonth().length(isLeapYear); i++) {
			//day = 1 + i;
			datePoints = getPointsByDate(collabPoints, date.plusDays(i));
			notEmpty = !datePoints.isEmpty();
			if (notEmpty)
				points.put(date.plusDays(i), datePoints);
		}
		return points;
	}

	/**
	 * 
	 * @param points
	 * @param date   to search
	 * @return all points of the date param
	 * 
	 * @author Diego Leon
	 */
	public ArrayList<Point> getPointsByDate(ArrayList<Point> points, LocalDate date) {
		ArrayList<Point> dayPoints = new ArrayList<>();

		for (Point point : points)
			/**
			 * convert to local date to compare only by day, month and year, ignoring hours
			 * and minutes.
			 */
			if (point.getDate().toLocalDate().equals(date))
				dayPoints.add(point);

		return dayPoints;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
