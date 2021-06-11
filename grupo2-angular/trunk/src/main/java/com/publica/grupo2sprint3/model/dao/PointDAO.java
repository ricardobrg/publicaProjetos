package com.publica.grupo2sprint3.model.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Point;

/**
 * Class for Point Services that allows adding, list, editing and remove the
 * collaborator's points in an Arraylist.
 * 
 * @version 1.1.0
 * @author Jess� Amaro <jesse.amaro7@gmail.com>
 */
public class PointDAO implements DAO {

	static Session session = HibernateConnectorFactory.getSessionFactory().openSession();
	private static PointDAO instance;

	PointDAO() {
	}

	public static PointDAO getInstance() {
		if (instance == null)
			instance = new PointDAO();
		return instance;
	}

	/**
	 * Method for inserting a new register of work. It gets current system time and
	 * uses it to create a new Point Register
	 * 
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Ana <carolsantos2002@gmail.com>
	 */
	@Override
	public boolean add(Object collab) {
		session = HibernateConnectorFactory.getSession();
		try {
			session.beginTransaction();
			Calendar calendar = Calendar.getInstance();
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			int dayMinute = (hour * 60) + minute;

			final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			final Date date = calendar.getTime();
			String finalFormatedDate = dateFormat.format(date);
			Point newPoint = new Point((Collaborator) collab, finalFormatedDate, dayMinute);
			
			if(collab == null)
				return false;
			
			session.save((Point) newPoint);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean addFuturePoint(Point newPoint) {
		session = HibernateConnectorFactory.getSession();
		try {
			session.beginTransaction();
			
			if(newPoint.getCollaborator() == null)
				return false;
			
			session.save(newPoint);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	
		
	}

	// FINDS start

	/**
	 * Searches points by date
	 * 
	 * @author Pablo Mafesosli <mafessolip@gmail.com>
	 * @author Vinicius Roosvelt <vinicius_roose@hotmail.com>
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * @author Jess� Amaro <jesse.amaro7@gmail.com>
	 * @author Ana <carolsantos2002@gmail.com>
	 * 
	 * @param date : String
	 * @return an arraylist of the found points
	 */
	public static ArrayList<Point> getPointsByDate(Collaborator collab, String date) {
		ArrayList<Point> filteredDates = new ArrayList<Point>();
		ArrayList<Point> pointsRegister = PointDAO.getInstance().getAll();

		// filtering points in a given date
		// TODO : Trocar getName por getCpf
		for (Point item : pointsRegister) {
			if (item.getCollaborator() != null)
				if(item.getCollaborator().getCpf() != null)
					if (item.getDate().equals(date) && item.getCollaborator().getCpf().equals(collab.getCpf())) {
						filteredDates.add(item);
			}
		}
		return filteredDates;
	}

	/**
	 * This method returns all points for a specified employee.
	 * 
	 * @param collab
	 * @return allCollabsPoints
	 * 
	 * @autor Jess� Amaro <jesse.amaro7@gmail.com>
	 * @author Ana <carolsantos2002@gmail.com>
	 */
	public static ArrayList<Point> getAllCollabPoints(Collaborator collab) {
		ArrayList<Point> allCollabsPoints = new ArrayList<Point>();
		ArrayList<Point> pointsRegister = PointDAO.getInstance().getAll();

		for (Point point : pointsRegister) {
			if (point.getCollaborator() == collab) {
				allCollabsPoints.add(point);
			}
		}
		return allCollabsPoints;
	}

	/**
	 * This method edit the points for a specified employee.
	 * 
	 * @autor Jess� Amaro <jesse.amaro7@gmail.com>
	 */
	public static void editCollabsPoint(int dayMinute, Point point) {
		point.setDayMinute(dayMinute);
		session = HibernateConnectorFactory.getSession();
		session.beginTransaction();
		session.saveOrUpdate(point);
		session.update(point);
		session.getTransaction().commit();
	}

	/**
	 * Method for update a register of work.
	 * 
	 * @author Ana <carolsantos2002@gmail.com>
	 */
	@Override
	public boolean updateById(int id, Object point) {
		session = HibernateConnectorFactory.getSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(point);
			session.save(point);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeById(int id) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.remove(session.find(Point.class, id));
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method remove the points for a specified employee.
	 * 
	 * NOT USED
	 * 
	 * @autor Jess� Amaro <jesse.amaro7@gmail.com>
	 */
	public static boolean removeCollabsPoint(Point point) {
		session = HibernateConnectorFactory.getSession();
		session.beginTransaction();
		ArrayList<Point> pointIndex = PointDAO.getInstance().getAll();
		int i = pointIndex.indexOf(point);

		if (i != -1) {
			session.remove(point);
			session.getTransaction().commit();
			return true;
		}

		return false;
	}

	/**
	 * Method for remove a point by a identifier.
	 * 
	 * @author Jess� Amaro <jesse.amaro7@gmail.com>
	 */
	@Override
	public boolean removeByIdentifier(String identifier) {
		// UNUSED METHOD
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Point> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Point> criteria = builder.createQuery(Point.class);
		criteria.from(Point.class);
		ArrayList<Point> pointsRegister = (ArrayList<Point>) session.createQuery(criteria).getResultList();
		return pointsRegister;
	}

	@Override
	public Object findById(int id) {
		Point point = session.find(Point.class, id);
		return point;
	}

	@Override
	public Object findByIdentifier(String identifier) {
		return null;
	}

	@Override
	public boolean updateByIdentifier(String identifier, Object collab) {
		return false;
	}
}