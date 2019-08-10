package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Song;
import model.dao.CatDao;
import model.dao.SongDao;

/**
 * Servlet implementation class PublicIndexController
 */
//@WebServlet("/PublicIndexController")
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicIndexController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SongDao songDao = new SongDao();
		ArrayList<Song> listSongMostPopFromCat = new ArrayList<>();
		CatDao catDao = new CatDao();
		ArrayList<Category> listCat = catDao.getItems();
		ArrayList<Song> listSongLatest = songDao.getItemsLatest();
		ArrayList<Song> listMostPop = songDao.getItemsPop();
		Song mostPopSong = songDao.getItemsMostPop();
		
		for (Category category : listCat) {
			Song song = songDao.getItemMostPopByCatId(category.getCatId());
			listSongMostPopFromCat.add(song);
		}
		
		request.setAttribute("listSongMostPopFromCat", listSongMostPopFromCat);
		request.setAttribute("mostPopSong", mostPopSong);
		request.setAttribute("listMostPop", listMostPop);
		request.setAttribute("listSongLatest", listSongLatest);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
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
