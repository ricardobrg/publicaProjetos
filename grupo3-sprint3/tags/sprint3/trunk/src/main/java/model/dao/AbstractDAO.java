package model.dao;

public interface AbstractDAO {
	
	public void addObject(Object obj);
	
	public Object findObject(int id);

	public void updateObject(Object obj, int id);
	
	public void delObject(int id);
	
}