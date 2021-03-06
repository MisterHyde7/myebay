package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/ExecuteBuyAnnuncioServlet")
public class ExecuteBuyAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idUtenteParam = request.getParameter("idUtente");
		String idParam = request.getParameter("idAnnuncio");

		try {

			if (MyServiceFactory.getUtenteServiceInstance().procediAllAcquisto(Long.parseLong(idUtenteParam), Long.parseLong(idParam))) {

				Set<Acquisto> acquistiEffettuati = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElementoEagerAcquisti(Long.parseLong(idUtenteParam)).getAcquisti();
				request.setAttribute("acquisti_list_attr", acquistiEffettuati);
			}

		} catch (Exception e) {

		}
		
		request.getRequestDispatcher("/acquisto/results.jsp").forward(request, response);
	}

}
