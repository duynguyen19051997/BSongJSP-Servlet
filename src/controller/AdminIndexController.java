package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.AuthUtil;
import util.DBConnectionUtil;

/**
 * Servlet implementation class AdminIndexCatController
 */
//@WebServlet("/AdminIndexController")
public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminIndexController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		int countCat = DBConnectionUtil.countItems("SELECT COUNT(*) FROM categories");
		int countSong = DBConnectionUtil.countItems("SELECT COUNT(*) FROM songs");
		int countUser = DBConnectionUtil.countItems("SELECT COUNT(*) FROM users");
		int countContact = DBConnectionUtil.countItems("SELECT COUNT(*) FROM contacts");
		int countSlide = DBConnectionUtil.countItems("SELECT COUNT(*) FROM slides");
		request.setAttribute("countCat", countCat);
		request.setAttribute("countSong", countSong);
		request.setAttribute("countUser", countUser);
		request.setAttribute("countContact", countContact);
		request.setAttribute("countSlide", countSlide);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/index/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
