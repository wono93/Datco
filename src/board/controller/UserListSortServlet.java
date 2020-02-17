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

/**
 * Servlet implementation class UserListSortServlet
 */
@WebServlet("/board/userListSort")
public class UserListSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListSortServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//리스트로 출력할 최대값 
		final int numPerPage = 30;
		//리스트로 출력할 최소값
		int cPage = 1;
		
//		1	아이디 userid
//		2	닉네임 nickname
//		3	포인트 point
//		4	채택수 selectedcnt
		String colum = "";
		List<Point> list = null;
		
//		답변채택수로 정렬
		if(request.getParameter("sort") != null && "selected".equals(request.getParameter("sort"))){
			colum = "selectedcnt";
			list = new MypageService().selectPoint(colum,cPage, numPerPage);
			System.out.println("답변 채택순으로 정렬");
		}else {
//			포인트로 정렬 = default
			colum = "point";
			list = new MypageService().selectPoint(colum,cPage, numPerPage);
			System.out.println("포인트 순으로 정렬");
		}
		for(Point p : list) {
			p.setUserGrade(new GradeTemplate().userGrade(p.getPoint()));
		}
		String jsonStr = new Gson().toJson(list);
		
		// view
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
