package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.SongDao;
import model.dao.UserDAO;
import util.AuthUtil;
import util.StringUtil;

/**
 * Servlet implementation class AdminAddUserController
 */
//@WebServlet("/AdminAddUserController")
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddUserController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userInfor");

		// chỉ anhduy mới được thêm người dùng
		if (!"anhduy".equals(userLogin.getUserName())) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=4");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userInfor");

		// chỉ anhduy mới được thêm người dùng
		if (!"anhduy".equals(userLogin.getUserName())) {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=4");
			return;
		}
		SongDao songDao = new SongDao();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullname");

		if (!"".equals(songDao.validString(userName)) || !"".equals(songDao.validString(password))
				|| !"".equals(songDao.validString(fullName))) {
			if (!"".equals(songDao.validString(userName))) {
				request.setAttribute("userName-error", songDao.validString(userName));
			}
			if (!"".equals(songDao.validString(password))) {
				request.setAttribute("password-error", songDao.validString(password));
			}
			if (!"".equals(songDao.validString(fullName))) {
				request.setAttribute("fullName-error", songDao.validString(fullName));
			}
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}

		UserDAO userDAO = new UserDAO();

		password = StringUtil.md5(password);
		User user = new User(0, userName, password, fullName);
		if (userDAO.hasUser(user)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=4");
			rd.forward(request, response);
			return;
		} else if (userDAO.addItem(user) > 0) {
			// success
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=1");
			return;
		} else {
			// fail
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
	}

}
