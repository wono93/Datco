package message.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import message.model.vo.Message;

import static common.JDBCTemplate.close;

public class MessageDAO {
	
	private Properties prop = new Properties();

	public MessageDAO() {
		
		String fileName = MessageDAO.class.getResource("/sql/message/message-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMessage(Connection conn, Message m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMessage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getSendUser());
			pstmt.setString(2, m.getGetUser());
			pstmt.setString(3, m.getMsgContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Message> selectMessageSender(Connection conn, String userId) {
		List<Message> msgList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMessageSender");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			msgList = new ArrayList<>();
			while(rset.next()) {
				int messageNo = rset.getInt("message_No");
				String getUser = rset.getString("getuser");
				String sendUser = rset.getString("senduser");
				String msgTitle = rset.getString("title");
				String msgContent = rset.getString("msgcontent");
				Date sendDate = rset.getDate("senddate");
				Date readDate = rset.getDate("readdate");
				Message m = new Message(messageNo,getUser, sendUser, msgTitle, msgContent, sendDate, readDate);
				msgList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return msgList;
	}

	public List<Message> selectMessageGeter(Connection conn, String userId) {
		List<Message> msgList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMessageGeter");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			msgList = new ArrayList<>();
			while(rset.next()) {
				int messageNo = rset.getInt("message_no");
				String getUser = rset.getString("getuser");
				String sendUser = rset.getString("senduser");
				String msgTitle = rset.getString("title");
				String msgContent = rset.getString("msgcontent");
				Date sendDate = rset.getDate("senddate");
				Date readDate= rset.getDate("readDate");
				Message m = new Message(messageNo, getUser, sendUser, msgTitle, msgContent, sendDate, readDate);
				msgList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return msgList;
	}

	public Message selectMessage(Connection conn, int messageNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMessage");
		Message m = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, messageNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String getUser = rset.getString("getuser");
				String sendUser = rset.getString("senduser");
				String msgTitle = rset.getString("title");
				String msgContent = rset.getString("msgcontent");
				Date sendDate = rset.getDate("senddate");
				Date readDate= rset.getDate("readDate");
				m = new Message(messageNo, getUser, sendUser, msgTitle, msgContent, sendDate, readDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}

	public int updateReadDate(Connection conn, Message m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateReadDate");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, m.getMessageNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;	
	}

	public int deleteMessage(Connection conn, int messageNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMessage");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, messageNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;	
	}

}
