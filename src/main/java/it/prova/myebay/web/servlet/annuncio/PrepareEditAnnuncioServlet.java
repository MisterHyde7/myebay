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
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/PrepareEditAnnuncioServlet")
public class PrepareEditAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInput = request.getParameter("idAnnuncio");
		Long idLong = Long.parseLong(idInput);

		try {
			
			Annuncio annuncioDaModificare = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElementoEagerConCategorie(idLong);
			
			List<Categoria> listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAllElements();
			request.setAttribute("mappaCategorieConSelezionati_attr",
					UtilityForm.buildCheckedCategorieFromCategorieAlreadyInAnnuncio(listaCategorie, annuncioDaModificare.getCategorie()));
			request.setAttribute("annuncioDaModificare", annuncioDaModificare);
		} catch (Exception e) {

		}

		request.getRequestDispatcher("/annuncio/edit.jsp").forward(request, response);
		
	}

}
