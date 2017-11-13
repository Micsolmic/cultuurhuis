package pack.mikhail.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import pack.mikhail.entities.Mandje;
import pack.mikhail.repositories.Repo;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/index.jsp";
	private final transient Repo cultuurhuisRepository = new Repo();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		

		// maak sessie indien nog niet bestaat
		// en set attributen
		if (request.getSession(false) == null) {

			HttpSession session = request.getSession();
			session.setAttribute("mandje", new Mandje());
		}
		
		try{
			
			if(request.getParameter("ItemsInMandje")!=null) {
				
			request.setAttribute("extraLinks", true);
				
			}
			request.setAttribute("genres", cultuurhuisRepository.getGenres());
			
			
		request.getRequestDispatcher(VIEW).forward(request, response);
			
			
		}catch(SQLException ex){out.println(ex);}
		
		

	}

	// set resource.name en gebruik deze om een datasource object te maken
	// stel deze in als variabele in de repository

	@Resource(name = Repo.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		cultuurhuisRepository.setDataSource(dataSource);
	}

}
