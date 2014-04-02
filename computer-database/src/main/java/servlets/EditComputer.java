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

import model.Company;
import model.Computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import services.CompanyServices;
import services.ComputerServices;
import tools.Tools;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private ComputerServices computerServices;
	
	@Autowired
	private CompanyServices companyServices;
	
	
	 @Override
	    public void init() throws ServletException {
	    	super.init();
	    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
	    }
	 
	 
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
		Computer computer=computerServices.find(id);
		request.setAttribute("computerName", computer.getName());
		request.setAttribute("computerIntroduced", Tools.createStringFromCalendar(computer.getDateIntroduced()));
		request.setAttribute("computerDiscontinued", Tools.createStringFromCalendar(computer.getDateDiscontinued()));
		request.setAttribute("companyId", computer.getCompany().getId());
		request.setAttribute("id", id);

		
		ArrayList<Company> companyList = companyServices.getAll();
		request.setAttribute("companyList", companyList);
		
		getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		boolean error=false;
		String name =request.getParameter("name");
		if (name==null || "".equals(name)) {
			error=true;
		}		
		Calendar dateIntroduced = new GregorianCalendar();
		String introduced=request.getParameter("introduced");
		if (introduced!=null && !"".equals(introduced)) {
			error=!(Tools.validDate(introduced));
			if(!error) {
				Tools.setCalendar(dateIntroduced, introduced);
			}
		} else {
			dateIntroduced=null;
		}
		
		Calendar dateDiscontinued = new GregorianCalendar();
		String discontinued=request.getParameter("discontinued");
		if(discontinued!=null&& !"".equals(discontinued)) {
			error=!(Tools.validDate(discontinued));
			if(!error) {
				Tools.setCalendar(dateDiscontinued, discontinued);
			}		} else {
			dateDiscontinued=null;
		}
				
		String idString =request.getParameter("company");
		Company company=null;
		if(idString!=null && ! "null".equals(idString)) {
			int companyId=Integer.parseInt(idString);
			String companyName=companyServices.getName(companyId);
		
			company = new Company(companyId,companyName);
		} 
		
		
		Computer computer=new Computer(Integer.parseInt(id),name,dateIntroduced,dateDiscontinued,company);
		
		if (!error) {

			computerServices.update(computer);
			
			response.sendRedirect("/computer-database/Dashboard");
		} else {
			response.sendRedirect("/computer-database/AddComputer");
		}
	}

}
