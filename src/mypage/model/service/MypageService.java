package mypage.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDAO;
import mypage.model.dao.MypageDAO;
import mypage.model.vo.BlackList;
import mypage.model.vo.Point;
import mypage.model.vo.Scrap;

public class MypageService {

	public int insertPointLog(String userId, int point, String msg) {
		// 포인트로그 테이블에 가서 insert해주기
		int result = 0;

		return result;
	}

	public List<BlackList> selectBlackList(String userId) {
		// 블랙리스트 가져오기
		Connection conn = getConnection();
		List<BlackList> blackList = new MypageDAO().selectBlackList(conn, userId);

		close(conn);
		return blackList;
	}

	public List<Scrap> selectScrap(String userId) {
		// 스크랩 가져오기
		Connection conn = getConnection();
		List<Scrap> scrapList = new MypageDAO().selectScrap(conn, userId);

		return scrapList;
	}

	public int deleteScrap(Scrap scrap) {
		Connection conn = getConnection();
		int result = new MypageDAO().deleteScrap(conn, scrap);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Point> selectPoint(String colum, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Point> list = new MypageDAO().selectPoint(conn, colum, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int addBlackList(BlackList bl) {
		Connection conn = getConnection();
		int result = new MypageDAO().addBlackList(conn, bl);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		return result;
	}

	public int deleteBlackUser(BlackList bl) {
		Connection conn = getConnection();
		int result = new MypageDAO().deleteBlackUser(conn, bl);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		return result;
	}

	public List<Point> searchUser(String colum, String searchNick) {
		Connection conn = getConnection();
		List<Point> list = new MypageDAO().searchUser(conn, searchNick);

		close(conn);

		return list;
	}

	public Point getPoint(String userId) {
		Connection conn = getConnection();
		Point point = new MypageDAO().getPoint(conn, userId);
		close(conn);

		return point;
	}

	public int scrapAdd(Scrap s) {
		Connection conn = getConnection();
		int result = new MypageDAO().scrapAdd(conn, s);
		close(conn);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		return result;
	}

	public int selectUserPoint(String userId) {
		Connection conn = getConnection();
		int point = new MypageDAO().selectUserPoint(conn, userId);
		close(conn);
		return point;
	}

	public int insertUser(Point p) {
		Connection conn = getConnection();
		int pointResult = new MypageDAO().insertUser(conn, p);

		if (pointResult > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);

		return pointResult;
	}

	public int selectMyBoardCount(String userId) {
		Connection conn = getConnection();
		int result = new BoardDAO().selectMyBoardCount(conn, userId);
		close(conn);
		return result;
	}

	public List<Scrap> selectScrapExist(String userId, int boardNo) {
		// 스크랩 유무 조회용
		Connection conn = getConnection();
		List<Scrap> scrapList = new MypageDAO().selectScrapExist(conn, userId, boardNo);

		return scrapList;
	}
}
