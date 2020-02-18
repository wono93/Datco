package board.model.service;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDAO;
import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.DelBoard;
import board.model.vo.SelectedComment;

import static common.JDBCTemplate.*;

public class BoardService {

	public List<Board> selectBoardWriter(String userId) {
		//유저가 작성한 board들 전달해주기
		return null;
	}

	public List<BoardComment> selectBoardCommentWriter(String userId) {
		// 유저가 작성한 comment 전달해주기
		return null;
	}

	public Board selectBoardNo(int boardNo, boolean hasRead) {
		// 게시글 하나 가져오기
		Connection conn = getConnection();
		int result = 0;
		
		// 조회수 증가
		if (hasRead == false) {
			result = new BoardDAO().increaseReadCount(conn, boardNo);
		}
		
		//System.out.println("boardNo@Service="+boardNo);
		Board b = new BoardDAO().selectBoard(boardNo, conn);
		
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		return b;
	}

	public Board selectBoardNo(int boardNo) {
		// 게시글 하나 가져오기
		Connection conn = getConnection();
		
		Board b = new BoardDAO().selectBoard(boardNo, conn);
		
		close(conn);
		return b;
	}
	
	public List<BoardComment> selectCommentList(int boardNo) {
		// 댓글가져오기
		Connection conn = getConnection();
		List<BoardComment> list= new BoardDAO().selectCommentList(conn, boardNo);
		
		close(conn);
		return list;		
	}

	public int insertBoard(Board board) {
		Connection conn = getConnection();
		// 게시글 추가
		int result = new BoardDAO().insertBoard(conn, board);
		System.out.println("board@Servlet"+board);
		// hellomvc 참고
//		새로작성한 boardNo 가져오기
//		select seq_boardno.currval from dual;
		int boardNo = new BoardDAO().selectBoardLastSeq(conn);
		board.setBoardNo(boardNo);
		
		//트랜잭션처리
		if(result > 0) commit(conn);
		else rollback(conn);
		
		//자원반납
		close(conn);
		return result;
	}

	public int updateBoard(Board board) {
		// 게시글 수정
		Connection conn = getConnection();
		int result = new BoardDAO().updateBoard(conn, board);
		
		//트랜잭션처리
		if(result > 0) commit(conn);
		else rollback(conn);
		
		//자원반납
		close(conn);
		return result;
	}

