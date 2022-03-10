package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityUtente;

@WebServlet("/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// estraggo input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Utente utenteInstance = UtilityUtente.createUtenteFromParams(nomeParam, cognomeParam, usernameParam,
				passwordParam);

		try {

			// se la validazione non risulta ok
			if (!UtilityUtente.validateUtenteBean(utenteInstance)) {

				request.setAttribute("insert_utente_attr", utenteInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/utente/register.jsp").forward(request, response);
				return;
			}
			utenteInstance.setStato(StatoUtente.CREATO);
			utenteInstance.setDateCreated(new Date());
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteInstance);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("successMessage", "Elemento inserito con successo");
		response.sendRedirect("index.jsp");
	}
}
