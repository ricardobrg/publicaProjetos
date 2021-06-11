package controller;

import java.util.ArrayList;
import model.dao.PayrollDAO;
import model.dao.WorkEntryDAO;
import model.entities.payroll.Payroll;
import model.entities.workHours.HoursCalc;

/***
 * 
 * PayrollController
 * 
 * Class used to manage the payroll in the application controller.
 * 
 * @version 1.0.0
 *
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class PayrollController {

  PayrollDAO payrollDAO = new PayrollDAO();

  /**
   * Class to generate payroll of collaborator.
   * It is basically an insert method of a payroll generated to each collaborator.
   * 
   * @param payroll
   * @return salary
   * 
   * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
   * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
   */
  public int insert(Payroll payroll) {

    WorkEntryDAO workEntryDAO = new WorkEntryDAO(payroll.getCollaborator().getCpf());

    int hours = HoursCalc.pointDifference(workEntryDAO.findBetweenDates(
        payroll.getInitDate().atStartOfDay(), payroll.getFinalDate().atStartOfDay()));

    payroll.calcPayment(hours);

    return payrollDAO.insert(payroll);
  }
  
  /**
   * Class to find a payroll of a collaborator.
   * The id is the payroll id
   * 
   * @param int id (of the collaborator)
   * @return the payroll to the specified id
   * 
   * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
   * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
   */
  public ArrayList<Payroll> findByCollab(int id) {
    return payrollDAO.find(id);
  }

  /**
   * Class to list all Payrolls.
   * It is basically an insert method of a payroll generated to each collaborator.
   * 
   * @param payroll
   * @return salary
   * 
   * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
   * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
   */
  public ArrayList<Payroll> getAll() {
    return payrollDAO.getAll();
  }

}
