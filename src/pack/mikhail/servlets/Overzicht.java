package pack.mikhail.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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
 * Servlet implementation class Overzicht
 */
@WebServlet("/overzicht")
public class Overzicht extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/overzicht.jsp";
	private final transient Repo cultuurhuisRepository = new Repo();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		
		HttpSession session = request.getSession();
		
		
		
		int klantid = (int) session.getAttribute("klantid");
		
		
		Mandje mand = (Mandje) session.getAttribute("mand");
		HashMap<Integer,Integer> reserveringen = mand.getReserveringen();
		Set<Entry<Integer,Integer>> entryset = reserveringen.entrySet();
		List<Reservatieregel> lijstReservatieregels = new ArrayList<>();
		for(Entry<Integer,Integer> entry: entryset) {
			
			int idVoorstelling = entry.getKey();
			int zitjes = entry.getValue();
			
			try {
				
			Reservatieregel rr = cultuurhuisRepository.commitReservering(idVoorstelling, zitjes, klantid);
			
			lijstReservatieregels.add(rr);
			mand.ledig();
			}catch(SQLException ex) {out.println(ex);}
			
		}
		
	request.setAttribute("mand", mand);
		request.setAttribute("entryset", entryset);
		request.setAttribute("lijstReservatieregels", lijstReservatieregels);
		request.getRequestDispatcher(VIEW).forward(request, response);   
	}

	
	
	// set resource.name en gebruik deze om een datasource object te maken
		// stel deze in als variabele in de repository

		@Resource(name = Repo.JNDI_NAME)
		void setDataSource(DataSource dataSource) {
			cultuurhuisRepository.setDataSource(dataSource);
		}

}
