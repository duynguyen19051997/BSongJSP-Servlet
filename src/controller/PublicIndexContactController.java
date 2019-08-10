package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDAO;

/**
 * Servlet implementation class PublicIndexContactController
 */
//@WebServlet("/PublicIndexContactController")
public class PublicIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicIndexContactController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ContactDAO conDao = new ContactDAO();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		Contact contact = new Contact(0, name, email, website, message);
		if(conDao.sendContact(contact) > 0) {
			//success
			RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp?msg=1");
			rd.forward(request, response);
			return;
		}else {
			//fail
			RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
	}

}
