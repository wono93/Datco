package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.SelectedComment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//파라미터로 boardNo 읽어오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardCode = request.getParameter("boardCode");
		//System.out.println("boardNo@Servlet="+boardNo);
		
		//쿠키로 조회수처리
		Cookie[] cookies = request.getCookies();
		String boardCookieVal = "";
		boolean hasRead = false;
		
		if(cookies != null) {
			
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				
				if("boardView".equals(name)) {
					boardCookieVal = value;
					if(value.contains("|"+boardNo+"|")) {
						hasRead = true;
						break;
					}
				}
			}
		}
		
		if(hasRead == false) {
			boardCookieVal = boardCookieVal +"|"+boardNo + "|";
			Cookie boardCookie = new Cookie("boardView", boardCookieVal);
			boardCookie.setMaxAge(90*24*60*60);//90일
			boardCookie.setPath(request.getContextPath()+"/board");
			response.addCookie(boardCookie);
		}
		
		Board board = new BoardService().selectBoardNo(boardNo, hasRead);
		
		if(boardCode == null) boardCode = board.getBoardCode();
		
		//System.out.println("board.getBoardCode()@ViewServlet="+board.getBoardCode());
		List<BoardComment> commentList = new BoardService().selectCommentList(boardNo);
		SelectedComment selc = null;
		if("CDR".equals(board.getBoardCode()) && 
				(board.getCmtSelect().equals("Y"))){
			//코드리플의 채택된 댓글이 있다면 select으로 cmt번호 전달하기
			selc = new BoardService().selectSelectedCmt(board.getBoardNo());
			if(selc.getCmtNo() != 0)
				request.setAttribute("select", selc.getCmtNo());
		}
		
		if(board != null) {
			request.setAttribute("board", board);
			request.setAttribute("selc", selc);
			request.setAttribute("commentList", commentList);
			request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp").forward(request, response);
			
			return;
		}
		String msg = "해당하는 게시글이 없습니다.";
		String loc = "/board/boardList?boardCode="+boardCode;
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