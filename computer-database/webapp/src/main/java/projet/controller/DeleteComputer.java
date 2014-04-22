package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import projet.mapper.Mapper;
import projet.service.ComputerServices;


@Controller
@RequestMapping("DeleteComputer")
public class DeleteComputer {


	@Autowired
	private ComputerServices computerServices;
	
	@Autowired
	private Mapper mapper;
	

    public DeleteComputer() {
        super();
    }

    
    @RequestMapping(method=RequestMethod.GET)
	protected ModelAndView doGet(
			@RequestParam(value="id",required=true) String stringId,
			@RequestParam(value="currentPage",required=false) String currentPage ,
			@RequestParam(value="orderByColums",required=false) String orderByColumns ,
			@RequestParam(value="orderByType",required=false) String orderByType ,
			@RequestParam(value="pattern",required=false) String pattern)  {
    	
    	long id = mapper.parseIdToInt(stringId);
		computerServices.delete(id);
				
		ModelAndView mav=new ModelAndView(new RedirectView("Dashboard?delete=true"));
		
		mav.addObject("currentPage",currentPage);
		mav.addObject("orderByColumns",orderByColumns);
		mav.addObject("orderByType",orderByType);
		mav.addObject("pattern",pattern);
		
		return mav;
	}


    
}
