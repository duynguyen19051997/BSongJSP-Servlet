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
 * Servlet implementation class AdminEditUserController
 */
//@WebServlet("/AdminEditUserController")
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminEditUserController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return;
		}
		UserDAO userDAO = new UserDAO();
		int id = Integer.valueOf(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		User userLogin = (User)session.getAttribute("userInfor");
		
		//Chỉ anhduy mới toàn quyền và user khác chỉ đc sửa chính họ
		if("anhduy".equals(userLogin.getUserName()) || id == userLogin.getId()) {
			User objUser = userDAO.getItem(id);
			if(objUser != null) {
				request.setAttribute("objUser", objUser);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=7");
				return;
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=5");
			return;
		}
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
		UserDAO userDAO = new UserDAO();
		SongDao songDao = new SongDao();
		int id = Integer.valueOf(request.getParameter("id"));
		int currentPage = 1;
		try {
			if ((request.getParameter("page")) != null) {
				currentPage = Integer.valueOf(request.getParameter("page"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		User userLogin = (User)session.getAttribute("userInfor");
		
		//Chỉ anhduy mới toàn quyền và user khác chỉ đc sửa chính họ
		if("anhduy".equals(userLogin.getUserName()) || id == userLogin.getId()) {
			User objUser = userDAO.getItem(id);
			if(objUser == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=0");
				rd.forward(request, response);
				return;
			}
			// get data
			String password = request.getParameter("password");
			if (password.isEmpty()) {
				password = objUser.getPassword();
			} else {
				password = StringUtil.md5(password);
			}
			String fullName = request.getParameter("fullname");

			//validate by java
			if( !"".equals(songDao.validString(fullName))) {
				
				if(!"".equals(songDao.validString(fullName))) {
					request.setAttribute("fullname-error",songDao.validString(fullName));
				}
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
				return;
			}
			
			// set data
			objUser.setPassword(password);
			objUser.setFullName(fullName);
			if(userDAO.editItem(objUser) > 0) {
				//success
				response.sendRedirect(request.getContextPath()+"/admin/user/index?page=" + currentPage + "&msg=2");
				return;
			}else {
				//fail
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=0");
				rd.forward(request, response);
				return;
			}

		}else {
			response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=5");
			return;
		}
	}

}
