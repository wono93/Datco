package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.SelectedComment;
import mypage.model.service.MypageService;

/**
 * Servlet implementation class BoardCmtSelectServlet
 */
@WebServlet("/board/boardCmtSelect")
public class BoardCmtSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCmtSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//채택하기
		//채택댓글테이블에 insert
		
		String userId = "";
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int cmtNo = Integer.parseInt(request.getParameter("cmtNo"));
		
		SelectedComment selc = new SelectedComment();
		selc.setBoardNo(boardNo);
		selc.setCmtNo(cmtNo);
		
		int result = new BoardService().updateSelectCmt(selc); 
		
		if(result>0) {
			int rPoint = new MypageService().insertPointLog(userId, selc.getPoint(),"답글채택");
			
		}
		
		String msg = result>0?"답변채택 성공! 채택하신 답변자에게 포인트가 지급됩니다!":"답변채택 실패!";
		String loc = "/board/boardView?boardNo="+boardNo;
		request.setAttribute("loc", loc);
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
