package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/PrepareInsertAnnuncioServlet")
public class PrepareInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("insert_annuncio_attr", new Annuncio());
		
		try {
			request.setAttribute("categorie", MyServiceFactory.getCategoriaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/annuncio/insert.jsp").forward(request, response);
	}

}
