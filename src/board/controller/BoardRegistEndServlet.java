package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.model.service.BoardService;
import board.model.vo.Board;
import mypage.model.service.MypageService;

/**
 * Servlet implementation class BoardRegistEndServlet
 */
@WebServlet("/board/boardRegistEnd")
public class BoardRegistEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final int BOARDPOINT = 50;
    private final int CMTPOINT = 50;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRegistEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//작성완료 or 수정완료시 insert/update처리해주기

		//Board의 setter나 생성자로 만들기 
		//boardNo는 나중에
		
		String boardWriter = request.getParameter("boardWriter");
		String boardCode = request.getParameter("boardCode");
		String boardOption = request.getParameter("boardOption");
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		
		boardContent = boardContent.replaceAll("<", "&lt;")
								   .replaceAll(">", "&gt;")
								   .replaceAll("\\n", "<br>");

		//코드리플게시판 boardContent = boardContent + codeContent JSON으로 담기
		if("CDR".equals(boardCode)) {
			String codeContent = request.getParameter("codeContent");
			codeContent = boardContent.replaceAll("<", "&lt;")
					   .replaceAll(">", "&gt;")
					   .replaceAll("\\n", "<br>");
			List<String> list = new ArrayList<>();
			list.add(boardContent);
			list.add(codeContent);
			
			String jsonContent = new Gson().toJson(list);
			boardContent = jsonContent;
//			boardContent = new Gson().toJson(list);
			//System.out.println("CodeContent="+CodeContent);
		}
		String originalFileName = request.getParameter("originalFileName");
		String renamedFileName = request.getParameter("renamedFileName");
		String boardWriterGrade = request.getParameter("boardWriterGrade");
		
//		System.out.println("boardWriter@servlet="+boardWriter);
		
		
		
		//insert, update 분기점
		int boardNo = 0;
		String boardNoStr = request.getParameter("boardNo");
		int result = 0;
		Board board = null;
		
		if(boardNoStr == null || "".equals(boardNoStr)) {
			
			//insert
			System.out.println("-------Insert@Servlet-------");
			//시퀀스 넘버담기
			
			board = new Board(0, boardWriter, boardWriterGrade, boardCode, boardOption, boardTitle, boardContent, originalFileName, renamedFileName, 0, 0, 0, null, null);
			//System.out.println("board@Servlet="+board);
			result = new BoardService().insertBoard(board);
			boardNo = board.getBoardNo();
			//System.out.println("result@RegistServlet="+result);
			if("CDR".equals(board.getBoardCode())) {
				//채택댓글 테이블에 추가하기
				int point = Integer.parseInt(request.getParameter("point"));
				//System.out.println("point="+point);
				int selectCmt = new BoardService().insertSelectedComment(boardNo,point); 
				
				//회원 보유포인트 검사 if(point<50){return msg ="코드리플 작성시 필요한 포인트가 부족합니다.";}
				//포인트 추가
				int plusPoint = new MypageService().insertPointLog(board.getBoardWriter(), BOARDPOINT, "게시글 작성");
			}
		}else {
			//update
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
			board = new Board(boardNo , boardWriter, boardWriterGrade, boardCode, boardOption, boardTitle, boardContent, originalFileName, renamedFileName, 0, 0, 0, null, null);
			
			System.out.println("-------Update@Servlet-------");
			
			result = new BoardService().updateBoard(board);
		}
		
		//0 일 경우 msg alert(msg)처리
		String msg = result>0?"게시글 등록 성공!":"게시글 등록 실패!";
		
		//1일경우 해당 게시글로 이동
		String loc = "";
		if(result == 1) {
			loc = "/board/boardView?boardNo="+board.getBoardNo();
		} else {
			loc = "/board/boardList?boardCode="+board.getBoardCode();
		}
		
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
