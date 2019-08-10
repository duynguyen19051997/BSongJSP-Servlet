package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.CatDao;
import model.dao.SongDao;
import util.DefineUtil;

/**
 * Servlet implementation class PublicIndexCatController
 */
//@WebServlet("/PublicIndexCatController")
public class PublicIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicIndexCatController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SongDao songDao = new SongDao();
		CatDao catDao = new CatDao();
		int catId = 0;
		int currentPage = 1;
		try {
			if(request.getParameter("id") != null) {
				catId = Integer.valueOf(request.getParameter("id"));
			}
			if(request.getParameter("page") != null) {
				currentPage = Integer.valueOf(request.getParameter("page"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int numberOfItems = songDao.getNumberOfItem(catId);
		int numberOfPages = (int)Math.ceil((float)numberOfItems / DefineUtil.NUMBER_PER_PAGE);
		if(currentPage > numberOfPages) {
			currentPage = numberOfPages;
		}
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		Song mostPopSong = songDao.getItemMostPopByCatId(catId);
		request.setAttribute("mostPopSongFromCatId", mostPopSong);
		request.setAttribute("catName", catDao.getItem(catId));
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listSongByCatId", songDao.getItemsByCatIdPagination(catId,offset));
		RequestDispatcher rd = request.getRequestDispatcher("/public/category.jsp");
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
