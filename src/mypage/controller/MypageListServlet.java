package mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class MypageListServlet
 */
@WebServlet(urlPatterns = "/mypage/mypageList", name = "MypageListServlet")
public class MypageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MypageListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");

		// views/mypage/mypageList.jsp
		User user = new UserService().selectOne(userId);
		String view = "";
		if (user != null) {
			view = "/WEB-INF/views/mypage/mypageList.jsp";
			request.setAttribute("user", user);
		} else {
			view = "/WEB-INF/views/common/msg.jsp";

			String loc = "/";
			String msg = "로그인상태를 확인해주세요.";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}

		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
