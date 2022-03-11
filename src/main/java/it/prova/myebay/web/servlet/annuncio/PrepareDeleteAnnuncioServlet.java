package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/PrepareDeleteAnnuncioServlet")
public class PrepareDeleteAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idAnnuncio = request.getParameter("idAnnuncio");
		
		try {
			
			Annuncio annuncioDaEliminare = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(Long.parseLong(idAnnuncio));
			request.setAttribute("annuncio_delete_attr", annuncioDaEliminare);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/annuncio/delete.jsp").forward(request, response);
		
	}

}
