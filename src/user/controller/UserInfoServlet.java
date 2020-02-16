package user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import common.GradeTemplate;
import mypage.model.service.MypageService;
import mypage.model.vo.Point;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/user/userInfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 정보보기 팝업창 띄워주는 서블렛
		
		
		String nickName = request.getParameter("nickName");
		System.out.println(nickName);
		User user = new UserService().selectOnebyNick(nickName);		
		System.out.println("===========servlet@user=="+user);
		String userId = user.getUserId();
		
		
		Point point = new MypageService().getPoint(userId);
		List<Board> myCurrentBoard = new BoardService().myCurrentBoard(userId);
		int myCmtTotals = new BoardService().myCmtTotal(userId);
		int myBoardTotals = new BoardService().myBoardTotal(userId);
		String myCmtTotal = Integer.toString(myCmtTotals);
		String myBoardTotal = Integer.toString(myBoardTotals);
		
		//포인트등급 이미지 가져오기
		point.setUserGrade(new GradeTemplate().userGrade(point.getPoint()));
		
		System.out.println("=============servlet@@point==="+point);
		
		request.setAttribute("user", user);
		request.setAttribute("point", point);
		request.setAttribute("myCurrentBoard", myCurrentBoard);
		request.setAttribute("myBoardTotal", myBoardTotal);
		request.setAttribute("myCmtTotal", myCmtTotal);
		System.out.println(myCurrentBoard.toString());
		request.getRequestDispatcher("/WEB-INF/views/user/userInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
