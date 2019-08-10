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
//@WebServlet("/AdminEditCatController")
public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminEditCatController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		CatDao catDao = new CatDao();
		int catId = Integer.valueOf(request.getParameter("id"));
		request.setAttribute("objCat", catDao.getItem(catId));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		CatDao catDao = new CatDao();
		int catId = Integer.valueOf(request.getParameter("id"));
		int sort = 500;
		try {
			if(request.getParameter("number") != null) {
				sort = Integer.valueOf(request.getParameter("number"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String catName = request.getParameter("name");
		if(catName.isEmpty() || "".equals(catName)) {
			request.setAttribute("name-error", "Vui lòng không để trống!");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
			rd.forward(request, response);
			return;
		}else {
			Category objCat = new Category(catId, catName, sort);
			if(catDao.editItem(objCat) > 0) {
				//success
				response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=2");
				return;
			}else {
				//fail
				response.sendRedirect(request.getContextPath() + "/admin/cat/edit?msg=0");
				return;
			}
		}
	}

}
