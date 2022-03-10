package it.prova.myebay.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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

@WebServlet("/ExecuteSearchAnnuncioServlet")
public class ExecuteSearchAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String testoAnnuncioParam = request.getParameter("annuncio");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieParam = request.getParameterValues("categoriaInput");

		Annuncio annuncioDaCercare = UtilityAnnuncio.createAnnuncioFromParam(testoAnnuncioParam, prezzoParam);

		try {
			List<Categoria> listaCategorieCompleta = MyServiceFactory.getCategoriaServiceInstance().listAllElements();
			Set<Categoria> setCategorie = UtilityForm.buildSetCategorieForAnnuncio(listaCategorieCompleta,
					categorieParam);
			if (!setCategorie.isEmpty() && setCategorie!=null)
				annuncioDaCercare.setCategorie(setCategorie);
			List<Annuncio> annunciTrovati = MyServiceFactory.getAnnuncioServiceInstance()
					.findByExample(annuncioDaCercare);
			request.setAttribute("search_annunci_attr", annunciTrovati);
		} catch (Exception e) {

			e.printStackTrace();
		}

		request.getRequestDispatcher("/annuncio/results.jsp").forward(request, response);
	}

}
