package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteAnnuncioServlet")
public class ExecuteDeleteAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAnnuncioParam = request.getParameter("idAnnuncio");
		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idAnnuncioParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("HomeServlet").forward(request, response);
			return;
		}

		try {
			if (MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(Long.parseLong(idAnnuncioParam))
					.isAperto()) {
				MyServiceFactory.getAnnuncioServiceInstance().rimuovi(Long.parseLong(idAnnuncioParam));
			} else {
				request.setAttribute("errorMessage",
						"Attenzione l'annuncio e' stato chiuso e non puo' essere eliminato");
			}
		} catch (ElementNotFoundException e) {
			request.getRequestDispatcher("PrepareListAnnuncioServlet?operationResult=NOT_FOUND").forward(request,
					response);
			return;
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("HomeServlet").forward(request, response);
			return;
		}

		String destinazione = "PrepareListAnnuncioServlet?idUtente=" + idUtenteParam;
		response.sendRedirect(destinazione);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
