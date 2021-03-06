package it.prova.myebay.web.servlet.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PrepareLoginDaAnnuncioServlet")
public class PrepareLoginDaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAnnuncio = request.getParameter("id");
			request.setAttribute("idAnnuncio", idAnnuncio);
		request.getRequestDispatcher("loginDaAnnuncio.jsp").forward(request, response);
	}

}
