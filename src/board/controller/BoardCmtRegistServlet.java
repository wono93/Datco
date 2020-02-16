package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCmtRegistServlet
 */
@WebServlet("/board/boardCmtRegist")
public class BoardCmtRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCmtRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//insert
		//1.파라미터
		String cmtWriter = request.getParameter("cmtWriter");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int cmtLevel = Integer.parseInt(request.getParameter("cmtLevel"));
		int cmtRefNo = Integer.parseInt(request.getParameter("cmtRefNo"));
		String cmtContent = request.getParameter("cmtContent");
		String cmtWriterGrade = request.getParameter("cmtWriterGrade");
		
		BoardComment bc = new BoardComment(0, cmtWriter, cmtWriterGrade, boardNo, cmtLevel, cmtRefNo, cmtContent, null, 0, 0);
//		System.out.println("BoardComment@CmtRegistServlet="+bc);
		bc.setCmtRefNo(cmtRefNo);
		//2.업무로직
		int result = new BoardService().insertBoardComment(bc);
		
		//3.view단처리: 댓글 등록여부를 msg.jsp통해서 알림 후, /board/boardView로 이동
		String msg = "";
		String loc = "/board/boardView?boardNo="+boardNo;
		if(result>0) {
			msg="댓글이 성공적으로 작성되었습니다.";
		} else {
			msg="댓글작성에 실패하였습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);

		//ajax응답용 서블릿
		
		//상의 후 결정
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
