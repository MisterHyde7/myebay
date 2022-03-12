package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/admin/PrepareDeleteUtentePerAdminServlet")
public class PrepareDeleteUtentePerAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtente = request.getParameter("idUtente");
		
		try {
			
			Utente utenteDaDisabilitare = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idUtente));
			request.setAttribute("utente_delete_attr", utenteDaDisabilitare);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("delete.jsp").forward(request, response);
	}

}
