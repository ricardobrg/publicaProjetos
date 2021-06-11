//package model;
//
//import java.util.Calendar;
//
//import org.junit.Assert;
//import org.testng.annotations.Test;
//
//import model.entities.person.Collaborator;
//import model.entities.workHours.WorkEntry;
//
//public class WorkEntryTest {
//
//	@Test
//	public void getRegistersInMonthTest() {
//		Collaborator colab = new Collaborator();
//		
//		colab.setName("Pedro");
//		
//		WorkEntry point = new WorkEntry(colab);
//		
//		point.clockIn();
//		point.clockIn();
//		point.clockIn();
//		
//		Assert.assertEquals(point.getClock().size(), 3);
//		
//		Assert.assertEquals(point.getClock().get(0).get(Calendar.MILLISECOND),
//				point.getClock().get(1).get(Calendar.MILLISECOND));
//		
//	}
//}
