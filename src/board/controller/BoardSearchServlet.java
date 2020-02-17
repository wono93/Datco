package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import common.BoardPaging;

/**
 * Servlet implementation class BoardSearchServlet
 */
@WebServlet("/board/boardSearch")
public class BoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardCode = request.getParameter("boardCode");
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");
		
		final int numPerPage = 10;	//한페이지당 수
		int cPage = 1;				//현재 페이지
		final int pageBarSize = 5;	//페이지바 최대크기
		
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
			
		}

		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		int totalBoardCount = new BoardService().selectBoardCount(boardCode);
		int totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
		List<Board> list = new BoardService().selectBoardSearch(boardCode, searchType, searchText, cPage, numPerPage);

		
		
		String pageBar = new BoardPaging().pagingBar(request.getContextPath(), boardCode, cPage, pageNo, pageEnd, totalPage);
		

		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
