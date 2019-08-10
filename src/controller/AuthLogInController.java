package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.StringUtil;

/**
 * Servlet implementation class AuthLoginController
 */
//@WebServlet("/AuthLogInController")
public class AuthLogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthLogInController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		String userName =  request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		User userInfor = userDao.getItemByUserAndPassword(userName, password);
		if(userInfor != null) {
			//success
			HttpSession session = request.getSession();
			session.setAttribute("userInfor", userInfor);
			response.sendRedirect(request.getContextPath() + "/admin/index");
			return;
		}else {
			//fail
			response.sendRedirect(request.getContextPath() + "/auth/login?msg=error");
			return;
		}
	}

}
