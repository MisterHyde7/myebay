package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/admin/ExecuteVisualizzaUtentePerAdminServlet")
public class ExecuteVisualizzaUtentePerAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtente = request.getParameter("idUtente");
		
		try {
			
			Utente utenteDaVisualizzare = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElementoConRuoli(Long.parseLong(idUtente));
			request.setAttribute("utente_show_attr", utenteDaVisualizzare);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("show.jsp").forward(request, response);
	}

}
