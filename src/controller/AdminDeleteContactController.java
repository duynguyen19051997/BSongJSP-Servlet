package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContactDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDeleteCatController
 */
//@WebServlet("/AdminDeleteContactController")
public class AdminDeleteContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDeleteContactController() {
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
		ContactDAO contacDAO = new ContactDAO();
		int id = Integer.valueOf(request.getParameter("id"));
		if(contacDAO.deleteItem(id) > 0) {
			//success
			response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=3");
			return;
		}else {
			//fail
			response.sendRedirect(request.getContextPath()+"/admin/contact/index?msg=0");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