	public int deleteBoard(int boardNo) {
		// 게시글 삭제
		// 신고시에도 삭제처리
		Connection conn = getConnection();
		int result = new BoardDAO().deleteBoard(conn, boardNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int selctReportBoard(int boardNo, String userId) {
		// 신고로그테이블 가서 게시글을 유저가 신고한 기록이 있는지 확인.
		Connection conn = getConnection();
		int result = new BoardDAO().selctReportBoard(conn, boardNo, userId);
		close(conn);
		return result;
	}

	public int insertReportBoard(int boardNo, String userId) {
		Connection conn = getConnection();
		int report = new BoardDAO().insertReportBoard(conn, boardNo, userId);
		if(report > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return report;
	}


	public int insertBoardComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoardComment(conn, bc);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public SelectedComment selectSelectedCmt(int boardNo) {
		Connection conn = getConnection();
		SelectedComment selc = new BoardDAO().selectSelectedCmt(conn, boardNo);
		close(conn);
		return selc;
	}

	public int insertSelectedComment(int boardNo, int point) {
		//코드리플 작성시 채택테이블에 추가하기
		Connection conn = getConnection();
		int result = new BoardDAO().insertSelectedComment(conn, boardNo, point);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoardList(int cPage, int numPerPage, String boardCode) {
		Connection conn = getConnection();
		List<Board> list= new BoardDAO().selectBoardList(conn, cPage, numPerPage, boardCode);
		close(conn);
		return list;
	}

	public BoardComment selectCmtNo(int cmtNo) {
		// 게시글 하나 가져오기
		Connection conn = getConnection();
		BoardComment bc = new BoardDAO().selectCmtNo(cmtNo, conn);
		close(conn);
		return bc;
	}


	public List<DelBoard> selectDelBoardList(int cPage, int numPerPage, int reportcount) {
		Connection conn = getConnection();
		List<DelBoard> list= new BoardDAO().selectDelBoardList(conn, cPage, numPerPage, reportcount);
		close(conn);
		return list;
	}

	public int selctReportCmt(int cmtNo, String userId) {
		// 신고로그테이블 가서 게시글을 유저가 신고한 기록이 있는지 확인.
		Connection conn = getConnection();
		int result = new BoardDAO().selctReportCmt(conn, cmtNo, userId);
		close(conn);
		return result;
	}

	public int insertReportCmt(int cmtNo, String userId) {
		Connection conn = getConnection();
		int report = new BoardDAO().insertReportCmt(conn, cmtNo, userId);
		if(report > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return report;
	}


	public int updateSelectCmt(SelectedComment selc) {
		Connection conn = getConnection();
		int result = new BoardDAO().updateSelectCmt(conn, selc); 
		//트랜잭션처리
		if(result > 0) commit(conn);
		else rollback(conn);
		
		//자원반납
		close(conn);
		return result;
	}

	public int boardCmtDel(int cmtNo) {
		Connection conn = getConnection();
		int result = new BoardDAO().boardCmtDel(conn, cmtNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public int selectBoardCount(String boardCode) {
		Connection conn = getConnection();
		int result = new BoardDAO().selectBoardCount(conn, boardCode);
		close(conn);
		return result;
	}


	public int selectDelBoardCount(int reportcount) {
		Connection conn = getConnection();
		int result = new BoardDAO().selectDelBoardCount(conn, reportcount);
		close(conn);
		return result;
	}

    public int myCmtTotal(String userId) {
		//특정 유저의댓글 갯수 가져오기
		Connection conn = getConnection();
		int myCmtTotal= new BoardDAO().myCmtTotal(conn, userId);
		
		close(conn);
		return myCmtTotal;
	}

	public List<Board> myCurrentBoard(String userId) {
		//특정 유저의 최근 작성글  5개 가져오기
		Connection conn = getConnection();
	
		List<Board> myCurBoardt= new BoardDAO().myCurrentBoard(conn, userId);
		close(conn);
		return myCurBoardt;
	}

	public int myBoardTotal(String userId) {
		//특정 유저의 게시글 갯수 가져오기
		Connection conn = getConnection();
		int myBoardTotal= new BoardDAO().myBoardTotal(conn, userId);
				
		close(conn);
		return myBoardTotal;
	}

	public List<DelBoard> selectRepBoardList(int cPage, int numPerPage, int reportcount) {
		Connection conn = getConnection();
		List<DelBoard> list= new BoardDAO().selectRepBoardList(conn, cPage, numPerPage, reportcount);
		close(conn);
		return list;
	}

	public int selectRepBoardCount(int reportcount) {
		Connection conn = getConnection();
		int result = new BoardDAO().selectRepBoardCount(conn, reportcount);
		close(conn);
		return result;
	}

	public DelBoard selectDelBoard(int boardNo) {
		Connection conn = getConnection();
		DelBoard dBoard = new BoardDAO().selectDelBoard(conn, boardNo);
		close(conn);
		return dBoard;
	}

	public List<Board> selectBoardSearch(String boardCode, String searchType, String searchText, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> myCurBoardt= new BoardDAO().selectBoardSearch(conn, boardCode, searchType, searchText, cPage, numPerPage);
		close(conn);
		return myCurBoardt;
	}

	
	public List<Board> selectMyBoardList(int cPage, int numPerPage, String userId) {
		//유저가 작성한 board들 전달해주기
		Connection conn = getConnection();
		List<Board> list= new BoardDAO().selectMyBoardList(conn, cPage, numPerPage, userId);
		
		close(conn);
		return list;		
	}
	public int selectMyBoardCount(String userId) {
		Connection conn = getConnection();
		int result = new BoardDAO().selectMyBoardCount(conn, userId);
		close(conn);
		return result;
	}


}
