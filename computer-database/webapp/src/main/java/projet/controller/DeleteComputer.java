package projet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import projet.exception.TransactionException;
import projet.service.ComputerServices;


@Controller
@RequestMapping("DeleteComputer")
public class DeleteComputer {


	@Autowired
	private ComputerServices computerServices;
	
	

    public DeleteComputer() {
        super();
    }

    
    @RequestMapping(method=RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request)  {
		int id= Integer.parseInt(request.getParameter("id"));
		computerServices.delete(id);
				
		ModelAndView mav=new ModelAndView(new RedirectView("Dashboard?delete=true"));
		
		mav.addObject("currentPage",request.getParameter("currentPage"));
		mav.addObject("orderByColumns",request.getParameter("orderByColumns"));
		mav.addObject("orderByType",request.getParameter("orderByType"));
		mav.addObject("pattern",request.getParameter("pattern"));
		
		return mav;
	}

    @RequestMapping(method=RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		response.sendRedirect("/computer-database/Dashboard");
	}

    @ExceptionHandler(TransactionException.class)
	public String handleAllException(Exception ex) {
 
		return "error-pages/transactionError";
 
	}
}
