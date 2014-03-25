package tools;

import java.util.Calendar;

public class Tools {

		
	public static void setCalendar(Calendar calendar, String parameter) {
		String[] tmp=parameter.split("/");
		calendar.set(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[0])-1, Integer.parseInt(tmp[1]));
	}
	
	public static String createStringFromCalendar(Calendar calendar) {
		return(Integer.toString(calendar.get(Calendar.MONTH)+1)+"/"+
				Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))+"/"+
				Integer.toString(calendar.get(Calendar.YEAR)));
	}
}
