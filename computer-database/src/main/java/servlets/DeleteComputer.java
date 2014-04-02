package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import services.ComputerServices;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet("/DeleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ComputerServices computerServices;
	
	 @Override
	    public void init() throws ServletException {
	    	super.init();
	    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
	    }
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComputer() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		computerServices.delete(id);
		
				
		getServletContext().getRequestDispatcher("/Dashboard").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/computer-database/Dashboard");
	}

}
