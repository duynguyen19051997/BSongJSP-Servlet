package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDAO;

/**
 * Servlet implementation class PublicCommentAjaxController
 */
//@WebServlet("/PublicCommentAjaxController")
public class PublicCommentAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicCommentAjaxController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		ContactDAO contactDAO = new ContactDAO();

		String name = request.getParameter("aName");
		String email = request.getParameter("aEmail");
		String website = request.getParameter("aWebsite");
		String message = request.getParameter("aMessage");
		if (request.getParameter("aId") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
			rd.forward(request, response);
		}
		int songId = Integer.valueOf(request.getParameter("aId"));
		Contact obJContact = new Contact(0, name, email, website, message, songId);
		if (contactDAO.addItem(obJContact) > 0) {
			ArrayList<Contact> listsContact = contactDAO.getItemsBySongID(songId);
			request.setAttribute("listsContact", listsContact);
			request.setAttribute("empty", "");
			RequestDispatcher rd = request.getRequestDispatcher("/public/comment.jsp");
			rd.forward(request, response);
		}
	}

}
