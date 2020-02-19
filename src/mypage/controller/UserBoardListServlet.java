package mypage.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.DelBoard;
import common.BoardPaging;
import common.MyBoardPaging;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/mypage/userBoardList")
public class UserBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	//private static final int REPORTCOUNT = new BoardReportServlet().REPORTCOUNT;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터에 boardcode 읽어와서 분기처리.
		final int numPerPage = 10;//한페이지당 수
		int cPage = 1;//요청페이지
		
		String boardCode = request.getParameter("boardCode");
		String userId = request.getParameter("userId");
		int totalBoardCount = 0;
		int totalPage = 0;
		
		
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
			
		}
		
		List<Board> list = null;
		
		
		final int pageBarSize = 5;
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;	
		totalBoardCount = new BoardService().selectMyBoardCount(userId);
		totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
		list = boardService.selectMyBoardList(cPage, numPerPage, userId);
		String pageBar = new MyBoardPaging().pagingBar(request.getContextPath(), userId, cPage, pageNo, pageEnd, totalPage);

		if(list!=null) {
			request.setAttribute("list", list);
			request.setAttribute("pageBar", pageBar);
			request.getRequestDispatcher("/WEB-INF/views/mypage/userBoardList.jsp").forward(request, response);
		}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

