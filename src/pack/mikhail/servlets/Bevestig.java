package pack.mikhail.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import pack.mikhail.entities.Klant;
import pack.mikhail.repositories.Repo;

/**
 * Servlet implementation class BevestigReservatie
 */
@WebServlet("/bevestig")
public class Bevestig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/bevestig.jsp";
	private final transient Repo cultuurhuisRepository = new Repo();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String login = request.getParameter("login");
		String passwd = request.getParameter("passwd");
		try {
			
			Optional<Klant> klant = cultuurhuisRepository.zoekKlant(login, passwd);
			if(!klant.isPresent()) {
				
				request.setAttribute("foutmelding", "Verkeerde gebruikersnaam of passwoord");
				request.getRequestDispatcher(VIEW).forward(request, response);
			}else {
				
				request.setAttribute("klantinfo", klant.get().toInfo());
				request.setAttribute("stap2ontgrendeld", true);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
			
		}catch(SQLException ex) {out.println(ex);}
		
		
	}
	
	

	// set resource.name en gebruik deze om een datasource object te maken
	// stel deze in als variabele in de repository

	@Resource(name = Repo.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		cultuurhuisRepository.setDataSource(dataSource);
	}

}
