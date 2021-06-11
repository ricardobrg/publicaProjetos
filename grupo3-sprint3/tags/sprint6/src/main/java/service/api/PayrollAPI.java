package service.api;

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
import controller.PayrollController;
import model.dao.CollaboratorDAO;
import model.entities.payroll.DiscountSalary;
import model.entities.payroll.Payroll;
import model.entities.person.Collaborator;

/***
 * Class PayrollAPI<br>
 * This class is the DTO for the application.<br>
 * It calls the controller in the methods and does the needed operations.<br>
 * It also handles errors.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 * @author Giovanni Buzzi
 * @author Jonathas Rocha
 * @author Nicole Taufenbach
 */
@Path("/payrolls")
public class PayrollAPI {

	PayrollController payrollController = new PayrollController();
	Map<String, String> responseObj = new HashMap<>();
	Response.ResponseBuilder builder;

	/***
	 * Insert<br>
	 * This method inserts a payroll.<br>
	 * It handles null entries and invalid values. Otherwise, throws a success
	 * message.
	 * 
	 * @param Payroll : payroll
	 * @return builds the "insert a payroll"
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(Payroll payroll) {
		Collaborator collab = new CollaboratorDAO().findByCpf(payroll.getCollaborator().getCpf());
		payroll.setCollaborator(collab);

		try {
			if (payroll.getCollaborator() == null || 
					payroll.getInitDate() == null || 
					payroll.getFinalDate() == null)
				throw new Exception("Null entry!");

			for (DiscountSalary discSalary : payroll.getDiscounts()) {
				discSalary.setPayroll(payroll);
				System.out.println(discSalary.getName());
			}

			int index = payrollController.insert(payroll);
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
	 * Find<br>
	 * This method finds a payroll connected to a collaborator.<br>
	 * It handles null entries. Otherwise, throws a success message.
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @return builds the "find a payroll"
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 */
	@GET
	@Path("/collaborator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") String id) {
		try {
			ArrayList<Payroll> payrolls = payrollController.findByCollab(Integer.parseInt(id));

			if (payrolls == null) {
				responseObj.put("error", "Empty Payroll");
				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
				
			} else {
				
				for (Payroll payroll : payrolls) {
					payroll.payrollNotNull();
				}
				
				builder = Response.status(Response.Status.OK).entity(payrolls);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/***
	 * GetAll<br>
	 * This method shows all payrolls to a specific collaborator.<br>
	 * The collaborator is filtered in the URI.
	 * 
	 * @return builds the "getAll payrolls"
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("id") String id) {
		try {
			ArrayList<Payroll> payrolls = payrollController.findByCollab(Integer.parseInt(id));
			builder = Response.status(Response.Status.OK).entity(payrolls);
			for (Payroll payroll : payrolls) {
				payroll.payrollNotNull();
			}
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}
}
