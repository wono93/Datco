package board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import board.model.service.BoardService;
import board.model.vo.Board;
import mypage.model.service.MypageService;
import mypage.model.vo.BlackList;
import user.model.vo.User;

/**
 * Servlet implementation class MainBoardListServlet
 */
@WebServlet("/main")
public class MainBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	메인페이지의 게시글리스트 갯수 	
		final int MAXPAGE = 10;
		final int MINPAGE = 1;
		Map<String, List<Board>> list = new HashMap<>();
		
		//회원의 블랙리스트가 존재하는지 확인하기
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userLoggedIn");
		List<BlackList> blackList = null;
		if(user != null)
			blackList = new MypageService().selectBlackList(user.getUserId());
		
		//자유게시판
		String boardCode = "FRE";
		list.put(boardCode,new BoardService().selectBoardList(MINPAGE, MAXPAGE, boardCode, blackList));
		//코드리플
		boardCode = "CDR";
		list.put(boardCode,new BoardService().selectBoardList(MINPAGE, MAXPAGE, boardCode, blackList));
		//구인구직
		boardCode = "JOB";
		list.put(boardCode,new BoardService().selectBoardList(MINPAGE, MAXPAGE, boardCode, blackList));
		
		String jsonStr = new Gson().toJson(list);
		
		System.out.println(list);
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