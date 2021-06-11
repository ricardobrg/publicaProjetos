package service.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.VacationController;

/***
 * Class VacationAPI<br>
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
@Path("/vacations")
public class VacationAPI {

	VacationController vacationController = new VacationController();
	Map<String, String> responseObj = new HashMap<>();
	Response.ResponseBuilder builder;

	/***
	 * Finds a vacation related to a collaborator. If the entry is valid, it returns
	 * an success message. Otherwise, throws an error. 
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param cpf  the collaborator's CPF
	 * @param date the closing day of vacation
	 * @return the collaborator's vacation
	 */
	@GET
	@Path("/{cpf}-{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("cpf") String cpf, @PathParam("date") String date) {

		try {
			date = date.replace(".", "/");

			responseObj = vacationController.getVacationList(cpf, date);
			builder = Response.status(Response.Status.OK).entity(responseObj);

		} catch (Exception e) {

			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

}
