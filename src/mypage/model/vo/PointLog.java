package mypage.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class PointLog implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private int prePoint;
	private int chPoint;
	private Date pointRegDate;
	private String pointLog;
	
	public PointLog() {
		super();
	}
	
	public PointLog(String userId, int prePoint, int chPoint, Date pointRegDate, String pointLog) {
		super();
		this.userId = userId;
		this.prePoint = prePoint;
		this.chPoint = chPoint;
		this.pointRegDate = pointRegDate;
		this.pointLog = pointLog;
	}
	
	@Override
	public String toString() {
		return "Scrap [userId=" + userId + ", prePoint=" + prePoint + ", chPoint=" + chPoint + ", pointRegDate="
				+ pointRegDate + ", pointLog=" + pointLog + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPrePoint() {
		return prePoint;
	}
	public void setPrePoint(int prePoint) {
		this.prePoint = prePoint;
	}
	public int getChPoint() {
		return chPoint;
	}
	public void setChPoint(int chPoint) {
		this.chPoint = chPoint;
	}
	public Date getPointRegDate() {
		return pointRegDate;
	}
	public void setPointRegDate(Date pointRegDate) {
		this.pointRegDate = pointRegDate;
	}
	public String getPointLog() {
		return pointLog;
	}
	public void setPointLog(String pointLog) {
		this.pointLog = pointLog;
	}
}
