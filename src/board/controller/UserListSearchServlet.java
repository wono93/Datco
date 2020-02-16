package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.GradeTemplate;
import mypage.model.service.MypageService;
import mypage.model.vo.Point;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserListSearchServlet
 */
@WebServlet("/board/userListSearch")
public class UserListSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String searchNick = request.getParameter("searchNick");
//		1	아이디 userid
//		2	닉네임 nickname
//		3	포인트 point
//		4	채택수 selectedcnt
		String colum = "";
		List<Point> list = null;
		
		list = new MypageService().searchUser(colum,searchNick);
		
		for(Point p : list) {
			p.setUserGrade(new GradeTemplate().userGrade(p.getPoint()));
		}
		
		String jsonStr = new Gson().toJson(list);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(jsonStr);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
