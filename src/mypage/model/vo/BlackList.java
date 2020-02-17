package mypage.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BlackList implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String blackId;
	private Date regDate;
	private String memo;
	private String blackNickName;
	private String blackUserGrade;


	public BlackList() {
		super();
	}

	public BlackList(String userId, String blackId, Date regDate, String memo, String blackNickName,
			String blackUserGrade) {
		super();
		this.userId = userId;
		this.blackId = blackId;
		this.regDate = regDate;
		this.memo = memo;
		this.blackNickName = blackNickName;
		this.blackUserGrade = blackUserGrade;
	}

	public BlackList(String userId, String blackId, Date regDate, String memo) {
		super();
		this.userId = userId;
		this.blackId = blackId;
		this.regDate = regDate;
		this.memo = memo;
	}

	public BlackList(String userId, String blackId, Date regDate, String memo, String blackNickName) {
		super();
		this.userId = userId;
		this.blackId = blackId;
		this.regDate = regDate;
		this.memo = memo;
		this.blackNickName = blackNickName;
	}
	
	

	@Override
	public String toString() {
		return "BlackList [userId=" + userId + ", blackId=" + blackId + ", regDate=" + regDate + ", memo=" + memo
				+ ", blackNickName=" + blackNickName + ", blackUserGrade=" + blackUserGrade + "]";
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBlackId() {
		return blackId;
	}
	public void setBlackId(String blackId) {
		this.blackId = blackId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getBlackNickName() {
		return blackNickName;
	}

	public void setBlackNickName(String blackNickName) {
		this.blackNickName = blackNickName;
	}

	public String getBlackUserGrade() {
		return blackUserGrade;
	}

	public void setBlackUserGrade(String blackUserGrade) {
		this.blackUserGrade = blackUserGrade;
	}
	
	
}
