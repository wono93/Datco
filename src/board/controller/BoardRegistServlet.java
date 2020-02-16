package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardRegistServlet
 */
@WebServlet("/board/boardRegist")
public class BoardRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 작성/수정 폼 돌려주기
		
		//수정시
		//board.getBoardNo() 으로 NullPointer/NumberFormat Exception 유도하는 것도 방법.
		if(request.getParameter("boardNo") != null) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			Board board = new BoardService().selectBoardNo(boardNo, false);
			request.setAttribute("board", board);
		}
		
		//글작성 폼으로
		//boardregist.jsp에서 <%=board.getBoardNo()!=null?board.getBoardNo():""%>
		request.getRequestDispatcher("/WEB-INF/views/board/boardRegist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
