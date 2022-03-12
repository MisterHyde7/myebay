package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;
import it.prova.myebay.utility.UtilityUtente;

@WebServlet("/admin/ExecuteEditUtenteAdminServlet")
public class ExecuteEditUtenteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String[] ruoliSelezionati = request.getParameterValues("ruoloInput");
		String idUtente = request.getParameter("idUtente");

		Utente utenteInstance = UtilityUtente.createUtenteFromParams(nomeParam, cognomeParam, usernameParam);
		utenteInstance.setId(Long.parseLong(idUtente));

		try {
			List<Ruolo> listaRuoli = MyServiceFactory.getRuoloServiceInstance().listAllElements();
			
			if (!UtilityUtente.validateUtenteBeanSenzaPassword(utenteInstance)) {

				if (ruoliSelezionati != null) {
					for (String stringItem : ruoliSelezionati)
						utenteInstance.getRuoli().add(listaRuoli.get(Integer.valueOf(stringItem)));
				}
				request.setAttribute("mappaRuoliSelezionati_attr",
						UtilityForm.buildCheckedRuoliFromUtenteAlreadyInUtente(listaRuoli, utenteInstance.getRuoli()));

				request.setAttribute("utenteDaModificare", utenteInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("edit.jsp").forward(request, response);
				return;
			}
			
			MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteInstance, Long.parseLong(idUtente));
			request.setAttribute("utente_show_attr", MyServiceFactory.getUtenteServiceInstance().caricaSingoloElementoConRuoli(Long.parseLong(idUtente)));
			request.setAttribute("mappaRuoliSelezionati_attr",
					UtilityForm.buildCheckedRuoliFromUtenteAlreadyInUtente(listaRuoli, utenteInstance.getRuoli()));
		} catch (Exception e) {

		}

		request.getRequestDispatcher("show.jsp").forward(request, response);
	}

}
