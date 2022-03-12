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

@WebServlet("/admin/PrepareEditUtentePerAdminServlet")
public class PrepareEditUtentePerAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtente = request.getParameter("idUtente");

		try {
			
			Utente utenteDaModificare = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElementoConRuoli(Long.parseLong(idUtente));
			
			List<Ruolo> listaRuoli = MyServiceFactory.getRuoloServiceInstance().listAllElements();
			request.setAttribute("mappaRuoliSelezionati_attr",
					UtilityForm.buildCheckedRuoliFromUtenteAlreadyInUtente(listaRuoli, utenteDaModificare.getRuoli()));
			request.setAttribute("utenteDaModificare", utenteDaModificare);
		} catch (Exception e) {

		}

		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

}
