package pack.mikhail.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import pack.mikhail.entities.Klant;
import pack.mikhail.entities.Mandje;
import pack.mikhail.repositories.Repo;

/**
 * Servlet implementation class NieuweKlant
 */
@WebServlet("/nieuweklant")
public class NieuweKlant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/nieuweklant.jsp";
	private static final String REDIRECT_URL = "/bevestig";
	
	private final transient Repo cultuurhuisRepository = new Repo();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();			
			
	
		request.getRequestDispatcher(VIEW).forward(request, response);  
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// maak sessie indien nog niet bestaat
				// en set mandje sessie attribuut
				if (request.getSession(false) == null) {

					HttpSession session = request.getSession();
					Mandje mand = new Mandje();			
					session.setAttribute("mand", mand);
				}
		
		
		
		PrintWriter out = response.getWriter();
		String voornaam = request.getParameter("voornaam");
		String familienaam = request.getParameter("familienaam");
		String straat = request.getParameter("straat");
		int huisnr = Integer.valueOf(request.getParameter("huisnummer"));
		int postcode = Integer.valueOf(request.getParameter("postcode"));		
		String gemeente = request.getParameter("gemeente");
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		String paswoord = request.getParameter("paswoord");
		String herhaalPaswoord = request.getParameter("herhaalPaswoord");
		
		
		//dummy id van 0
		Klant  klantInWording = new Klant(0,voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam, paswoord);
		try {
			
			
			if(!paswoord.equals(herhaalPaswoord)) {
				
				request.setAttribute("klantInWording", klantInWording);
				request.setAttribute("onpaswoordelijk", true);
				request.getRequestDispatcher(VIEW).forward(request, response);
				
				
			}	else {
			
			
			if(cultuurhuisRepository.loginBestaatAl(gebruikersnaam)) {
				request.setAttribute("loginBezet", true);
				request.setAttribute("klantInWording", klantInWording);
				request.getRequestDispatcher(VIEW).forward(request, response);
				
				
				
				
			}else {
				
				
				cultuurhuisRepository.commitKlant(klantInWording);
				
				response.sendRedirect(request.getContextPath()+REDIRECT_URL+"?klantAangemaakt=true");
			}
			}
			
		}catch(SQLException ex){out.println(ex);}
		}
	 
	

	

	// set resource.name en gebruik deze om een datasource object te maken
	// stel deze in als variabele in de repository

	@Resource(name = Repo.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		cultuurhuisRepository.setDataSource(dataSource);
	}
}
