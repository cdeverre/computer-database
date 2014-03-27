package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tools {

		
	public static void setCalendar(Calendar calendar, String parameter) {
		String[] tmp=parameter.split("/");
		calendar.set(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[0])-1, Integer.parseInt(tmp[1]));
	}
	
	public static String createStringFromCalendar(Calendar calendar) {
		String res;
		if (calendar==null) {
			res="";
		} else {
			res=Integer.toString(calendar.get(Calendar.MONTH)+1)+"/"+
					Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))+"/"+
					Integer.toString(calendar.get(Calendar.YEAR));
		}
		return(res);
	}

	public static boolean validDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		format.setLenient(false);
		try {
	          format.parse(date);
	          return true;
	     }
	     catch(ParseException e){
	          return false;
	     }
	}
	
}
