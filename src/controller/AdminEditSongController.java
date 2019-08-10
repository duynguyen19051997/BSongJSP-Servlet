package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.Song;
import model.dao.CatDao;
import model.dao.SongDao;
import util.AuthUtil;
import util.FileUtil;

@MultipartConfig
/**
 * Servlet implementation class AdminEditSongController
 */
//@WebServlet("/AdminEditSongController")
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// định nghĩa tên thư mực lưu file upload
	private static final String DI_STRING = "files";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminEditSongController() {
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
		SongDao songDao = new SongDao();
		int id = Integer.valueOf(request.getParameter("id"));
		Song objSong = songDao.getItem(id);
		request.setAttribute("objSong", objSong);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/edit.jsp");
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
		int id = Integer.valueOf(request.getParameter("id"));
		SongDao songDao = new SongDao();
		Song objSongOld = songDao.getItem(id);
		int page = 1;
		try {
			if(request.getParameter("page") != null) {
				page = Integer.valueOf(request.getParameter("page"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// thông tin file
		Part filePart = request.getPart("picture");
		String songName = request.getParameter("name");
		// lấy file name vừa upload
		String picture = filePart.getSubmittedFileName();
		String preview = request.getParameter("preview");
		String detai = request.getParameter("detail");
		if (!"".equals(songDao.validString(request.getParameter("category")))
				|| !"".equals(songDao.validString(songName)) || !"".equals(songDao.validString(preview))
				|| !"".equals(songDao.validString(detai))) {
			if (!"".equals(songDao.validString(request.getParameter("category")))) {
				request.setAttribute("cat-error", songDao.validString(request.getParameter("category")));
			}
			if (!"".equals(songDao.validString(songName))) {
				request.setAttribute("songName-error", songDao.validString(songName));
			}
			if (!"".equals(songDao.validString(preview))) {
				request.setAttribute("preview-error", songDao.validString(preview));
			}
			if (!"".equals(songDao.validString(detai))) {
				request.setAttribute("detai-error", songDao.validString(detai));
			}
			RequestDispatcher rd = request.getRequestDispatcher("/admin/song/edit.jsp");
			rd.forward(request, response);
			return;
		}
		int catId = Integer.valueOf(request.getParameter("category"));

		if (!picture.isEmpty()) {
			// tạo thư mực chứa file upload
			String dirPath = request.getServletContext().getRealPath("") + DI_STRING;
			// đặt lại tên
			picture = FileUtil.rename(picture);
			File createDir = new File(dirPath);
			// kiểm tra đã có file hay chưa
			if (!createDir.exists()) {
				createDir.mkdir();
			}
			// tạo đường dẫn file
			String filePath = dirPath + File.separator + picture;
			// ghi file
			filePart.write(filePath);
		} else {
			picture = objSongOld.getSongPicture();
		}
		
		Song objSong = new Song(id, songName, preview, detai, picture,
				new Category(catId, catDao.getItem(catId).getCatName()));
		if (songDao.editSong(objSong) > 0) {
			// success
			response.sendRedirect(request.getContextPath() + "/admin/song/index?page=" + page + "&msg=2");
			return;
		} else {
			// fail
			RequestDispatcher rd = request.getRequestDispatcher("/admin/song/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
	}
}
