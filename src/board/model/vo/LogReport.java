package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class LogReport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int No;
	private String userId;
	private Date reportDate;
	
	public LogReport() {
		super();
	}
	
	public LogReport(int no, String userId, Date reportDate) {
		super();
		No = no;
		this.userId = userId;
		this.reportDate = reportDate;
	}


	public Date getReportDate() {
		return reportDate;
	}


	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}


	public int getNo() {
		return No;
	}
	public void setNo(int No) {
		this.No = No;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
