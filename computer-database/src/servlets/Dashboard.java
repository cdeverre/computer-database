package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ServiceFactory;
import domainClasses.Computer;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numberOfComputer =ServiceFactory.getComputerServices().count();
		request.setAttribute("numberOfComputer", numberOfComputer);
		
		String pageNumberString=request.getParameter("pageNumber");
		int pageNumber=0;
		if(pageNumberString!=null) {
			pageNumber=Integer.parseInt(pageNumberString);
		}
		ArrayList<Computer> computerList = ServiceFactory.getComputerServices().getAllPagination(pageNumber);
		request.setAttribute("computerList", computerList);
		request.setAttribute("numberOfPage", ServiceFactory.getComputerServices().getNumberOfPage(numberOfComputer) );
		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
