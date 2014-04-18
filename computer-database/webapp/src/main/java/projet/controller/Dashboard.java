package projet.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projet.exception.TransactionException;
import projet.model.Computer;
import projet.service.ComputerServices;
import projet.wrapper.PageWrapper;



@Controller
@RequestMapping("Dashboard")
public class Dashboard  {
	

       
	@Autowired
	private ComputerServices computerServices;
	
	
    /**
     * 
     */
    public Dashboard() {
        super();
    }

    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response)  {
		int numberOfComputer =0;
		List<Computer> computerList=null;
		String currentPageString=request.getParameter("currentPage");
		String orderByColumns=request.getParameter("orderByColumns");
		String orderByType=request.getParameter("orderByType");
		String pattern=request.getParameter("pattern");
		PageWrapper page=new PageWrapper(currentPageString, orderByColumns, orderByType, pattern);
		
		int numberOfPage=1;
		if(	page.getPattern()!=null && !"".equals(page.getPattern())) {
			numberOfComputer=computerServices.count(page.getPattern());
			numberOfPage=Math.max(1,computerServices.getNumberOfPage(numberOfComputer));
			page.setCurrentPage(Math.min(page.getCurrentPage(),numberOfPage));
			computerList = computerServices.search(page.getPattern(),page.getCurrentPage(),
					page.getOrderByColumns(),page.getOrderByType());
		} else {
			numberOfComputer=computerServices.count();
			numberOfPage=Math.max(1,computerServices.getNumberOfPage(numberOfComputer));
			page.setCurrentPage(Math.min(page.getCurrentPage(),numberOfPage));
			computerList = computerServices.getAllPagination(page.getCurrentPage(),
			page.getOrderByColumns(),page.getOrderByType());
		}
		
		
		request.setAttribute("page",page);
		
		request.setAttribute("numberOfPage", numberOfPage );
		request.setAttribute("numberOfComputer", numberOfComputer);
		request.setAttribute("computerList", computerList);
		
		request.setAttribute("add",request.getParameter("add"));
		request.setAttribute("delete",request.getParameter("delete"));
		request.setAttribute("update",request.getParameter("update"));
		
		return("dashboard");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		this.doGet(request, response);
	}
	
	
	@ExceptionHandler(TransactionException.class)
	public String handleAllException(Exception ex) {
 
		return "error-pages/transactionError";
 
	}

}
