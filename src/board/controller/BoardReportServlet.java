package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardReportServlet
 */
@WebServlet("/board/boardReport")
public class BoardReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final int REPORTCOUNT = 10;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//boardCode 도 던져주세요(BoardListServlet 호출용)
		String boardCode = request.getParameter("boardCode");
		String userId = request.getParameter("userId");
		String referer = request.getParameter("Referer");
		
		//borad | comment 인지 확인하기
		if(request.getParameter("boardNo") != null) {
			System.out.println("request.getParameter(\"boardNo\")="+request.getParameter("boardNo"));
			
			//게시글 신고
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			//System.out.println("boardNo@ReportServlet="+boardNo);
			
			//신고로그 테이블에 있는지 확인(중복검열)
			int result = new BoardService().selctReportBoard(boardNo, userId);
			System.out.println("result@ReportServlet="+result);
			
			if(result == 0) {
				//없을시 신고처리(insert)해주기
				result = new BoardService().insertReportBoard(boardNo, userId);
			}else {
				errorMsg(request, response, referer, "해당 글은 이미 신고하셨습니다.");
				return;
			}
			
			//게시글의 신고누적수 조회
			Board board = new BoardService().selectBoardNo(boardNo);
			System.out.println("board@ReportServlet"+board);
			
			System.out.println("board.getReportedCnt()="+board.getReportedCnt());
			if(board.getReportedCnt()>=REPORTCOUNT) {
				
				//게시글 테이블에서 삭제
				int moveResult = new BoardService().deleteBoard(boardNo);
				System.out.println("moveResult@Servlet="+moveResult);
				
				if(moveResult > 0) {
					//게시판 리스트로 돌아가기
					boardCode = board.getBoardCode();
					request.setAttribute("boardCode", boardCode);
					request.getRequestDispatcher("/board/boardList").forward(request, response);
					return;
				}
			}
		} else if(request.getParameter("cmtNo") != null) {
			//boardView?boardNo 호출용
			int boardNo2 = Integer.parseInt(request.getParameter("boardNo2"));
			System.out.println("request.getParameter(\"cmtNo\")="+request.getParameter("cmtNo"));
			
			//댓글 신고
			int cmtNo = Integer.parseInt(request.getParameter("cmtNo"));
			
			int result = new BoardService().selctReportCmt(cmtNo, userId);
			//System.out.println("result@ReportServlet="+result);
			if(result == 0) {
				//신고처리 해주기
				result = new BoardService().insertReportCmt(cmtNo, userId);
			} else {
				errorMsg(request, response, referer, "해당 글은 이미 신고하셨습니다.");
				return;
			}
			
			//댓글의 신고누적수 조회
			BoardComment boardComment = new BoardService().selectCmtNo(cmtNo);
			System.out.println("boardComment@Servlet="+boardComment);
			
			System.out.println("boardComment.getReported()="+boardComment.getReported());
			if(boardComment.getReported()>=REPORTCOUNT) {
				
				//댓글 테이블에서 삭제
				int moveResult = new BoardService().boardCmtDel(cmtNo);
				System.out.println("moveResult@Servlet="+moveResult);
				
				if(moveResult > 0) {
					//댓글삭제 후 게시판 돌아가기
					boardNo2 = boardComment.getBoardNo();
					request.setAttribute("boardNo", boardNo2);
					request.getRequestDispatcher("/board/boardView").forward(request, response);
					return;
				}
			}
			
		}
		errorMsg(request, response, referer, "신고가 정상적으로 처리되었습니다.");
		
	}
	private void errorMsg(HttpServletRequest request, HttpServletResponse response, String loc, String msg) throws ServletException, IOException {
		request.setAttribute("loc", loc);
		request.setAttribute("msg", msg);
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
