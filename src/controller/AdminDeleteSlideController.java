package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Slide;
import model.dao.SlideDao;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDeleteSongController
 */
//@WebServlet("/AdminDeleteSlideController")
public class AdminDeleteSlideController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// định nghĩa tên thư mực lưu file upload
		private static final String DI_STRING = "files";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDeleteSlideController() {
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
		SlideDao slideDao = new SlideDao();
		int slideId = Integer.valueOf(request.getParameter("id"));
		Slide objSlide = slideDao.getItem(slideId);
		String picture = objSlide.getImage();
		if(!picture.isEmpty()) {
			File file = new File(request.getServletContext().getRealPath("") + DI_STRING + File.separator + picture);
			file.delete();
		}
		if(slideDao.deleteItem(slideId) > 0) {
			//success
			response.sendRedirect(request.getContextPath() + "/admin/slide/index?msg=3");
			return;
		}else {
			//fail
			response.sendRedirect(request.getContextPath() + "/admin/slide/index?msg=0");
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
