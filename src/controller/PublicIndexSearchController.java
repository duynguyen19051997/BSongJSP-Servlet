package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDao;
import util.DefineUtil;

/**
 * Servlet implementation class PublicIndexSearchController
 */
//@WebServlet("/PublicIndexSearchController")
public class PublicIndexSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicIndexSearchController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SongDao songDao = new SongDao();
		String search = request.getParameter("search");
		if(!search.isEmpty()) {
			int currentPage = 1;
			try {
				if(request.getParameter("page") != null) {
					currentPage = Integer.valueOf(request.getParameter("page"));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			int numberOfItems = songDao.getNumberOfItemBySearch(search);
			int numberOfPages = (int)Math.ceil((float)numberOfItems / DefineUtil.NUMBER_PER_PAGE);
			if(currentPage > numberOfPages) {
				currentPage = numberOfPages;
			}
			if(currentPage < 1) {
				currentPage = 1;
			}
			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
			ArrayList<Song> listSongBySearch = songDao.searchSong(search, offset);
			request.setAttribute("resultSearch", listSongBySearch);
			request.setAttribute("search", search);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("currentPage", currentPage);
			RequestDispatcher rd = request.getRequestDispatcher("/public/search.jsp");
			rd.forward(request, response);
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
			rd.forward(request, response);
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
