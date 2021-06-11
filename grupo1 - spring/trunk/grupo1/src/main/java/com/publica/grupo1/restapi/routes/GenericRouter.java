package com.publica.grupo1.restapi.routes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

/***
 * Class for common requests, select by id, delete by id, update and insert.
 * Each method check the access level. The returns are a object in JSON with a
 * HTTP OK message or a HTTP message error.
 * 
 * @author Diego Leon
 */
public class GenericRouter<T> {

	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();

	/***
	 * create a object in database.
	 * 
	 * @param t     object to insert.
	 * @param token user token
	 * @param dao   to use
	 * 
	 * @return if succefull the object in JSON and a OK HTTP message, if not a HTTP
	 *         error.
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> create(T t, String token, HibernateDAO<T> dao) {
		if (securityApi.hasAdminAccess(token)) {
			int id = dao.insert(t);
			return messageGenerator.generateInsertedObjectMessage(t.toString());

		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	/***
	 * return a JSON array of the DAO type.
	 * 
	 * @param token user token
	 * @param dao   to use
	 * 
	 * @return if succefull the array in JSON and a OK HTTP message, if not a HTTP
	 *         error.
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> getAll(String token, HibernateDAO<T> dao) {
		if (securityApi.hasAdminAccess(token)) {
			List<T> objects = dao.getAll();

			if (objects == null) {
				return messageGenerator.generateEmptyListError();
			} else {
				return new ResponseEntity<>(objects, HttpStatus.OK);
			}
		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	/***
	 * get a object by id.
	 * 
	 * @param id    of the desired object.
	 * @param token user token
	 * @param dao   to use
	 * 
	 * @return if succefull the object in JSON and a OK HTTP message, if not a HTTP
	 *         error.
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> selectById(int id, String token, HibernateDAO<T> dao) {
		if (securityApi.hasAdminAccess(token)) {
			T t = dao.selectById(id);

			if (t == null) {
				return messageGenerator.generateIdNotFoundError();
			} else {
				return new ResponseEntity<>(t, HttpStatus.OK);
			}
		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	/***
	 * update a object in database.
	 * 
	 * @param t     object to update.
	 * @param token user token
	 * @param dao   to use
	 * 
	 * @return if succefull the updated object in JSON and a OK HTTP message, if not
	 *         a HTTP error.
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> update(T t, int id, String token, HibernateDAO<T> dao) {
		if (securityApi.hasAdminAccess(token)) {
			DBConnection.getSession().clear();
			dao.update(t);
			return messageGenerator.generateUpdateSuccessMessage(t);
		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	/***
	 * delete a object in database.
	 * 
	 * @param id    of the object to delete.
	 * @param token user token
	 * @param dao   to use
	 * 
	 * @return if succefull the deleted object id in JSON and a OK HTTP message, if
	 *         not a HTTP error.
	 * @author Diego Leon
	 */
	public ResponseEntity<Object> delete(int id, String token, HibernateDAO<T> dao) {
		if (securityApi.hasAdminAccess(token)) {
			T foundObject = dao.selectById(id);
			dao.delete(foundObject);

			return messageGenerator.generateDeleteSuccessMessage(id);
		} else {
			return messageGenerator.generateUnauthorizedError();
		}

	}
}
