package model.dao.file;

import java.util.ArrayList;

/***
 * DatabaseCrudDecorator Abstract Class<br>
 * This abstract class assures that all extended classes
 * will apply the current methods and receives the interfaces 
 * (DatabaseCrud in this class) on the constructor.    
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
abstract class DatabaseCrudDecorator implements DatabaseCrud {

	private DatabaseCrud wrapee;

	DatabaseCrudDecorator(DatabaseCrud source){
		this.wrapee = source;
	}
	
	@Override
	public <T> boolean add(T input) {
		return this.wrapee.add(input);
	}
	
	@Override
	public <T> T findById(int id) {	
		return this.wrapee.findById(id);
	}
	
	@Override
	public boolean deleteById(int id) {	
		return this.wrapee.deleteById(id);
	}
	
	@Override
	public <T> boolean updateById(T input, int id) {	
		return this.wrapee.updateById(input,id);
	}
	
	@Override
	public <T> ArrayList<T> getAll() {	
		return this.wrapee.getAll();
	}


	
}
