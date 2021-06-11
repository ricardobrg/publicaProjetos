package view.workHours;

import java.util.ArrayList;

import com.google.gson.JsonObject;

/***
 * WorkHoursListAll. Lists all Days, Months and Years + Hour and Minute of Day.
 * This information is unique to each CPF in database.
 * 
 * @version 2.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class ListAll {

	public ListAll() {

	}

	public void print(ArrayList<JsonObject> clocks) {

		String output;
		System.out.println();
		System.out.println("=======================");
		for (int i = 0; i < clocks.size(); i++) {
			output = clocks.get(i).getAsJsonObject("clock").getAsJsonObject("date").get("day") + "/"
				   + clocks.get(i).getAsJsonObject("clock").getAsJsonObject("date").get("month") + "/"
				   + clocks.get(i).getAsJsonObject("clock").getAsJsonObject("date").get("year") + " -> "
			       + clocks.get(i).getAsJsonObject("clock").getAsJsonObject("time").get("hour") + ":"
				   + clocks.get(i).getAsJsonObject("clock").getAsJsonObject("time").get("minute") + ":"
				   + clocks.get(i).getAsJsonObject("clock").getAsJsonObject("time").get("second");
			System.out.println(output);
		}
		System.out.println("=======================");
	}
}
