package board.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.DelBoard;
import board.model.vo.SelectedComment;
import mypage.model.dao.MypageDAO;

public class BoardDAO {

	private Properties prop = new Properties();

	public BoardDAO() {

		String fileName = BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectBoardLastSeq(Connection conn) {
		// 게시판 시퀀스 가져오기
		int boardNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardLastSeq");

		try {
			pstmt = conn.prepareStatement(query);// 담기
			rset = pstmt.executeQuery();// 실행

			if (rset.next())
				boardNo = rset.getInt("board_No");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		// insert into tb_board values
		// (tb_board_seq.nextval, 'ddochi', 'fre', 'none',
		// '테스트글 1', '로렘입숨숨 asldkfjl', null, null, default,
		// default, default, default, default, 'gold');

		String query = prop.getProperty("insertBoard");

		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardWriter());
			pstmt.setString(2, board.getBoardCode());
			pstmt.setString(3, board.getBoardOption());
			pstmt.setString(4, board.getBoardTitle());
			pstmt.setString(5, board.getBoardContent());
			pstmt.setString(6, board.getOriginalFileName());
			pstmt.setString(7, board.getRenamedFileName());
			pstmt.setString(8, board.getBoardWriterGrade());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("result=" + result);
		return result;
	}

	public SelectedComment selectSelectedCmt(Connection conn, int boardNo) {
		SelectedComment selc = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectSelectedCmt");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				selc.setBoardNo(rset.getInt("board_no"));
				selc.setCmtNo(rset.getInt("cmtno"));
				selc.setSelectedDate(rset.getDate("selecteddate"));
				selc.setPoint(rset.getInt("awardpoint"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return selc;
	}

	public int insertSelectedComment(Connection conn, int boardNo, int point) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertSelectedComment");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, point);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertReportBoard(Connection conn, int boardNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertReportBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selctReportBoard(Connection conn, int boardNo, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("selctReportBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				result++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int selctReportCmt(Connection conn, int cmtNo, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("selctReportCmt");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cmtNo);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			while (rset.next())
				result++;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int insertReportCmt(Connection conn, int cmtNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertReportCmt");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cmtNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int cmtNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteComment");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cmtNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Board selectBoard(int boardNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;

		String query = prop.getProperty("selectBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardWriter(rset.getString("userId"));
				b.setBoardWriterGrade(rset.getString("userGrade"));
				b.setBoardCode(rset.getString("boardCode"));
				b.setBoardOption(rset.getString("boardOption"));
				b.setBoardTitle(rset.getString("boardTitle"));
				b.setBoardContent(rset.getString("boardContent"));
				b.setOriginalFileName(rset.getString("originalFileName"));
				b.setRenamedFileName(rset.getString("renamedFileName"));
				b.setReadCnt(rset.getInt("readCount"));
				b.setSelectedCnt(rset.getInt("selectedCount"));
				b.setBoardRegDate(rset.getDate("boardRegDate"));
				b.setReportedCnt(rset.getInt("reportedCount"));
				b.setCmtSelect(rset.getString("cmtselect"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public int updateBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardOption());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getOriginalFileName());
			pstmt.setString(5, b.getRenamedFileName());
			pstmt.setInt(6, b.getBoardNo());

			result = pstmt.executeUpdate();
			// System.out.println("Board@DAO="+b);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertBoardComment(Connection conn, BoardComment bc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoardComment");
		try {
			String bcRef = bc.getBoardNo() == 0 ? null : "" + bc.getBoardNo();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bc.getCmtWriter());
			pstmt.setString(2, bcRef);
			pstmt.setInt(3, bc.getCmtLevel());
			pstmt.setInt(4, bc.getCmtRefNo());
			pstmt.setString(5, bc.getCmtContent());
			pstmt.setString(6, bc.getCmtWriterGrade());

			result = pstmt.executeUpdate();

			// System.out.println("BoardComment@DAO="+bc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<BoardComment> selectCommentList(Connection conn, int boardNo) {
		List<BoardComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCommentList");
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				BoardComment b = new BoardComment();
				b.setCmtNo(rset.getInt("cmtNo"));
				b.setCmtWriter(rset.getString("userId"));
				b.setBoardNo(rset.getInt("board_no"));// null인 참조댓글필드는 0
				b.setCmtLevel(rset.getInt("cmtLevel"));
				b.setCmtRefNo(rset.getInt("cmtRefNo"));
				b.setCmtContent(rset.getString("cmtContent"));
				b.setCmtRegDate(rset.getDate("cmtRegDate"));
				b.setCmtWriterGrade(rset.getString("userGrade"));

				list.add(b);
			}
			// System.out.println("list@size="+list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int boardCmtDel(Connection conn, int cmtNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("boardCmtDel");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, cmtNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateSelectCmt(Connection conn, SelectedComment selc) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("updateSelectCmt");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, selc.getCmtNo());
			pstmt.setInt(2, selc.getBoardNo());

			result = pstmt.executeUpdate();
			System.out.println("selectedCmt@DAO=" + selc);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Board> selectBoardList(Connection conn, int cPage, int numPerPage, String boardCode) {
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardList");
		try {
			pstmt = conn.prepareStatement(query);
			// 시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setString(1, boardCode);
			pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(3, cPage * numPerPage);
			rset = pstmt.executeQuery();
			System.out.println("query");
			while (rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardWriter(rset.getString("userId"));
				b.setBoardOption(rset.getString("boardOption"));
				b.setBoardTitle(rset.getString("boardTitle"));
				b.setBoardWriterGrade("userGrade");
				b.setReadCnt(rset.getInt("readCount"));
				b.setCmtSelect(rset.getString("cmtselect"));
				b.setBoardRegDate(rset.getDate("boardRegDate"));
				list.add(b);
				System.out.println(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<DelBoard> selectDelBoardList(Connection conn, int cPage, int numPerPage, int i) {
		List<DelBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectDelBoardList");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, i);
			pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(3, cPage * numPerPage);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				DelBoard b = new DelBoard();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardWriter(rset.getString("userId"));
				b.setBoardOption(rset.getString("boardOption"));
				b.setBoardTitle(rset.getString("boardTitle"));
				b.setBoardWriterGrade("userGrade");
				b.setReadCnt(rset.getInt("readCount"));
				b.setCmtSelect(rset.getString("cmtselect"));
				b.setBoardRegDate(rset.getDate("boardRegDate"));
				b.setDelRepDate(rset.getDate("DEL_REP_date"));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int increaseReadCount(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("increaseReadCount");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public BoardComment selectCmtNo(int cmtNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BoardComment bc = null;
		/*
		 * CREATE TABLE Tb_comment ( cmtNo NUMBER NOT NULL, userId VARCHAR2(20) NOT
		 * NULL, board_no NUMBER NOT NULL, cmtLevel NUMBER default 1 NOT NULL, cmtRefNo
		 * NUMBER NULL, cmtContent CLOB NULL, cmtRegDate DATE default sysdate NULL,
		 * Recommended NUMBER default 0 NULL, reported NUMBER default 0 NULL, userGrade
		 * VARCHAR2(10) NULL, CONSTRAINT TB_COMMENT_PK PRIMARY KEY (cmtNo) );
		 */
		String query = prop.getProperty("selectCmt");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cmtNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				bc = new BoardComment();
				bc.setCmtNo(rset.getInt("cmtNo"));
				bc.setCmtWriter(rset.getString("userId"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setCmtLevel(rset.getInt("cmtLevel"));
				bc.setCmtRefNo(rset.getInt("cmtRefNo"));
				bc.setCmtContent(rset.getString("cmtContent"));
				bc.setCmtRegDate(rset.getDate("cmtRegDate"));
				bc.setRecommend(rset.getInt("Recommended"));
				bc.setReported(rset.getInt("reported"));
				bc.setCmtWriterGrade(rset.getString("userGrade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return bc;
	}

	public int selectBoardCount(Connection conn, String boardCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("selectBoardCount");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardCode);
			rset = pstmt.executeQuery();
			while (rset.next())
				result = Integer.parseInt(rset.getString("COUNT(*)"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectDelBoardCnt(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("selectDelBoardCount");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next())
				result = Integer.parseInt(rset.getString("COUNT(*)"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int myCmtTotal(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("myCmtTotal");
		int myCmtTotal = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				myCmtTotal = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return myCmtTotal;
	}

	public List<Board> myCurrentBoard(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		List<Board> myCurBoard = new ArrayList<>();
		ResultSet rset = null;
		String query = prop.getProperty("myCurrentBoard");
		System.out.println("DAO,myCurBoard===============================" + query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardCode(rset.getString("boardCode"));
				b.setBoardOption(rset.getString("boardOption"));
				b.setBoardTitle(rset.getString("boardTitle"));
				b.setBoardRegDate(rset.getDate("boardRegDate"));

				myCurBoard.add(b);

			}
			System.out.println("DAO,myCurBoard===============================" + myCurBoard.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return myCurBoard;

	}

	public int myBoardTotal(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("myBoardTotal");
		int myBoardTotal = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				myBoardTotal = rset.getInt(1);
				System.out.println("===========DAO@myboardtotal" + myBoardTotal);
				System.out.println("===========DAO@rset" + rset.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return myBoardTotal;
	}

}