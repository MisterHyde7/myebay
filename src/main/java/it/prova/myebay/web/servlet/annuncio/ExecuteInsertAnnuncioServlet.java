package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityAnnuncio;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/ExecuteInsertAnnuncioServlet")
public class ExecuteInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String testoParam = request.getParameter("testo");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieSelezionate = request.getParameterValues("categoriaEntry");
		String idUtente = request.getParameter("idUtente");

		Annuncio annuncioInstance = UtilityAnnuncio.createAnnuncioFromParam(testoParam, prezzoParam);

		try {

			// se la validazione non risulta ok
			if (!UtilityAnnuncio.validaAnnuncioBean(annuncioInstance)) {

				request.setAttribute("mappaCategorieConSelezionati_attr", UtilityForm.buildCheckedCategorieForPages(
						MyServiceFactory.getCategoriaServiceInstance().listAllElements(), categorieSelezionate));

				request.setAttribute("insert_annuncio_attr", annuncioInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/annuncio/insert.jsp").forward(request, response);
				return;
			}
			for (String stringItem : categorieSelezionate != null ? categorieSelezionate : new String[] {}) {
				annuncioInstance.getCategorie()
						.add(MyServiceFactory.getCategoriaServiceInstance().listAllElements().get(Integer.valueOf(stringItem)));
			}
			annuncioInstance.setDataInserimento(new Date());
			annuncioInstance.setUtente(MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idUtente)));
			MyServiceFactory.getAnnuncioServiceInstance().inserisciNuovo(annuncioInstance);
			request.setAttribute("show_annuncio_attr", annuncioInstance);
			request.setAttribute("categorie", annuncioInstance.getCategorie());

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("successMessage", "Elemento inserito con successo");
		request.getRequestDispatcher("/annuncio/show.jsp").forward(request, response);

	}

}
