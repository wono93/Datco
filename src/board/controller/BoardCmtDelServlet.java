package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardCmtDelServlet
 */
@WebServlet("/board/boardCmtDel")
public class BoardCmtDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCmtDelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getParameter("delNo"));
		
		int cmtNo = Integer.parseInt(request.getParameter("cmtNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		int result = new BoardService().boardCmtDel(cmtNo);
		
//		System.out.println("delNo@Servlet="+delNo);
		
		String msg = "";
		String loc = "/board/boardView?boardNo="+boardNo;
		
		if(result>0) {
			msg="댓글이 성공적으로 삭제되었습니다.";
		} else {
			msg="댓글 삭제에 실패하였습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
