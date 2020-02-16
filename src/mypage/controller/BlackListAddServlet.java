package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypage.model.service.MypageService;
import mypage.model.vo.BlackList;

/**
 * Servlet implementation class BlackListAddServlet
 */
@WebServlet("/mypage/userBlackListAdd.do")
public class BlackListAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlackListAddServlet() {
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
		String blackId = request.getParameter("blackId");
		String memo = request.getParameter("memo");
		String gsonblist = null;

		response.setContentType("application/json; charset=utf-8");

		// 이미 존재하는 차단유저인지 검사.
		List<BlackList> blackList = new MypageService().selectBlackList(userId);
		String exist = null;
		for (BlackList bl : blackList) {
			if (blackId.equals(bl.getBlackId())) {
				System.out.println(bl.getBlackId() + "===========블랙아이디 검사중");
				exist = "exist";
				gsonblist = new Gson().toJson(exist);
				PrintWriter out = response.getWriter();
				out.write(gsonblist);
				
			} 
		}
		if(exist == null) {
			// 존재하지 않는 차단 유저일 경우 insert통해 추가.
			BlackList getbl = new BlackList(userId, blackId, null, memo);
			int result = new MypageService().addBlackList(getbl);

			if (result > 0) {
				// 업데이트 된 차단 유저 다시 조회하여 jsp에 보내기
				blackList = new MypageService().selectBlackList(userId);
				gsonblist = new Gson().toJson(blackList);

				PrintWriter out = response.getWriter();
				out.write(gsonblist);
//						System.out.println("blackListArr Add Servlet gsonlist" + gsonblist);

			} else {
				exist = "insertfail";
				gsonblist = new Gson().toJson(exist);
				PrintWriter out = response.getWriter();
				out.write(gsonblist);
			}
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
