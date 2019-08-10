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
import util.AuthUtil;
import util.DefineUtil;

/**
 * Servlet implementation class AminIndexContactController
 */
//@WebServlet("/AminIndexContactController")
public class AminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AminIndexContactController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		ContactDAO contactDao = new ContactDAO();
		int numberOfItem = contactDao.getNumberOfItem();
		int numberOfPage = (int) Math.ceil((float) numberOfItem / DefineUtil.NUMBER_PER_PAGE);
		int currentPage = 1;
		try {
			if (request.getParameter("page") != null) {
				currentPage = Integer.valueOf(request.getParameter("page"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (currentPage > numberOfPage) {
			currentPage = numberOfPage;
		}
		if (currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		ArrayList<Contact> listCt = contactDao.getItemsPagination(offset);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("numberOfItem", numberOfItem);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listCt", listCt);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/contact/index.jsp");
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
