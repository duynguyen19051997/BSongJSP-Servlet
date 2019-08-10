package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.bean.Song;
import model.dao.ContactDAO;
import model.dao.SongDao;

/**
 * Servlet implementation class PublicIndexDetailController
 */
//@WebServlet("/PublicIndexDetailController")
public class PublicIndexDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicIndexDetailController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SongDao songDao = new SongDao();
		ContactDAO contactDAO = new ContactDAO();
		
		int id = 0;
		try {
			if(request.getParameter("id") != null) {
				id = Integer.valueOf(request.getParameter("id"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		request.setAttribute("listSongById", songDao.getItemsByCatIdAndIdSong(songDao.getIdCatByIdSong(id), id));
		Song objSong = songDao.getItem(id);
		if (objSong == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		ArrayList<Contact> listsContact = contactDAO.getItemsBySongID(id);
		
		objSong.setSongCouter(objSong.getSongCouter() + 1);
		if (songDao.updateCounter(id, objSong.getSongCouter()) > 0) {
			request.setAttribute("listsContact", listsContact);
			request.setAttribute("songDetail", objSong);
			RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
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
