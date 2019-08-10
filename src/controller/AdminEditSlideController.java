package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Slide;
import model.dao.SlideDao;
import util.AuthUtil;
@MultipartConfig
/**
 * Servlet implementation class AdminEditSlideController
 */
//@WebServlet("/AdminEditSlideController")
public class AdminEditSlideController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminEditSlideController() {
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
		int id = Integer.valueOf(request.getParameter("id"));
		SlideDao slideDao = new SlideDao();
		Slide objSlide = slideDao.getItem(id);
		request.setAttribute("objSlide", objSlide);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/slide/edit.jsp");
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
		SlideDao slideDao = new SlideDao();
		int slideId = Integer.valueOf(request.getParameter("id"));
		int sort = 500;
		try {
			if(request.getParameter("number") != null) {
				sort = Integer.valueOf(request.getParameter("number"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		System.out.println(sort);
		Slide objSlide = new Slide(slideId, "", sort);
		if (slideDao.editSong(objSlide) > 0) {
			// success
			response.sendRedirect(request.getContextPath() + "/admin/slide/index?msg=2");
			return;
		} else {
			// fail
			RequestDispatcher rd = request.getRequestDispatcher("/admin/slide/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
	}

}
