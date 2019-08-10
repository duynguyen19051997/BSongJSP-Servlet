package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.CatDao;
import model.dao.SongDao;
import util.AuthUtil;

@MultipartConfig
/**
 * Servlet implementation class AdminAddCatController
 */
//@WebServlet("/AdminDeleteCatController")
public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDeleteCatController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		CatDao catDao = new CatDao();
		int catId = Integer.valueOf(request.getParameter("id"));
		SongDao songDao = new SongDao();
		ArrayList<Song> listSong = (ArrayList<Song>)songDao.getItemsByCatId(catId);
		if(listSong.size() > 0) {
			if (songDao.deleteItemsByCatId(catId) > 0) {
				if (catDao.deleteItem(catId) > 0) {
					// success
					response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=3");
					return;
				} else {
					// fail
					response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=0");
					return;
				}
			} else {
				// fail
				response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=0");
				return;
			}
		}
		if (catDao.deleteItem(catId) > 0) {
			// success
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=3");
			return;
		} else {
			// fail
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=0");
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
