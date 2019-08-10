package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDao;
import util.AuthUtil;
@MultipartConfig
/**
 * Servlet implementation class AdminAddCatController
 */
//@WebServlet("/AdminAddCatController")
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddCatController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		CatDao catDao = new CatDao();
		String catName = request.getParameter("name");
		int sort = 500;
		try {
			if(request.getParameter("number") != null) {
				sort = Integer.valueOf(request.getParameter("number"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if(catName.isEmpty() || "".equals(catName)) {
			request.setAttribute("name-error", "Vui lòng không để trống!");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
			rd.forward(request, response);
			return;
		}else {
			Category objCat = new Category(0, catName, sort);
			if(catDao.addItem(objCat) > 0) {
				//success
				response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=1");
				return;
			}else {
				//fail
				response.sendRedirect(request.getContextPath() + "/admin/cat/add?msg=0");
				return;
			}
		}
	}

}
