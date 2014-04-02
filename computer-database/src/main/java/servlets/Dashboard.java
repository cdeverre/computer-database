package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import services.ComputerServices;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private ComputerServices computerServices;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numberOfComputer =0;
		ArrayList<Computer> computerList=new ArrayList<Computer>();
		
		String currentPageString=request.getParameter("currentPage");
		int currentPage=1;
		if(currentPageString!=null) {
			currentPage=Integer.parseInt(currentPageString);
			currentPage=Math.max(currentPage, 1);
		} 
		
		String orderByColumns=request.getParameter("orderByColumns");
		if (orderByColumns==null) {
			orderByColumns="name";
		}
		
		boolean orderByType=true;
		String orderByTypeString=request.getParameter("orderByType");
		if (orderByTypeString!=null ){
			orderByType=Boolean.parseBoolean(orderByTypeString);
		}
		
		
		String pattern=request.getParameter("pattern");
		
		int numberOfPage=1;
		if(	pattern!=null && !"".equals(pattern)) {
			numberOfComputer=computerServices.count(pattern);
			numberOfPage=Math.max(1,computerServices.getNumberOfPage(numberOfComputer));
			currentPage=Math.min(currentPage,numberOfPage);
			computerList = computerServices.search(pattern,currentPage,orderByColumns,orderByType);
		} else {
			numberOfComputer=computerServices.count();
			numberOfPage=Math.max(1,computerServices.getNumberOfPage(numberOfComputer));
			currentPage=Math.min(currentPage, numberOfPage);
			computerList = computerServices.getAllPagination(currentPage,orderByColumns,orderByType);
		}
		
//		UrlParameters urlParameters=new UrlParameters(currentPage,numberOfPage,orderByColumns,orderByType,pattern);
		
//		request.setAttribute("urlParameters", urlParameters);
		
		request.setAttribute("pattern", pattern);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("numberOfPage", numberOfPage );
		request.setAttribute("orderByColumns", orderByColumns);
		request.setAttribute("orderByType", orderByType);
		
		request.setAttribute("numberOfComputer", numberOfComputer);
		request.setAttribute("computerList", computerList);
		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
