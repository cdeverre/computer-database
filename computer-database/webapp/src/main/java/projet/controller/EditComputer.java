package projet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import projet.dto.ComputerDto;
import projet.exception.TransactionException;
import projet.mapper.Mapper;
import projet.model.Company;
import projet.model.Computer;
import projet.service.CompanyServices;
import projet.service.ComputerServices;

/**
 * Servlet implementation class EditComputer
 */
@Controller
@RequestMapping("EditComputer")
public class EditComputer  {
       
	@Autowired
	private ComputerServices computerServices;
	
	@Autowired
	private CompanyServices companyServices;
	
	@Autowired
	private Mapper mapper;
	
	 
	 
    
    public EditComputer() {
        super();
    }

    
    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(HttpServletRequest request, ModelMap map) {
		String stringid=request.getParameter("id");
		long id = mapper.parseIdToInt(stringid);
		Computer computer=computerServices.find(id);
		ComputerDto computerDto= mapper.computerToComputerDto(computer);
		
		List<Company> companyList = companyServices.getAll();
		map.addAttribute("companyList", companyList);
		map.addAttribute("computerDto", computerDto);
		
		return("editComputer");

		
	}

    
    @RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doPost(@Valid ComputerDto computerDto, BindingResult result) {
		
		ModelAndView mav;
		if (!result.hasErrors()) {

			Computer computer=mapper.computerDtoToComputer(computerDto);
			
			computerServices.find(computer.getId());

			
			companyServices.exist(computer.getCompany());
 
			computerServices.update(computer);
			
			mav=new ModelAndView(new RedirectView("Dashboard?update=true"));
		} else {
			mav=new ModelAndView("editComputer");

			mav.addObject("computerDto",computerDto);
			
			List<Company> companyList = companyServices.getAll();
			mav.addObject("companyList", companyList);
			

		}
		return mav;
	}
    
    @ExceptionHandler(TransactionException.class)
	public String handleAllException(Exception ex) {
 
		return "error-pages/transactionError";
 
	}

}
