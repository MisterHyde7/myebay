package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/ExecuteBuyAnnuncioServlet")
public class ExecuteBuyAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtenteParam = request.getParameter("idUtente");
		String prezzoParam = request.getParameter("prezzo");
		
		try {
			
			MyServiceFactory.getUtenteServiceInstance().procediAllAcquisto(Long.parseLong(idUtenteParam));
			
		} catch (Exception e) {
			
		}
	}

}
