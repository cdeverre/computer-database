package projet.mapper;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import projet.dto.ComputerDto;
import projet.model.Company;
import projet.model.Computer;
import projet.service.CompanyServices;

@Component
public class Mapper {

	@Autowired
	private CompanyServices companyServices;
	
	@Autowired
	private ResourceBundleMessageSource message;
	
	
	public Computer computerDtoToComputer(ComputerDto computerDto) {
		
		Locale locale=LocaleContextHolder.getLocale();
		String pattern=message.getMessage("date.pattern", null, locale);
		
		DateTimeFormatter dtf= DateTimeFormat.forPattern(pattern);
		
		DateTime dateIntroduced;
		if (computerDto.getDateIntroduced()!=null && !"".equals(computerDto.getDateIntroduced())) {
			dateIntroduced = dtf.parseDateTime(computerDto.getDateIntroduced());
		} else {
			dateIntroduced=null;
		}
		
		DateTime dateDiscontinued;
		if (computerDto.getDateDiscontinued()!=null && !"".equals(computerDto.getDateDiscontinued())) {
			dateDiscontinued = dtf.parseDateTime(computerDto.getDateDiscontinued());
		} else {
			dateDiscontinued=null;
		}
		Company company =null;
		if (computerDto.getCompany()!=null && !"0".equals(computerDto.getCompany())) {
			Long id=Long.parseLong(computerDto.getCompany());
			String companyName=companyServices.getName(id);
			
			company = new Company(id,companyName);
		}
		
		if (computerDto.getId()!=null && !"null".equals(computerDto.getId() )) {
			return new Computer(Long.parseLong(computerDto.getId()),computerDto.getName(),dateIntroduced,dateDiscontinued,company);
		} else {
			return new Computer(computerDto.getName(),dateIntroduced,dateDiscontinued,company);
		}
	}
	
	
	public ComputerDto computerToComputerDto(Computer computer) {
		Locale locale=LocaleContextHolder.getLocale();
		String pattern=message.getMessage("date.pattern", null, locale);
		
		String id=Long.toString(computer.getId());
		String name= computer.getName();
		DateTime d=computer.getIntroduced();
		String introduced="";
		String discontinued="";
		if (d!=null) {
			introduced = d.toString(pattern);
		}
		d=computer.getDiscontinued();
		if (d!=null) {
			discontinued = d.toString(pattern);
		}
		String company_id="0";
		if (computer.getCompany()!=null) {
			company_id = Long.toString(computer.getCompany().getId());
		}
		
		return new ComputerDto(id,name,introduced,discontinued,company_id);
	}


	public long parseIdToInt(String stringid) {
		long res=0;
		if (stringid!= null && stringid.matches("\\d++")) {
			res=Long.parseLong(stringid);
		}
		return res;
	}
}
