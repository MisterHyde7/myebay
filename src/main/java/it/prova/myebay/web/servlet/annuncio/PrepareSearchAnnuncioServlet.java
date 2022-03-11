package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/PrepareSearchAnnuncioServlet")
public class PrepareSearchAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtente = request.getParameter("idUtente");
		
		try {
			
			request.setAttribute("search_categorie_attr", MyServiceFactory.getCategoriaServiceInstance().listAllElements());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("idUtente", Long.parseLong(idUtente));
		request.getRequestDispatcher("/annuncio/search.jsp").forward(request, response);
	}

}
