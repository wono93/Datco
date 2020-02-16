package message.model.service;

import message.model.dao.MessageDAO;
import message.model.vo.Message;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
public class MessageService {

	
	public int insertMessage(Message m) {
		Connection conn = getConnection();
		int result = new MessageDAO().insertMessage(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Message> selectMessageSender(String userId) {
		Connection conn = getConnection();
		List<Message> msgList = new MessageDAO().selectMessageSender(conn,userId);
		close(conn);
		return msgList;
	}

	public List<Message> selectMessageGeter(String userId) {
		Connection conn = getConnection();
		List<Message> msgList = new MessageDAO().selectMessageGeter(conn,userId);
		close(conn);
		return msgList;
	}


	public Message selectMessage(int messageNo) {
		Connection conn = getConnection();
		Message m = new MessageDAO().selectMessage(conn, messageNo);
		close(conn);
		return m;

	}

	public int updateReadDate(Message m) {
		Connection conn = getConnection();
		int result = new MessageDAO().updateReadDate(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteMessage(int messageNo) {
		Connection conn = getConnection();
		int result = new MessageDAO().deleteMessage(conn, messageNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

}
