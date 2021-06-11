package model.entities.payroll;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.testng.annotations.Test;

import models.entities.payroll.DiscountSalary;
import models.entities.payroll.Payroll;
import models.entities.person.Collaborator;

public class PayrollTest {

	@Test
	public void calcPaymentTest() {
		
		Collaborator colab = new Collaborator();
		colab.setCpf("08309171951");
		colab.setWorkHours(8);
		colab.setSalary(1000.0);
		colab.setName("Giovanni Buzzi");
		
		Payroll payment = new Payroll(colab, LocalDate.now(), LocalDate.now().plusMonths(1));
		DiscountSalary discount = new DiscountSalary("INSS", 10.0, true);
		payment.setExtraHourPayment(10);
		
		payment.addDiscount(discount);
		
		discount = new DiscountSalary("Ave", 100.0, false);
		payment.addDiscount(discount);
		discount = new DiscountSalary("Ave", 100.0, false);
		payment.addDiscount(discount);
		
		assertEquals(payment.calcPayment(176), 700);
		
	}
}
