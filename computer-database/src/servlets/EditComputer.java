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
 * Servlet implementation class EditComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputer() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		Computer computer=ServiceFactory.getComputerServices().find(id);
		request.setAttribute("computerName", computer.getName());
		request.setAttribute("computerIntroduced", Tools.createStringFromCalendar(computer.getDateIntroduced()));
		request.setAttribute("computerDiscontinued", Tools.createStringFromCalendar(computer.getDateDiscontinued()));
		request.setAttribute("companyId", computer.getCompany().getId());
		request.setAttribute("id", id);

		
		ArrayList<Company> companyList = ServiceFactory.getCompanyServices().getAll();
		request.setAttribute("companyList", companyList);
		
		getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String name =request.getParameter("name");
		
		Calendar dateIntroduced = new GregorianCalendar();
		Tools.setCalendar(dateIntroduced, request.getParameter("introduced"));
		
		Calendar dateDiscontinued = new GregorianCalendar();
		Tools.setCalendar(dateDiscontinued, request.getParameter("discontinued"));
		
		int companyId=Integer.parseInt(request.getParameter("company"));
		String companyName=ServiceFactory.getCompanyServices().getName(companyId);
		
		Company company = new Company(companyId,companyName);
		
		
		Computer computer=new Computer(Integer.parseInt(id),name,dateIntroduced,dateDiscontinued,company);
		
		ServiceFactory.getComputerServices().update(computer);
		
		response.sendRedirect("/computer-database/Dashboard");
	}

}
