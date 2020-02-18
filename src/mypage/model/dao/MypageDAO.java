package mypage.model.dao;

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

import message.model.dao.MessageDAO;
import mypage.model.vo.BlackList;
import mypage.model.vo.Point;
import mypage.model.vo.PointLog;
import mypage.model.vo.Scrap;
import user.model.vo.User;
import static common.JDBCTemplate.close;
public class MypageDAO {
	
	private Properties prop = new Properties();

	public MypageDAO() {
		
		String fileName = MypageDAO.class.getResource("/sql/mypage/mypage-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Scrap> selectScrap(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectScrap");
		System.out.println(query);
		List<Scrap> list = new ArrayList<>();
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Scrap s = new Scrap();
				s.setUserId(rset.getString("userId"));
				s.setMemo(rset.getString("memo"));
				s.setBoardNo(rset.getInt("boardNo"));
				s.setScrapRegDate(rset.getDate("scrapRegDate"));
				s.setBoardWriter(rset.getString("boardWriter"));
				s.setBoardTitle(rset.getString("boardTitle"));
//				System.out.println("each scrap DAO==========="+s);
				list.add(s);
			}
			System.out.println("list DAO======="+list.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;	
	}

	public int deleteScrap(Connection conn, Scrap scrap) {
		// 유저아이디, 게시글번호로 where문 작성
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteScrap");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, scrap.getUserId());
			pstmt.setInt(2, scrap.getBoardNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

	public List<Point> selectPoint(Connection conn, String colum, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPoint").replace("\u2606", colum);
		List<Point> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String userId = rset.getString("USERID");
				String nickName = rset.getString("NICKNAME");
				int point = rset.getInt("POINT");
				int selectCnt = rset.getInt("SELECTEDCNT");
				list.add(new Point(userId, nickName, null, point, selectCnt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public List<BlackList> selectBlackList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		List<BlackList> blackList = new ArrayList<>();
		ResultSet rset =  null;
		String query = prop.getProperty("selectBlackList");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BlackList bl = new BlackList();
				bl.setBlackId(rset.getString("blackId"));
				bl.setMemo(rset.getString("memo"));
				bl.setUserId(userId);
				bl.setRegDate(rset.getDate("regDate"));
				bl.setBlackNickName(rset.getString("nickName"));
				bl.setBlackUserGrade(Integer.toString(rset.getInt("point")));
				blackList.add(bl);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		System.out.println(blackList);
		
		return blackList;
	}

	public int addBlackList(Connection conn, BlackList bl) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("addBlackList");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bl.getUserId());
			pstmt.setString(2, bl.getBlackId());
			pstmt.setString(3, bl.getMemo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteBlackUser(Connection conn, BlackList bl) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBlackUser");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bl.getUserId());
			pstmt.setString(2, bl.getBlackId());
			
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Point> searchUser(Connection conn, String searchNick) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Point> list = new ArrayList<Point>();
		String query = prop.getProperty("searchUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchNick+"%");
			
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
				String userId = rset.getString("USERID");
				String nickName = rset.getString("NICKNAME");
				int point = rset.getInt("POINT");
				int selectCnt = rset.getInt("SELECTEDCNT");
				list.add(new Point(userId, nickName, null, point, selectCnt));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	

	public Point getPoint(Connection conn, String userId) {
		PreparedStatement pstmt = null;
	
		List<Point> blackList = new ArrayList<>();
		ResultSet rset =  null;
		String query = prop.getProperty("getPoint");
		Point point = new Point();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				point.setPoint(Integer.parseInt(rset.getString("point")));
				point.setSelectCnt(Integer.parseInt(rset.getString("selectedCnt")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
//		System.out.println("DAO===blackList==pointLog==="+blackList);
		
		return point;
	}

	public int scrapAdd(Connection conn, Scrap s) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("scrapAdd");
//		String query = "INSERT INTO TB_SCRAP VALUES(?, ?, ?,DEFAULT)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, s.getUserId());
			pstmt.setInt(2, s.getBoardNo());
			pstmt.setString(3, s.getMemo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);			
		}
		
		
		
		return result;
	}
	
	public int selectUserPoint(Connection conn, String userId) {
			PreparedStatement pstmt = null;
			ResultSet rset =  null;
			String query = prop.getProperty("selectUserPoint");
			int point = 0;
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userId);
				rset = pstmt.executeQuery();
				while(rset.next())
					point = rset.getInt("POINT");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return point;
		}
	
	public int insertUser(Connection conn, Point p) {
		PreparedStatement pstmt = null;
		int pointResult = 0;
//		String query = "INSERT INTO TB_POINT VALUES(?, ?, ?)";
		String query = prop.getProperty("insertUser");
//		System.out.println("p@DAO"+p);
//		System.out.println("query@DAO"+query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getUserId());
			pstmt.setInt(2, p.getPoint());
			pstmt.setInt(3, p.getSelectCnt());
			pointResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return pointResult;
	}

	public List<Scrap> selectScrapExist(Connection conn, String userId, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectScrapExist");
		System.out.println(query);
		List<Scrap> list = new ArrayList<>();
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, boardNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Scrap s = new Scrap();
				s.setUserId(rset.getString("userId"));
				s.setMemo(rset.getString("memo"));
				s.setBoardNo(rset.getInt("boardNo"));
				s.setScrapRegDate(rset.getDate("scrapRegDate"));
				s.setBoardWriter(rset.getString("boardWriter"));
				s.setBoardTitle(rset.getString("boardTitle"));
//				System.out.println("each scrap DAO==========="+s);
				list.add(s);
			}
			System.out.println("list DAO==selectScrapExist====="+list.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}


