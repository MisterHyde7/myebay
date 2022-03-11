package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/admin/PrepareInsertUtenteAdminServlet")
public class PrepareInsertUtenteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("insert_utente_attr", new Utente());

		try {
			request.setAttribute("ruoli", MyServiceFactory.getRuoloServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}

}
