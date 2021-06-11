package model.dao.file;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.entities.workHours.WorkEntry;

/***
 * WorkEntryDecorator Class<br>
 * This concrete method implements "decorators", 
 * which are additional methods other than the default ones
 * (defined in DatabaseCrud). In this case it gets 
 * Work Entries in the database between two dates, 
 * instead of doing it by ID.
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class WorkEntryDecorator extends DatabaseCrudDecorator {

	public WorkEntryDecorator(DatabaseCrud source) {
		super(source);
	}

	public ArrayList<LocalDateTime> findBetweenDates(String cpf, LocalDate date1, LocalDate date2) {

		ArrayList<WorkEntry> wks = super.getAll();
		ArrayList<LocalDateTime> returnBetweenDates = new ArrayList<LocalDateTime>();

		for (int i = 0; i < wks.size(); i++) {
			if (wks.get(i).getClock().getDayOfYear() >= date1.getDayOfYear()
					&& wks.get(i).getClock().getYear() == date1.getYear()
					&& wks.get(i).getClock().getDayOfYear() <= date2.getDayOfYear()
					&& wks.get(i).getClock().getYear() == date2.getYear()) {
				returnBetweenDates.add(wks.get(i).getClock());
			}
		}
		return returnBetweenDates;
	}

}
