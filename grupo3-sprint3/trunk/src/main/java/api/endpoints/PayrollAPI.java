package api.endpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controllers.PayrollController;
import models.entities.payroll.DiscountSalary;
import models.entities.payroll.Payroll;
import models.entities.person.Collaborator;
import models.entities.security.AccessLevel;
import models.hibernate.CollaboratorHibernate;

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
@RestController
@RequestMapping("/payrolls")
@CrossOrigin
public class PayrollAPI extends Api{

	PayrollController payrollController = new PayrollController();
	ResponseEntity<Object> responseEntity;

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
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody Payroll payroll,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();
		
		Collaborator collab = (Collaborator) new CollaboratorHibernate().find("cpf", payroll.getCollaborator().getCpf());
		payroll.setCollaborator(collab);

		try {

			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");
			
			if (payroll.getCollaborator() == null || 
					payroll.getInitDate() == null || 
					payroll.getFinalDate() == null)
				throw new Exception("Null entry!");
			
			for (DiscountSalary discSalary : payroll.getDiscounts()) {
				discSalary.setPayroll(payroll);
			}

			int index = payrollController.insert(payroll);
			if (index == -1)
				throw new Exception("Error inserting in database!");

			responseObj.put("success", "Inserted in database!");
			responseObj.put("id", String.valueOf(index));
			
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (SecurityException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/***
	 * Find<br>
	 * This method lists payrolls from a collaborator.<br>
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
	@GetMapping(value = "/collaborator/{cpf}", produces = "application/json")
	public ResponseEntity<Object> findByCpf(@PathVariable("cpf") String cpf,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			ArrayList<Payroll> payrolls = payrollController.findByCpf(cpf);
			
			if (payrolls == null) {
				responseObj.put("error", "Payroll not found");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				if (!hasCpfAccess(AccessLevel.MEDIUM, token, payrolls.get(0).getCollaborator().getCpf()))
					throw new SecurityException("Access Denied");

				for (Payroll payroll : payrolls) {
					payroll.payrollNotNull();
				}
				responseEntity = new ResponseEntity<>(payrolls, HttpStatus.OK);
			}

		} catch (SecurityException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}


		return responseEntity;
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
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			Payroll payroll = payrollController.findById(Integer.parseInt(id));
			
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");
			
			if (payroll == null) {
				responseObj.put("error", "Empty Payroll");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
				
			} else {
				
				payroll.payrollNotNull();
				responseEntity = new ResponseEntity<>(payroll, HttpStatus.OK);
			}

		} catch (SecurityException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}


		return responseEntity;
	}

}
