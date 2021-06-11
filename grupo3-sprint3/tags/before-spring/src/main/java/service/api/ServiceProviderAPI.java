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
import controller.ServiceProviderController;
import model.entities.person.ServiceProvider;

/***
 * Class ServiceProviderAPI<br>
 * This class is the DTO for the application.<br>
 * It calls the controller in the methods and does the needed operations.<br>
 * It also handles errors.
 * 
 * @author Caio Shimada
 * @author Giovanni Buzzi
 * @author Jonathas Rocha
 * @author Nicole Taufenbach
 * 
 * @version 1.0.0
 */
@Path("/providers")
public class ServiceProviderAPI {

	ServiceProviderController providerController = new ServiceProviderController();
	Map<String, String> responseObj = new HashMap<>();
	Response.ResponseBuilder builder;

	/***
	 * Insert<br>
	 * This method inserts a Service Provider.<br>
	 * It handles null entries and invalid values.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param a service provider
	 * @return builds the "insert a service provider"
	 * 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(ServiceProvider provider) {

		try {
			if (provider.getCnpj() == null)
				throw new Exception("Null entry!");

			int index = providerController.insert(provider);
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
	 * This method finds a Service Provider via its ID.<br>
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
	 * @return builds the "find a service provider"
	 * 
	 * 
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") String id) {
		try {
			ServiceProvider provider = providerController.findById(Integer.parseInt(id));

			if (provider == null) {
				responseObj.put("error", "ID not found");
				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
			} else {
				builder = Response.status(Response.Status.OK).entity(provider);
			}

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
	 * @return builds the "update a service provider"
	 * 
	 */
	@PATCH
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, ServiceProvider provider) {
		try {
			ServiceProvider providerFound = providerController.findById(Integer.parseInt(id));
			if (providerFound == null) {
				responseObj.put("error", "ID not found!");
				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);

			} else if (providerFound.getName().equals(provider.getName())
					&& providerFound.getCnpj().equals(provider.getCnpj())
					&& providerFound.getContact().equals(provider.getContact())
					&& providerFound.getEndereco().equals(provider.getEndereco())
					&& providerFound.getSocialReason().equals(provider.getSocialReason())
					&& providerFound.getDescription().equals(provider.getDescription())) {

				responseObj.put("success", "Nothing to alter!");
				builder = Response.status(Response.Status.NO_CONTENT).entity(responseObj);

			} else {
				provider.setId(Integer.parseInt(id));
				int index = providerController.update(provider);
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
	 * Delete<br>
	 * This method deletes a Service Provider.<br>
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
	 * 
	 * @return builds the "delete a Service Provider"
	 * 
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		try {
			int index = providerController.delete(Integer.parseInt(id));

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
	 * Replace<br>
	 * This method replaces a Service Provider.<br>
	 * Basically changes entirely a Service Provider.<br>
	 * It handles null entries. Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * 
	 * @return builds the "replace a Service Provider"
	 * 
	 */
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response replace(@PathParam("id") String id, ServiceProvider provider) {
		try {
			ServiceProvider providerFound = providerController.findById(Integer.parseInt(id));
			if (providerFound == null) {
				responseObj.put("error", "ID not found!");
				builder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);

			} else {
				provider.setId(Integer.parseInt(id));
				int index = providerController.update(provider);
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
	 * GetAll<br>
	 * This method shows all Service Providers.<br>
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @return builds the "getAll Service Providers"
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		try {
			ArrayList<ServiceProvider> providers = providerController.getAll();
			builder = Response.status(Response.Status.OK).entity(providers);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}
}
