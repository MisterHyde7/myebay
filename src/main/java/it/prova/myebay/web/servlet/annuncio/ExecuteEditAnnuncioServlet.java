package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityAnnuncio;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/ExecuteEditAnnuncioServlet")
public class ExecuteEditAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// estraggo input
		String testoParam = request.getParameter("testo");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieSelezionate = request.getParameterValues("categoriaInput");
		String idAnnuncio = request.getParameter("idAnnuncio");
		
		try {
			
			if (prezzoParam.isBlank() || prezzoParam == "") {
				prezzoParam = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(Long.parseLong(idAnnuncio)).toString();
			}
			
			Annuncio annuncioInstance = UtilityAnnuncio.createAnnuncioFromParam(testoParam, prezzoParam);

			List<Categoria> listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAllElements();

			if (!UtilityAnnuncio.validaAnnuncioBean(annuncioInstance)) {

				request.setAttribute("mappaCategorieConSelezionati_attr",
						UtilityForm.buildCheckedCategorieForPages(listaCategorie, categorieSelezionate));

				request.setAttribute("annuncioDaModificare", annuncioInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/annuncio/edit.jsp").forward(request, response);
				return;
			}

			if (categorieSelezionate != null) {
				for (String stringItem : categorieSelezionate)
					annuncioInstance.getCategorie().add(listaCategorie.get(Integer.valueOf(stringItem)));
			}
			
			MyServiceFactory.getAnnuncioServiceInstance().aggiorna(annuncioInstance);
			request.setAttribute("show_annuncio_attr", annuncioInstance);
		} catch (Exception e) {

		}

		request.setAttribute("successMessage", "Elemento modificato con successo");
		request.getRequestDispatcher("/utente/show.jsp").forward(request, response);
	}
}