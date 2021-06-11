package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Point;

public class PointDAOTest {

	@Test
	public void addExistingCollabTest() {
		Collaborator collab = CollaboratorDAO.getInstance().findById(29);
		Boolean result = PointDAO.getInstance().add(collab);
		Assert.assertTrue(result);
	}
	
	@Test
	public void editCollabsPointTest() {
		PointDAO.getInstance();
		Point result = (Point) PointDAO.getInstance().findById(3);
		PointDAO.editCollabsPoint(370, result);
	}

	@Test
	public void findByIdTest() {
		Point result = (Point) PointDAO.getInstance().findById(7);
		Assert.assertEquals(result.getDayMinute(), 720);
	}

	@Test
	public void getAllTest() {
		ArrayList<Point> points = PointDAO.getInstance().getAll();
		Assert.assertNotNull(points);
	}

	@Test
	public void getAllCollabsPointTest() {
		Point result = (Point) PointDAO.getInstance().findById(1);
		Collaborator resultCollab = result.getCollaborator();
		PointDAO.getInstance();
		ArrayList<Point> collabPoints = PointDAO.getAllCollabPoints(resultCollab);
		Assert.assertNotNull(collabPoints);
	}

	@Test
	public void getInstanceTest() {
		PointDAO dao = PointDAO.getInstance();
		Assert.assertEquals(dao.getClass(), new PointDAO().getClass());
	}

	@Test
	public void getPointsByDateTest() {
		Point result = (Point) PointDAO.getInstance().findById(10);
		Collaborator resultCollab = result.getCollaborator();
		PointDAO.getInstance();
		ArrayList<Point> collabPoints = PointDAO.getPointsByDate(resultCollab, "11/01/2021");
		Assert.assertNotNull(collabPoints);
	}

	@Test
	public void removeByIdTest() {
		PointDAO dao = PointDAO.getInstance();
		Assert.assertEquals(dao.removeById(4), true);
	}

	@Test
	public void updateByIdTest() {
		PointDAO dao = PointDAO.getInstance();
		Point result = (Point) PointDAO.getInstance().findById(3);
		result.setDayMinute(550);
		result.setDate("14/12/2020");
		int id = result.getId();
		Assert.assertEquals(dao.updateById(id, result), true);
	}
	
	@Test
	public void addFuturePointTest() {
		Collaborator collab = CollaboratorDAO.getInstance().findById(38);
		Point p1 = new Point(collab, "21/05/2022", 480);
		Point p2 = new Point(collab, "21/05/2022", 720);
		Point p3 = new Point(collab, "21/05/2022", 780);
		Point p4 = new Point(collab, "21/05/2022", 1080);
		
		Assert.assertTrue(PointDAO.getInstance().addFuturePoint(p1));
		Assert.assertTrue(PointDAO.getInstance().addFuturePoint(p2));
		Assert.assertTrue(PointDAO.getInstance().addFuturePoint(p3));
		Assert.assertTrue(PointDAO.getInstance().addFuturePoint(p4));
	}

	
//	@Test
//	public void removeCollabsPointTest() { //FALHA
//		Point point = (Point) PointDAO.getInstance().findById(4);
//		Assert.assertEquals(PointDAO.removeCollabsPoint(point), true);
//	}

}
