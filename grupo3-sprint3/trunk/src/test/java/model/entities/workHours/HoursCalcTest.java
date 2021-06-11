package model.entities.workHours;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import models.entities.person.Collaborator;
import models.entities.workHours.HoursCalc;
import models.entities.workHours.WorkEntry;

public class HoursCalcTest {

	@Test
	public void pointDifferenceTest() {

		Collaborator colab = new Collaborator();
		ArrayList<LocalDateTime> workEntries = new ArrayList<LocalDateTime>();
		
		LocalDateTime localdate = LocalDateTime.now();
		
		WorkEntry wk = new WorkEntry(colab);
		wk.setClock(localdate);
		workEntries.add(wk.getClock());
		
		localdate = localdate.plusHours(3);
		
		wk = new WorkEntry(colab);
		wk.setClock(localdate);
		workEntries.add(wk.getClock());
		
		Assert.assertEquals(3, HoursCalc.pointDifference(workEntries));

	}
}
