package projet.validator;

import projet.dto.ComputerDto;
import projet.tool.Tools;

public class ComputerValidator {

	
	
	public static String validate(ComputerDto computer) {
		StringBuilder error=new StringBuilder();
		
		if(computer.getId()!=null && !"null".equals(computer.getId())) {
			if ( !(computer.getId().matches("\\d+"))) {
				error.append("1");
			} else {
				error.append("0");
			}
		} else {
			error.append("0");
		}
		
		if (computer.getName()==null || "".equals(computer.getName())) {
			error.append("1");
		} else {
			error.append("0");
		}
		
		if (computer.getDateIntroduced()!=null && !"".equals(computer.getDateIntroduced())) {
			if (!Tools.validDate(computer.getDateIntroduced())) {
				error.append("1");
			} else {
				error.append("0");
			}
		} else {
			error.append("0");
		}
		
		if(computer.getDateDiscontinued()!=null&& !"".equals(computer.getDateDiscontinued())) {
			if(!Tools.validDate(computer.getDateDiscontinued())) {
				error.append("1");
			} else {
				error.append("0");
			}
		} else {
			error.append("0");
		}
		
		if(computer.getCompany()!=null && !"0".equals(computer.getCompany())) {
			if ( !(computer.getCompany().matches("\\d+"))) {
				error.append("1");
			} else {
				error.append("0");
			}
		}  else {
			error.append("0");
		}
		return error.toString();
	}
	
	public static boolean idIsValid(String id) {
		boolean res=false;
		if(id!=null) {
			res=(id.matches("\\d+")) ;
		}
		return res;
	}

}
