package user.model.service;

import java.sql.Connection;

import user.model.dao.UserDAO;
import user.model.vo.User;
import static common.JDBCTemplate.*;

public class UserService {

	public User selectOne(String userId) {
		Connection conn = getConnection();
		User u = new UserDAO().selectOne(conn,userId);
		close(conn);
		return u;
	}

	public User selectUser(User user) {
		//로그인시 userId,pw 가지고 통과
		//회원정보수정시 userId, pw가지고 통과
		return user;
	}

	public void updateLoginDate(String userId) {
		//유저테이블 로그인날짜 update하기
	}

	public int insertUser(User user) {
		Connection conn = getConnection();
		int result = new UserDAO().insertUser(conn,user);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public User selectHelpUserId(String name, String email) {
		//사용자 정보로 검색
		Connection conn = getConnection();
		User user= new UserDAO().selectHelpUserId(conn,name,email);
		close(conn);
		return user ;
	}

	public int updatePassword(String userId, String newPassword) {
		//비밀번호 업데이트하기
		Connection conn = getConnection();
		int result = new UserDAO().updatePassword(userId,newPassword,conn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateUser(User user) {
		//회원정보 양식에 올라온거 다 바꾸기(기존값도 기존값으로 update)
		Connection conn = getConnection();
		int result = new UserDAO().updateUser(user,conn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteUser(String password) {
		//delete 시켜주기
		Connection conn = getConnection();
		int result = new UserDAO().deleteUser(conn,password);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public User selectHelpUserPw(String userId, String email) {
		//사용자정보로 검색
		Connection conn = getConnection();
		User passUser = new UserDAO().selectHelpUserPw(conn,userId,email);
		close(conn);
		return passUser;
	}

	public int updateUserRole(String userId, String role) {
		Connection conn = getConnection();
		int result = new UserDAO().updateUserRole(conn,userId,role);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public User checkId(String userId) {
		Connection conn = getConnection();
		User u = new UserDAO().checkId(conn,userId);
		close(conn);
		System.out.println("User@service"+u);
		return u;
	}

	public int updatePassword(User u) {
		Connection conn =getConnection();
		int result = new UserDAO().updatePassword(conn, u);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	public User selectOnebyNick(String nickName) {
		Connection conn = getConnection();
		User u = new UserDAO().selectOnebyNick(conn,nickName);
		close(conn);
		return u;
	}


}
