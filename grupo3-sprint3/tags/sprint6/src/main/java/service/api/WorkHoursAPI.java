package service.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.WorkHoursController;
import model.entities.person.Collaborator;
import model.entities.workHours.WorkEntry;

/**
 * <strong> Class WorkHoursAPI </strong> <br>
 * This class is the DTO for the WorkHours.<br>
 * It calls the <code> <a> WorkHoursController </a> </code> on the methods and
 * performs the necessary operations.<br>
 * It also handles errors related to the exception. <br>
 *
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 *
 * @version 1.0.0
 */
@Path("/work-hours")
public class WorkHoursAPI {

	WorkHoursController workHoursController;
	Map<String, String> responseObj = new HashMap<>();
	Response.ResponseBuilder builder;

	/**
	 * <strong> Insert </strong> <br>
	 * 
	 * Enter a new time in the database. <br>
	 * The time entered is given by the current time!<br>
	 * 
	 * Receives a Json that contains someone's 'CPF', the new point is directly
	 * linked to the 'CPF' registered in the database!
	 *
	 * @param collab - Receives a collaborator as a parameter
	 * @return build
	 *
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 */
	@POST
	@Path("/clock")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(Collaborator collab) {

		try {
			if (collab.getCpf() == null) {
				throw new Exception("Null entry!"); // return a '400: Bad Request'
			}

			workHoursController = new WorkHoursController(collab.getCpf());

			int index = workHoursController.clockIn();
			if (index == -1)
				throw new Exception("Error inserting in database!");

			WorkEntry workEntry = workHoursController.find(Integer.toString(index));

			responseObj.put("success", workEntry.getClock().toString());
			responseObj.put("collabName", workEntry.getCollaborator().getName());
			responseObj.put("id", String.valueOf(index));
			builder = Response.status(Response.Status.OK).entity(responseObj);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/**
	 * <strong> getAll </strong> <br>
	 * 
	 * Returns a list of points between the desired date! <br>
	 * Fetch point information based on employee's 'CPF' <br>
	 *
	 * @param cpf String - Receives a 'CPF' as one of the parameters
	 * @param date1 String - Receives a start date as one of the parameters for the date
	 * @param date2 String - Receives an end date as one of the parameters for the date
	 * @return build
	 *
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 */
	@GET
	@Path("/{cpf}/{date1}-{date2}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("cpf") String cpf, @PathParam("date1") String date1,
			@PathParam("date2") String date2) {
		date1 = date1.replace(".", "/");
		date2 = date2.replace(".", "/");

		workHoursController = new WorkHoursController(cpf);

		ArrayList<LocalDateTime> localDate = workHoursController.showHours(date1, date2);

		builder = Response.status(Response.Status.OK).entity(localDate);

		builder.header("Hours", workHoursController.calculateHours(date1, date2));

		return builder.build();
	}

}
