package mypage.model.vo;

import java.io.Serializable;

public class Point implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String nickName;
	private String userGrade;
	private int point;
	private int selectCnt;
	
	
	public Point() {
		super();
	}
	
	public Point(String userId, String nickName, String userGrade, int point, int selectCnt) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.userGrade = userGrade;
		this.point = point;
		this.selectCnt = selectCnt;
	}
    
    	public Point(String userId, String nickName,  int point, int selectCnt) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.point = point;
		this.selectCnt = selectCnt;
	}
    
	@Override
	public String toString() {
		return "Point [userId=" + userId + ", nickName=" + nickName + ", userGrade=" + userGrade + ", point=" + point
				+ ", selectCnt=" + selectCnt + "]";
	}
	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getSelectCnt() {
		return selectCnt;
	}
	public void setSelectCnt(int selectCnt) {
		this.selectCnt = selectCnt;
	};
	
	
}
