package projet.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import projet.dto.ComputerDto;
import projet.mapper.Mapper;
import projet.model.Company;
import projet.model.Computer;
import projet.service.CompanyServices;
import projet.service.ComputerServices;
import projet.validator.ComputerValidator;



@Controller
@RequestMapping("AddComputer")
public class AddComputer  {
       
	
	@Autowired
	private CompanyServices companyServices;
	
	@Autowired
	private ComputerServices computerServices;
	
	@Autowired
	private Mapper mapper;
    

    public AddComputer() {
        super();
    }

    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response)  {
		ArrayList<Company> companyList = companyServices.getAll();
		request.setAttribute("companyList", companyList);
			
		return("addComputer");
	}

    @RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response)  {
		String error;
		String name =request.getParameter("name");
		String introduced=request.getParameter("introduced");
		String discontinued=request.getParameter("discontinued");
		String idString =request.getParameter("company");
		
		ComputerDto computerDto=new ComputerDto(name,introduced,discontinued,idString);
		
		error=ComputerValidator.validate(computerDto);
		
		ModelAndView mav;
		
		if (error.equals("00000")) {
			Computer computer=mapper.computerDtoToComputer(computerDto);
			
			companyServices.exist(computer.getCompany());
			
			computerServices.create(computer);
			
			mav=new ModelAndView(new RedirectView("Dashboard?add=true"));
		} else {
			mav=new ModelAndView("addComputer");
			mav.addObject("computerName",name);
			mav.addObject("computerIntroduced",introduced);
			mav.addObject("computerDiscontinued",discontinued);
			if(error.substring(4,5).equals("0")) {
				mav.addObject("companyId",idString);
			} else {
				mav.addObject("companyId",0);
			}
			
			ArrayList<Company> companyList = companyServices.getAll();
			mav.addObject("companyList", companyList);
			
			mav.addObject("errorName",error.substring(1, 2));
			mav.addObject("errorIntroduced",error.substring(2, 3));
			mav.addObject("errorDiscontinued",error.substring(3, 4));
			mav.addObject("errorCompanyId",error.substring(4, 5));
			
		}
		return mav;
	}
	

}
