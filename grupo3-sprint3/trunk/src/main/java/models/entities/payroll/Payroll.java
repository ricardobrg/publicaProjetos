package models.entities.payroll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.entities.person.Collaborator;

/***
 * 
 * Class Payroll
 * 
 * Class responsible for supporting the employee's payroll business rules, the same account with
 * methods for adding discounts, calculating the payroll and calculating the discounts on the
 * payroll.
 * 
 * @version 1.5.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
@Entity
@Table(name = "payrolls")
public class Payroll {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private LocalDate initDate;
  private LocalDate finalDate;

  @ManyToOne
  private Collaborator collaborator;

  private double netSalary;
  private double extraHourPayment;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "payroll")
  private List<DiscountSalary> discounts;

  public void payrollNotNull() {
    for (DiscountSalary discSalary : discounts) {
      discSalary.setPayroll(null);
    }
  }

  public Payroll(Collaborator colab, LocalDate initDate, LocalDate finalDate) {
    this.collaborator = colab;
    this.initDate = initDate;
    this.finalDate = finalDate;
    discounts = new ArrayList<DiscountSalary>();
  }

  public Payroll() {
    discounts = new ArrayList<DiscountSalary>();
  }

  /**
   * 
   * Method to add new discount object to paymentroll
   * 
   * @param DiscountSalary discount
   */
  public void addDiscount(DiscountSalary discount) {
    this.discounts.add(discount);
  }

  /**
   * Method to calculate payment roll with the extra hours, discounts, etc...
   * 
   * @param extraHours value of extra hours
   * @param monthHours
   * @return payment
   */
  public double calcPayment(int monthHours) {

    double payment = this.collaborator.getSalary() / (this.collaborator.getWorkHours() * 22);
    payment *= monthHours;

    if (monthHours > collaborator.getWorkHours() * 22) {
      payment += extraHourPayment * (monthHours - collaborator.getWorkHours() * 22);
    }

    netSalary = calcDiscount(payment);
    return netSalary;
  }

  /***
   * Method to calculate discounts for payment roll.
   * 
   * @param double payment
   * @return double discounts
   */
  public double calcDiscount(Double payment) {

    double totalDiscountPercentage = 0;
    double totalDiscountFix = 0;
    double discounts = 0;

    for (int i = 0; i < this.discounts.size(); i++) {
      if (this.discounts.get(i).isPercentage()) {
        totalDiscountPercentage += this.discounts.get(i).getValue();
      } else {
        totalDiscountFix += this.discounts.get(i).getValue();
      }
    }

    if (totalDiscountPercentage > 0) {
      discounts = payment * (totalDiscountPercentage / 100);
    }
    discounts += totalDiscountFix;
    return payment - discounts;
  }
  
  public void setDiscounts(List<DiscountSalary> discounts) {
    this.discounts = discounts;
  }
  
  public List<DiscountSalary> getDiscounts() {
    return this.discounts;
  }

  public double getNetSalary() {
    return netSalary;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setCollaborator(Collaborator collaborator) {
    this.collaborator = collaborator;
  }

  public Collaborator getCollaborator() {
    return this.collaborator;
  }

  public LocalDate getInitDate() {
    return initDate;
  }

  public void setInitDate(LocalDate initDate) {
    this.initDate = initDate;
  }

  public LocalDate getFinalDate() {
    return finalDate;
  }

  public void setFinalDate(LocalDate finalDate) {
    this.finalDate = finalDate;
  }

  public double getExtraHourPayment() {
    return extraHourPayment;
  }

  public void setExtraHourPayment(double extraHourPayment) {
    this.extraHourPayment = extraHourPayment;
  }

}
