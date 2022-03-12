package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/admin/ExecuteDeleteUtenteAdminServlet")
public class ExecuteDeleteUtenteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteParam)) {
			
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}

		try {
			
			MyServiceFactory.getUtenteServiceInstance().rimuovi(Long.parseLong(idUtenteParam));
		} catch (ElementNotFoundException e) {
			request.getRequestDispatcher("PrepareListUtenteServlet?operationResult=NOT_FOUND").forward(request, response);
			return;
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}

		response.sendRedirect("PrepareListUtenteServlet?operationResult=SUCCESS");
	}

}
