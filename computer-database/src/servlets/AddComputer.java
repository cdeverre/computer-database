package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ServiceFactory;
import tools.Tools;
import domainClasses.Company;
import domainClasses.Computer;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Company> companyList = ServiceFactory.getCompanyServices().getAll();
		request.setAttribute("companyList", companyList);
			
		getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean error=false;
		String name =request.getParameter("name");
		if (name==null || name.equals("")) {
			error=true;
		}
		
		Calendar dateIntroduced = new GregorianCalendar();
		String introduced=request.getParameter("introduced");
		if (introduced!=null && !introduced.equals("")) {
			error=!(Tools.validDate(introduced));
			if(!error) {
				Tools.setCalendar(dateIntroduced, introduced);
			}
		} else {
			dateIntroduced=null;
		}
		
		Calendar dateDiscontinued = new GregorianCalendar();
		String discontinued=request.getParameter("discontinued");
		if(discontinued!=null&& !discontinued.equals("")) {
			error=!(Tools.validDate(discontinued));
			if(!error) {
				Tools.setCalendar(dateDiscontinued, discontinued);
			}
		} else {
			dateDiscontinued=null;
		}
				
		String idString =request.getParameter("company");
		Company company=null;
		if(idString!=null && ! idString.equals("null")) {
			try {
				int id=Integer.parseInt(idString);
				String companyName=ServiceFactory.getCompanyServices().getName(id);
				
				company = new Company(id,companyName);
			} catch (NumberFormatException e) {
			}
			
		} 
		
		
		Computer computer=new Computer(name,dateIntroduced,dateDiscontinued,company);
		
		if (!error) {
			ServiceFactory.getComputerServices().create(computer);
			
			response.sendRedirect("/computer-database/Dashboard");
		} else {
			response.sendRedirect("/computer-database/AddComputer");
		}
	}
	

}
