package user.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import user.model.vo.User;

import static common.JDBCTemplate.close;
public class UserDAO {

	private Properties prop = new Properties();
	
	public UserDAO() {
		String fileName = UserDAO.class.getResource("/sql/user/user-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int updateUserRole(Connection conn, String userId, String role) {
		int result = 0;
		PreparedStatement pstmt = null;
//		String query = prop.getProperty("updateUserRole");
		String query = "update tb_user set userRole = ? where userId = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, role);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int insertUser(Connection conn, User user) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getNickName());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getAddress());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public User selectOne(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		User u = null;
		System.out.println("query@="+query);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				u = new User();
				u.setUserId(rset.getString("userId"));
				u.setUserName(rset.getString("name"));
				u.setNickName(rset.getString("nickname"));
				u.setPassword(rset.getString("password"));
				u.setEmail(rset.getString("email"));
				u.setPhone(rset.getString("phone"));
				u.setEnrollDate(rset.getDate("enrollDate"));
				u.setUserRole(rset.getString("userRole"));
				u.setAddress(rset.getString("address"));
				u.setPreLogDate(rset.getDate("preLogDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return u;
	}

	public int deleteUser(Connection conn, String password) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteUser");
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, password);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateUser(User user, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateUser");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getNickName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public User selectHelpUserId(Connection conn, String name,String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectHelpUserId");
		User user = null;
//		System.out.println("query@DAO"+query);
//		System.out.println("name@DAO"+name);
//		System.out.println("name@DAO"+email);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				user=new User();
				user.setUserId(rset.getString("userId"));
				user.setUserName(rset.getString("name"));
				user.setNickName(rset.getString("nickname"));
				user.setPassword(rset.getString("password"));
				user.setEmail(rset.getString("email"));
				user.setPhone(rset.getString("phone"));
				user.setEnrollDate(rset.getDate("enrollDate"));
				user.setUserRole(rset.getString("userRole"));
				user.setAddress(rset.getString("address"));
				user.setPreLogDate(rset.getDate("preLogDate"));
			}
			System.out.println("userId@DAO"+user);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return user ;
	}

	public int updatePassword(String userId, String newPassword, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePassword");
		System.out.println("query@DAO"+query);
		System.out.println("newPassowrd@DAO"+newPassword);
		System.out.println("userId@DAO"+userId);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public User selectHelpUserPw(Connection conn, String userId, String email) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("selectHelpUserPw");
		ResultSet rset = null;
		User passUser = null;
//		System.out.println("userId@DAO"+userId);
//		System.out.println("email@DAO"+email);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				passUser = new User();
				passUser.setUserId(rset.getString("userId"));
				passUser.setUserName(rset.getString("name"));
				passUser.setNickName(rset.getString("nickname"));
				passUser.setPassword(rset.getString("password"));
				passUser.setEmail(rset.getString("email"));
				passUser.setPhone(rset.getString("phone"));
				passUser.setEnrollDate(rset.getDate("enrollDate"));
				passUser.setUserRole(rset.getString("userRole"));
				passUser.setAddress(rset.getString("address"));
				passUser.setPreLogDate(rset.getDate("preLogDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("passUser@DAO"+passUser);
		return passUser;
	}
	public User checkId(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("checkId");
		ResultSet rset = null;
		
		User u = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			System.out.println("query@DAO"+query);
			pstmt.setString(1, userId);
			
			rset= pstmt.executeQuery();
			while(rset.next()) {
				u = new User();
				u.setUserId(rset.getString("userId"));
				u.setUserName(rset.getString("name"));
				u.setNickName(rset.getString("nickname"));
				u.setPassword(rset.getString("password"));
				u.setEmail(rset.getString("email"));
				u.setPhone(rset.getString("phone"));
				u.setEnrollDate(rset.getDate("enrollDate"));
				u.setUserRole(rset.getString("userRole"));
				u.setAddress(rset.getString("address"));
				u.setPreLogDate(rset.getDate("preLogDate"));
				System.out.println("User@DAO"+u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return u;
	}

	public int updatePassword(Connection conn, User u) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword"); 
		System.out.println("passowrd@DAO"+u.getPassword());
		System.out.println("userId@DAO"+u.getUserId());
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, u.getPassword());
			pstmt.setString(2, u.getUserId());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public User selectOnebyNick(Connection conn, String nickName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		String query = prop.getProperty("selectOnebyNick");
		String query = "select * from tb_user where nickname = ?";
		User u = null;
		System.out.println("query@="+query);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nickName);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				u = new User();
				u.setUserId(rset.getString("userId"));
				u.setUserName(rset.getString("name"));
				u.setNickName(rset.getString("nickname"));
				u.setPassword(rset.getString("password"));
				u.setEmail(rset.getString("email"));
				u.setPhone(rset.getString("phone"));
				u.setEnrollDate(rset.getDate("enrollDate"));
				u.setUserRole(rset.getString("userRole"));
				u.setAddress(rset.getString("address"));
				u.setPreLogDate(rset.getDate("preLogDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return u;
	}

	
}
