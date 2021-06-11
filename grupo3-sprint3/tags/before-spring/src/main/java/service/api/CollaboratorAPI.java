package service.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import controller.CollaboratorController;
import model.entities.person.Collaborator;

/***
 * Class CollaboratorAPI<br>
 * This class is the DTO for the application.<br>
 * It calls the controller in the methods and does the needed operations.<br>
 * It also handle errors.
 * 
 * @author Caio Shimada
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Nicole Taufenbach
 * 
 * @version 1.0.0
 */
@Path("/collaborators")
public class CollaboratorAPI {

	CollaboratorController collabController = new CollaboratorController();
	Map<String, String> responseObj = new HashMap<>();
	Response.ResponseBuilder builder;

	/***
	 * Find<br>
	 * This method finds a collaborator via its ID.<br>
	 * It handles null entries.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @return builds the "find a collaborator"
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") String id) {
		try {
			Collaborator collab = collabController.findById(Integer.parseInt(id));

			if (collab == null) {
				responseObj.put("error", "ID not found");
				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
			} else {
				builder = Response.status(Response.Status.OK).entity(collab);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/***
	 * GetAll<br>
	 * This method shows all collaborators.<br>
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @return builds the "getAll collaborators"
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		try {
			ArrayList<Collaborator> collabs = collabController.getAll();
			builder = Response.status(Response.Status.OK).entity(collabs);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/***
	 * Insert<br>
	 * This method inserts a collaborator.<br>
	 * It handles null entries and invalid values.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param Collaborator : collab
	 * @return builds the "insert a collaborator"
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(Collaborator collab) {
		try {
			if (collab.getCpf() == null || collab.getRole() == null)
				throw new Exception("Null entry!");

			int index = collabController.insert(collab);
			if (index == -1)
				throw new Exception("Error inserting in database!");

			responseObj.put("success", "Inserted in database!");
			responseObj.put("id", String.valueOf(index));
			builder = Response.status(Response.Status.OK).entity(responseObj);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/***
	 * Delete<br>
	 * This method deletes a collaborator.<br>
	 * It handles invalid entries.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @return builds the "delete a collaborator"
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		try {
			int index = collabController.delete(Integer.parseInt(id));

			if (index == -1)
				throw new Exception("Error deleting from database!");

			responseObj.put("success", "Deleted from database!");
			responseObj.put("id", String.valueOf(index));
			builder = Response.status(Response.Status.OK).entity(responseObj);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/***
	 * Update<br>
	 * This method updates a collaborator.<br>
	 * Can change only some values (patch)<br>
	 * It handles null entries and invalid values. Otherwise, throws a success
	 * message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @param collab Collaborator
	 * @return builds the "update a collaborator"
	 * 
	 */
	@PATCH
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, Collaborator collab) {
		try {
			Collaborator collabFound = collabController.findById(Integer.parseInt(id));
			if (collabFound == null) {
				responseObj.put("error", "ID not found!");
				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);

			} else if (collabFound.getName().equals(collab.getName()) && collabFound.getCpf().equals(collab.getCpf())
					&& collabFound.getAdmissionDate().equals(collab.getAdmissionDate())
					&& collabFound.getContact().equals(collab.getContact())
					&& collabFound.getEndereco().equals(collab.getEndereco())
					&& collabFound.getLastVacationDate().equals(collab.getLastVacationDate())
					&& collabFound.getPis().equals(collab.getPis()) && collabFound.getRole().equals(collab.getRole())
					&& collabFound.getPwdHash().equals(collab.getPwdHash())
					&& collabFound.getSalary() == collab.getSalary()
					&& collabFound.getWorkHours() == collab.getWorkHours()) {

				responseObj.put("success", "Nothing to alter!");
				builder = Response.status(Response.Status.NO_CONTENT).entity(responseObj);

			} else {
				collab.setId(Integer.parseInt(id));
				int index = collabController.update(collab);
				if (index == -1) {
					throw new Exception("Error updating from database!");

				} else {
					responseObj.put("success", "Updated from database!");
					responseObj.put("id", id);
					builder = Response.status(Response.Status.OK).entity(responseObj);
				}
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/***
	 * Replace<br>
	 * This method replaces a collaborator.<br>
	 * Basically changes entirely a collaborator.<br>
	 * It handles null entries and invalid values. Otherwise, throws a success
	 * message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param id String - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @param collab Collaborator
	 * @return builds the "replace a collaborator"
	 */
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response replace(@PathParam("id") String id, Collaborator collab) {
		try {
			Collaborator collabFound = collabController.findById(Integer.parseInt(id));
			if (collabFound == null) {
				responseObj.put("error", "ID not found!");
				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);

			} else {
				collab.setId(Integer.parseInt(id));
				int index = collabController.update(collab);
				if (index == -1) {
					throw new Exception("Error updating from database!");

				} else {
					responseObj.put("success", "Updated from database!");
					responseObj.put("id", id);
					builder = Response.status(Response.Status.OK).entity(responseObj);

				}
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}
}
