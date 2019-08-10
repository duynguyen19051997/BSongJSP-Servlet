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

import model.bean.Slide;
import model.dao.SongDao;
import util.AuthUtil;
import util.FileUtil;

@MultipartConfig
/**
 * Servlet implementation class AdminAddSongController
 */
//@WebServlet("/AdminAddSlideController")
public class AdminAddSlideController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// định nghĩa tên thư mực lưu file upload
		private static final String DI_STRING = "files";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddSlideController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/admin/slide/add.jsp");
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
		SongDao songDao = new SongDao();
		int sort = 500;
		try {
			if(request.getParameter("name") != null ) {
				sort = Integer.valueOf(request.getParameter("name"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		//thông tin file
		Part filePart = request.getPart("pictureslide");
		//lấy file name vừa upload
		String picture = filePart.getSubmittedFileName();
		if(!picture.isEmpty() || !"".equals(picture)) {
			//tạo thư mực chứa file upload
			String dirPath = request.getServletContext().getRealPath("") + DI_STRING;
			//đặt lại tên
			picture = FileUtil.rename(picture);
			File createDir = new File(dirPath);
			//kiểm tra đã có file hay chưa
			if(!createDir.exists()) {
				createDir.mkdir();
			}
			//tạo đường dẫn file
			String filePath = dirPath + File.separator + picture;
			//ghi file
			filePart.write(filePath);
			Slide objSlide = new Slide(0,picture , sort);
			if(songDao.addSlide(objSlide) > 0) {
				//success
				response.sendRedirect(request.getContextPath() + "/admin/slide/index?msg=1");
				return;
			}else {
				//fail
				response.sendRedirect(request.getContextPath() + "/admin/slide/add?msg=0");
				return;
			}
		}else {
			request.setAttribute("error", "Không có tệp nào được chọn!");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/slide/add.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
