package pack.mikhail.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import pack.mikhail.entities.Mandje;
import pack.mikhail.entities.Reservatieregel;
import pack.mikhail.repositories.Repo;

/**
 * Servlet implementation class Bekijkmandje
 */
@WebServlet("/bekijkmandje")
public class Bekijkmandje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/bekijkmandje.jsp";  
	private static final String REDIRECT_URL = "%s/index";
	private final transient Repo cultuurhuisRepository = new Repo();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bekijkmandje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
	
		
		if(session == null) {
			
			response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
			
			
		}else {
			
			try {
				
				//haal mandje op uit de session en get reserveringen
				Mandje mand = (Mandje) session.getAttribute("mand");
				HashMap<Integer, Integer> reserveringen = mand.getReserveringen();	
				//Collection reservatieregels zal doorgegeven worden aan VIEW
				List<Reservatieregel> reservatieregels = new ArrayList<>();
				double totaal = 0;
				//overloop de reserveringen en maak een reservatieregel met de details van de voorstelling en bestelde plaatsen
				for(Entry entry: reserveringen.entrySet()) {
					
					//new Reservatieregel(Voorstelling, besteldePlaatsen)
					Reservatieregel rr = new Reservatieregel(cultuurhuisRepository.getVoorstellingById((int)entry.getKey()), (int)entry.getValue());
					reservatieregels.add(rr);
					
					totaal+=rr.getVoorstelling().getPrijs() * rr.getPlaatsen();
					
				}			
				
				request.setAttribute("reservatieregels", reservatieregels);
				request.setAttribute("totaal", totaal);
				request.getRequestDispatcher(VIEW).forward(request, response);
				
				
			}catch(SQLException ex) {out.println(ex);}
			
			
			
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
	
		
			
			//haal mandje op uit de session en get reserveringen
			Mandje mand = (Mandje) session.getAttribute("mand");
			
			
			String[] teVerwijderenIds = request.getParameterValues("verwijder");
			for(String s: teVerwijderenIds) {
				
			mand.verwijderReservatieEntry(Integer.valueOf(s));
				
			}
			
			
		response.sendRedirect(request.getContextPath()+"/bekijkmandje");
	
	
	
	
	
	}
	// set resource.name en gebruik deze om een datasource object te maken
		// stel deze in als variabele in de repository

		@Resource(name = Repo.JNDI_NAME)
		void setDataSource(DataSource dataSource) {
			cultuurhuisRepository.setDataSource(dataSource);
		}
	

}
