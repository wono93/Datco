package mypage.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Scrap implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String userId;
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String memo;
	private Date ScrapRegDate;
	
	public Scrap() {
		super();
	}
	

	@Override
	public String toString() {
		return "Scrap [userId=" + userId + ", boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter="
				+ boardWriter + ", memo=" + memo + ", ScrapRegDate=" + ScrapRegDate + "]";
	}



	public Scrap(String userId, int boardNo, String boardTitle, String boardWriter, String memo, Date scrapRegDate) {
		super();
		this.userId = userId;
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.memo = memo;
		ScrapRegDate = scrapRegDate;
	}

	public Scrap(String userId, int boardNo, String boardTitle, String memo, Date scrapRegDate) {
		super();
		this.userId = userId;
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.memo = memo;
		ScrapRegDate = scrapRegDate;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
    
	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
    
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getScrapRegDate() {
		return ScrapRegDate;
	}

	public void setScrapRegDate(Date scrapRegDate) {
		ScrapRegDate = scrapRegDate;
	}
	
	
	
	
	
}
