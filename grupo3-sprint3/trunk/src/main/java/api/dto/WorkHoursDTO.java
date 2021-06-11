package api.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WorkHoursDTO {

	private ArrayList<LocalDateTime> entries;
	private int workedHours;
	
	public WorkHoursDTO(ArrayList<LocalDateTime> entries, int workedHours) {
		this.setEntries(entries);
		this.setWorkedHours(workedHours);
	}

	public ArrayList<LocalDateTime> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<LocalDateTime> entries) {
		this.entries = entries;
	}

	public int getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(int workedHours) {
		this.workedHours = workedHours;
	}
	
	
	
}
