package service.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * Class RoleAPI<br>
 * This class is the DTO for the application.<br>
 * It calls the controller in the methods and does the needed operations.<br>
 * It also handle errors.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 * @author Giovanni Buzzi
 * @author Jonathas Rocha
 * @author Nicole Taufenbach
 */
@RestController
@RequestMapping("/roles")
public class RoleAPI {

	/***
	 * Find<br>
	 * This method finds a role via its ID.<br>
	 * It handles null entries.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param id String - this way it can be converted to JSON (and will be parsed
	 *           in the try)
	 * @return builds the "find a role"
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public String find(@PathVariable("id") String id) {
		return "lzdgg";
	}

//	/***
//	 * Insert<br>
//	 * This method inserts a role.<br>
//	 * It handles null entries.<br>
//	 * Otherwise, throws a success message.
//	 * 
//	 * @author Caio Shimada
//	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
//	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
//	 * @author Nicole Taufenbach
//	 * 
//	 * @param role : Role
//	 * @return builds the "insert a role"
//	 */
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response insert(Role role) {
//
//		try {
//			if (role.getName() == null || role.getAccessLevel() == null)
//				throw new Exception("Null entry!");
//
//			int index = roleController.add(role);
//
//			responseObj.put("success", "Inserted in database!");
//			responseObj.put("id", String.valueOf(index));
//			builder = Response.status(Response.Status.OK).entity(responseObj);
//
//		} catch (Exception e) {
//			responseObj.put("error", e.getMessage());
//			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//		}
//
//		return builder.build();
//	}
//
//	/***
//	 * Delete<br>
//	 * This method deletes a role.<br>
//	 * It handles invalid entries.<br>
//	 * Otherwise, throws a success message.
//	 * 
//	 * @author Caio Shimada
//	 * @author Giovanni Buzzi
//	 * @author Jonathas Rocha
//	 * @author Nicole Taufenbach
//	 * 
//	 * @param String id - this way it can be converted to JSON (and will be parsed
//	 *               in the try)
//	 * @return builds the "delete a role"
//	 */
//	@DELETE
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response delete(@PathParam("id") String id) {
//		try {
//			int index = roleController.delete(Integer.parseInt(id));
//
//			responseObj.put("success", "Deleted from database!");
//			responseObj.put("id", String.valueOf(index));
//			builder = Response.status(Response.Status.OK).entity(responseObj);
//
//		} catch (IllegalArgumentException e) {
//			responseObj.put("error", e.getMessage());
//			builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
//
//		} catch (Exception e) {
//			responseObj.put("error", e.getMessage());
//			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//		}
//
//		return builder.build();
//	}
//
//	/***
//	 * Update<br>
//	 * This method updates a role.<br>
//	 * Can change only some values (patch).<br>
//	 * It handles null entries and invalid values.<br>
//	 * Otherwise, throws a success message.
//	 * 
//	 * @author Caio Shimada
//	 * @author Giovanni Buzzi
//	 * @author Jonathas Rocha
//	 * @author Nicole Taufenbach
//	 * 
//	 * @param String id - this way it can be converted to JSON (and will be parsed
//	 *               in the try)
//	 * @param role Role
//	 * 
//	 * @return builds the "update a role"
//	 */
//	@PATCH
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response update(@PathParam("id") String id, Role role) {
//		try {
//			Role roleFound = roleController.find(Integer.parseInt(id));
//			if (roleFound == null) {
//				responseObj.put("error", "ID not found!");
//				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
//
//			} else if (roleFound.getAccessLevel() == role.getAccessLevel()
//					&& roleFound.getName().equals(role.getName())) {
//
//				responseObj.put("success", "Nothing to alter!");
//				builder = Response.status(Response.Status.NO_CONTENT).entity(responseObj);
//
//			} else {
//				role.setId(Integer.parseInt(id));
//				int index = roleController.edit(role);
//				if (index == -1) {
//					throw new Exception("Error updating from database!");
//
//				} else {
//					responseObj.put("success", "Updated from database!");
//					responseObj.put("id", id);
//					builder = Response.status(Response.Status.OK).entity(responseObj);
//				}
//			}
//
//		} catch (Exception e) {
//			responseObj.put("error", e.getMessage());
//			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//		}
//
//		return builder.build();
//	}
//
//	/***
//	 * Replace<br>
//	 * This method replaces a role.<br>
//	 * Basically changes entirely a role.<br>
//	 * It handles null entries and invalid values. Otherwise, throws a success
//	 * message.
//	 * 
//	 * @author Caio Shimada
//	 * @author Giovanni Buzzi
//	 * @author Jonathas Rocha
//	 * @author Nicole Taufenbach
//	 * 
//	 * @param String id - this way it can be converted to JSON (and will be parsed
//	 * @param role Role 
//	 * @return builds the "replace a role"
//	 */
//	@PUT
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response replace(@PathParam("id") String id, Role role) {
//		try {
//			Role roleFound = roleController.find(Integer.parseInt(id));
//			if (roleFound == null) {
//				responseObj.put("error", "ID not found!");
//				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
//
//			} else {
//				role.setId(Integer.parseInt(id));
//				int index = roleController.edit(role);
//				if (index == -1) {
//					throw new Exception("Error updating from database!");
//
//				} else {
//					responseObj.put("success", "Updated from database!");
//					responseObj.put("id", id);
//					builder = Response.status(Response.Status.OK).entity(responseObj);
//
//				}
//			}
//
//		} catch (Exception e) {
//			responseObj.put("error", e.getMessage());
//			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//		}
//
//		return builder.build();
//	}
//
//	/***
//	 * GetAll<br>
//	 * This method shows all roles.<br>
//	 * 
//	 * @author Caio Shimada
//	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
//	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
//	 * @author Nicole Taufenbach
//	 * 
//	 * @return builds the "getAll roles"
//	 */
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAll() {
//		try {
//			ArrayList<Role> roles = roleController.getAll();
//			builder = Response.status(Response.Status.OK).entity(roles);
//
//		} catch (Exception e) {
//			responseObj.put("error", e.getMessage());
//			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//		}
//
//		return builder.build();
//	}
}
