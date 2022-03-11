package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;
import it.prova.myebay.utility.UtilityUtente;

@WebServlet("/admin/ExecuteInsertUtenteAdminServlet")
public class ExecuteInsertUtenteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String[] ruoliSelezionati = request.getParameterValues("ruoloEntry");

		Utente utenteInstance = UtilityUtente.createUtenteFromParams(nomeParam, cognomeParam, usernameParam,
				passwordParam);

		try {

			// se la validazione non risulta ok
			if (!UtilityUtente.validateUtenteBean(utenteInstance)) {

				request.setAttribute("mappaRuoliSelezionati_attr", UtilityForm.buildCheckedRuoliForPages(
						MyServiceFactory.getRuoloServiceInstance().listAllElements(), ruoliSelezionati));

				request.setAttribute("insert_utente_attr", utenteInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("insert.jsp").forward(request, response);
				return;
			}
			for (String stringItem : ruoliSelezionati != null ? ruoliSelezionati : new String[] {}) {
				utenteInstance.getRuoli().add(MyServiceFactory.getRuoloServiceInstance().listAllElements()
						.get(Integer.valueOf(stringItem)));
			}
			utenteInstance.setDateCreated(new Date());
			
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteInstance);
			request.setAttribute("utente_show_attr", utenteInstance);
			request.setAttribute("ruoli", utenteInstance.getRuoli());

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("successMessage", "Elemento inserito con successo");
		request.getRequestDispatcher("show.jsp").forward(request, response);

	}

}
