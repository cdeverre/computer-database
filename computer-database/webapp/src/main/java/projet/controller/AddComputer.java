package projet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
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
    
	@Autowired 
	private ComputerValidator validator;


	
    public AddComputer() {
        super();
    }

    
    @InitBinder
    private void initBinder(WebDataBinder binder) {
    	binder.setValidator(validator);
    }
    
    
    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(ModelMap map)  {
    	List<Company> companyList = companyServices.getAll();
		map.addAttribute("companyList", companyList);
		map.addAttribute("computerDto", new ComputerDto());
		
		return "addComputer";
	}

    @RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doPost(@Valid ComputerDto computerDto , BindingResult result)  {
    	
    	ModelAndView mav;
		
		if (!result.hasErrors()) {
			Computer computer=mapper.computerDtoToComputer(computerDto);
			
			companyServices.exist(computer.getCompany());
			
			computerServices.create(computer);
			
			mav=new ModelAndView(new RedirectView("Dashboard?add=true"));
		} else {
			
			mav=new ModelAndView("addComputer");
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
