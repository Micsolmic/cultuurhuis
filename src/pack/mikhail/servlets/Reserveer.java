package pack.mikhail.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import pack.mikhail.entities.Mandje;
import pack.mikhail.entities.Voorstelling;
import pack.mikhail.repositories.Repo;

/**
 * Servlet implementation class Reserveer
 */
@WebServlet("/reserveer")
public class Reserveer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/reserveer.jsp";
	private static final String REDIRECT_URL = "%s/index";
	private final transient Repo cultuurhuisRepository = new Repo();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// maak sessie indien nog niet bestaat
				// en set mandje sessie attribuut
				if (request.getSession(false) == null) {

					HttpSession session = request.getSession();
					Mandje mand = new Mandje();			
					session.setAttribute("mand", mand);
				}
		
		
		PrintWriter out = response.getWriter();


		Voorstelling voorstelling = null;
      
	if (request.getParameter("voorstellingId") == null) {
		response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
		} else {

			
			try {
				int voorstellingId = Integer.valueOf(request.getParameter("voorstellingId"));
				voorstelling = cultuurhuisRepository.getVoorstellingById(voorstellingId);
				HttpSession session = request.getSession(false);
				
				Mandje mand = (Mandje)session.getAttribute("mand");
			  //  out.println(mand);
			   
			int zitjes = (mand.getZitjes(voorstellingId)); 
			    //out.println(zitjes+" zitjes");
			if (zitjes > -1) {
					request.setAttribute("zitjes", zitjes);
				}

			
			request.setAttribute("voorstelling", voorstelling);
			request.getRequestDispatcher(VIEW).forward(request, response);

			} catch (SQLException ex) {
				out.println(ex);
			}
			
			
	}
	


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		int voorstellingId = Integer.valueOf(request.getParameter("voorstellingId"));
		int zitjes = Integer.valueOf(request.getParameter("plaatsen"));

		HttpSession session = request.getSession();
		Mandje mandje = (Mandje) session.getAttribute("mand");
		
		HashMap<Integer, Integer> reserveringen = mandje.getReserveringen();
		
		
		//indien zitjes al eerder besteld vr de voorstelling, tel oude en nieuwe zitjes samen 
		if(reserveringen.get(voorstellingId)!=null){
			
			int zitjesAlBesteld = reserveringen.get(voorstellingId);
			zitjes += zitjesAlBesteld;
			
		}
		
		mandje.addReservatieEntry(voorstellingId, zitjes);
		
		
		response.sendRedirect(request.getContextPath()+"/bekijkmandje");
		
	}

	// set resource.name en gebruik deze om een datasource object te maken
	// stel deze in als variabele in de repository

	@Resource(name = Repo.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		cultuurhuisRepository.setDataSource(dataSource);
	}

}
