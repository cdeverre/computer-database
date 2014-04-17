package projet.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Tools {


	public static boolean validDate(String date,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
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
