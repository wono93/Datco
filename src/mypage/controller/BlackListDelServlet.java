package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.security.Init;

import mypage.model.service.MypageService;
import mypage.model.vo.BlackList;

/**
 * Servlet implementation class BlackListDelServlet
 */
@WebServlet("/mypage/BlackListDel")
public class BlackListDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlackListDelServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String blackId = request.getParameter("blackId");
		String userId = request.getParameter("userId");
		String gsonblist = null;

		BlackList bl = new BlackList();
		bl.setBlackId(blackId);
		bl.setUserId(userId);
		

		int result = new MypageService().deleteBlackUser(bl);
		String suc = null;

		
		if (result > 0) {
			suc = "completed";
			gsonblist = new Gson().toJson(suc);

			PrintWriter out = response.getWriter();
			out.write(gsonblist);

		} else {
			suc = "failed";
			gsonblist = new Gson().toJson(suc);
			PrintWriter out = response.getWriter();
			out.write(gsonblist);
		}
	

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
