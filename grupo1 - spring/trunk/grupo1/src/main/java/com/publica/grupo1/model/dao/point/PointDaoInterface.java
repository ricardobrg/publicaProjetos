package com.publica.grupo1.model.dao.point;

import java.util.ArrayList;
import java.util.HashMap;

import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.point.Point;

public interface PointDaoInterface extends HibernateDAO<Point> {
	public HashMap<Integer, ArrayList<Point>> getCollaboratorPointsByMonth(Collaborator collaborator, int month);
}
