package projet.mapper;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projet.dto.ComputerDto;
import projet.model.Company;
import projet.model.Computer;
import projet.service.CompanyServices;
import projet.tool.Tools;

@Component
public class Mapper {

	@Autowired
	private CompanyServices companyServices;
	
	
	public Computer computerDtoToComputer(ComputerDto computerDto) {
		Calendar dateIntroduced= new GregorianCalendar();
		if (computerDto.getDateIntroduced()!=null && !"".equals(computerDto.getDateIntroduced())) {
			Tools.setCalendar(dateIntroduced, computerDto.getDateIntroduced());
		} else {
			dateIntroduced=null;
		}
		
		Calendar dateDiscontinued= new GregorianCalendar();
		if (computerDto.getDateDiscontinued()!=null && !"".equals(computerDto.getDateDiscontinued())) {
			Tools.setCalendar(dateDiscontinued, computerDto.getDateDiscontinued());
		} else {
			dateDiscontinued=null;
		}
		Company company =null;
		if (computerDto.getCompany()!=null && !"0".equals(computerDto.getCompany())) {
			Long id=Long.parseLong(computerDto.getCompany());
			String companyName=companyServices.getName(id);
			
			company = new Company(id,companyName);
		}
		
		if (!"null".equals(computerDto.getId())) {
			return new Computer(Long.parseLong(computerDto.getId()),computerDto.getName(),dateIntroduced,dateDiscontinued,company);
		} else {
			return new Computer(computerDto.getName(),dateIntroduced,dateDiscontinued,company);
		}
	}
	
	
	public ComputerDto computerToComputerDto(Computer computer) {
		String id=Long.toString(computer.getId());
		String name= computer.getName();
		String introduced = Tools.createStringFromCalendar(computer.getDateIntroduced());
		String discontinued = Tools.createStringFromCalendar(computer.getDateDiscontinued());
		String company_id = Long.toString(computer.getCompany().getId());
		
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
