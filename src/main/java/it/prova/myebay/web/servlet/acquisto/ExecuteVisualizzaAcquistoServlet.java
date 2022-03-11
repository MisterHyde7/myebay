package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/ExecuteVisualizzaAcquistoServlet")
public class ExecuteVisualizzaAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idAcquisto = request.getParameter("idAcquisto");
		
		try {
			
			Acquisto acquistoDaVisualizzare = MyServiceFactory.getAcquistoServiceInstance().caricaSingoloElemento(Long.parseLong(idAcquisto));
			request.setAttribute("acquisto_show_attr", acquistoDaVisualizzare);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/acquisto/show.jsp").forward(request, response);
	}

}
