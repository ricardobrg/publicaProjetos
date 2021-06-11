package model.dao.hibernate;

import java.time.LocalDate;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import model.entities.payroll.DiscountSalary;
import model.entities.payroll.Payroll;
import model.entities.person.Collaborator;

public class PayrollHibernateTest {

  PayrollHibernate payrollHibernate;
  Payroll payroll;

  Collaborator collab;
  CollaboratorHibernate collabHibernate;

  /*
   * public void setTest() { collabHibernate = new CollaboratorHibernate();
   * 
   * collab = new Collaborator(); collab.setCpf("08309171951"); collab.setName("Giovanni");
   * collab.setWorkHours(8); collab.setSalary(3000);
   * 
   * Contact contact = new Contact(); Endereco endereco = new Endereco();
   * 
   * collab.setEndereco(endereco); collab.setContact(contact);
   * 
   * collabHibernate.insert(collab);
   * 
   * payroll = new Payroll(collab, LocalDate.now());
   * 
   * payrollHibernate = new PayrollHibernate();
   * 
   * collab.setId(setLastId("collaborators")); }
   * 
   * public void erase() { collabHibernate.deleteById(collab.getId()); }
   * 
   * public int setLastId(String table) { Session session =
   * HibernateConector.getSessionFactory().openSession(); session.beginTransaction(); int id = (int)
   * session.createSQLQuery("SELECT id FROM " + table +
   * " ORDER BY id DESC LIMIT 1;").uniqueResult(); session.getTransaction().commit();
   * session.close(); return id; }
   */

  @Test(priority = 1)
  public void insertTest() {

    payrollHibernate = new PayrollHibernate();
    collabHibernate = new CollaboratorHibernate();

//    payrollHibernate.setTestDatabase();
//    collabHibernate.setTestDatabase();

    collab = (Collaborator) collabHibernate.find("cpf", "01888041293");

    payroll = new Payroll(collab, LocalDate.of(2005, 12, 12), LocalDate.of(2020, 12, 12));
    
    DiscountSalary discount1 = new DiscountSalary("INSS", 10.0, true);
    DiscountSalary discount2 = new DiscountSalary("DBA", 500.0, false);
    
    discount1.setPayroll(payroll);
    discount2.setPayroll(payroll);

    payroll.addDiscount(discount1);
    payroll.addDiscount(discount2);

    payroll.calcPayment(12);
    int insertedId = payrollHibernate.insert(payroll);
    Assert.assertNotEquals(insertedId, -1);
    payroll.setId(insertedId);
  }

  @Test(priority = 2)
  public void getAllTest() {

    payrollHibernate = new PayrollHibernate();

    ArrayList<Payroll> as = payrollHibernate.getAll();

    System.out.println(as.get(0).getNetSalary());
    System.out.println(as.get(0).getDiscounts().get(0).getName());

    // collab.setId(setLastId("collaborators"));
  }

  /*
   * @Test(priority=2) public void findByIdTest() { Collaborator collabFound =
   * collabHibernate.findById(collab.getId()); Assert.assertEquals(collabFound.getCpf(),
   * collab.getCpf()); collab = collabFound; }
   * 
   * @Test(priority=3) public void findByCpfTest() { Collaborator collabFound =
   * collabHibernate.findByCpf(collab.getCpf()); Assert.assertEquals(collabFound.getCpf(),
   * collab.getCpf()); }
   * 
   * @Test(priority=4) public void getAllTest() { Assert.assertNotNull(collabHibernate.getAll()); }
   * 
   * @Test(priority=5) public void updateTest() { collab.getEndereco().setComplemento("420");
   * collab.setWorkHours(6); collab.setSalary(2500);
   * 
   * Assert.assertTrue(collabHibernate.update(collab)); }
   * 
   * @Test(priority=6) public void deleteTest() {
   * Assert.assertTrue(collabHibernate.deleteById(collab.getId())); }
   */
}
