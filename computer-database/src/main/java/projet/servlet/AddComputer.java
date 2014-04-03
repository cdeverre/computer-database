package projet.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import projet.dto.ComputerDto;
import projet.mapper.Mapper;
import projet.model.Company;
import projet.model.Computer;
import projet.service.CompanyServices;
import projet.service.ComputerServices;
import projet.validator.ComputerValidator;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Autowired
	private CompanyServices companyServices;
	
	@Autowired
	private ComputerServices computerServices;
	
	@Autowired
	private Mapper mapper;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
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
		ArrayList<Company> companyList = companyServices.getAll();
		request.setAttribute("companyList", companyList);
			
		getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error;
		String name =request.getParameter("name");
		String introduced=request.getParameter("introduced");
		String discontinued=request.getParameter("discontinued");
		String idString =request.getParameter("company");
		
		ComputerDto computerDto=new ComputerDto(name,introduced,discontinued,idString);
		
		error=ComputerValidator.validate(computerDto);
		
		
		if (error.equals("00000")) {
			Computer computer=mapper.computerDtoToComputer(computerDto);
			
			companyServices.exist(computer.getCompany());
			
			computerServices.create(computer);
			
			response.sendRedirect("/computer-database/Dashboard?add=true");
		} else {
			request.setAttribute("computerName",name);
			request.setAttribute("computerIntroduced",introduced);
			request.setAttribute("computerDiscontinued",discontinued);
			if(error.substring(4,5).equals("0")) {
				request.setAttribute("companyId",idString);
			} else {
				request.setAttribute("companyId",0);
			}
			
			ArrayList<Company> companyList = companyServices.getAll();
			request.setAttribute("companyList", companyList);
			
			request.setAttribute("errorName",error.substring(1, 2));
			request.setAttribute("errorIntroduced",error.substring(2, 3));
			request.setAttribute("errorDiscontinued",error.substring(3, 4));
			request.setAttribute("errorCompanyId",error.substring(4, 5));
			getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
			
		}
	}
	

}
