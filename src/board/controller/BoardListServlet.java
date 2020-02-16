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
import board.model.vo.DelBoard;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	private static final int REPORTCOUNT = new BoardReportServlet().REPORTCOUNT;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터에 boardcode 읽어와서 분기처리.
		final int numPerPage = 20;//한페이지당 수
		int cPage = 1;//요청페이지
		
		String boardCode = request.getParameter("boardCode");
		int totalBoardCount = 0;
		int totalPage = 0;
		
		
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
			cPage = 1;
		}
		
		List<Board> list = null;
		List<DelBoard> dlist = null;
		
		String pageBar = "";	
		final int pageBarSize = 10;
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		
		if("REP".equals(boardCode)){
			//신고게시판 -> 신고수 10개 이상인 것만 보여주기
			totalBoardCount = new BoardService().selectDelBoardCount();
			totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
			dlist = boardService.selectRepBoardList(cPage, numPerPage, REPORTCOUNT);
			
		}else if("DEL".equals(boardCode)) {
			//삭제게시판 -> 신고수 10개 미만인 것만 보여주기
			totalBoardCount = new BoardService().selectDelBoardCount();
			totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
			dlist = boardService.selectDelBoardList(cPage, numPerPage, REPORTCOUNT);
		}else {
			totalBoardCount = new BoardService().selectBoardCount(boardCode);
			totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
			list = boardService.selectBoardList(cPage, numPerPage, boardCode);
		}
		
		System.out.println("list@servlet="+list);

		
		if(pageNo == 1 ){
			pageBar += "<span>[이전]</span>"; 
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/board/boardList?cPage="+(pageNo-1)+"'>[이전]</a> ";
		}
			
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/board/boardList?cPage="+pageNo+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		if(pageNo > totalPage){
			pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/board/boardList?cPage="+pageNo+"'>[다음]</a>";
		}

		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
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
