package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/PrepareListAnnuncioDaComprareServlet")
public class PrepareListAnnuncioDaComprareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<Annuncio> annunciPubblicati = MyServiceFactory.getAnnuncioServiceInstance().listAllElements();
			request.setAttribute("search_annunci_attr", annunciPubblicati);
			if (request.getAttribute("errorMessage") != null) {
				request.setAttribute("errorMessage",
						"Attenzione l'annuncio e' stato chiuso e non puo' essere eliminato");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/annuncio/results.jsp").forward(request, response);
	}

}
