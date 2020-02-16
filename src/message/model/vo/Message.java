package message.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private int messageNo;
	private String getUser;
	private String sendUser;
	private String msgTitle;
	private String msgContent;
	private Date sendDate;
	private Date readDate;
	
	public Message() {
		super();
	}

	public Message(int messageNo, String getUser, String sendUser, String msgTitle, String msgContent, Date sendDate,
			Date readDate) {
		super();
		this.messageNo = messageNo;
		this.getUser = getUser;
		this.sendUser = sendUser;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.sendDate = sendDate;
		this.readDate = readDate;
	}

	@Override
	public String toString() {
		return "Message [messageNo=" + messageNo + ", getUser=" + getUser + ", sendUser=" + sendUser + ", msgTitle="
				+ msgTitle + ", msgContent=" + msgContent + ", sendDate=" + sendDate + ", readDate=" + readDate + "]";
	}
	
	public String getMsgTitle() {
		return msgTitle;
	}
	
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public int getMessageNo() {
		return messageNo;
	}

	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}

	public String getGetUser() {
		return getUser;
	}

	public void setGetUser(String getUser) {
		this.getUser = getUser;
	}

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readOrNot) {
		this.readDate = readOrNot;
	}
	
	
}
