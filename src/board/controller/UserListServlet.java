package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MypageService;
import mypage.model.vo.Point;
import mypage.model.vo.PointLog;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/board/userList")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final int numPerPage = 100;//한페이지당 수
		int cPage = 1;//요청페이지
		
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
			
		}
//		1	아이디 userid
//		2	닉네임 nickname
//		3	포인트 point
//		4	채택수 selectedcnt
		String colum = "";
		
//		답변채택수로 정렬
		if(request.getParameter("sort") != null && "selected".equals(request.getParameter("sort"))){
			colum = "selectedcnt";
			List<Point> list = new MypageService().selectPoint(colum,cPage, numPerPage);
			request.setAttribute("list", list);
			System.out.println("답변");
		}else {
//			포인트로 정렬 = default
			colum = "point";
			List<Point> list = new MypageService().selectPoint(colum,cPage, numPerPage);
			request.setAttribute("list", list);
			System.out.println("포인트");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/board/userList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
