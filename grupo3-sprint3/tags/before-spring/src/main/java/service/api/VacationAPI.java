package service.api;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.CollaboratorController;
import controller.VacationController;
import model.entities.person.Collaborator;
import service.dto.VacationDTO;
import utils.convertions.DateConversions;

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
	VacationDTO vacDTO = new VacationDTO();

	VacationController vacationController = new VacationController();
	Map<String, String> responseObj = new HashMap<>();
	Response.ResponseBuilder builder;

	/***
	 * Finds a vacation related to a collaborator. If the entry is valid, it returns
	 * a success message. Otherwise, throws an error.
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

			vacDTO = vacationController.getVacationList(cpf, date);
			builder = Response.status(Response.Status.OK).entity(vacDTO);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/***
	 * Returns vacation info about a collaborator Changes the lastVacationDate field
	 * to the sum of the number of vacation days the collaborator is allowed to have
	 * and the date they wish. If the entry is valid, it returns a success message.
	 * Otherwise, throws an error.
	 * 
	 * @author Giovanni Buzzi
	 * @author Nicole Taufenbach
	 * 
	 * @param cpf  the collaborator's CPF
	 * @param date the closing day of vacation
	 * @return the collaborator's vacation
	 */
	@GET
	@Path("/allowed/{cpf}-{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allowedFind(@PathParam("cpf") String cpf, @PathParam("date") String date) {
		CollaboratorController collabController = new CollaboratorController();
		LocalDate localDate = DateConversions.convertDate(date);

		try {
			date = date.replace(".", "/");
			
			Collaborator collab = collabController.findByCpf(cpf);
			vacDTO = vacationController.getVacationList(cpf, date);
			
			if (vacDTO.getCanVacation()) {
				collab.setLastVacationDate(
						DateConversions.parseDate(
							  localDate.plusDays(vacDTO.getVacationDays()).toString()));
				
				collabController.update(collab);
				responseObj.put("returnDate", collab.getLastVacationDate());
				
			} else if (collab.getInVacation()) {
				responseObj.put("error", "O usuário já está em férias.");
				
			} else {
				responseObj.put("error", "O usuário não pode tirar férias.");
			}

			builder = Response.status(Response.Status.OK).entity(responseObj);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

}
