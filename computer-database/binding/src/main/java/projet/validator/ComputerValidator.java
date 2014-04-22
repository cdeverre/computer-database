package projet.validator;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import projet.dto.ComputerDto;
import projet.tool.Tools;

@Component
public class ComputerValidator implements Validator{


	@Autowired
	private ResourceBundleMessageSource message;
	
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(ComputerDto.class);
	}

	@Override
	public void validate(Object arg0, Errors error) {
		ComputerDto computer=(ComputerDto) arg0;
		
		Locale locale=LocaleContextHolder.getLocale();
		String pattern=message.getMessage("date.pattern", null, locale);
		
		if(computer.getId()!=null && !"null".equals(computer.getId()) && !(computer.getId().matches("\\d+"))) {
			error.rejectValue("id", "error.computer.id");
		}
		
		if (computer.getName()==null || "".equals(computer.getName())) {
			error.rejectValue("name", "error.computer.name");
		}
		
		if (computer.getDateIntroduced()!=null && !"".equals(computer.getDateIntroduced())
			&& !Tools.validDate(computer.getDateIntroduced(),pattern)) {
				error.rejectValue("dateIntroduced", "error.computer.introduced");
		}
		
		if(computer.getDateDiscontinued()!=null&& !"".equals(computer.getDateDiscontinued())  
			&& !Tools.validDate(computer.getDateDiscontinued(),pattern)) {
				error.rejectValue("dateDiscontinued", "error.computer.discontinued");
		}
		
		if(computer.getCompany()!=null && !"0".equals(computer.getCompany()) && !(computer.getCompany().matches("\\d+") )) {
			error.rejectValue("company", "error.computer.company");
		}
	}
	
	


	




}
